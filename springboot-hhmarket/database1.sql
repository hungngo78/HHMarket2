use hhmarketdb;

create table `user` (
	user_id integer not null, 
	address varchar(255), 
	city varchar(255), 
	email varchar(255), 
	first_name varchar(255), 
	last_name varchar(255), 
	password varchar(255), 
	`state` varchar(255), 
	user_name varchar(255), 
	zip_code varchar(255), 
	primary key (user_id)
) engine=InnoDB;

create table hibernate_sequence ( next_val bigint(20) )engine=InnoDB;
INSERT hibernate_sequence(next_val) VALUES (1) ;

create table main_category (
	main_category_id integer not null, 
	description varchar(255), 
	name varchar(255), 
	primary key (main_category_id)
) engine=InnoDB;

create table category (
	category_id integer not null, 
	description varchar(255), 
	name varchar(255), 
	picture varchar(255), 
	main_category_id integer, 
	primary key (category_id)
) engine=InnoDB;

create table product (
	product_id integer not null, 
	description varchar(255), 
	name varchar(255), 
	category_id integer, 
	user_id integer, 
	primary key (product_id)
) engine=InnoDB;

create table product_details (
	product_details_id integer not null, 
	amount integer not null, 
	color varchar(255), 
	picture varchar(255), 
	price float not null, 
	size varchar(255), 
	product_id integer, 
	primary key (product_details_id)
) engine=InnoDB;

create table cart (
	cart_id integer not null, 
	date_open date, 
	user_id integer, 
	primary key (cart_id)
) engine=InnoDB;

create table cart_details (
	cart_details_id integer not null, 
	amount integer not null, 
	extended_price float not null, 
	`type` integer not null, 
	cart_id integer, 
	product_details_id integer, 
	primary key (cart_details_id)
) engine=InnoDB;

create table `shopping_order` (
	order_id integer not null, 
	delivery_date date, 
	delivery_fee float not null, 
	note varchar(255), 
	order_date date, 
	`status` integer not null, 
	user_id integer, primary key (order_id)
) engine=InnoDB;

create table order_details (
	order_details_id integer not null, 
	amount integer not null, 
	extended_price float not null, 
	order_id integer, 
	product_details_id integer, 
	primary key (order_details_id)
) engine=InnoDB;

create table review (
	review_id integer not null, 
	content varchar(1000), 
	overall_rating integer not null, 
	review_date date, 
	title varchar(255), 
	product_id integer, 
	user_id integer, 
	primary key (review_id)
) engine=InnoDB;

alter table cart add constraint FKl70asp4l4w0jmbm1tqyofho4o foreign key (user_id) references user (user_id);
alter table cart_details add constraint FKhq1m50l0ke2fkqxxd6ubo3x4b foreign key (cart_id) references cart (cart_id);
alter table cart_details add constraint FKtibhs55lvuicxc2r84wqiynt foreign key (product_details_id) references product_details (product_details_id);
alter table category add constraint FK1o9opwme2q425qr8iwr3jqqml foreign key (main_category_id) references main_category (main_category_id);
alter table `order` add constraint FKt7abetueht6dd1gs9jyl3o4t7 foreign key (user_id) references user (user_id);
alter table order_details add constraint FKrqebearjsp1kgak9edqrenaeb foreign key (order_id) references `order` (order_id);
alter table order_details add constraint FKos61972xo18nahkjujx4ck9ys foreign key (product_details_id) references product_details (product_details_id);
alter table product add constraint FK1mtsbur82frn64de7balymq9s foreign key (category_id) references category (category_id);
alter table product add constraint FK979liw4xk18ncpl87u4tygx2u foreign key (user_id) references user (user_id);
alter table product_details add constraint FKrhahp4f26x99lqf0kybcs79rb foreign key (product_id) references product (product_id);
alter table review add constraint FKiyof1sindb9qiqr9o8npj8klt foreign key (product_id) references product (product_id);
alter table review add constraint FKiyf57dy48lyiftdrf7y87rnxi foreign key (user_id) references user (user_id);
	
	
	
	
	