package cd.wayupdotdev.uza.ui.viewModel

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    private val _state = MutableStateFlow<MainState>(MainState.Uninitialized)
    val isShow: StateFlow<MainState>
    get() = _state

    private val mutableStateFlow = MutableStateFlow(true)
    val isLoading = mutableStateFlow.asStateFlow()

    init {
        viewModelScope.launch {
            delay(2000)
            mutableStateFlow.value = false
        }

        isOnboardingShow()
    }
    private fun isOnboardingShow() = viewModelScope.launch {
            _state.emit(MainState.Loading)
            if (sharedPreferences.getBoolean("is-show", false)) {
                _state.emit(MainState.Success(true))
            } else {
                _state.emit(MainState.Success(false))
            }
        }
}
