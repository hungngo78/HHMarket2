#-------------------------------------------------- Account ------------------------------------------------
1. login
 - Description: authenticate user 
 - http://localhost:8080/account/login?username={username}&password={password}
 - Method: GET
 - Resquest params :  
   . username
   . password
 - Response: 
   {
      "userId": 2,
      "userName": "hung",
      "password": "123",
      "email": "hung@yahoo.com",
      "firstName": "Hung Tran Minh",
      "lastName": "Ngo",
      "address": "621 Beach Blvd",
      "city": "Westminster",
      "state": "CA",
      "zipCode": "92563"
   }

2. add
 - Description: add user 
 - http://localhost:8080/account/add
 - Method: POST
 - Request 
   {
       "UserId": 2,
       "UserName": "hung",
       "Password": "123",
       "Email": "hung@yahoo.com",
       "FirstName": "Hung Tran Minh",
       "LastName": "Ngo",
       "Address": "621 Beach Blvd",
       "City": "Westminster",
       "State": "CA",
       "ZipCode": "92563"
   }
 - Response: 
   {
      "userId": 2,
      "userName": "hung",
      "password": "123",
      "email": "hung@yahoo.com",
      "firstName": "Hung Tran Minh",
      "lastName": "Ngo",
      "address": "621 Beach Blvd",
      "city": "Westminster",
      "state": "CA",
      "zipCode": "92563"
   }
   
3. update
 - Description: update user 
 - http://localhost:8080/account/update
 - Method: POST
 - Request 
   {
       "UserId": 2,
       "UserName": "hung",
       "Password": "123",
       "Email": "hung@yahoo.com",
       "FirstName": "Hung Tran Minh",
       "LastName": "Ngo",
       "Address": "621 Beach Blvd",
       "City": "Westminster",
       "State": "CA",
       "ZipCode": "92563"
   }
 - Response: 
   {
      "userId": 2,
      "userName": "hung",
      "password": "123",
      "email": "hung@yahoo.com",
      "firstName": "Hung Tran Minh",
      "lastName": "Ngo",
      "address": "621 Beach Blvd",
      "city": "Westminster",
      "state": "CA",
      "zipCode": "92563"
   }

4. get
 - Description: get user information by UserId 
 - http://localhost:8080/account/get/{userId}
 - Method: GET
 - Path variable: userId
 - Response: 
   {
      "userId": 2,
      "userName": "hung",
      "password": "123",
      "email": "hung@yahoo.com",
      "firstName": "Hung Tran Minh",
      "lastName": "Ngo",
      "address": "6311 Willow Cir",
      "city": "Westminster",
      "state": "CA",
      "zipCode": "92683"
   }
   
#-------------------------------------------------- Product ------------------------------------------------
5. get_all_categories
 - Description: get all categories 
 - http://localhost:8080/category/get_all_categories
 - Method: GET
 - Path variable:  None  
 - Response: 
   [
    {
        "categoryId": 7,
        "name": "Electric",
        "picture": "aa.jpg",
        "description": "aa"
    },
    {
        "categoryId": 11,
        "name": "Mens clothing",
        "picture": "aa1.jpg",
        "description": "aa1"
    },
    {
        "categoryId": 12,
        "name": "ABC",
        "picture": "aa2.jpg",
        "description": "aa2"
    }
  ]
 
6.  get_products_by_category
 - Description: get all product of a given category
 - http://localhost:8080/category/get_products_by_category/{categoryId}
 - Method: GET
 - Path variable:  categoryId  
 - Response: 
   [
    {
        "productId": 1001,
        "productionName": "Fruit of the Loom",
        "description": "Fruit of the Loom Big Men's Dual Defense EverSoft Crew Sweatshirt",
        "minPrice": 8,
        "maxPrice": 20,
        "picture": "image1.jpg,image2.jpg,image3.jpg",
        "color": "Navy",
	"reviewNumber": 20,
        "overrallRating": "2.4"
    }
   ]

7. get_production_by_productId
 - Description: get information of a given productId
 - http://localhost:8080/category/get_production_by_productId/{productId}
 - Method: GET
 - Path variable:  productId  
 - Response: 
    {
        "productId": 1001,
        "productionName": "Fruit of the Loom",
        "description": "Fruit of the Loom Big Men's Dual Defense EverSoft Crew Sweatshirt",
        "minPrice": 8,
        "maxPrice": 20,
        "picture": "image1.jpg,image2.jpg,image3.jpg",
        "color": "Navy",
	"reviewNumber": 20,
        "overrallRating": "2.4"
    }
    
8. get_production_by_product_details_id
 - Description: get information of product by a given productDetailsId
 - http://localhost:8080/category/get_production_by_product_details_id/{productDetailsId}
 - Method: GET
 - Path variable:  productId  
 - Response: 
    {
        "productId": 1001,
        "productionName": "Fruit of the Loom",
        "description": "Fruit of the Loom Big Men's Dual Defense EverSoft Crew Sweatshirt",
        "minPrice": 8,
        "maxPrice": 20,
        "picture": "image1.jpg,image2.jpg,image3.jpg",
        "color": "Navy",
	"reviewNumber": 20,
        "overrallRating": "2.4"
    }

