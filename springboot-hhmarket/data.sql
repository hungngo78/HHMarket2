
INSERT INTO `user` (`user_id`, `user_name`, `password`, `Email`, `first_name`, `last_name`, `address`, `city`, `state`, `zip_code`) 
   VALUES ('1', 'hung', '1234', 'abc@gmail.com', 'Hung First Name', 'Ngo', '621 Beach Blvd', 'Westminster', 'CA', '98765');
INSERT INTO `user` (`user_id`, `user_name`, `password`, `Email`, `first_name`, `last_name`, `address`, `city`, `state`, `zip_code`) 
   VALUES ('2', 'huong', '1234', 'huong@gmail.com', 'Huong', 'Nguyen', '621 Beach Blvd', 'Westminster', 'CA', '98765');
INSERT INTO `user` (`user_id`, `user_name`, `password`, `Email`, `first_name`, `last_name`, `address`, `city`, `state`, `zip_code`) 
   VALUES ('3', 'huongquadeo', '1234', 'aaa@gmail.com', 'Huong1 ', 'Nguyen 1', '621 Beach Blvd', 'Westminster', 'CA', '98765');

INSERT INTO `Review` (`review_id`, `content`, `overall_rating`, `review_date`, `user_id`, `product_id`, `title`) 
   VALUES (1, 'hone was a croc. When I first received the dress, it was storn. They never responded any of my emails and it was a waste of 20 dollars', 1, '2018-09-02 00:00:00', 2, 1000, 'Horrible');
INSERT INTO `Review` (`review_id`, `content`, `overall_rating`, `review_date`, `user_id`, `product_id`, `title`) 
   VALUES (2, 'Little heavy, it\'s a nice pants, like the Rose and metal. ', 4, '2018-10-01 00:00:00', 2, 1000, 'Like rose gold');
INSERT INTO `Review` (`review_id`, `content`, `overall_rating`, `review_date`, `user_id`, `product_id`, `title`)  
   VALUES (3, 'The shirt we purchased arrived in great shape. Exactly as advertise', 5, '2017-04-04 00:00:00', 2, 1000, 'Great');
INSERT INTO `Review` (`review_id`, `content`, `overall_rating`, `review_date`, `user_id`, `product_id`, `title`)  
   VALUES (4, 'Very good stuff', 2, '2019-01-20 11:24:16', 2, 1000, 'Good');
INSERT INTO `Review` (`review_id`, `content`, `overall_rating`, `review_date`, `user_id`, `product_id`, `title`)  
   VALUES (5, 'I like it', 4, '2019-01-20 11:25:23', 3, 2, 'So good');

	
-------------------
INSERT INTO `category` (`category_id`, `name`, `picture`, `description`, `main_category_id`) 
   VALUES (7, 'Womens Rompers & Jumpsuits', 'category7.jpg', 'aa', 1);
INSERT INTO `category` (`category_id`, `name`, `picture`, `description`, `main_category_id`) 
   VALUES (11, 'Sweaters', 'category11.jpg', 'aa1', 1);
INSERT INTO `category` (`category_id`, `name`, `picture`, `description`, `main_category_id`) 
   VALUES (12, 'Womens Wear-to-Work Dresses', 'category12.jpg', 'wear in office', 1);
INSERT INTO `category` (`category_id`, `name`, `picture`, `description`, `main_category_id`) 
   VALUES (13, 'Womens Cocktail & Party Dresses', 'category13.jpg', 'wear in party', 1);
INSERT INTO `category` (`category_id`, `name`, `picture`, `description`, `main_category_id`) 
   VALUES (14, 'Womens Night Out Dresses', 'category14.jpg', 'hang out with boyfriend', 1);
INSERT INTO `category` (`category_id`, `name`, `picture`, `description`, `main_category_id`) 
   VALUES (15, 'Womens Activewear Leggings, Pants & Capris', 'category15.jpg', 'do excercise', 1);

---------------------------------------------------
INSERT INTO `product` (`product_id`, `name`, `description`, `category_id`, `user_id`)
   VALUES (1000, 'Sexydance', 'Women\'s Summer Holiday Beach Bardot Button Through Ladies Sling Long Smock Sun Dress', 7, 2);
