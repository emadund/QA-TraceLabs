package test.db;

import org.bouncycastle.math.raw.Mod;

public class MainTest {
    public static void main(String[] args) throws Exception {
        String email;

        email = ModulServices.getEmail("negative235");
        System.out.println(email);

    }
}
