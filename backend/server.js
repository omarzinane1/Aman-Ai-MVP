require('dotenv').config();
const express = require('express');
const cors = require('cors');
const { Pool } = require('pg');
const admin = require('firebase-admin');
const path = require('path');
const fs = require('fs');

const app = express();
app.use(cors());
app.use(express.json());

const fcmKeyPath = path.join(__dirname, 'fcm_key.json');
if (fs.existsSync(fcmKeyPath)) {
    admin.initializeApp({ credential: admin.credential.cert(require(fcmKeyPath)) });
} else {
    console.warn("fcm_key.json not found. Push notifications will be disabled.");
}

const pool = new Pool({
  user: process.env.DB_USER,
  host: process.env.DB_HOST,
  database: process.env.DB_NAME,
  password: process.env.DB_PASSWORD,
  port: 5432,
});

app.post('/api/v1/register', async (req, res) => {
  const { deviceId, pushToken } = req.body;
  try {
    await pool.query(
      'INSERT INTO users (device_hash, push_token) VALUES ($1, $2) ON CONFLICT (device_hash) DO UPDATE SET push_token = $2',
      [deviceId, pushToken]
    );
    res.status(201).json({ status: 'registered' });
  } catch (err) {
    console.error(err);
    res.status(500).json({ error: 'Internal Server Error' });
  }
});

app.post('/api/v1/alerts', async (req, res) => {
  const { userId, riskLevel, location, scores, contacts } = req.body;
  try {
    await pool.query(
      'INSERT INTO alerts (user_hash, risk_level, lat, lon, scores) VALUES ($1, $2, $3, $4, $5)',
      [userId, riskLevel, location.lat, location.lon, JSON.stringify(scores)]
    );
    
    let pushSent = false;
    if (admin.apps.length > 0) {
        for (const c of contacts) {
          if (c.type === 'user' && c.pushToken) {
            try {
              await admin.messaging().send({
                token: c.pushToken,
                notification: { title: 'Alerte AMAN-AI', body: `Urgence niveau ${riskLevel}` }
              });
              pushSent = true;
            } catch(e) {
                console.error("Error sending push:", e.message);
            }
          }
        }
    }
    
    res.status(pushSent ? 200 : 404).json({ message: pushSent ? 'Push envoyé' : 'Aucun push envoyé ou Firebase non configuré' });
  } catch (err) {
    console.error(err);
    res.status(500).json({ error: 'Internal Server Error' });
  }
});

app.listen(3000, () => console.log('Backend running on port 3000'));
