package com.amanai.network

import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface AlertApi {
    @POST("api/v1/alerts")
    suspend fun sendAlert(@Body alert: AlertRequest): Response<AlertResponse>
}

data class AlertRequest(
    @SerializedName("userId") val userId: String,
    @SerializedName("riskLevel") val riskLevel: Int,
    @SerializedName("location") val location: Location,
    @SerializedName("scores") val scores: Map<String, Float>,
    @SerializedName("contacts") val contacts: List<Contact>
)

data class Location(val lat: Double, val lon: Double)
data class AlertResponse(val message: String)

class AlertRepository {
    private val api: AlertApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/") // Localhost for Android Emulator
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = retrofit.create(AlertApi::class.java)
    }

    suspend fun sendAlert(
        userId: String,
        riskLevel: Int,
        location: android.location.Location,
        scores: Map<String, Float>,
        contacts: List<Contact>
    ): Result<Boolean> {
        return try {
            val request = AlertRequest(
                userId = userId,
                riskLevel = riskLevel,
                location = Location(location.latitude, location.longitude),
                scores = scores,
                contacts = contacts
            )
            val response = api.sendAlert(request)
            if (response.isSuccessful) {
                Result.success(true)
            } else {
                Result.failure(Exception("HTTP ${response.code()}: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
