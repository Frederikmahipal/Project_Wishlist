create
    definer = simon@`%` procedure reset_database()
BEGIN
    set foreign_key_checks = 0;

    drop table if exists links;
    drop table if exists sessions;
    drop table if exists userPictures;
    drop table if exists users;
    drop table if exists wishes;
    drop table if exists wishlists;
    drop table if exists wishPictures;

    set foreign_key_checks = 1;

    CREATE TABLE IF NOT EXISTS users (
                                         id INT AUTO_INCREMENT PRIMARY KEY,
                                         email VARCHAR(256) NOT NULL,
                                         fullName VARCHAR(256) NOT NULL,
                                         argon2Password VARCHAR(256) NOT NULL,
                                         created DATETIME NOT NULL,
                                         CONSTRAINT email UNIQUE (email)
    )  CHARSET=UTF8;

    CREATE TABLE IF NOT EXISTS sessions (
                                            id INT AUTO_INCREMENT PRIMARY KEY,
                                            userId INT NOT NULL,
                                            sessionKey VARCHAR(256) NOT NULL,
                                            created DATETIME NOT NULL,
                                            validUntil DATETIME NOT NULL,
                                            invalidated BINARY NOT NULL,
                                            CONSTRAINT sessionKey UNIQUE (sessionKey),
                                            CONSTRAINT FKSessionsUserId FOREIGN KEY (userId)
                                                REFERENCES users (id)
    )  CHARSET=UTF8;

    CREATE TABLE IF NOT EXISTS userPictures (
                                                id INT AUTO_INCREMENT PRIMARY KEY,
                                                userId INT NOT NULL,
                                                created DATETIME NOT NULL,
                                                pictureData BLOB NOT NULL,
                                                CONSTRAINT FKUserPicturesUserId FOREIGN KEY (userId)
                                                    REFERENCES users (id)
    )  CHARSET=UTF8;

    CREATE TABLE IF NOT EXISTS wishlists (
                                             id INT AUTO_INCREMENT PRIMARY KEY,
                                             userId INT NOT NULL,
                                             name VARCHAR(256) NOT NULL,
                                             created DATETIME NOT NULL,
                                             deleted INT NOT NULL,
                                             CONSTRAINT FKWishlistsUserId FOREIGN KEY (userId)
                                                 REFERENCES users (id)
    )  CHARSET=UTF8;

    CREATE TABLE IF NOT EXISTS links (
                                         id INT AUTO_INCREMENT PRIMARY KEY,
                                         wishlistId INT NOT NULL,
                                         resourcePath VARCHAR(256) NOT NULL,
                                         created DATETIME NOT NULL,
                                         CONSTRAINT FKLinksWishlistId FOREIGN KEY (wishlistId)
                                             REFERENCES wishlists (id)
    )  CHARSET=UTF8;

    CREATE TABLE IF NOT EXISTS wishes (
                                          id INT AUTO_INCREMENT PRIMARY KEY,
                                          wishlistId INT NOT NULL,
                                          created DATETIME NOT NULL,
                                          title VARCHAR(256) NOT NULL,
                                          price DECIMAL(9 , 2 ) NOT NULL,
                                          url VARCHAR(2048) NOT NULL,
                                          note VARCHAR(5000) NOT NULL,
                                          CONSTRAINT FKWishesWishlistId FOREIGN KEY (wishlistId)
                                              REFERENCES wishlists (id)
    )  CHARSET=UTF8;

    CREATE TABLE IF NOT EXISTS wishPictures (
                                                id INT AUTO_INCREMENT PRIMARY KEY,
                                                wishId INT NOT NULL,
                                                created DATETIME NOT NULL,
                                                pictureData BLOB NOT NULL,
                                                CONSTRAINT FKWishPicturesWishId FOREIGN KEY (wishId)
                                                    REFERENCES wishes (id)
    )  CHARSET=UTF8;


END;