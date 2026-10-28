CREATE TABLE urls(
    short_code VARCHAR(7) PRIMARY KEY CHECK (short_code ~ '^[A-Za-z0-9]{7}$'),
    long_url TEXT NOT NULL,
    user_id UUID NOT NULL REFERENCES users(id),
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
)