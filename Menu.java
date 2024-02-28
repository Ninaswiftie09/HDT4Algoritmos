import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        System.out.println("Hola, deseas hacer una operación de infix o postfix?");
        String type = leer.nextLine();

        Evaluator evaluator = EvaluatorFactory.getEvaluator(type);
        if (evaluator != null) {
            System.out.println("Introduce la operación:");
            String operacion = leer.nextLine();
            try {
                int result = evaluator.evaluateExpression(operacion);
                System.out.println("El resultado es: " + result);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println("Error.");
        }
        leer.close();
    }

}
