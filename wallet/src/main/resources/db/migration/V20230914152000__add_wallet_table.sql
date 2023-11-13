CREATE OR REPLACE TABLE wallets(
    id SERIAL PRIMARY KEY,
    member_id INT NOT NULL,
    ticker VARCHAR(20) NOT NULL REFERENCES currencies,
    total_balance BIGINT NOT NULL DEFAULT 0,
    available_balance BIGINT NOT NULL DEFAULT 0,
    locked_amount BIGINT NOT NULL DEFAULT 0,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW()
);

CREATE UNIQUE INDEX wallets_member_id_ticker_key ON wallets (member_id, ticker);