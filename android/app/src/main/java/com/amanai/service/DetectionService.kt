package com.amanai.service

import android.app.*
import android.content.*
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.amanai.ml.AudioClassifier
import kotlinx.coroutines.*
import java.util.*

class DetectionService : Service(), SensorEventListener {
    private lateinit var audioClassifier: AudioClassifier
    private var alertJob: Job? = null
    private var isCancelled = false
    private lateinit var sensorManager: SensorManager

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, DetectionService::class.java)
        }
    }

    override fun onCreate() {
        super.onCreate()
        audioClassifier = AudioClassifier(this)
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        startForeground(1, createNotification())
        startAudioCapture()
        registerSensors()
    }

    private fun createNotification(): Notification {
        val channelId = "aman_ai_service"
        val channelName = "AMAN-AI Detection Service"
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_LOW)
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }

        return NotificationCompat.Builder(this, channelId)
            .setContentTitle("AMAN-AI")
            .setContentText("Protection active")
            .setSmallIcon(android.R.drawable.ic_lock_idle_lock)
            .build()
    }

    private fun startAudioCapture() {
        // Implementation for audio capture and periodic classification
    }

    private fun registerSensors() {
        val accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        // Process accelerometer data
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    private fun processScores(audioScore: Float, motionScore: Float) {
        var finalScore = audioScore * 0.55f + motionScore * 0.45f
        if (isNight()) finalScore *= 1.2f
        finalScore = (finalScore * 100).coerceAtMost(100f)
        if (finalScore >= 61 && alertJob == null) {
            alertJob = CoroutineScope(Dispatchers.IO).launch {
                delay(30000)
                if (!isCancelled) sendAlert()
            }
        }
    }

    private fun isNight(): Boolean {
        val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        return hour >= 22 || hour <= 6
    }

    private fun sendAlert() {
        // Fallback to SMS if backend call fails
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
