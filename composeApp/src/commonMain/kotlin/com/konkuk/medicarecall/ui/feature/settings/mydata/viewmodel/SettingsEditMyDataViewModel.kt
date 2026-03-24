package com.konkuk.medicarecall.ui.feature.settings.mydata.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konkuk.medicarecall.data.repository.UserRepository
import com.konkuk.medicarecall.domain.model.PushNotification
import com.konkuk.medicarecall.domain.model.UserInfo
import com.konkuk.medicarecall.domain.model.type.GenderType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel
import kotlin.coroutines.cancellation.CancellationException

@KoinViewModel
class SettingsEditMyDataViewModel(
    private val userRepository: UserRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(SettingsEditMyDataUiState())
    val uiState: StateFlow<SettingsEditMyDataUiState> = _uiState.asStateFlow()

    // API 호출 함수
    fun loadMyInfo() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            try {
                userRepository.getMyInfo()
                    .onSuccess { myInfo ->
                        _uiState.update { it.copy(myDataInfo = myInfo) }
                    }
                    .onFailure { exception ->
                        _uiState.update {
                            it.copy(errorMessage = "내 정보를 불러오지 못했습니다: ${exception.message}")
                        }
                    }
            } catch (ce: CancellationException) {
                throw ce
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(errorMessage = "내 정보를 불러오지 못했습니다: ${e.message}")
                }
            } finally {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }

    fun updateUserData(
        userInfo: UserInfo,
        onComplete: (() -> Unit)? = null,
    ) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null, isUpdateSuccess = false) }
            try {
                userRepository.updateMyInfo(userInfo)
                    .onSuccess {
                        _uiState.update {
                            it.copy(
                                isUpdateSuccess = true,
                                myDataInfo = userInfo, // 저장 성공 시 최신 데이터로 동기화
                            )
                        }
                        onComplete?.invoke()
                    }
                    .onFailure { e ->
                        if (e is CancellationException) {
                            throw e
                        } else {
                            _uiState.update { it.copy(errorMessage = "정보 업데이트에 실패했습니다.") }
                        }
                    }
            } finally {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }

    fun initializeNotificationSettings(myDataInfo: UserInfo) {
        val masterOn = myDataInfo.pushNotification.isAllEnabled
        _uiState.update {
            it.copy(
                masterChecked = masterOn,
                completeChecked = myDataInfo.pushNotification.isCarecallCompletedEnabled || masterOn,
                abnormalChecked = myDataInfo.pushNotification.isHealthAlertEnabled || masterOn,
                missedChecked = myDataInfo.pushNotification.isCarecallMissedEnabled || masterOn,
            )
        }
    }

    // 토글 타입별로 새 상태를 계산하고 서버에 반영하는 함수
    fun updateNotificationSettingByType(
        type: String,
        checked: Boolean,
    ) {
        val currentInfo = uiState.value.myDataInfo ?: return
        val currentState = uiState.value

        val newMasterChecked: Boolean
        val newCompleteChecked: Boolean
        val newAbnormalChecked: Boolean
        val newMissedChecked: Boolean

        when (type) {
            "master" -> {
                newMasterChecked = checked
                newCompleteChecked = checked
                newAbnormalChecked = checked
                newMissedChecked = checked
            }

            "complete" -> {
                newCompleteChecked = checked
                newAbnormalChecked = currentState.abnormalChecked
                newMissedChecked = currentState.missedChecked
                newMasterChecked = checked && newAbnormalChecked && newMissedChecked
            }

            "abnormal" -> {
                newCompleteChecked = currentState.completeChecked
                newAbnormalChecked = checked
                newMissedChecked = currentState.missedChecked
                newMasterChecked = newCompleteChecked && checked && newMissedChecked
            }

            "missed" -> {
                newCompleteChecked = currentState.completeChecked
                newAbnormalChecked = currentState.abnormalChecked
                newMissedChecked = checked
                newMasterChecked = newCompleteChecked && newAbnormalChecked && checked
            }

            else -> return
        }

        _uiState.update {
            it.copy(
                masterChecked = newMasterChecked,
                completeChecked = newCompleteChecked,
                abnormalChecked = newAbnormalChecked,
                missedChecked = newMissedChecked,
            )
        }

        val updatedUserInfo = currentInfo.copy(
            pushNotification = PushNotification(
                all = if (newMasterChecked) "ON" else "OFF",
                carecallCompleted = if (newCompleteChecked) "ON" else "OFF",
                healthAlert = if (newAbnormalChecked) "ON" else "OFF",
                carecallMissed = if (newMissedChecked) "ON" else "OFF",
            ),
        )

        updateUserData(updatedUserInfo)
    }

    fun initializeFormData(myDataInfo: UserInfo) {
        _uiState.update {
            it.copy(
                isMale = myDataInfo.gender == GenderType.MALE,
                name = myDataInfo.name,
                birth = myDataInfo.birthDate.replace("-", ""),
            )
        }
    }

    fun updateIsMale(value: Boolean) {
        _uiState.update { it.copy(isMale = value) }
    }

    fun updateName(value: String) {
        _uiState.update { it.copy(name = value) }
    }

    fun updateBirth(value: String) {
        _uiState.update { it.copy(birth = value) }
    }
}
