mysql> use BookStore_SpringApplication;
Reading table information for completion of table and column names
You can turn off this feature to get a quicker startup with -A

Database changed
mysql> show tables;
+---------------------------------------+
| Tables_in_BookStore_SpringApplication |
+---------------------------------------+
| book                                  |
| cart                                  |
| hibernate_sequence                    |
| user_registration                     |
+---------------------------------------+
4 rows in set (0.01 sec)

mysql> select * from book;
+---------+-------------+------------------+------------+-----------+-------+----------+
| book_id | author_name | book_description | book_image | book_name | price | quantity |
+---------+-------------+------------------+------------+-----------+-------+----------+
|       1 | Senthil     | Lean About React | Bright     | React Js  |   400 |        4 |
+---------+-------------+------------------+------------+-----------+-------+----------+
1 row in set (0.00 sec)

mysql> select * from book;
+---------+-------------+-------------------------+------------+-------------+-------+----------+
| book_id | author_name | book_description        | book_image | book_name   | price | quantity |
+---------+-------------+-------------------------+------------+-------------+-------+----------+
|       1 | Senthil     | Lean About React        | Bright     | React Js    |   400 |        4 |
|       2 | Priyanka    | Learn about Annotations | Bright     | Spring Boot |   500 |        5 |
+---------+-------------+-------------------------+------------+-------------+-------+----------+
2 rows in set (0.00 sec)
mysql> select * from book;
+---------+-------------+-------------------------+------------+-------------+-------+----------+
| book_id | author_name | book_description        | book_image | book_name   | price | quantity |
+---------+-------------+-------------------------+------------+-------------+-------+----------+
|       1 | Senthil     | Lean About React        | Bright     | React Js    |   400 |        4 |
|       2 | Chirag      | Learn about Annotations | Bright     | Spring Boot |   500 |        6 |
+---------+-------------+-------------------------+------------+-------------+-------+----------+
2 rows in set (0.00 sec)

mysql> select * from user_registration;
+---------+------------+-----------------------------+------------+-----------+----------+
| user_id | address    | email                       | first_name | last_name | password |
+---------+------------+-----------------------------+------------+-----------+----------+
|       3 | Sector 21d | piyush.verma@bridgelabz.com | Piyush     | Verma     | admin123 |
|       4 | Sector 21d | Nikhil.verma@bridgelabz.com | Nikhil     | Verma     | admin123 |
+---------+------------+-----------------------------+------------+-----------+----------+
2 rows in set (0.00 sec)

mysql> select * from cart;
+---------+----------+---------+---------+
| cart_id | quantity | book_id | user_id |
+---------+----------+---------+---------+
|       5 |        4 |       1 |       3 |
+---------+----------+---------+---------+
1 row in set (0.00 sec)

mysql> select * from user_registration;
+---------+------------+-----------------------------+------------+-----------+-----------+
| user_id | address    | email                       | first_name | last_name | password  |
+---------+------------+-----------------------------+------------+-----------+-----------+
|       3 | Sector 21d | piyush.verma@bridgelabz.com | Piyush     | Verma     | admin123  |
|       4 | Sector 21d | Nikhil.verma@bridgelabz.com | Nikhil     | Verma     | admin123  |
|       6 | Colony     | Pankaj.Verma@gmail.com      | Pankaj     | Verma     | admin1234 |
|       7 | Colony     | Sheetal.sharma@gmail.com    | Sheetal    | Sharma    | admin1234 |
+---------+------------+-----------------------------+------------+-----------+-----------+
4 rows in set (0.00 sec)
mysql> select * from book;
+---------+-------------+-------------------------+------------+-------------+-------+----------+
| book_id | author_name | book_description        | book_image | book_name   | price | quantity |
+---------+-------------+-------------------------+------------+-------------+-------+----------+
|       1 | Senthil     | Lean About React        | Bright     | React Js    |   400 |        4 |
|       2 | Chirag      | Learn about Annotations | Bright     | Spring Boot |   500 |        6 |
+---------+-------------+-------------------------+------------+-------------+-------+----------+
2 rows in set (0.03 sec)

mysql> select * from user_registration;
+---------+------------+-----------------------------+------------+-----------+-----------+
| user_id | address    | email                       | first_name | last_name | password  |
+---------+------------+-----------------------------+------------+-----------+-----------+
|       3 | Sector 21d | piyush.verma@bridgelabz.com | Piyush     | Verma     | admin123  |
|       4 | Sector 21d | Nikhil.verma@bridgelabz.com | Nikhil     | Verma     | admin123  |
|       6 | Colony     | Pankaj.Verma@gmail.com      | Pankaj     | Verma     | admin1234 |
|       7 | Colony     | Sheetal.sharma@gmail.com    | Sheetal    | Sharma    | admin1234 |
+---------+------------+-----------------------------+------------+-----------+-----------+
4 rows in set (0.00 sec)

