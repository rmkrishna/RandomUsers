package com.abitztech.randomusers.ui.screens.users

import com.abitztech.randomusers.data.model.User
import com.abitztech.randomusers.ui.base.BaseEffect
import com.abitztech.randomusers.ui.base.BaseEvent
import com.abitztech.randomusers.ui.base.BaseState

sealed class UsersEffect : BaseEffect() {
    object None : UsersEffect()
}

sealed class UsersEvent : BaseEvent {
    object GetUsers : UsersEvent()
}

data class UsersState(
    val isLoading: Boolean, val users: List<User> = arrayListOf()
) : BaseState()