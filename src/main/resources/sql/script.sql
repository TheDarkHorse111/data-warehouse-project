CREATE DATABASE data_warehouse;

CREATE TABLE deal(
id bigserial PRIMARY KEY,
from_currency varchar(40) NOT NULL,
to_currency varchar(40) NOT NULL,
deal_timestamp timestamp NOT NULL,
amount numeric(10,3) NOT NULL
)