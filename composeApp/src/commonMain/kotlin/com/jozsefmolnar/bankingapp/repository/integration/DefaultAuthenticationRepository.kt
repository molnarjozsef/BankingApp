package com.jozsefmolnar.bankingapp.repository.integration

import com.jozsefmolnar.bankingapp.AppConstants.AccountOpeningCampaignCredit
import com.jozsefmolnar.bankingapp.AppConstants.AmountFieldKey
import com.jozsefmolnar.bankingapp.AppConstants.UsersCollection
import com.jozsefmolnar.bankingapp.repository.AuthenticationRepository
import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.map

class DefaultAuthenticationRepository(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore,
) : AuthenticationRepository {

    override fun getCurrentUser() = firebaseAuth.authStateChanged

    override fun getUserEmail() = firebaseAuth.authStateChanged.map { it?.email }

    override suspend fun createAccount(
        email: String,
        password: String,
    ) {
        firebaseAuth.createUserWithEmailAndPassword(
            email = email,
            password = password
        )
        firebaseFirestore
            .collection(UsersCollection)
            .document(email)
            .set(mapOf(AmountFieldKey to AccountOpeningCampaignCredit))
    }

    override suspend fun login(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(
            email = email,
            password = password
        )
    }

    override suspend fun logout() {
        firebaseAuth.signOut()
    }
}

