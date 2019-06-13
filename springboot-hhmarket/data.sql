INSERT INTO `user` (`user_id`, `user_name`, `password`, `Email`, `first_name`, `last_name`, `address`, `city`, `state`, `zip_code`) 
   VALUES ('1', 'hung', '123', 'abc@gmail.com', 'Hung First Name', 'Ngo', '6311 Willow Cir', 'Westminster', 'CA', '92683');
INSERT INTO `user` (`user_id`, `user_name`, `password`, `Email`, `first_name`, `last_name`, `address`, `city`, `state`, `zip_code`) 
   VALUES ('2', 'huong', '123', 'huong@gmail.com', 'Huong', 'Nguyen', '6311 Willow Cir', 'Westminster', 'CA', '92683');
INSERT INTO `user` (`user_id`, `user_name`, `password`, `Email`, `first_name`, `last_name`, `address`, `city`, `state`, `zip_code`) 
   VALUES ('3', 'huongquadeo', '1234', 'aaa@gmail.com', 'Huong1 ', 'Nguyen 1', '6311 Willow Cir', 'Westminster', 'CA', '92683');


INSERT INTO `main_category` (`main_category_id`, `name`, `description`) VALUES (1, 'Loai 1', 'aaa');
INSERT INTO `main_category` (`main_category_id`, `name`, `description`) VALUES (2, 'Loai 2', 'bbb');


INSERT INTO `category` (`category_id`, `name`, `picture`, `description`, `main_category_id`) 
   VALUES (7, 'Electric', 'aa.jpg', 'aa', 1);
INSERT INTO `category` (`category_id`, `name`, `picture`, `description`, `main_category_id`) 
   VALUES (11, 'Mens clothing', 'aa1.jpg', 'aa1', 1);
INSERT INTO `category` (`category_id`, `name`, `picture`, `description`, `main_category_id`) 
   VALUES (12, 'ABC', 'aa2.jpg', 'aa2', 2);
INSERT INTO `category` (`category_id`, `name`, `picture`, `description`, `main_category_id`) 
   VALUES (13, 'DEF', 'aa3.jpg', 'aaaaa', 2);
INSERT INTO `category` (`category_id`, `name`, `picture`, `description`, `main_category_id`) 
   VALUES (15, 'GHK', 'aa2.jpg', 'asdds', 2);
   
INSERT INTO `product` (`product_id`, `name`, `description`, `category_id`, `user_id`)
   VALUES (1000, 'Samsung galaxy 16GB', '64GB, 4GB RAM, IP68 Water and Dust Proof, Camera: 12 MP, Front: 8 MP, Fast', 7, 2);
INSERT INTO `product` (`product_id`, `name`, `description`, `category_id`, `user_id`)
   VALUES (1001, 'Fruit of the Loom', 'Fruit of the Loom Big Men''s Dual Defense EverSoft Crew Sweatshirt', 11, 2);
   
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5000, 'Red', '29Lx31W', 'image1.jpg,image1_1.jpg,image1_2.jpg', 48, 26, 1000);
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5001, 'Blue', '28Lx30W', 'image2.jpg', 28, 10, 1000);
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5002, 'Black', '30Lx40W', 'image1.jpg,image1_1.jpg,image1_2.jpg', 40, 30, 1000);
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5003, 'Navy', '2XL', 'image1.jpg,image2.jpg,image3.jpg', 8, 20, 1001);
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5004, 'Charcoal Heather', '3XL', 'image1.jpg,image2.jpg,image3.jpg', 9, 10, 1001);
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5005, 'True Red', '4XL', 'image1.jpg,image2.jpg,image3.jpg', 20, 5, 1001);
   
 
 
INSERT INTO `Cart` (`cart_id`, `date_open`, `user_id`) VALUES (1, '2019-01-19 20:48:17', 2);

INSERT INTO `Cart_Details` (`cart_details_id`, `amount`, `extended_price`, `type`, `product_details_id`, `cart_id`) 
  VALUES (6004, 4, 48, 0, 5000, 1);
