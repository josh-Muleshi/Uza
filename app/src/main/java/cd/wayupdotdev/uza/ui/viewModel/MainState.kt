package cd.wayupdotdev.uza.ui.viewModel

sealed class MainState {
    object Uninitialized : MainState()
    object Loading : MainState()
    data class Error(val errorMessage: String) : MainState()
    data class Success(val isShow: Boolean) : MainState()
}