CREATE TABLE object_key
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    object_key VARCHAR(256) NOT NULL,
    created_at DATETIME     NOT NULL,
    updated_at DATETIME     NOT NULL,
    UNIQUE (object_key)
);