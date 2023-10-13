CREATE OR REPLACE TABLE orders(
    id SERIAL PRIMARY KEY,
    member_id INT NOT NULL REFERENCES members,
    trade_type VARCHAR(20) NOT NULL,
    order_type VARCHAR(20) NOT NULL,
    unit_price INT NOT NULL,
    total_volume INT NOT NULL,
    trade_volume INT DEFAULT 0,
    cancel_order_id INT REFERENCES orders(id),
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW()
);

COMMENT ON COLUMN orders.trade_type is '거래 유형: BUY, SELL, CANCEL';
COMMENT ON COLUMN orders.order_type is '주문 유형: MARKET, LIMIT';
COMMENT ON COLUMN orders.unit_price is '거래 단가';
COMMENT ON COLUMN orders.total_volume is '총 주문수량';
COMMENT ON COLUMN orders.trade_volume is '체결된 주문수량';
COMMENT ON COLUMN orders.cancel_order_id is '주문취소시 원본 주문 id';

CREATE OR REPLACE TABLE trades(
    id SERIAL PRIMARY KEY,
    trade_type VARCHAR(20) NOT NULL,
    trade_unit_price INT NOT NULL,
    trade_volume INT NOT NULL,
    trade_amount BIGINT NOT NULL,
    maker_member_id INT NOT NULL REFERENCES members(id),
    maker_order_id INT NOT NULL REFERENCES orders(id),
    taker_member_id INT NOT NULL REFERENCES members(id),
    taker_order_id INT NOT NULL REFERENCES orders(id),
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW()
);

COMMENT ON COLUMN trades.trade_type is '거래 유형(taker 기준): BUY, SELL, CANCEL';
COMMENT ON COLUMN trades.trade_unit_price is '체결단가';
COMMENT ON COLUMN trades.trade_volume is '체결량';
COMMENT ON COLUMN trades.trade_amount is '체결금액';
COMMENT ON COLUMN trades.maker_member_id is '메이커 유저 id';
COMMENT ON COLUMN trades.maker_order_id is '메이커 주문 id';
COMMENT ON COLUMN trades.taker_member_id is '테이커 유저 id';
COMMENT ON COLUMN trades.taker_order_id is '테이커 주문 id';
