package com.abitztech.randomusers.ui.screens.detail

import com.abitztech.randomusers.data.model.User
import com.abitztech.randomusers.ui.base.BaseEffect
import com.abitztech.randomusers.ui.base.BaseEvent
import com.abitztech.randomusers.ui.base.BaseState

sealed class UserDetailEffect : BaseEffect() {
    object None : UserDetailEffect()
}

sealed class UserDetailEvent : BaseEvent {
    data class GetUser(val id: String) : UserDetailEvent()
}

data class UserDetailState(
    val isLoading: Boolean, val user: User? = null
) : BaseState()