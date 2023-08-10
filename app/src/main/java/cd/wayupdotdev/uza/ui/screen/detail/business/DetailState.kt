package cd.wayupdotdev.uza.ui.screen.detail.business

import cd.wayupdotdev.uza.data.model.Post

sealed class DetailState {
    object Uninitialized: DetailState()
    object Loading : DetailState()
    data class Error(val message: String) : DetailState()
    data class Success(val post: Post) : DetailState()
}