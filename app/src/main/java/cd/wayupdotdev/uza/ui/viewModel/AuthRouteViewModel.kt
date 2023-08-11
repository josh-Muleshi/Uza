package cd.wayupdotdev.uza.ui.viewModel

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cd.wayupdotdev.uza.ui.viewModel.business.AuthRouteState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthRouteViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    private val _state = MutableStateFlow<AuthRouteState>(AuthRouteState.Uninitialized)
    val isAuth: StateFlow<AuthRouteState>
        get() = _state

    init {
        viewModelScope.launch {
            _state.emit(AuthRouteState.Loading)
            if (sharedPreferences.getBoolean("is-auth", false)) {
                _state.emit(AuthRouteState.Success(true))
            } else {
                _state.emit(AuthRouteState.Success(false))
            }
        }
    }

}