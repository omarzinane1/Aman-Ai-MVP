Hi # AMAN-AI MVP

Protection discrète et intelligente des personnes en situation de risque.

## Structure du projet

- `training/`: Environnement d'entraînement Jupyter (Dockerisé).
- `backend/`: Serveur Node.js et base de données PostgreSQL (Docker Compose).
- `android/`: Application mobile Kotlin/Jetpack Compose.

## Déploiement rapide

### 1. Entraînement
```bash
cd training
docker build -t aman-jupyter .
docker run -p 8888:8888 -v $(pwd):/home/jovyan/work aman-jupyter
```

### 2. Backend
```bash
cd backend
docker-compose up --build
```

### 3. Application Android
- Copiez les modèles `.tflite` dans `android/app/src/main/assets/`.
- Compilez avec Android Studio.

## Architecture
- **IA**: Modèles TensorFlow Lite (CNN pour l'audio, Bi-LSTM pour le mouvement).
- **Backend**: Node.js, Express, PostgreSQL, Firebase Cloud Messaging.
- **Mobile**: Kotlin, Jetpack Compose, Retrofit, TFLite.

---
*Projet de fin de module - FST Marrakech - Avril 2026*
