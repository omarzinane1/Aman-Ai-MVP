package com.amanai.network

import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

data class AlertRequest(
    @SerializedName("userId") val userId: String,
    @SerializedName("riskLevel") val riskLevel: Int,
    @SerializedName("location") val location: Location,
    @SerializedName("scores") val scores: Map<String, Float>,
    @SerializedName("contacts") val contacts: List<Contact>
)

data class Location(
    val lat: Double,
    val lon: Double
)

data class Contact(
    val type: String,
    val pushToken: String? = null,
    val phone: String? = null
)

interface AmanApi {
    @POST("/api/v1/alerts")
    suspend fun sendAlert(@Body request: AlertRequest): Response<Unit>
}

class AlertRepository {
    private val api: AmanApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(AmanApi::class.java)
    }

    suspend fun sendAlert(request: AlertRequest): Boolean {
        return try {
            val response = api.sendAlert(request)
            response.isSuccessful
        } catch (e: Exception) {
            false
        }
    }
}