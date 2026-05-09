package com.konkuk.medicarecall.ui.model

import com.konkuk.medicarecall.domain.model.type.AlarmType
import com.konkuk.medicarecall.ui.type.AlarmActionType

data class AlarmModel(
    val id: Int,
    val type: AlarmType,
    val message: String,
    val time: String,
    val actionType: AlarmActionType = AlarmActionType.NONE
)
