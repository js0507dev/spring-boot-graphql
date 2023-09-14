CREATE OR REPLACE TABLE currencies(
    ticker VARCHAR(20) PRIMARY KEY,
    name VARCHAR(60) NOT NULL,
    description TEXT,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW()
);

CREATE OR REPLACE TABLE wallets(
    id SERIAL PRIMARY KEY,
    member_id INT NOT NULL REFERENCES members,
    ticker VARCHAR(20) NOT NULL REFERENCES currency,
    total_balance BIGINT NOT NULL DEFAULT 0,
    available_balance BIGINT NOT NULL DEFAULT 0,
    locked_amount BIGINT NOT NULL DEFAULT 0,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW()
);

CREATE UNIQUE INDEX wallets_member_id_ticker_key ON wallets (member_id, ticker);