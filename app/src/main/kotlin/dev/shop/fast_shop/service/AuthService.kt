package dev.shop.fast_shop.service // Atualize o pacote

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AuthService {
    private val auth: FirebaseAuth = Firebase.auth

    // Login com email/senha
    fun login(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit,
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val uidUser =  auth.currentUser?.uid
                    if (uidUser != null) {
                        onSuccess()
                    } else {
                        onFailure("Erro ao recuperar usuário")
                    }
                } else {
                    onFailure(task.exception?.message ?: "Erro desconhecido")
                }
            }
    }

    // Cadastro de novo usuário
    fun register(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onSuccess()
                } else {
                    onFailure(task.exception?.message ?: "Erro ao criar conta")
                }
            }
    }
}