USE test;

DROP TABLE IF EXISTS Frequency;
DROP TABLE IF EXISTS Employee;
DROP TABLE IF EXISTS Positions;
DROP TABLE IF EXISTS Order_recipe;
DROP TABLE IF EXISTS Recipe_instruction;
DROP TABLE IF EXISTS Recipe_ingredient;
DROP TABLE IF EXISTS Stock;
DROP TABLE IF EXISTS Ingredient;
DROP TABLE IF EXISTS Recipe;
DROP TABLE IF EXISTS Orders;
DROP TABLE IF EXISTS Company;
DROP TABLE IF EXISTS Private_customer;
DROP TABLE IF EXISTS Customer;
DROP TABLE IF EXISTS Postal;


CREATE TABLE Postal(
    zip INTEGER(4) NOT NULL,
    postal VARCHAR(30) NOT NULL,
    PRIMARY KEY(zip)
);

CREATE TABLE Customer(
customer_id INTEGER AUTO_INCREMENT NOT NULL,
address VARCHAR(30) NOT NULL,
zip INTEGER(4) NOT NULL,
email_address VARCHAR(40),
phone INTEGER(8) UNIQUE NOT NULL,
PRIMARY KEY(customer_id),
FOREIGN KEY(zip) REFERENCES Postal(zip)
);

CREATE TABLE Private_customer(
    private_id INTEGER PRIMARY KEY AUTO_INCREMENT,
    last_name VARCHAR(30) NOT NULL,
    first_name VARCHAR(30) NOT NULL,
    customer_id INTEGER NOT NULL,
    FOREIGN KEY(customer_id) REFERENCES Customer(customer_id)
);

CREATE TABLE Company(
    company_id INTEGER PRIMARY KEY AUTO_INCREMENT,
    company_name VARCHAR(30) NOT NULL,
    customer_id INTEGER NOT NULL,
    FOREIGN KEY(customer_id) REFERENCES Customer(customer_id)
);

CREATE TABLE Orders(
    order_id INTEGER PRIMARY KEY AUTO_INCREMENT,
    payment_status BOOLEAN NOT NULL,
    order_date DATE NOT NULL,
    delivery_date DATE NOT NULL,
    delivery_time DOUBLE NOT NULL,
    address VARCHAR(30),
    zip INTEGER(4),
    take_away BOOLEAN NOT NULL,
    other_request VARCHAR(30),
    made BOOLEAN NOT NULL,
    ingredients_purchased BOOLEAN NOT NULL,
    delivered BOOLEAN NOT NULL,
    customer_id INTEGER NOT NULL,
    FOREIGN KEY(customer_id) REFERENCES Customer(customer_id)
);

CREATE TABLE Recipe(
    recipe_name VARCHAR(30) UNIQUE NOT NULL PRIMARY KEY,
    recipe_type ENUM ('NONE', 'VEGAN', 'VEGETARIAN', 'PESCATARIAN', 'MEATLOVER') NOT NULL,
    diet_type ENUM ('NONE', 'LOW_FAT', 'LOW_CARB', 'LOW_CALORIE', 'GLUTEN_FREE') NOT NULL,
    price DOUBLE NOT NULL
);

CREATE TABLE Ingredient(
    ingredient_name VARCHAR(30) UNIQUE NOT NULL PRIMARY KEY
);

CREATE TABLE Stock(
    stock_id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    ingredient_name VARCHAR(30) UNIQUE NOT NULL,
    quantity DOUBLE NOT NULL ,
    measurement VARCHAR(20) NOT NULL,
    FOREIGN KEY (ingredient_name) REFERENCES Ingredient(ingredient_name)
);

CREATE TABLE Recipe_ingredient (
    recipe_name VARCHAR(30) NOT NULL,
    ingredient_name VARCHAR(30) UNIQUE NOT NULL,
    quantity DOUBLE NOT NULL,
    PRIMARY KEY (recipe_name,ingredient_name),
    CONSTRAINT recipeIng_fk FOREIGN KEY (recipe_name)
    REFERENCES Recipe(recipe_name)
        ON DELETE CASCADE,
    CONSTRAINT recipeIng_fk2 FOREIGN KEY (ingredient_name)
    REFERENCES Ingredient(ingredient_name)
        ON DELETE CASCADE
);

CREATE TABLE Recipe_instruction(
    recipe_name VARCHAR(30) NOT NULL,
    step_number INTEGER NOT NULL,
    description VARCHAR(500),
    PRIMARY KEY (recipe_name, step_number),
    CONSTRAINT recipeIns_fk FOREIGN KEY (recipe_name)
    REFERENCES Recipe(recipe_name)
        ON DELETE CASCADE
);

CREATE TABLE Order_recipe(
    order_id INTEGER NOT NULL,
    recipe_name VARCHAR(30) NOT NULL,
    quantity INTEGER NOT NULL,
    PRIMARY KEY(order_id, recipe_name),
    FOREIGN KEY(order_id) REFERENCES Orders(order_id),
    FOREIGN KEY(recipe_name) REFERENCES Recipe(recipe_name)
);

CREATE TABLE Positions(
    position_id INTEGER AUTO_INCREMENT NOT NULL,
    position_name VARCHAR(10) NOT NULL,
    PRIMARY KEY(position_id)
);

CREATE TABLE Employee(
    emp_id INTEGER AUTO_INCREMENT PRIMARY KEY,
    last_name VARCHAR(30) NOT NULL,
    first_name VARCHAR(30) NOT NULL,
    phone INTEGER(8) UNIQUE NOT NULL,
    date_of_employment DATE,
    position_id INTEGER NOT NULL,
    username VARCHAR(10) NOT NULL,
    password VARCHAR(15) NOT NULL,
    email_address VARCHAR(30),
    CONSTRAINT employee_fk FOREIGN KEY(position_id)
    REFERENCES Positions(position_id)
);

CREATE TABLE Frequency(
    frequency_id INTEGER NOT NULL AUTO_INCREMENT,
    frequency_day INTEGER NOT NULL,
    frequency_month INTEGER NOT NULL,
    PRIMARY KEY (frequency_id)
);

CREATE TABLE Subscription(
    subs_id INTEGER AUTO_INCREMENT NOT NULL,
    order_id INTEGER NOT NULL,
    frequency_id INTEGER NOT NULL,
    PRIMARY KEY(subs_id),
    FOREIGN KEY(order_id) REFERENCES Orders(order_id),
    FOREIGN KEY(frequency_id) REFERENCES Frequency(frequency_id)
);