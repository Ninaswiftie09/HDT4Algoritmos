//

public class EvaluatorFactory {
      //   * Método estático para obtener un evaluador de expresiones matemáticas.

    public static Evaluator getEvaluator(String type) {
        if (type.equalsIgnoreCase("infix")) {
            return new Infix(); //si el tipo es infix se retorna un objeto de la clase Infix
        } else if (type.equalsIgnoreCase("postfix")) {
            return new Postfix(); // si el tipo no es valido se retorna null
        }
        return null;
    }
}
