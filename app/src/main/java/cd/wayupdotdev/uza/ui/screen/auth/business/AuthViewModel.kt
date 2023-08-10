package cd.wayupdotdev.uza.ui.screen.auth.business

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cd.wayupdotdev.uza.data.repository.UserRepoImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val userRepo: UserRepoImpl,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    private val _state = MutableStateFlow<AuthState>(AuthState.Uninitialized)
    val state: StateFlow<AuthState>
        get() = _state

    fun register(idToken: String) = viewModelScope.launch {
        _state.emit(AuthState.Loading)
        try {
            userRepo.signInWithGoogle(idToken)
            val editor = sharedPreferences.edit()
            editor.apply {
                putBoolean("is-auth", true)
            }.apply()
            _state.emit(AuthState.Success)
        } catch (e: Exception) {
            _state.emit(AuthState.Error(e.localizedMessage ?: e.message.toString()))
        }
    }
}