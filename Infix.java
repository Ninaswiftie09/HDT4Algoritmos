import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Stack;

public class Infix implements Evaluator {
    @Override
    public int evaluateExpression(String expression) throws Exception {
        ArrayList<Character> elementos = convertirExpresionALista(expression);
        Stack<Character> operadores = new Stack<>();
        Stack<Integer> operandos = new Stack<>();

        for (char elemento : elementos) {
            if (Character.isDigit(elemento)) {
                operandos.push(Character.getNumericValue(elemento));
            } else if (elemento == '(') {
                operadores.push(elemento);
            } else if (elemento == ')') {
                while (!operadores.isEmpty() && operadores.peek() != '(') {
                    char operador = operadores.pop();
                    int operando2 = operandos.pop();
                    int operando1 = operandos.pop();
                    operandos.push(realizarOperacion(operador, operando1, operando2));
                }
                operadores.pop();
            } else {
                while (!operadores.isEmpty() && precedencia(operadores.peek()) >= precedencia(elemento)) {
                    char operador = operadores.pop();
                    int operando2 = operandos.pop();
                    int operando1 = operandos.pop();
                    operandos.push(realizarOperacion(operador, operando1, operando2));
                }
                operadores.push(elemento);
            }
        }

        while (!operadores.isEmpty()) {
            char operador = operadores.pop();
            int operando2 = operandos.pop();
            int operando1 = operandos.pop();
            operandos.push(realizarOperacion(operador, operando1, operando2));
        }

        int resultado = operandos.pop();
        guardar(resultado);
        return resultado;
    }

    private ArrayList<Character> convertirExpresionALista(String expression) {
        ArrayList<Character> elementos = new ArrayList<>();
        for (char c : expression.toCharArray()) {
            if (c != ' ') {
                elementos.add(c);
            }
        }
        return elementos;
    }

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

    private void guardar(int resultado) throws Exception {
        BufferedWriter writer = new BufferedWriter(new FileWriter("resultado.txt"));
        writer.write(Integer.toString(resultado));
        writer.close();
    }
}