INSERT INTO `product` (`product_id`, `name`, `description`, `category_id`, `user_id`)
   VALUES (1003, 'HIMONE', 'Women Summer Autumn FloralPrint Boho Skirt Dress Short Sleeve Party Bodycon Long Maxi Dress Beach', 7, 2);   
INSERT INTO `product` (`product_id`, `name`, `description`, `category_id`, `user_id`)

INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5000, 'Green', 'M', 'image1.jpg,image1_1.jpg,image1_2.jpg', 48, 24, 1000);
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5001, 'White', 'M', 'image1.jpg,image2.jpg,image3.jpg', 28, 10, 1000);
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5002, 'Black Sunflower', 'L', 'image1.jpg', 40, 30, 1000);
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5003, 'Floral', '2XL', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 8, 20, 1000);
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5013, 'Floral', '3XL', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 8, 10, 1000);
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5014, 'Floral', '4XL', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 9, 10, 1000);
 
 
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5006, 'Green (Short Sleeve)', '29Lx31W', 'image1.jpg,image2.jpg', 48, 100, 1003);
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5007, 'Pink (Short Sleeve)', '4XL', 'image1.jpg,image2.jpg', 10, 50, 1003);
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5008, 'Polka dot(Short Sleeve', 'XXL', 'image1.jpg,image2.jpg', 10, 50, 1003);
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5009, 'Red (Short Sleeve)', 'XL', 'image1.jpg,image2.jpg', 10, 50, 1003);

--------------------------------------------------------------------------
INSERT INTO `product` (`product_id`, `name`, `description`, `category_id`, `user_id`)
   VALUES (1001, 'LA Gypsy', 'Women\'s Brushed Faux Wrap Sweater', 11, 2);	
INSERT INTO `product` (`product_id`, `name`, `description`, `category_id`, `user_id`)
   VALUES (1002, 'Jacquline Design Studio', 'Women\'s Tape Yarn Cardigan', 11, 2);	
   
   
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5004, 'Charcoal', '3XL', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 9, 10, 1001);
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5005, 'Ivory', '4XL', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 20, 5, 1001);


INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5010, 'Black', 'M', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 10, 50, 1002);
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5011, 'Oatmeal', 'M', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 10, 50, 1002);

   
--------------------------------
INSERT INTO `product` (`product_id`, `name`, `description`, `category_id`, `user_id`)
   VALUES (1004, 'Black Label by Evan Picone', 'Women\'s Essential Wrap Dress', 12, 2);
INSERT INTO `product` (`product_id`, `name`, `description`, `category_id`, `user_id`)
   VALUES (1005, 'WRAPPER', 'Women\'s Button Detail Tank Dress', 12, 2);
INSERT INTO `product` (`product_id`, `name`, `description`, `category_id`, `user_id`)
   VALUES (1006, 'Unique Bargains', 'Women Contrast Doll Collar Short Sleeves Flare Dress Green S', 12, 2);
INSERT INTO `product` (`product_id`, `name`, `description`, `category_id`, `user_id`)
   VALUES (1007, 'White Mark', 'Women\'s Trellis Wrap Dress', 12, 2);
INSERT INTO `product` (`product_id`, `name`, `description`, `category_id`, `user_id`)
   VALUES (1008, 'Black Label by Evan Picone', 'Women\'s Light Contrast Dress', 12, 2);
   
   
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5015, 'Black', '4', 'image1.jpg,image2.jpg,image3.jpg', 15.50, 30, 1004);  
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5016, 'Black', '6', 'image1.jpg,image2.jpg,image3.jpg', 15.50, 30, 1004);  
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5017, 'Black', '8', 'image1.jpg,image2.jpg,image3.jpg', 15.50, 30, 1004);  
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5018, 'Black', '10', 'image1.jpg,image2.jpg,image3.jpg', 15.50, 30, 1004);  
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5019, 'Black', '12', 'image1.jpg,image2.jpg,image3.jpg', 15.50, 30, 1004); 
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5020, 'Black', '14', 'image1.jpg,image2.jpg,image3.jpg', 15.50, 30, 1004);  
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5021, 'Black', '16', 'image1.jpg,image2.jpg,image3.jpg', 15.50, 30, 1004);  
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5022, 'Black', '18', 'image1.jpg,image2.jpg,image3.jpg', 15.50, 30, 1004);   

INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5023, 'Black', 'M', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 15.98, 30, 1005); 
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5024, 'Black', 'L', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 15.98, 30, 1005);
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5025, 'Black', 'XL', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 15.98, 30, 1005);
   
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5026, 'Black', 'S', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 25, 30, 1006); 
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5027, 'Black', 'M', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 25, 30, 1006); 
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5028, 'Black', 'L', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 25, 30, 1006); 
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5029, 'Black', 'XL', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 25, 30, 1006); 
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5030, 'Green', 'S', 'image1.jpg,image2.jpg,image3.jpg', 25, 30, 1006); 
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5031, 'Green', 'M', 'image1.jpg,image2.jpg,image3.jpg', 25, 30, 1006); 
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5032, 'Green', 'L', 'image1.jpg,image2.jpg,image3.jpg', 25, 30, 1006); 
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5033, 'Green', 'XL', 'image1.jpg,image2.jpg,image3.jpg', 25, 30, 1006); 
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5034, 'Pink', 'S', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 25, 30, 1006); 
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5035, 'Pink', 'M', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 25, 30, 1006); 
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5036, 'Pink', 'L', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 25, 30, 1006); 
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5037, 'Pink', 'XL', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 25, 30, 1006); 
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5038, 'Red', 'S', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 25, 30, 1006); 
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5039, 'Red', 'M', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 25, 30, 1006); 
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5040, 'Red', 'L', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 25, 30, 1006); 
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5041, 'Red', 'XL', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 25, 30, 1006);  
 
 
 INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5042, 'Black', 'S', 'image1.jpg,image2.jpg,image3.jpg', 29.99, 30, 1007);  
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5043, 'Black', 'M', 'image1.jpg,image2.jpg,image3.jpg', 29.99, 30, 1007);  
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5044, 'Black', 'L', 'image1.jpg,image2.jpg,image3.jpg', 29.99, 30, 1007);  
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5045, 'Black', 'XL', 'image1.jpg,image2.jpg,image3.jpg', 29.99, 30, 1007);  
 
 
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5046, 'Khaki_Black', '4', 'image1.jpg,image2.jpg,image3.jpg', 41.50, 30, 1008);  
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5047, 'Khaki_Black', '6', 'image1.jpg,image2.jpg,image3.jpg', 41.50, 30, 1008);  
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5048, 'Khaki_Black', '8', 'image1.jpg,image2.jpg,image3.jpg', 41.50, 30, 1008);  
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5049, 'Khaki_Black', '10', 'image1.jpg,image2.jpg,image3.jpg', 41.50, 30, 1008); 
 INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5050, 'Khaki_Black', '12', 'image1.jpg,image2.jpg,image3.jpg', 41.50, 30, 1008);  
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5051, 'Khaki_Black', '14', 'image1.jpg,image2.jpg,image3.jpg', 41.50, 30, 1008);  
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5052, 'Khaki_Black', '16', 'image1.jpg,image2.jpg,image3.jpg', 41.50, 30, 1008); 
 
 
------------------

INSERT INTO `product` (`product_id`, `name`, `description`, `category_id`, `user_id`)
   VALUES (1009, 'Doublju', 'Doublju Women\'s A-Line Summer Short Sleeve Off The Shoulder Cocktail Skater Dresses CORAL XL', 13, 2);
INSERT INTO `product` (`product_id`, `name`, `description`, `category_id`, `user_id`)
   VALUES (1010, 'Miusol', 'MIUSOL Women\'s Vintage Square Neck Floral Lace 2/3 Sleeve Cocktail Swing Dresses for Women (Navy Blue L)', 13, 2);
INSERT INTO `product` (`product_id`, `name`, `description`, `category_id`, `user_id`)
   VALUES (1011, 'Miusol', 'MIUSOL Women\'s Vintage Evening Cocktail Party Dresses for Women', 13, 2);
INSERT INTO `product` (`product_id`, `name`, `description`, `category_id`, `user_id`)
   VALUES (1012, 'OUMY', 'OUMY Women Short Sleeve Lace Cocktail Evening Party Mini Dress', 13, 2);
