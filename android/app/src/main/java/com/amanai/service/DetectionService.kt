package com.amanai.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.amanai.ml.AudioClassifier
import kotlinx.coroutines.*

import android.location.Location
import com.amanai.network.AlertRepository
import com.amanai.sms.SmsSender
import com.amanai.network.Contact
import com.amanai.ml.MotionClassifier
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.Tasks
import java.util.concurrent.TimeUnit

class DetectionService : Service(), SensorEventListener {
    private lateinit var audioClassifier: AudioClassifier
    private lateinit var motionClassifier: MotionClassifier
    private lateinit var alertRepo: AlertRepository
    private lateinit var smsSender: SmsSender
    private var alertJob: Job? = null
    private var contactsList: List<Contact> = emptyList()
    private val serviceScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    override fun onCreate() {
        super.onCreate()
        audioClassifier = AudioClassifier(this)
        motionClassifier = MotionClassifier(this)
        alertRepo = AlertRepository()
        smsSender = SmsSender(this)
        startForeground(1, createNotification())
        startAudioCapture()
        registerSensors()
        loadContacts() 
    }

    private fun startAudioCapture() {
        // Implementation for audio capture and processing
    }

    private fun registerSensors() {
        // Implementation for sensor registration
    }

    private fun createNotification(): Notification {
        val channelId = "aman_ai_detection"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "AMAN-AI Detection Service",
                NotificationManager.IMPORTANCE_LOW
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
        return NotificationCompat.Builder(this, channelId)
            .setContentTitle("AMAN-AI")
            .setContentText("Protection active en arrière-plan")
            .setSmallIcon(android.R.drawable.ic_notification_overlay)
            .build()
    }

    private fun processScores(audioScore: Float, motionScore: Float) {
        var finalScore = audioScore * 0.55f + motionScore * 0.45f
        if (isNight()) finalScore *= 1.2f
        finalScore = (finalScore * 100).coerceAtMost(100f)
        
        if (finalScore >= 61 && alertJob == null) {
            alertJob = serviceScope.launch {
                delay(30000)
                if (alertJob?.isActive == true) {
                    val location = getLastLocation()
                    if (location != null) {
                        // Tentative d'envoi via Firebase
                        val result = alertRepo.sendAlert(
                            userId = "device_owner_${android.os.Build.MODEL}", // Placeholder for MVP
                            riskLevel = finalScore.toInt(),
                            location = location,
                            scores = mapOf("audio" to audioScore, "motion" to motionScore),
                            contacts = contactsList
                        )
                        
                        // Fallback SMS : soit pas de réseau, soit aucun push envoyé (pushSent = false)
                        if (result.isFailure || result.getOrNull() == false) {
                            smsSender.sendSmsToAllContacts(contactsList, finalScore.toInt(), location)
                        }
                    }
                }
                alertJob = null
            }
        }
    }

    private fun isNight(): Boolean {
        return false // Simplified for now
    }

    private suspend fun getLastLocation(): Location? {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        return try {
            val task = fusedLocationClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, null)
            Tasks.await(task, 5, TimeUnit.SECONDS)
        } catch (e: Exception) {
            null
        }
    }

    private fun loadContacts() {
        // In a real app, this would fetch from Firestore or a local database
        // For the MVP, we can have a placeholder or a simple list
        contactsList = listOf(
            Contact("Urgence", "0600000000") // TODO: Replace with real contacts
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceScope.cancel()
    }

    override fun onSensorChanged(event: SensorEvent?) {
        // Implementation for sensor processing
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

    override fun onBind(intent: Intent?): IBinder? = null

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, DetectionService::class.java)
        }
    }
}
