CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    device_hash VARCHAR(255) UNIQUE,
    push_token VARCHAR(255),
    created_at TIMESTAMP DEFAULT NOW()
);
CREATE TABLE IF NOT EXISTS alerts (
    id SERIAL PRIMARY KEY,
    user_hash VARCHAR(255),
    risk_level INT,
    lat FLOAT, lon FLOAT,
    scores JSONB,
    created_at TIMESTAMP DEFAULT NOW()
);
