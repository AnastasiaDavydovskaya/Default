package by.tms.davydovskaya.todo;

import java.util.Base64;

public class Test {

    public static void main(String[] args) {
        String value = "Hello World!";
        String encoded = Base64.getEncoder().withoutPadding().encodeToString(value.getBytes());
        System.out.println(encoded);
        String decoded = new String(Base64.getDecoder().decode(encoded));
        System.out.println(decoded);
    }
}
