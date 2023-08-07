package com.abitztech.randomusers.ui.screens.users

import com.abitztech.randomusers.data.model.Result
import com.abitztech.randomusers.data.model.User
import com.abitztech.randomusers.domain.usecases.GetUsersUseCase
import com.abitztech.randomusers.ui.base.MviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.collectLatest

@HiltViewModel
class UsersViewModel @Inject internal constructor(
    private val getUsersUseCase: GetUsersUseCase
) : MviViewModel<UsersEvent, UsersState, UsersEffect>() {
    override fun createInitialState(): UsersState = UsersState(false)

    override fun handleEvent(event: UsersEvent) {
        when (event) {
            UsersEvent.GetUsers -> {
                getUsers()
            }
        }
    }

    private fun getUsers() {
        safeLaunch {
            getUsersUseCase().collectLatest {
                when (it) {
                    is Result.Loading -> {
                        setState { copy(isLoading = true) }
                    }

                    is Result.Success -> {
                        setState { copy(isLoading = false, users = it.data as ArrayList<User>) }
                    }

                    else -> {
                        setState { copy(isLoading = false) }
                        // TODO Handle the error case
                    }
                }
            }
        }
    }
}