mysql> select * from book;
+---------+-------------+----------------------------------+------------+----------------------+-------+----------+
| book_id | author_name | book_description                 | book_image | book_name            | price | quantity |
+---------+-------------+----------------------------------+------------+----------------------+-------+----------+
|       1 | Senthil     | Lean About React                 | Bright     | React Js             |   400 |        4 |
|       2 | Chirag      | Learn about Annotations          | Bright     | Spring Boot          |   500 |        6 |
|      10 | Divesh      | Learn about collections          | Bright     | Data Structure       |   600 |        7 |
|      11 | Ram         | Learn about File Handeling       | Bright     | File Handeling       |   400 |        8 |
|      15 | Rajesh      | Learn about Strength of Material | Bright     | Strength Of Material |   550 |       22 |
|      37 | Tina        | Learn English                    | Bright     | English              |   300 |        2 |
+---------+-------------+----------------------------------+------------+----------------------+-------+----------+
6 rows in set (0.00 sec)

mysql> select * from user_registration;
+---------+------------+-----------------------------+------------+-----------+-----------+
| user_id | address    | email                       | first_name | last_name | password  |
+---------+------------+-----------------------------+------------+-----------+-----------+
|       3 | Sector 21d | piyush.verma@bridgelabz.com | Piyush     | Verma     | admin123  |
|       4 | Sector 21d | Nikhil.verma@bridgelabz.com | Nikhil     | Verma     | admin123  |
|       6 | Colony     | Pankaj.Verma@gmail.com      | Pankaj     | Verma     | admin1234 |
|       7 | Colony     | Sheetal.sharma@gmail.com    | Sheetal    | Sharma    | admin1234 |
|      14 | House      | Rakesh.sharma@gmail.com     | Rakesh     | Sharma    | root123   |
|      38 | House      | Ramit.Chauhan@gmail.com     | Ramit      | Chauhan   | root123   |
+---------+------------+-----------------------------+------------+-----------+-----------+
6 rows in set (0.00 sec)

mysql> select * from cart;
+---------+----------+---------+---------+
| cart_id | quantity | book_id | user_id |
+---------+----------+---------+---------+
|       5 |        4 |       1 |       3 |
|      12 |        7 |      10 |       6 |
|      16 |       22 |      15 |      14 |
|      17 |        8 |      11 |       7 |
|      41 |        2 |      37 |      38 |
+---------+----------+---------+---------+
5 rows in set (0.00 sec)

mysql> select * from cart;
+---------+----------+---------+---------+
| cart_id | quantity | book_id | user_id |
+---------+----------+---------+---------+
|       5 |        4 |       1 |       3 |
|      12 |        7 |      10 |       6 |
|      16 |       22 |      15 |      14 |
|      17 |        8 |      11 |       7 |
|      41 |        2 |      37 |      38 |
+---------+----------+---------+---------+
5 rows in set (0.01 sec)

mysql> select * from order;
ERROR 1064 (42000): You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near 'order' at line 1
mysql> select * from book;
+---------+-------------+----------------------------------+------------+----------------------+-------+----------+
| book_id | author_name | book_description                 | book_image | book_name            | price | quantity |
+---------+-------------+----------------------------------+------------+----------------------+-------+----------+
|       1 | Senthil     | Lean About React                 | Bright     | React Js             |   400 |        4 |
|       2 | Chirag      | Learn about Annotations          | Bright     | Spring Boot          |   500 |        6 |
|      10 | Divesh      | Learn about collections          | Bright     | Data Structure       |   600 |        7 |
|      11 | Ram         | Learn about File Handeling       | Bright     | File Handeling       |   400 |        8 |
|      15 | Rajesh      | Learn about Strength of Material | Bright     | Strength Of Material |   550 |       22 |
|      37 | Tina        | Learn English                    | Bright     | English              |   300 |        1 |
+---------+-------------+----------------------------------+------------+----------------------+-------+----------+
6 rows in set (0.00 sec)

mysql> select * from cart;
+---------+----------+---------+---------+
| cart_id | quantity | book_id | user_id |
+---------+----------+---------+---------+
|       5 |        4 |       1 |       3 |
|      12 |        7 |      10 |       6 |
|      16 |       22 |      15 |      14 |
|      17 |        8 |      11 |       7 |
|      41 |        2 |      37 |      38 |
+---------+----------+---------+---------+
5 rows in set (0.00 sec)



