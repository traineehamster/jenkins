package tests;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static config.Credentials.credentials;

public class OwnerTests {

    @Test
    @Tag("owner")
    void readFromPropertiesTest() {
        String login = credentials.login();
        String password = credentials.password();

        System.out.println(login);
        System.out.println(password);


        String message = String.format("I try to login as %s with password %s", login, password);

        System.out.println(message);
    }
}
