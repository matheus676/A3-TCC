import java.lang.reflect.Type;
import java.util.HashMap;

import java_cup.runtime.Symbol;


public class Main {
    public static void main(String[] args) throws Exception {
      Scanner lexer = new Scanner(new java.io.FileReader(args[0]));
      parser p = new parser(lexer);            
      Symbol s = p.parse();
      HashMap configs = (HashMap) s.value;
   }
}
