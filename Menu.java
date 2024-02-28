import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
         //objeto Scanner para leer la entrada del usuario
        Scanner leer = new Scanner(System.in);
        System.out.println("Hola, deseas hacer una operación de infix o postfix?");
        //        // Solicitar al usuario que elija el tipo de operación

        String type = leer.nextLine();
        // Obtener un evaluador según el tipo especificado por el usuario

        Evaluator evaluator = EvaluatorFactory.getEvaluator(type);
                // Verificar si se obtuvo un evaluador válido

        if (evaluator != null) {
                        // Solicitar al usuario que introduzca la operación

            System.out.println("Introduce la operación:");
            String operacion = leer.nextLine();
            try {
                                // Evaluar la operación y mostrar el resultado

                int result = evaluator.evaluateExpression(operacion);
                System.out.println("El resultado es: " + result);
                                // Capturar y manejar cualquier excepción que ocurra durante la evaluación

            } catch (Exception e) {
                            // Mostrar un mensaje de error si no se pudo obtener un evaluador válido

                System.out.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println("Error.");
        }
                // Cerrar el objeto Scanner para liberar recursos

        leer.close();
    }

}