9.  get_product_title_by_id
 - Description: get title of a given product 
 - http://localhost:8080/category/get_product_by_id/{productId}
 - Method: GET
 - Path variable:  productId  
 - Response: 
   {
    "title": "Electric > Samsung galaxy 16GB",
    "productId": 1000
   }
  
  
10.  get_product_details_by_productId
 - Description: get all product details of a given productId
 - http://localhost:8080/category/get_product_details_by_productId/{productId}
 - Method: GET
 - Path variable:  productId  
 - Response: 
   [
    {
        "productDetailsId": 5003,
        "color": "Navy",
        "size": "2XL",
        "picture": "image1.jpg,image2.jpg,image3.jpg",
        "price": 8,
        "amount": 20,
        "productId": 1001,
        "productionName": "Fruit of the Loom",
        "description":  "Fruit of the Loom Big Men's Dual Defense EverSoft Crew Sweatshirt"
    },
    {
        "productDetailsId": 5004,
        "color": "Charcoal Heather",
        "size": "3XL",
        "picture": "image1.jpg,image2.jpg,image3.jpg",
        "price": 9,
        "amount": 10,
        "productId": 1001,
        "productionName": "Fruit of the Loom",
        "description":  "Fruit of the Loom Big Men's Dual Defense EverSoft Crew Sweatshirt"
    }
   ]

11. get_product_details_by_product_details_id
 - Description: get product details by a given productDetailsId
 - http://localhost:8080/category/get_product_details_by_product_details_id/{productDetailsId}
 - Method: GET
 - Path variable:  productDetailsId  
 - Response: 
    {
        "productDetailsId": 5003,
        "color": "Navy",
        "size": "2XL",
        "picture": "image1.jpg,image2.jpg,image3.jpg",
        "price": 8,
        "amount": 20,
        "productId": 1001,
        "productionName": "Fruit of the Loom",
        "description":  "Fruit of the Loom Big Men's Dual Defense EverSoft Crew Sweatshirt"
    }



#-------------------------------------------------- Shopping ------------------------------------------------   
12. add_cart
 - Description: add new shopping cart
 - http://localhost:8080/shopping/add_cart
 - Method: POST
 - Request: 
   {
  	  "UserId": 123,
  	  "DateOpen": 2019-06-07
   }
 - Response: 
   {
   	  "cartId": "123",
   	  "dateOpen": "2019-06-07"     	     	   
   }


13. get_cart_item_numer
 - Description: get number of items in shopping cart
 - http://localhost:8080/shopping/get_cart_item_numer/{userId}
 - Method: GET
 - Path variable:  userId  
 - Response: 
   {
    "number_of_cart_items": 1
   }
 
14. get_cart_items
 - Description: get all items in shopping cart
 - http://localhost:8080/shopping/get_cart_items/{userId}
 - Method: GET
 - Path variable:  userId  
 - Response: 
   [
    {
        "cartId": 1,
        "cartDetailsId": 6004,
        "amount": 4,
        "type": 0,
        "extendedPrice": 480,						--> gia tai thoi diem mua
        "productId": 1000,
        "productDetailsId": 5000,
        "color": "Red",
        "size": "29Lx31W",
        "picture": "image1.jpg,image1_1.jpg,image1_2.jpg",
        "price": 48,									--> $/unit
        "totalAmountProduction": 26,					--> tong so luong cua san pham nay trong kho
        "name": "Samsung galaxy 16GB"
    },
    {
        "cartId": 1,
        "cartDetailsId": 6005,
        "amount": 5,
        "type": 0,
        "extendedPrice": 240,
        "productId": 1000,
        "productDetailsId": 5001,
        "color": "Blue",
        "size": "28Lx30W",
        "picture": "image2.jpg",
        "price": 28,
        "totalAmountProduction": 10,
        "name": "Samsung galaxy 16GB"
    }
   ]
 
15. get_cart_item
 - Description: get cart item of a given pair of values: cartId and productDetailsId
 - http://localhost:8080/shopping/get_cart_item/{cartId}/{productDetailsId}
 - Method: GET
 - Path variable:  
   . cartId
   . productDetailsId
 - Response: 
    {
        "cartId": 1,
        "cartDetailsId": 6004,
        "amount": 4,
        "type": 0,
        "extendedPrice": 480,									--> gia tai thoi diem mua
        "productId": 1000,
        "productDetailsId": 5000,
        "color": "Red",
        "size": "29Lx31W",
        "picture": "image1.jpg,image1_1.jpg,image1_2.jpg",
        "price": 48,											--> $/unit
        "totalAmountProduction": 26,							--> tong so luong cua san pham nay trong kho
        "name": "Samsung galaxy 16GB"
    }
   
