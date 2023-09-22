CREATE OR REPLACE TABLE tokens(
    jwt VARCHAR(8192) PRIMARY KEY,
    member_id INT NOT NULL REFERENCES members,
    revoked BOOLEAN NOT NULL DEFAULT false,
    revoke_reason TEXT,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW()
);

CREATE UNIQUE INDEX tokens_jwt_key ON tokens (jwt);
CREATE INDEX tokens_member_id_idx ON tokens (member_id);
