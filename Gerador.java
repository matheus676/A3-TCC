import java.util.ArrayList;
import java.util.List;

public class Gerador {
    private static int tempCount = 0;
    private static int labelCount = 0;
    // Lista global onde guardaremos o código gerado
    public static List<Quadrupla> codigo = new ArrayList<>();

    public static String novaTemp() {
        return "t" + (++tempCount);
    }

    public static String novoLabel() {
        return "L" + (++labelCount);
    }

    public static void add(String op, String arg1, String arg2, String res) {
        codigo.add(new Quadrupla(op, arg1, arg2, res));
    }
    
    // Sobrecarga para operações unárias ou labels
    public static void add(String op, String arg1, String res) {
        add(op, arg1, null, res);
    }
}