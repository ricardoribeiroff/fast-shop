package dev.shop.fast_shop.data

import com.google.firebase.firestore.FirebaseFirestore
import dev.shop.fast_shop.model.Lists
import kotlinx.coroutines.tasks.await
import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.util.Locale

class DatabaseHelper {
    private val db = FirebaseFirestore.getInstance()

    suspend fun getProducts(uidUser: String): List<Lists> {
        return try {
            val documents = db.collection("lists")
                .whereEqualTo("uid_user", uidUser)
                .get()
                .await()
            documents.map { document ->
                val data = document.data
                Lists(
                    id = document.id,
                    name = data["name"] as? String ?: "",
                    date = (data["date"] as? Timestamp)?.toDate(), // Corrigido para pegar apenas a data
                    market = data["market"] as? String ?: "",
                    uidUser = data["uid_user"] as? String ?: "" // Adicionado se necessário
                )
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    fun convertStringToTimestamp(dateString: String): Timestamp? {
        return try {
            val formatter = SimpleDateFormat("ddMMyyyy", Locale.getDefault())
            val date = formatter.parse(dateString)
            if (date != null) {
                Timestamp(date)
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    suspend fun addList(list: Lists): Result<String> {
        return try {
            // Não altera o campo date aqui
            val listWithoutId = list.copy(id = "")
            val documentRef = db.collection("lists")
                .add(listWithoutId.toFirestoreMap())
                .await()
            Result.success(documentRef.id)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun Lists.toFirestoreMap(): Map<String, Any?> {
        return mapOf(
            "name" to this.name,
            "date" to convertStringToTimestamp(this.date.toString()), // Converte a String original
            "market" to this.market,
            "uid_user" to this.uidUser // Chave corrigida para match com Firestore
        )
    }
}