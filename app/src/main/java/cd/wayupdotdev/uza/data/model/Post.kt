package cd.wayupdotdev.uza.data.model

import java.util.Date

data class Post(
    val uid: String = "",
    val userUid: String = "",
    val title: String = "",
    val description: String = "",
    val price: Double = 0.0,
    val quantity: Int = 0,
    val devise: String = "",
    val imageUrl: String = "",
    val createdAt: Date? = null,
    val isFavorite: Boolean = false,
    val notification: Boolean = true
){
    fun toRoomChurch(): RoomUza {
        return RoomUza(
            uid = uid,
            userUid = userUid,
            title = title,
            description = description,
            price = price,
            quantity = quantity,
            devise = devise,
            imageUrl = imageUrl,
            createdAt = java.sql.Date(System.currentTimeMillis()),
            isFavorite = isFavorite,
        )
    }
}

//val Post.generatedId: String
//    get() = title.filterNot { it.isWhitespace() }.filterNot { it == '"' }.take(20)