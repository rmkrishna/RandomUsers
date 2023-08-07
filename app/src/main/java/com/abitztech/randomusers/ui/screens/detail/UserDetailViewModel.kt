package com.abitztech.randomusers.ui.screens.detail

import com.abitztech.randomusers.domain.usecases.GetUserUseCase
import com.abitztech.randomusers.ui.base.MviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject internal constructor(
    private val getUserUseCase: GetUserUseCase
) : MviViewModel<UserDetailEvent, UserDetailState, UserDetailEffect>() {
    override fun createInitialState(): UserDetailState = UserDetailState(false)

    override fun handleEvent(event: UserDetailEvent) {
        when (event) {
            is UserDetailEvent.GetUser -> {
                getUser(event.id)
            }

            else -> {}
        }
    }

    private fun getUser(id: String) {
        safeLaunch {
//            setState { copy(isLoading = true) }
            val user = getUserUseCase(id)
            setState { copy(isLoading = false, user = user) }
        }
    }
}