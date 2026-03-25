package com.konkuk.medicarecall.ui.feature.alarm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konkuk.medicarecall.data.repository.NotificationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class AlarmViewModel(
    private val repository: NotificationRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(AlarmUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadAlarms()
    }

    private fun loadAlarms() {
        viewModelScope.launch {
            repository.getNotifications()
                .onSuccess { alarmPages ->
                    _uiState.update { it.copy(alarmPages = alarmPages) }
                }
                .onFailure {
                    _uiState.update { it.copy(errorMessage = "알림을 불러오지 못했습니다.") }
                    it.printStackTrace()
                }
        }
    }
}