INSERT INTO `Cart_Details` (`cart_details_id`, `amount`, `extended_price`, `type`, `product_details_id`, `cart_id`) 
  VALUES (6005, 5, 24, 0, 5001, 1);
  
  
 
 INSERT INTO `Review` (`review_id`, `content`, `overall_rating`, `review_date`, `user_id`, `product_id`, `title`) 
   VALUES (1, 'hone was a croc. When I first received the phones touch pad didn’t work and the primary speaker didn’t work. Mobilehype never responded any of my emails and it was a waste of 300 dollars', 1, '2018-09-02 00:00:00', 2, 1000, 'Horrible');
INSERT INTO `Review` (`review_id`, `content`, `overall_rating`, `review_date`, `user_id`, `product_id`, `title`) 
   VALUES (4, 'Little heavy, it''s a nice phone, like the Rose and metal. 12 megal pixel back camera, I think 5 mega pixel up front. This one is locked to T Mobile but that who ai have but can get it unlocked after 6 months I believe. 5.5 inch screen on plus vs 4.7 inch, although Samsung Note II had 5.5 in 2012. 32 gig is a bit small 64 gig is plenty, have another 6s 4.7 gold with 128 gigs that''s a lot.', 4, '2018-10-01 00:00:00', 2, 1000, 'Like rose gold');
INSERT INTO `Review` (`review_id`, `content`, `overall_rating`, `review_date`, `user_id`, `product_id`, `title`)  
   VALUES (5, 'The phone we purchased arrived in great shape. Exactly as advertise', 5, '2017-04-04 00:00:00', 2, 1000, 'Great');
INSERT INTO `Review` (`review_id`, `content`, `overall_rating`, `review_date`, `user_id`, `product_id`, `title`)  
   VALUES (4021, 'AAAAAAAAA', 2, '2019-01-13 00:15:11', 2, 1001, 'AA');
INSERT INTO `Review` (`review_id`, `content`, `overall_rating`, `review_date`, `user_id`, `product_id`, `title`)  
   VALUES (4022, 'BBBBBBBBBBBBBBBBBBB', 4, '2019-01-13 00:17:39', 2, 1001, 'BBB');
INSERT INTO `Review` (`review_id`, `content`, `overall_rating`, `review_date`, `user_id`, `product_id`, `title`)  
   VALUES (4023, 'aaa', 3, '2019-01-13 00:24:06', 2, 1001, 'aaa');
INSERT INTO `Review` (`review_id`, `content`, `overall_rating`, `review_date`, `user_id`, `product_id`, `title`)  
   VALUES (5001, 'ss', 3, '2019-01-13 00:39:56', 2, 1001, 'ssxx');
INSERT INTO `Review` (`review_id`, `content`, `overall_rating`, `review_date`, `user_id`, `product_id`, `title`)  
   VALUES (5002, 'ss', 5, '2019-01-13 00:40:07', 2, 1001, 'Vui');
INSERT INTO `Review` (`review_id`, `content`, `overall_rating`, `review_date`, `user_id`, `product_id`, `title`)  
   VALUES (5003, 'Hhahahahaa', 4, '2019-01-13 00:44:42', 2, 1001, 'HiHI');
INSERT INTO `Review` (`review_id`, `content`, `overall_rating`, `review_date`, `user_id`, `product_id`, `title`)  
   VALUES (7001, 'Very good stuff', 2, '2019-01-20 11:24:16', 2, 1000, 'Good');
INSERT INTO `Review` (`review_id`, `content`, `overall_rating`, `review_date`, `user_id`, `product_id`, `title`)  
   VALUES (7002, 'ddddd', 4, '2019-01-20 11:25:23', 3, 2, 'dd');
INSERT INTO `Review` (`review_id`, `content`, `overall_rating`, `review_date`, `user_id`, `product_id`, `title`)  
   VALUES (7003, 'aaa', 4, '2019-01-20 11:26:05', 3, 2, 'aa');
INSERT INTO `Review` (`review_id`, `content`, `overall_rating`, `review_date`, `user_id`, `product_id`, `title`)  
   VALUES (7004, 's', 5, '2019-01-20 11:27:08', 3, 2, 's');