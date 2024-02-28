import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Stack;

public class Postfix implements Evaluator {
    @Override
    public int evaluateExpression(String expression) throws Exception {
        ArrayList<Character> elementos = new ArrayList<>();
        for (char c : expression.toCharArray()) {
            if (c != ' ') {
                elementos.add(c);
            }
        }

        Stack<Integer> stack = new Stack<>();
        for (char elemento : elementos) {
            if (Character.isDigit(elemento)) {
                stack.push(Character.getNumericValue(elemento));
            } else {
                int operando2 = stack.pop();
                int operando1 = stack.pop();
                switch (elemento) {
                    case '+':
                        stack.push(operando1 + operando2);
                        break;
                    case '-':
                        stack.push(operando1 - operando2);
                        break;
                    case '*':
                        stack.push(operando1 * operando2);
                        break;
                    case '/':
                        stack.push(operando1 / operando2);
                        break;
                    case '%':
                        stack.push(operando1 % operando2);
                        break;
                    default:
                        throw new Exception("Error: " + elemento);
                }
            }
        }

        int resultado = stack.pop();
        guardarResultado(resultado);
        return resultado;
    }

    private void guardarResultado(int resultado) throws Exception {
        BufferedWriter writer = new BufferedWriter(new FileWriter("resultado.txt"));
        writer.write(Integer.toString(resultado));
        writer.close();
    }
}
