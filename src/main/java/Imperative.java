package demo;

import java.util.HashMap;
import java.util.Map;

public class Imperative {

    // Primitives
    public static void demoPrimitives() {
        // Numbers
        System.out.println(1 + 1);

        // Strings
        String myString = "hello" + "world";
        System.out.println("Hello " + myString + "!");

        // Booleans
        System.out.println(1 < 3);
        System.out.println(true);
        System.out.println(false);
        System.out.println(!true);

        return;

        // Unit equivalent -- void method with no return
    }

    // Bindings
    public static void demoBindings() {
        // Immutable binding - final for constants
        final int x = 1;

        // Mutable variable
        int y = 2;
        y = 3;

        // Function-like method call
        sayHello();
        sayHello();
    }

    public static int add(int x, int y) {
        return x + y;
    }

    public static void sayHello() {
        System.out.println("hello");
    }

    // Functions
    public static void demoFunctions() {
        sayHelloTo("Java");

        sayHelloTwice("Java");

        sayHelloThrice("Java");

        int result = add(3, 4);
        System.out.println("Addition result: " + result);
    }

    public static void sayHelloTo(String name) {
        System.out.println("hello " + name);
    }

    public static void sayHelloTwice(String name) {
        System.out.println("hello " + name);
        System.out.println("hello again " + name);
    }

    public static void sayHelloThrice(String name) {
        System.out.println("hello " + name);
        System.out.println("hello again " + name);
        System.out.println("and hello again " + name);
    }

    // Control flow
    public static void demoControlflow() {
        // If-else
        String result = (10 > 14) ? "hello" : "world";
        System.out.println("Conditional result: " + result);

        // While loop
        int n = 10;
        while (n > 0) {
            System.out.println(n);
            n = n - 1;
        }

        // For loop
        for (int m = 0; m < 10; m++) {
            System.out.println(m);
        }

        // Cartesian product and triples
        cartesian(3);
        triples(10, 12);
    }

    public static void cartesian(int size) {
        for (int n = 0; n < size; n++) {
            for (int m = 0; m < size; m++) {
                System.out.println(n + ", " + m);
            }
        }
    }

    public static void triples(int max, int sum) {
        for (int i = 1; i < max; i++) {
            for (int j = i + 1; j < max; j++) {
                for (int k = j + 1; k < max; k++) {
                    if (i + j + k == sum) {
                        System.out.println("(" + i + ", " + j + ", " + k + ")");
                    }
                }
            }
        }
    }

    // Arrays and Mutable Maps
    public static void demoArrays() {
        // Arrays
        String[] arr = new String[10];
        arr[0] = "hello";
        arr[1] = "world";
        System.out.println(arr[0]);

        // Mutable Maps (HashMap)
        Map<String, Integer> m = new HashMap<>();
        m.put("mykey", 17);
        m.put("myother", 99);
        System.out.println(m.getOrDefault("mykey", -1));
    }
}
