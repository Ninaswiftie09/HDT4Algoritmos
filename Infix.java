import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Stack;

//Clase infix 
public class Infix implements Evaluator {
    @Override
    public int evaluateExpression(String expression) throws Exception {
                // Convertir la expresión en una lista de caracteres eliminando los espacios en blanco

        ArrayList<Character> elementos = convertirExpresionALista(expression);
                // Stacks para operadores y operandos

        Stack<Character> operadores = new Stack<>();
        Stack<Integer> operandos = new Stack<>();

        for (char elemento : elementos) {
            if (Character.isDigit(elemento)) {
                operandos.push(Character.getNumericValue(elemento)); // Si es un dígito, agregarlo a la pila de operandos
            } else if (elemento == '(') { // Si es '(' agregarlo a la pila de operadores
                operadores.push(elemento);
            } else if (elemento == ')') {  // Si es ')', realizar operaciones hasta encontrar '('
                while (!operadores.isEmpty() && operadores.peek() != '(') {
                    char operador = operadores.pop();
                    int operando2 = operandos.pop();
                    int operando1 = operandos.pop();
                    operandos.push(realizarOperacion(operador, operando1, operando2));
                }
                operadores.pop(); // Eliminar '('
            } else {
                                // Si es un operador, realizar operaciones hasta que la pila esté vacía o el operador tenga mayor precedencia

                while (!operadores.isEmpty() && precedencia(operadores.peek()) >= precedencia(elemento)) {
                    char operador = operadores.pop();
                    int operando2 = operandos.pop();
                    int operando1 = operandos.pop();
                    operandos.push(realizarOperacion(operador, operando1, operando2));
                }
                operadores.push(elemento); // Agregar el operador a la pila
            }
            }
        }
// Se realizan las operaciones restantes
        while (!operadores.isEmpty()) {
            char operador = operadores.pop();
            int operando2 = operandos.pop();
            int operando1 = operandos.pop();
            operandos.push(realizarOperacion(operador, operando1, operando2));
        }

        int resultado = operandos.pop(); // resultado final para obtener
        guardar(resultado);  // guarda el resultado final en un archivo
        return resultado;
    }
    // Método para convertir la expresión en una lista de caracteres

    private ArrayList<Character> convertirExpresionALista(String expression) {
        ArrayList<Character> elementos = new ArrayList<>();
        for (char c : expression.toCharArray()) {
            if (c != ' ') {
                elementos.add(c);
            }
        }
        return elementos;
    }
    // Método para determinar la precedencia de un operador

    private int precedencia(char operador) {
        switch (operador) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
            case '%':
                return 2;
            default:
                return 0;
        }
    }
    // Método para realizar una operación

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
                throw new Exception("Operación no válida: " + operador);
        }
    }

        // Método para guardar el resultado en un archivo
private void guardar(int resultado) throws Exception {
        BufferedWriter writer = new BufferedWriter(new FileWriter("resultado.txt"));
        writer.write(Integer.toString(resultado));
        writer.close();
    }
}
