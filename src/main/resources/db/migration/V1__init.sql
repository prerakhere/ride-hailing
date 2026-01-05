-- Enable UUID generator
CREATE EXTENSION IF NOT EXISTS pgcrypto;

-- USERS
CREATE TABLE users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    role VARCHAR(10) NOT NULL CHECK (role IN ('RIDER', 'DRIVER')),
    name VARCHAR(255) NOT NULL,
    phone VARCHAR(20) UNIQUE NOT NULL,
    is_available BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- RIDES
CREATE TABLE rides (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    status VARCHAR(20) NOT NULL DEFAULT 'REQUESTED'
        CHECK (status IN ('REQUESTED', 'ACCEPTED', 'INPROGRESS', 'COMPLETED', 'CANCELED', 'DRIVERCANCELED')),

    rider_id UUID NOT NULL REFERENCES users(id),
    driver_id UUID REFERENCES users(id),

    pickup_lat DECIMAL(10,8) NOT NULL,
    pickup_lng DECIMAL(11,8) NOT NULL,
    dropoff_lat DECIMAL(10,8) NOT NULL,
    dropoff_lng DECIMAL(11,8) NOT NULL,

    distance_km DECIMAL(6,2),
    duration_min INT,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- DRIVER LOCATIONS
CREATE TABLE driver_locations (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    driver_id UUID NOT NULL REFERENCES users(id),
    lat DECIMAL(10,8) NOT NULL,
    lng DECIMAL(11,8) NOT NULL,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT uq_driver_locations_driver UNIQUE (driver_id)
);

-- RIDE EVENTS
CREATE TABLE ride_events (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    ride_id UUID NOT NULL REFERENCES rides(id),
    event_type VARCHAR(50) NOT NULL,
    user_id UUID NOT NULL REFERENCES users(id),
    user_role VARCHAR(10) NOT NULL,
    details JSONB,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
