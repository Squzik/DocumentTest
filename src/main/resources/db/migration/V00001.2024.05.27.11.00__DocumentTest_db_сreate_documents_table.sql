CREATE TABLE documents (
                           id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                           type VARCHAR(255) NOT NULL,
                           content TEXT NOT NULL,
                           number VARCHAR(255) NOT NULL,
                           date TIMESTAMP NOT NULL

);
alter table documents
    owner to sma;