INSERT INTO `product` (`product_id`, `name`, `description`, `category_id`, `user_id`)
   VALUES (1013, 'Miusol', 'MIUSOL Women\'s Vintage Evening Cocktail Party Dresses for Women', 13, 2);
   
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5053, 'BLACKPINK', 'S', 'image1.jpg,image2.jpg,image3.jpg', 9.99, 30, 1009);  
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5054, 'BLACKPINK', 'M', 'image1.jpg,image2.jpg,image3.jpg', 9.99, 30, 1009); 
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5055, 'BLACKPINK', 'L', 'image1.jpg,image2.jpg,image3.jpg', 9.99, 30, 1009);  
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5056, 'BLACKROSE', 'S', 'image1.jpg,image2.jpg,image3.jpg', 9.99, 30, 1009);   
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5057, 'BLACKROSE', 'M', 'image1.jpg,image2.jpg,image3.jpg', 9.99, 30, 1009); 
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5058, 'BLACKROSE', 'L', 'image1.jpg,image2.jpg,image3.jpg', 9.99, 30, 1009);  
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5059, 'FLORALPINK', 'S', 'image1.jpg,image2.jpg,image3.jpg', 9.99, 30, 1009);   
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5060, 'FLORALPINK', 'M', 'image1.jpg,image2.jpg,image3.jpg', 9.99, 30, 1009); 
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5061, 'FLORALPINK', 'L', 'image1.jpg,image2.jpg,image3.jpg', 9.99, 30, 1009); 
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5062, 'NAVYPINK', 'S', 'image1.jpg,image2.jpg,image3.jpg', 9.99, 30, 1009);   
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5063, 'NAVYPINK', 'M', 'image1.jpg,image2.jpg,image3.jpg', 9.99, 30, 1009); 
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5064, 'NAVYPINK', 'L', 'image1.jpg,image2.jpg,image3.jpg', 9.99, 30, 1009); 
 
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5065, 'Burgundy', 'XS', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg,image5.jpg,image6.jpg,image7.jpg,image8.jpg', 32.99, 30, 1010); 
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5066, 'Burgundy', 'S', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg,image5.jpg,image6.jpg,image7.jpg,image8.jpg', 32.99, 30, 1010); 
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5067, 'Burgundy', 'M', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg,image5.jpg,image6.jpg,image7.jpg,image8.jpg', 32.99, 30, 1010); 
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5068, 'Burgundy', 'L', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg,image5.jpg,image6.jpg,image7.jpg,image8.jpg', 32.99, 30, 1010); 
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5069, 'Burgundy', 'XL', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg,image5.jpg,image6.jpg,image7.jpg,image8.jpg', 32.99, 30, 1010);  
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5070, 'Green', 'XS', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 32.99, 30, 1010); 
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5071, 'Green', 'S', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 32.99, 30, 1010); 
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5072, 'Green', 'M', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 32.99, 30, 1010); 
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5073, 'Green', 'L', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 32.99, 30, 1010); 
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5074, 'Green', 'XL', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 32.99, 30, 1010); 
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5075, 'Navy Blue', 'XS', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 32.99, 30, 1010); 
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5076, 'Navy Blue', 'S', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 32.99, 30, 1010); 
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5077, 'Navy Blue', 'M', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 32.99, 30, 1010); 
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5078, 'Navy Blue', 'L', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 32.99, 30, 1010); 
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5079, 'Navy Blue', 'XL', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 32.99, 30, 1010);  

INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5080, 'Black', 'S', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg,image5.jpg', 32.99, 30, 1011);   
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5081, 'Black', 'M', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg,image5.jpg', 32.99, 30, 1011);     
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5082, 'Black', 'L', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg,image5.jpg', 32.99, 30, 1011);     
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5083, 'Black', 'XL', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg,image5.jpg', 32.99, 30, 1011);     
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5084, 'Cyan', 'S', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg,image5.jpg', 15.99, 30, 1011);   
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5085, 'Cyan', 'M', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg,image5.jpg', 15.99, 30, 1011);     
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5086, 'Cyan', 'L', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg,image5.jpg', 15.99, 30, 1011);     
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5087, 'Cyan', 'XL', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg,image5.jpg', 15.99, 30, 1011);
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5088, 'Navy Blue', 'S', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg,image5.jpg', 32.99, 30, 1011);   
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5089, 'Navy Blue', 'M', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg,image5.jpg', 32.99, 30, 1011);     
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5090, 'Navy Blue', 'L', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg,image5.jpg', 32.99, 30, 1011);     
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5091, 'Navy Blue', 'XL', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg,image5.jpg', 32.99, 30, 1011);  
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5092, 'White', 'S', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg,image5.jpg', 15.99, 30, 1011);   
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5093, 'White', 'M', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg,image5.jpg', 15.99, 30, 1011);     
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5094, 'White', 'L', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg,image5.jpg', 15.99, 30, 1011);     
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5095, 'White', 'XL', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg,image5.jpg', 15.99, 30, 1011); 

INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5096, 'Black', 'S', 'image1.jpg,image2.jpg,image3.jpg', 15.99, 30, 1012);
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5097, 'Black', 'M', 'image1.jpg,image2.jpg,image3.jpg', 15.99, 30, 1012);
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5098, 'Black', 'L', 'image1.jpg,image2.jpg,image3.jpg', 15.99, 30, 1012);
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5099, 'Black', 'XL', 'image1.jpg,image2.jpg,image3.jpg', 15.99, 30, 1012);
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5100, 'Black', '2XL', 'image1.jpg,image2.jpg,image3.jpg', 15.99, 30, 1012);
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5101, 'Blue', 'S', 'image1.jpg,image2.jpg', 15.99, 30, 1012);
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5102, 'Blue', 'M', 'image1.jpg,image2.jpg', 15.99, 30, 1012);
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5103, 'Blue', 'L', 'image1.jpg,image2.jpg', 15.99, 30, 1012);
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5104, 'Blue', 'XL', 'image1.jpg,image2.jpg', 15.99, 30, 1012);
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5105, 'Blue', '2XL', 'image1.jpg,image2.jpg', 15.99, 30, 1012); 
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5106, 'Red', 'S', 'image1.jpg,image2.jpg,image3.jpg', 15.99, 30, 1012);
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5107, 'Red', 'M', 'image1.jpg,image2.jpg,image3.jpg', 15.99, 30, 1012);
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5108, 'Red', 'L', 'image1.jpg,image2.jpg,image3.jpg', 15.99, 30, 1012);
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5109, 'Red', 'XL', 'image1.jpg,image2.jpg,image3.jpg', 15.99, 30, 1012);
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5110, 'Red', '2XL', 'image1.jpg,image2.jpg,image3.jpg', 15.99, 30, 1012); 
 
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5111, 'Green', 'S', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 34.99, 30, 1013);
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5112, 'Green', 'M', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 34.99, 30, 1013);
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5113, 'Green', 'L', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 34.99, 30, 1013);
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5114, 'Green', 'XL', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 34.99, 30, 1013);
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5115, 'Green', '2XL', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 34.99, 30, 1013); 
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5116, 'Navy Blue', 'S', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 34.99, 30, 1013);
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5117, 'Navy Blue', 'M', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 34.99, 30, 1013);
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5118, 'Navy Blue', 'L', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 34.99, 30, 1013);
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5119, 'Navy Blue', 'XL', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 34.99, 30, 1013);
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5120, 'Navy Blue', '2XL', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 34.99, 30, 1013); 
 
 ------------------------------------------------
 
INSERT INTO `product` (`product_id`, `name`, `description`, `category_id`, `user_id`)
   VALUES (1014, 'ModaXpressOnline', 'Womens Juniors Sexy Off Shoulder Long Sleeve Stretchy Blue Bodycon Blue Dress 30092R', 14, 2);
INSERT INTO `product` (`product_id`, `name`, `description`, `category_id`, `user_id`)
   VALUES (1015, 'Mighty Fine', 'The Power Rangers Sexy Tunic Tank Dresses', 14, 2);
INSERT INTO `product` (`product_id`, `name`, `description`, `category_id`, `user_id`)
   VALUES (1016, 'Eye Candy', 'Eye Candy Juniors\' Spaghetti Strap Bodycon Rib Dresses', 14, 2);
INSERT INTO `product` (`product_id`, `name`, `description`, `category_id`, `user_id`)
   VALUES (1017, 'White Mark', 'Women\'s Sheath Mini Dress', 14, 2);
