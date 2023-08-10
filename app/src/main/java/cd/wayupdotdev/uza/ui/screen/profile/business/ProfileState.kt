package cd.wayupdotdev.uza.ui.screen.profile.business

import cd.wayupdotdev.uza.data.model.User

sealed class ProfileState {
    object Uninitialized : ProfileState()
    object Loading : ProfileState()
    data class Error(val errorMessage: String) : ProfileState()
    data class Success(val user: User): ProfileState()
}