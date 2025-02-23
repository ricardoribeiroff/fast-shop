package dev.shop.fast_shop.data

import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import dev.shop.fast_shop.model.Products
import kotlinx.coroutines.tasks.await
import com.google.firebase.Timestamp

class DatabaseHelper {
    val db = Firebase.firestore


    suspend fun getProducts(uidUser: String): List<Products> {
        return try {
            val documents = db.collection("lists")
                .whereEqualTo("uid_user", uidUser)
                .get()
                .await()
            documents.map { document ->
                val data = document.data
                Products(
                    id = document.id,
                    name = data["name"] as? String ?: "",
                    date = (data["date"] as? Timestamp)?.toDate(),
                    market = data["market"] as? String ?: ""
                )
            }
        } catch (e: Exception) {
            emptyList()
        }
    }


}

