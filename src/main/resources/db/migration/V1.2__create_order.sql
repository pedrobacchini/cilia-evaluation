CREATE TABLE "order"
(
  uuid UUID NOT NULL DEFAULT uuid_generate_v1(),
  CONSTRAINT order_pkey PRIMARY KEY ( uuid ),
  client_uuid     uuid             NOT NULL
    constraint fkr90nfre8si1iewp6h2w0c4cvv
    references client
);

CREATE TABLE order_item
(
  uuid UUID NOT NULL DEFAULT uuid_generate_v1(),
  CONSTRAINT order_item_pkey PRIMARY KEY ( uuid ),
  quantity  INT               NOT NULL,
  price     DOUBLE PRECISION  NOT NULL,
  order_uuid uuid             NOT NULL
    constraint fkr90nfre8si3129986h2w0c4cvv
    references "order",
  product_uuid uuid             NOT NULL
    constraint fkr321312d29986h2w0c4cvv
    references product
)