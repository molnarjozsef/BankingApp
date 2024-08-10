package repository

import dev.gitlive.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface AuthenticationRepository {

    fun getCurrentUser(): Flow<FirebaseUser?>

    fun getUserEmail(): Flow<String?>

    suspend fun createAccount(email: String, password: String)

    suspend fun login(email: String, password: String)

    suspend fun logout()
}
