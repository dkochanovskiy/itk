CREATE TABLE transaction (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    wallet_id UUID NOT NULL REFERENCES wallet(wallet_id),
    operation_type VARCHAR(20) NOT NULL,
    amount INTEGER NOT NULL
);