const functions = require('firebase-functions');
const admin = require('firebase-admin');
admin.initializeApp();
const db = admin.firestore();

// Cloud Function appelable depuis l'application Android
exports.sendAlert = functions.https.onCall(async (data, context) => {
  // Vérifier que l'utilisateur est authentifié
  if (!context.auth) {
    throw new functions.https.HttpsError('unauthenticated', 'Utilisateur non authentifié');
  }
  const userId = context.auth.uid;
  const { riskLevel, location, scores } = data;

  // Enregistrer l'alerte dans Firestore
  const alertRef = await db.collection('alerts').add({
    userId,
    riskLevel,
    location: new admin.firestore.GeoPoint(location.lat, location.lon),
    scores,
    timestamp: admin.firestore.FieldValue.serverTimestamp()
  });

  // Récupérer les tokens FCM des contacts de l'utilisateur
  const contactsSnapshot = await db.collection('users').doc(userId).collection('contacts').get();
  const tokens = [];
  contactsSnapshot.forEach(doc => {
    const token = doc.data().fcmToken;
    if (token) tokens.push(token);
  });

  let pushSent = false;
  if (tokens.length > 0) {
    const message = {
      notification: { title: 'Alerte AMAN-AI', body: `Urgence niveau ${riskLevel}` },
      tokens: tokens
    };
    const response = await admin.messaging().sendMulticast(message);
    pushSent = response.successCount > 0;
  }

  // Retourner un booléen indiquant si au moins un push a été envoyé
  return { alertId: alertRef.id, pushSent };
});
