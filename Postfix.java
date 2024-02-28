import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Stack;

// Clase que implementa la interfaz Evaluator para evaluar expresiones en notación postfix
public class Postfix implements Evaluator {

    // Método para evaluar la expresión postfix y guardar el resultado en un archivo
    @Override
    public int evaluateExpression(String expression) throws Exception {
        // Convierte la expresión en una lista de caracteres
        ArrayList<Character> elementos = convertirExpresionALista(expression);
        // Crea una pila para almacenar los operandos y resultados
        Stack<Integer> stack = new Stack<>();

        // Itera sobre los elementos de la expresión
        for (char elemento : elementos) {
            // Si el elemento es un dígito, lo apila en la pila
            if (Character.isDigit(elemento)) {
                stack.push(Character.getNumericValue(elemento));
            } else {
                // Si es un operador, desapila dos operandos, realiza la operación y apila el resultado
                int operando2 = stack.pop();
                int operando1 = stack.pop();
                int resultado = realizarOperacion(elemento, operando1, operando2);
                stack.push(resultado);
            }
        }

        // Al finalizar la evaluación, el resultado final queda en la cima de la pila
        int resultadoFinal = stack.pop();
        // Se guarda el resultado en un archivo
        guardarResultado(resultadoFinal);
        // Se devuelve el resultado final
        return resultadoFinal;
    }

    // Método para convertir la expresión en una lista de caracteres
    private ArrayList<Character> convertirExpresionALista(String expression) {
        ArrayList<Character> elementos = new ArrayList<>();
        // Convierte la cadena en un array de caracteres y los añade a la lista
        for (char c : expression.toCharArray()) {
            if (c != ' ') {
                elementos.add(c);
            }
        }
        return elementos;
    }

    // Método para realizar la operación especificada entre dos operandos
    private int realizarOperacion(char operador, int operando1, int operando2) throws Exception {
        switch (operador) {
            case '+':
                return operando1 + operando2;
            case '-':
                return operando1 - operando2;
            case '*':
                return operando1 * operando2;
            case '/':
                return operando1 / operando2;
            case '%':
                return operando1 % operando2;
            default:
                // Si el operador no es válido, lanza una excepción
                throw new Exception("Error: Operador no válido: " + operador);
        }
    }

    // Método para guardar el resultado en un archivo de texto
    private void guardarResultado(int resultado) throws Exception {
        BufferedWriter writer = new BufferedWriter(new FileWriter("resultado.txt"));
        // Escribe el resultado en el archivo y lo cierra
        writer.write(Integer.toString(resultado));
        writer.close();
    }
}
