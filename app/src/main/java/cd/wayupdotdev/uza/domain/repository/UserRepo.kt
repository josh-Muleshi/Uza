package cd.wayupdotdev.uza.domain.repository

import cd.wayupdotdev.uza.data.model.User
import cd.wayupdotdev.uza.domain.dto.CustomFirebaseUser
import kotlinx.coroutines.flow.Flow

interface UserRepo {
    suspend fun signInWithGoogle(idToken: String): CustomFirebaseUser
    fun getCurrentUser(): Flow<User?>
}