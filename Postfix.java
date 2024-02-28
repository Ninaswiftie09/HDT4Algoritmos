import java.util.ArrayList;
import java.util.Stack;

public class Postfix {
    private Operaciones operaciones = new Operaciones();

    public int solve(ArrayList<Character> elementos) throws Exception {
        Stack<Integer> stack = new Stack<>();
        for (char elemento : elementos) {
            if (Character.isDigit(elemento)) {
                stack.push(Character.getNumericValue(elemento));
            } else {
                int operando2 = stack.pop();
                int operando1 = stack.pop();
                switch (elemento) {
                    case '+':
                        stack.push(operaciones.add(operando1, operando2));
                        break;
                    case '-':
                        stack.push(operaciones.substraction(operando1, operando2));
                        break;
                    case '*':
                        stack.push(operaciones.multiplication(operando1, operando2));
                        break;
                    case '/':
                        stack.push(operaciones.division(operando1, operando2));
                        break;
                    case '%':
                        stack.push(operaciones.residue(operando1, operando2));
                        break;
                    default:
                        throw new Exception("Operación no válida: " + elemento);
                }
            }
        }
        return stack.pop();
    }
}
