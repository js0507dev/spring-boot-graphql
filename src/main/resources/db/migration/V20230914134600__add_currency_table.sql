CREATE OR REPLACE TABLE currencies(
    ticker VARCHAR(20) PRIMARY KEY,
    name VARCHAR(60) NOT NULL,
    description TEXT,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW()
);
