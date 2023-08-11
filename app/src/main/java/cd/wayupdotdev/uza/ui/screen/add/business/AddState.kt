package cd.wayupdotdev.uza.ui.screen.add.business

sealed class AddState {
    object Uninitialized : AddState()
    object Loading : AddState()
    data class Error(val errorMessage: String) : AddState()
    object Success : AddState()
}