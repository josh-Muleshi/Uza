package cd.wayupdotdev.uza.ui.screen.add.business

import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cd.wayupdotdev.uza.data.repository.PostRepoImpl
import cd.wayupdotdev.uza.domain.repository.CustomCameraRepo
import cd.wayupdotdev.uza.ui.viewModel.business.AuthRouteState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddViewModel @Inject constructor(
    private val repo: CustomCameraRepo,
    private val postRepo: PostRepoImpl,
    private val sharedPreferences: SharedPreferences
): ViewModel() {

    private val _data = MutableStateFlow<AddState>(AddState.Uninitialized)
    val data: StateFlow<AddState>
        get() = _data

    private val _addPostState = MutableStateFlow<AddState>(AddState.Uninitialized)
    val addPostState: StateFlow<AddState>
        get() = _addPostState

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

    fun addPost(title: String, description: String, price: Double, quantity: Int, devise: String, uri: Uri) = viewModelScope.launch {
        _addPostState.emit(AddState.Loading)
        try {
            postRepo.add(title, description, price, quantity, devise, uri)
            _addPostState.emit(AddState.Success)
        } catch (t: Throwable) {
            _addPostState.emit(AddState.Error(t.message.toString()))
        }
    }

//    init {
//        getImageUri()
//    }
//    private fun getImageUri() = viewModelScope.launch {
//        _data.emit(PostState.Loading)
//        try {
//            _data.emit(PostState.Success(repo.getImageUri()))
//            Log.e("url", repo.getImageUri().toString())
//        } catch (t: Throwable) {
//            _data.emit(PostState.Error(t.message.toString()))
//        }
//
//    }

    fun showCameraPreview(
        previewView: PreviewView,
        lifecycleOwner: LifecycleOwner
    ) {
        viewModelScope.launch {
            repo.showCameraPreview(
                previewView,
                lifecycleOwner
            )
        }
    }

    fun captureAndSave(context: Context) {
        viewModelScope.launch {
            repo.captureAndSaveImage(context) {
               launch {
                   _data.emit(AddState.Success)
               }
            }
        }
    }
}