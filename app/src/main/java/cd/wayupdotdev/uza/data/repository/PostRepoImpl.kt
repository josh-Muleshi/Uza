package cd.wayupdotdev.uza.data.repository

import android.net.Uri
import cd.wayupdotdev.uza.data.model.Post
import cd.wayupdotdev.uza.data.utils.FireBaseConstants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import java.util.Date
import java.util.Locale
import javax.inject.Inject


class PostRepoImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
    private val storage: FirebaseStorage
) {
    private val currentUser by lazy {
        firebaseAuth.currentUser
    }

   fun getAll() = callbackFlow {
        firestore.collection(FireBaseConstants.posts)
            .orderBy(Post::createdAt.name, Query.Direction.DESCENDING)
            .addSnapshotListener { value, error ->
                if (error != null && value == null) {
                    close(error)
                }

                value?.toObjects(Post::class.java).let { posts ->
                    if (!isClosedForSend) {
                        trySend(posts)
                    }
                }
            }
        awaitClose()
    }.catch {
        throw it
    }.flowOn(Dispatchers.IO)
    fun getPostByUid(uidPost: String) = callbackFlow {
        firestore.document("${FireBaseConstants.posts}/$uidPost")
            .addSnapshotListener { value, error ->
                if (error != null && value == null) {
                    close(error)
                }

                value?.toObject<Post>().let {
                    if (!isClosedForSend) {
                        trySend(it)
                    }
                }
            }
        awaitClose()
    }.catch {
        throw it
    }.flowOn(Dispatchers.IO)

    suspend fun add(title: String, description: String, price: Double, quantity: Int, devise: String, uri: Uri) {
        val fileRef = storage.reference.child("images/${getRandomString(12).lowercase(Locale.ROOT)}")
        fileRef.putFile(uri).await()
        val imageUrl = fileRef.downloadUrl.await().toString()

        addPostStore(title, description, price, quantity, devise, imageUrl)
    }

    private suspend fun addPostStore(title: String, description: String, price: Double, quantity: Int, devise: String, imageUrl: String){
        val post = Post(
            uid = getRandomString(12).lowercase(Locale.ROOT),
            userUid = currentUser?.uid.toString(),
            title = title,
            description = description,
            price = price,
            quantity = quantity,
            devise = devise,
            imageUrl = imageUrl,
            createdAt = Date(System.currentTimeMillis())
        )
        val doc = firestore.document("${FireBaseConstants.posts}/${post.uid}")
        doc.set(post).await()
    }

    private fun getRandomString(length: Int) : String {
        val charset = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        return (1..length)
            .map { charset.random() }
            .joinToString("")
    }

    suspend fun delete(post: Post) {
        firestore.document("${FireBaseConstants.posts}/${post.uid}").delete().await()
    }
}