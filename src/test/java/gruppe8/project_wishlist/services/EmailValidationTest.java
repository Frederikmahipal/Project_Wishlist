package gruppe8.project_wishlist.services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailValidationTest {

    //Arrange
    EmailValidation validator = new EmailValidation();

    // Act
    String valid = "malt@live.dk";
    String invalid = "malt15@livedk";


    @Test
    void isEmailValid() {


        boolean result = validator.isEmailValid(valid);
        boolean fakeResult = validator.isEmailValid(invalid);


        assertEquals(true,result);
        assertEquals(false,fakeResult);


    }
}