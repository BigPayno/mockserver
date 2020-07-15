package org.payno.mock.server;

import org.joor.Reflect;
import org.payno.mock.server.core.MockConfigurer.Default;

public class MockTest {
    public static class User{
        String name;
        String pass;
    }

    public static void main(String[] args) {
        User user = Reflect.on(User.class).create().get();
        System.out.println(user);
    }
}