INSERT INTO `product` (`product_id`, `name`, `description`, `category_id`, `user_id`)
   VALUES (1018, 'Emmalise', 'Emmalise Women\'s Sexy Off Shoulder Bodycon Mini Dress Short Sleeves', 14, 2);
   
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5121, 'Blue', 'S', 'image1.jpg', 19.99, 30, 1014); 
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5122, 'Blue', 'M', 'image1.jpg', 19.99, 30, 1014); 
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5123, 'Blue', 'L', 'image1.jpg', 19.99, 30, 1014);  
 
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5124, 'Green', 'S', 'image1.jpg', 19.95, 30, 1015); 
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5125, 'Green', 'M', 'image1.jpg', 19.95, 30, 1015); 
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5126, 'Green', 'L', 'image1.jpg', 19.95, 30, 1015);   
 
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5127, 'Black Combo', 'M', 'image1.jpg,image2.jpg,image3.jpg', 4.50, 30, 1016);    
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5128, 'Black Combo', 'L', 'image1.jpg,image2.jpg,image3.jpg', 4.50, 30, 1016);  
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5129, 'Black Combo', 'XL', 'image1.jpg,image2.jpg,image3.jpg', 4.50, 30, 1016);  
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5130, 'Gold Spice', 'M', 'image1.jpg,image2.jpg,image3.jpg', 4.50, 30, 1016);    
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5131, 'Gold Spice', 'L', 'image1.jpg,image2.jpg,image3.jpg', 4.50, 30, 1016);  
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5132, 'Gold Spice', 'XL', 'image1.jpg,image2.jpg,image3.jpg', 4.50, 30, 1016);   

INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5133, 'Black', 'S', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 20.00, 30, 1017);   
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5134, 'Black', 'M', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 20.00, 30, 1017);   
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5135, 'Black', 'L', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 20.00, 30, 1017);   
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5136, 'Black', 'XL', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 20.00, 30, 1017);   
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5137, 'Off-White', 'S', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 20.00, 30, 1017);   
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5138, 'Off-White', 'M', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 20.00, 30, 1017);   
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5139, 'Off-White', 'L', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 20.00, 30, 1017);   
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5140, 'Off-White', 'XL', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 20.00, 30, 1017);   
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5141, 'Pink', 'S', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 20.00, 30, 1017);   
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5142, 'Pink', 'M', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 20.00, 30, 1017);   
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5143, 'Pink', 'L', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 20.00, 30, 1017);   
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5144, 'Pink', 'XL', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 20.00, 30, 1017);  

INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5145, 'Black', 'S', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg,image5.jpg', 8.94, 30, 1018); 
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5146, 'Black', 'M', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg,image5.jpg', 8.94, 30, 1018);  
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5147, 'Black', 'L', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg,image5.jpg', 8.94, 30, 1018);  
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5148, 'Dark Rose', 'S', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg,image5.jpg', 8.94, 30, 1018);  
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5149, 'Dark Rose', 'M', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg,image5.jpg', 8.94, 30, 1018); 
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5150, 'Dark Rose', 'L', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg,image5.jpg', 8.94, 30, 1018);  
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5151, 'Khaki', 'S', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg,image5.jpg', 8.94, 30, 1018);  
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5152, 'Khaki', 'M', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg,image5.jpg', 8.94, 30, 1018);  
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5153, 'Khaki', 'L', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg,image5.jpg', 8.94, 30, 1018);   
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5154, 'Light Pink', 'S', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg,image5.jpg', 8.94, 30, 1018);  
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5155, 'Light Pink', 'M', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg,image5.jpg', 8.94, 30, 1018);  
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5156, 'Light Pink', 'L', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg,image5.jpg', 8.94, 30, 1018);    
 
 ---------------------

INSERT INTO `product` (`product_id`, `name`, `description`, `category_id`, `user_id`)
   VALUES (1019, 'Avia', 'Women\'s Active High Rise Performance Capri Legging', 15, 2);
INSERT INTO `product` (`product_id`, `name`, `description`, `category_id`, `user_id`)
   VALUES (1020, 'Time and Tru', 'Women\'s Jegging Capri', 15, 2);
INSERT INTO `product` (`product_id`, `name`, `description`, `category_id`, `user_id`)
   VALUES (1021, 'RealSize', 'Women\'s Stretch Denim Pull On Capri', 15, 2);
