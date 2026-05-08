package com.konkuk.medicarecall.ui.feature.login.elder.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class LoginElderPromotionViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(LoginElderPromotionUiState())
    val uiState = _uiState.asStateFlow()

    fun onPromotionCodeChange(value: String) {
        _uiState.update { it.copy(inputValue = value) }
    }

    fun onConfirm() {
        // TODO: 프로모션 검증 로직 추가
        _uiState.update { it.copy(showDialog = true) }
    }

    fun onDismissRequest() {
        _uiState.update { it.copy(showDialog = false) }
    }
}
