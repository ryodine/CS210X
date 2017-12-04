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
        String rterm = rng.nextInt(10) + ((random > 0.5)? "x" : "");
        if (random < 0.1) {
            return rterm;
        } else if (random > 0.85) {
            return rterm + " + " + createExpression();
        } else if (random > 0.7) {
            return rterm + " * " + createExpression();
        } else if (random > 0.55) {
            return createExpression() + " * " + rterm;
        } else if (random > 0.4) {
            return createExpression() + " + " + rterm;
        } else {
            return "(" + createExpression() + ")";
        }

    }
}
