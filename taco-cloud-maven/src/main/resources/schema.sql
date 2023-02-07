create table if not exists Bun_Pho_Order (
  id identity,
  delivery_Name varchar(50) not null,
  delivery_Street varchar(50) not null,
  delivery_City varchar(50) not null,
  delivery_State varchar(2) not null,
  delivery_Zip varchar(10) not null,
  cc_number varchar(16) not null,
  cc_expiration varchar(5) not null,
  cc_cvv varchar(3) not null,
  placed_at timestamp not null
);
create table if not exists Bun_Pho (
  id identity,
  name varchar(50) not null,
  bun_pho_order bigint not null,
  bun_pho_order_key bigint not null,
  created_at timestamp not null
);
create table if not exists Ingredient_Ref (
  ingredient varchar(4) not null,
  bun_pho bigint not null,
  bun_pho_key bigint not null
);
create table if not exists Ingredient (
  id varchar(4) not null,
  name varchar(25) not null,
  type varchar(10) not null
);
alter table Bun_Pho
    add foreign key (bun_pho_order) references Bun_Pho_Order(id);