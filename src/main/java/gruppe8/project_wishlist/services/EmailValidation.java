package gruppe8.project_wishlist.services;

public class EmailValidation {

    private final String REGEXLETTERS = "(?i).*[a-z].*";

    public boolean isEmailValid(String email){

        if (email.contains("@") && email.contains(".") && email.matches(REGEXLETTERS)){
            System.out.println("Valid email");
            return true;
        }
        else
            System.out.println("Invalid email. Must contain @, . and letters");
            return false;
    }
}
