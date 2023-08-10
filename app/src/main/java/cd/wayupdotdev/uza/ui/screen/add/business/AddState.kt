package cd.wayupdotdev.uza.ui.screen.add.business

sealed class AddState {
    object Uninitialized : AddState()
    object Loading : AddState()
    data class Error(val errorMessage: String) : AddState()
    //data class Success(val uri: Uri): PostState()
    object Success : AddState()
}