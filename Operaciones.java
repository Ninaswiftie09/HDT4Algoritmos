public class Operaciones {
    // método para sumar dos enteros
    public static int add(int n1, int n2) {
        return n1 + n2;
    }

    // Método para restar dos enteros
    public static int substraction(int n1, int n2) {
        return n1 - n2;
    }

    // Método para multiplicar dos enteros
    public static int multiplication(int n1, int n2) {
        return n1 * n2;
    }

    // Método para dividir dos enteros
    public static int division(int n1, int n2) {
        return n1 / n2;
    }

    // Método para obtener el residuo de la división de dos números enteros
    public static int residue(int n1, int n2) throws Exception {
        if (n2 == 0) {
            throw new Exception("División por cero, indeterminado");
        }
        return n1 % n2;
    }
}
