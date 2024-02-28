public class EvaluatorFactory {
    public static ExpressionEvaluator getEvaluator(String type) {
        if (type.equalsIgnoreCase("infix")) {
            return new Infix();
        } else if (type.equalsIgnoreCase("postfix")) {
            return new Postfix();
        }
        return null;
    }
}