16. add_shoping_item
 - Description: add shopping item into shopping cart
 - http://localhost:8080/shopping/add_shoping_item
 - Method: POST
 - Path variable:  Non
 - Request body:
   {
       "UserId": 2,
	   "ProductDetailsId": "5002",
	   "Amount":5,
	   "ExtendedPrice":100,
	   "Type":0
   } 
 - Response:
   {
       "cartDetailsId": 4,
       "amount": 5,
       "extendedPrice": 100,
       "type": 0
   }
   
17. update_quantity
 - Description: update quantity of shopping item in cart
 - http://localhost:8080/shopping/update_quantity
 - Method: PUT
 - Path variable:  Non
 - Request body:
   {
	   "CartDetailsId": 4,
	   "Amount":50				
   } 
 - Response:
   {
       "cartDetailsId": 4,
       "amount": 50,
       "extendedPrice": 100,
       "type": 0
   }
  
18. remove cart item
 - Description: update quantity of shopping item in cart
 - http://localhost:8080/shopping/remove_from_shopping_cart/{cartDetailsId}
 - Method: DELETE
 - Path variable:  cartDetailsId
 - Request body: Non  
 - Response:
   {
       123
   }

19. order
 - Description: make the order
 - http://localhost:8080/shopping/order/{userId}
 - Method: POST
 - Path variable:  userId
 - Request body: Non
 - Response 
   {
      "orderId": 17,
      "orderDate": "2019-06-03",
      "status": 0,
      "deliveryDate": "2019-06-03",
      "deliveryFee": 0,
      "note": "Note of user"
   }
   
#-------------------------------------------------- Review ------------------------------------------------  
20. add_review_item
 - Description: add new review
 - http://localhost:8080/review/add_review_item
 - Method: POST
 - Path variable:  Non
 - Request:
    {
    	"UserId": "2",
	    "ProductId": "1000",
	    "Title": "Te qua",
	    "Content": "San pham gi te qua, chua xai da hu",
	    "OverallRating": "2",
	    "ReviewDate": "2019-06-01"
   }  
 - Response: 
   {
       "reviewId": 21,
       "title": "Te qua",
       "content": "San pham gi te qua, chua xai da hu",
       "overallRating": 2,
       "reviewDate": "2019-06-03"
   }
    
21. get_reviews_by_productId
 - Description: get all reviews of a given product
 - http://localhost:8080/review/get_reviews_by_productId/{productId}
 - Method: GET
 - Path variable:  productId  
 - Response: 
   [
    {
        "ReviewId": 4021,
        "ProductId": 11,
        "UserName": "hung"
        "Title": "AA",
        "Content": "AAAAAAAAA",
        "OverallRating": 2,
        "ReviewDate": "2019-01-13"
    },
    {
        "ReviewId": 4022,
        "ProductId": 11,
        "UserName": "huong"
        "Title": "BBB",
        "Content": "BBBBBBBBBBBBBBBBBBB",
        "OverallRating": 4,
        "ReviewDate": "2019-01-13"
    }
   ]
 
22. get_rating_by_productId
 - Description: get all rating of a given product
 - http://localhost:8080/review/get_rating_by_productId/{productId}
 - Method: GET
 - Path variable:  productId  
 - Response: 
   {
        "oneStarReviewNumber": 0,
        "twoStarReviewNumber": 1,
        "threeStarReviewNumber": 2,
        "fourStarReviewNumber": 2,
        "fiveStarReviewNumber": 1,
         "oneStarReviewPercent": 0,
         "twoStarReviewPercent": 20,
        "threeStarReviewPercent": 20,
        "fourStarReviewPercent": 20,
        "fiveStarReviewPercent": 40,
         "overrallRating": "3.5"
    }
    

#-------------------------------------------------- Search ------------------------------------------------  
23. search
 - Description: search products by categoryId and searching criteria
 - http://localhost:8080/category/searching?categoryId={categoryId}&criteria=samsung+clothing
 - Method: GET
 - Request Param:  
    . productId  (not required)
    . search 
 - Response:
   [
    {
        "productId": 1000,
        "productionName": "Samsung galaxy 16GB",
        "description": "64GB, 4GB RAM, IP68 Water and Dust Proof, Camera: 12 MP, Front: 8 MP, Fast",
        "picture": "image1.jpg,image1_1.jpg,image1_2.jpg",
        "color": "Red",
        "minPrice": 8.0,
	"maxPrice": 48.0,
        "categoryName": "Electric",
        "categoryDescription": "aa",
        "reviewNumber": 5,
        "overrallRating": "2.8"
    },
    {
        "productId": 1001,
        "productionName": "Fruit of the Loom",
        "description": "Fruit of the Loom Big Men's Dual Defense EverSoft Crew Sweatshirt",
        "picture": "image1.jpg,image2.jpg,image3.jpg",
        "color": "Navy",
        "price": 8,
        "categoryName": "Mens clothing",
        "categoryDescription": "aa1",
        "reviewNumber": 6,
        "overrallRating": "3.5"
    }
   ]




 