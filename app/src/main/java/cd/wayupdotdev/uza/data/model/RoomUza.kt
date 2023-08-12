package cd.wayupdotdev.uza.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(tableName = "uza")
data class RoomUza(
    @PrimaryKey
    val uid: String,
    val userUid: String,
    val title: String,
    val description: String,
    val price: Double,
    val quantity: Int ,
    val devise: String,
    val imageUrl: String,
    val createdAt: Date,
    val isFavorite: Boolean
) {
    fun toPost(): Post {
        return Post(
            uid = uid,
            userUid = userUid,
            title = title,
            description = description,
            price = price,
            devise = devise,
            quantity = quantity,
            imageUrl = imageUrl,
            createdAt = createdAt,
            isFavorite = isFavorite
        )
    }
}
