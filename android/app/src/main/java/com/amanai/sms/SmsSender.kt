package com.amanai.sms

import android.content.Context
import android.telephony.SmsManager
import android.util.Log
import com.amanai.network.Contact
import android.location.Location
import android.os.Build

class SmsSender(private val context: Context) {
    fun sendSmsToAllContacts(contacts: List<Contact>, riskLevel: Int, location: Location?) {
        val message = buildString {
            append("ALERTE AMAN-AI : niveau de risque $riskLevel. ")
            if (location != null) {
                append("Position approximative : https://maps.google.com/?q=${location.latitude},${location.longitude} ")
            }
            append("Veuillez contacter l'utilisateur d'urgence.")
        }
        contacts.forEach { contact ->
            if (contact.phoneNumber.isNotBlank()) {
                try {
                    val smsManager = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                        context.getSystemService(SmsManager::class.java)
                    } else {
                        @Suppress("DEPRECATION")
                        SmsManager.getDefault()
                    }
                    smsManager.sendTextMessage(contact.phoneNumber, null, message, null, null)
                } catch (e: Exception) {
                    Log.e("SmsSender", "Échec d'envoi SMS à ${contact.phoneNumber}", e)
                }
            }
        }
    }
}
