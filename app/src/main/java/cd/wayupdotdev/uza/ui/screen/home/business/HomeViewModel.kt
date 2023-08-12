package cd.wayupdotdev.uza.ui.screen.home.business

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cd.wayupdotdev.uza.data.model.Post
import cd.wayupdotdev.uza.data.model.User
import cd.wayupdotdev.uza.data.repository.PostRepoImpl
import cd.wayupdotdev.uza.data.repository.UserRepoImpl
import cd.wayupdotdev.uza.ui.screen.profile.business.ProfileState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val postRepository: PostRepoImpl,
    private val userRepo: UserRepoImpl
) : ViewModel() {
    private val _data = MutableStateFlow<HomeState>(HomeState.Uninitialized)
    val data: StateFlow<HomeState>
        get() = _data

    private val _state = MutableStateFlow<ProfileState>(ProfileState.Uninitialized)
    val state: StateFlow<ProfileState>
        get() = _state


    init {
        viewModelScope.launch {
            _state.emit(ProfileState.Loading)
            try {
                userRepo.getCurrentUser().collect { user ->
                    _state.emit(ProfileState.Success(user = user as User))
                }
            } catch (e: Exception) {
                _state.emit(ProfileState.Error(e.localizedMessage ?: e.message.toString()))
            }
        }
        getAllPosts()
    }

    private fun getAllPosts() = viewModelScope.launch {
        _data.emit(HomeState.Loading)
        try {
            postRepository.getAll().collect { posts ->
                _data.emit(HomeState.Success(posts = posts as ArrayList<Post>))
            }
        } catch (t: Throwable) {
            _data.emit(HomeState.Error(t.message.toString()))
        }
    }

    fun addToFavorite(post: Post) {
        viewModelScope.launch(Dispatchers.IO) {
        }
    }
}