CREATE TABLE roles
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    create_by INT,
    update_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    update_by INT,
    version INT NOT NULL
);

CREATE TABLE users
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    create_by INT,
    update_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    update_by INT,
    version INT NOT NULL
);

CREATE TABLE products
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DOUBLE NOT NULL,
    create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    create_by INT,
    update_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    update_by INT,
    version INT NOT NULL
);

CREATE TABLE discount_deals
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(255) NOT NULL,
    create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    create_by INT,
    update_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    update_by INT,
    version INT NOT NULL
);

CREATE TABLE user_roles
(
    user_id INT NOT NULL,
    role_id INT NOT NULL,
    create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    create_by INT,
    update_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    update_by INT,
    version INT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_user_roles__users FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_user_roles__roles FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
);

CREATE TABLE baskets
(
    user_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    create_by INT,
    update_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    update_by INT,
    version INT NOT NULL,
    PRIMARY KEY (user_id, product_id),
    CONSTRAINT fk_baskets__users FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_baskets__products FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE
);

CREATE TABLE product_discount_deals
(
    product_id INT NOT NULL,
    discount_deal_id INT NOT NULL,
    create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    create_by INT,
    update_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    update_by INT,
    version INT NOT NULL,
    PRIMARY KEY (product_id, discount_deal_id),
    CONSTRAINT fk_product_discount_deals__discount_deals FOREIGN KEY (discount_deal_id) REFERENCES discount_deals(id) ON DELETE CASCADE,
    CONSTRAINT fk_product_discount_deals__products FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE
);