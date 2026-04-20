package com.amanai.network

import android.content.Context
import android.location.Location
import com.amanai.utils.NetworkUtils
import com.google.firebase.functions.FirebaseFunctions
import com.google.firebase.functions.ktx.functions
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import java.io.IOException

data class Contact(
    val name: String,
    val phoneNumber: String,
    val fcmToken: String? = null
)

class FirebaseAlertRepository(
    private val context: Context
) {
    private val functions: FirebaseFunctions = Firebase.functions

    suspend fun sendAlert(riskLevel: Int, location: Location, scores: Map<String, Float>): Result<Boolean> {
        // Vérifier la connectivité avant tout appel réseau
        if (!NetworkUtils.isOnline(context)) {
            return Result.failure(IOException("Pas de connexion Internet"))
        }
        return try {
            val data = hashMapOf(
                "riskLevel" to riskLevel,
                "location" to mapOf("lat" to location.latitude, "lon" to location.longitude),
                "scores" to scores
            )
            val result = functions.getHttpsCallable("sendAlert").call(data).await()
            val dataMap = result.getData() as? Map<*, *>
            val pushSent = dataMap?.get("pushSent") as? Boolean ?: false
            Result.success(pushSent)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
