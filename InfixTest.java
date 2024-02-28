import static org.junit.Assert.*;
import org.junit.Test;

public class InfixTest {

    @Test
    public void testEvaluateExpression() {
        Infix infix = new Infix();

        // Dado
        String expression1 = "2 + 3 * 4"; // Resultado esperado: 14
        String expression2 = "3 * (5 - 2) / 2"; // Resultado esperado: 4
        String expression3 = "8 + (6 / 2) - 1"; // Resultado esperado: 11

        try {
            // Cuando
            int result1 = infix.evaluateExpression(expression1);
            int result2 = infix.evaluateExpression(expression2);
            int result3 = infix.evaluateExpression(expression3);

            // Entonces
            assertEquals(14, result1);
            assertEquals(4, result2);
            assertEquals(11, result3);
        } catch (Exception e) {
            fail("Excepción lanzada: " + e.getMessage());
        }
    }

    @Test(expected = Exception.class)
    public void testInvalidOperation() throws Exception {
        Infix infix = new Infix();
        // Dado
        String invalidExpression = "2 ^ 3"; // Operación no válida

        // Cuando
        infix.evaluateExpression(invalidExpression);

        // Entonces: Se espera que lance una excepción
    }
}