INSERT INTO `product` (`product_id`, `name`, `description`, `category_id`, `user_id`)
   VALUES (1022, 'RealSize', 'Women\'s Stretch Pull On Capri', 15, 2);   
   
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5157, 'Black', 'XS', 'image1.jpg,image2.jpg', 11.50, 30, 1019);    
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5158, 'Black', 'S', 'image1.jpg,image2.jpg', 11.50, 30, 1019);   
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5159, 'Black', 'M', 'image1.jpg,image2.jpg', 12.96, 30, 1019);   
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5160, 'Black', 'L', 'image1.jpg,image2.jpg', 11.50, 30, 1019);   
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5161, 'Black', 'XL', 'image1.jpg,image2.jpg', 11.50, 30, 1019);    
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5162, 'Melange', 'XS', 'image1.jpg,image2.jpg,image3.jpg', 11.50, 30, 1019);    
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5163, 'Melange', 'S', 'image1.jpg,image2.jpg,image3.jpg', 11.50, 30, 1019);   
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5164, 'Melange', 'M', 'image1.jpg,image2.jpg,image3.jpg', 12.96, 30, 1019);   
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5165, 'Melange', 'L', 'image1.jpg,image2.jpg,image3.jpg', 11.50, 30, 1019);   
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5166, 'Melange', 'XL', 'image1.jpg,image2.jpg,image3.jpg', 11.50, 30, 1019);     
   
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5167, 'Arctic White', 'S', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 11.96, 30, 1020);  
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5168, 'Arctic White', 'M', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 11.96, 30, 1020);  
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5169, 'Arctic White', 'L', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 11.96, 30, 1020);  
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5170, 'Arctic White', 'XL', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 11.96, 30, 1020);   
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5171, 'BLCOVF', 'S', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 11.96, 30, 1020);  
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5172, 'BLCOVF', 'M', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 11.96, 30, 1020);  
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5173, 'BLCOVF', 'L', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 11.96, 30, 1020);  
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5174, 'BLCOVF', 'XL', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 11.96, 30, 1020);     
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5175, 'Light Denim', 'S', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 11.96, 30, 1020);  
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5176, 'Light Denim', 'M', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 11.96, 30, 1020);  
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5177, 'Light Denim', 'L', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 11.96, 30, 1020);  
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5178, 'Light Denim', 'XL', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 11.96, 30, 1020); 
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5179, 'Pink', 'S', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 11.96, 30, 1020);  
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5180, 'Pink', 'M', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 11.96, 30, 1020);  
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5181, 'Pink', 'L', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 11.96, 30, 1020);  
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5182, 'Pink', 'XL', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 11.96, 30, 1020);     
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5183, 'PLMLVS', 'S', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 11.96, 30, 1020);  
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5184, 'PLMLVS', 'M', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 11.96, 30, 1020);  
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5185, 'PLMLVS', 'L', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 11.96, 30, 1020);  
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5186, 'PLMLVS', 'XL', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 11.96, 30, 1020);    
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5187, 'SKYWHR', 'S', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 11.96, 30, 1020);  
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5188, 'SKYWHR', 'M', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 11.96, 30, 1020);  
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5189, 'SKYWHR', 'L', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 11.96, 30, 1020);  
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5190, 'SKYWHR', 'XL', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 11.96, 30, 1020);
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5191, 'WHTFRL', 'S', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 11.96, 30, 1020);  
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5192, 'WHTFRL', 'M', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 11.96, 30, 1020);  
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5193, 'WHTFRL', 'L', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 11.96, 30, 1020);  
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5194, 'WHTFRL', 'XL', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 11.96, 30, 1020); 
 
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5195, 'Deep Blue', 'S', 'image1.jpg,image2.jpg', 12.44, 30, 1021); 
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5196, 'Deep Blue', 'M', 'image1.jpg,image2.jpg', 12.44, 30, 1021);  
  
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5197, 'Grey', 'S', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 12.44, 30, 1022);  
INSERT INTO `product_details` (`product_details_id`, `color`, `size`, `picture`, `price`, `amount`, `product_id`) 
 VALUES (5198, 'Grey', 'M', 'image1.jpg,image2.jpg,image3.jpg,image4.jpg', 12.44, 30, 1022);   
 