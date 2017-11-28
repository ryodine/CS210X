import java.util.Random;

public class ExpressionGenerator {

    private static final Random rng = new Random();

    public static void main(String[] args) {
        System.out.println(createExpression());
        System.out.println(createExpression());
        System.out.println(createExpression());
        System.out.println(createExpression());
        System.out.println(createExpression());
        System.out.println(createExpression());
        System.out.println(createExpression());
        System.out.println(createExpression());
        System.out.println(createExpression());
        System.out.println(createExpression());
        System.out.println(createExpression());
        System.out.println(createExpression());
        System.out.println(createExpression());
        System.out.println(createExpression());

    }

    public static String createExpression() {
        Double random = rng.nextDouble();
        if (random < 0.1) {
            return "x";
        } else if (random > 0.85) {
            return "x + " + createExpression();
        } else if (random > 0.7) {
            return "x * " + createExpression();
        } else if (random > 0.55) {
            return createExpression() + " * x";
        } else if (random > 0.4) {
            return createExpression() + " + x";
        } else {
            return "(" + createExpression() + ")";
        }

    }
}
