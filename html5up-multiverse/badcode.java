public class BadCodeExample {

    // This method intentionally has issues for SonarQube to detect
    public static void main(String[] args) {
        System.out.println("Testing SonarQube analysis...");

        int x = 0;
        int y = 0;

        // Unused variable
        int unusedVar = 123;

        // Hardcoded credentials (security issue)
        String password = "admin123";

        // Empty catch block
        try {
            int result = x / y; // Division by zero (bug)
        } catch (Exception e) {
            // ignored
        }

        // Duplicate code
        if (x == 0) {
            System.out.println("x is zero");
        }
        if (x == 0) {
            System.out.println("x is zero");
        }

        // Infinite loop
        while (true) {
            break; // useless loop
        }

        System.out.println("End of bad code example.");
    }
}
