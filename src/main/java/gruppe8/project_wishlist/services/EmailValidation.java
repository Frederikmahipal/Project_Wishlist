package gruppe8.project_wishlist.services;

import org.springframework.stereotype.Service;

@Service
public class EmailValidation {
    EmailValidation() {
    }

    public boolean isEmailValid(String email) {
        String userPart;
        String domainPart;
        boolean hasAtSign = email.contains("@");
        boolean hasUserPart = false;
        boolean hasDomainPart = false;

        if (hasAtSign) {
            userPart = email.split("@")[0];
            domainPart = email.split("@")[1];
            hasUserPart = userPart.length() > 0;
            hasDomainPart = domainPart.contains(".") && domainPart.length() > 1;
        }

        return hasAtSign && hasUserPart && hasDomainPart;
    }
}
