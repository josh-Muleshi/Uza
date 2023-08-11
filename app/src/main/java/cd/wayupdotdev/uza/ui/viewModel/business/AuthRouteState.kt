package cd.wayupdotdev.uza.ui.viewModel.business

sealed class AuthRouteState {
    object Uninitialized : AuthRouteState()
    object Loading : AuthRouteState()
    data class Error(val errorMessage: String) : AuthRouteState()
    data class Success(val isAuth: Boolean) : AuthRouteState()
}

