import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;

import java_cup.runtime.Symbol;


public class Main {
    public static void main(String[] args) throws Exception {
      Scanner lexer = new Scanner(new java.io.FileReader(args[0]));
      parser p = new parser(lexer);            
      Symbol s = p.parse();
      
      try(BufferedWriter writer = new BufferedWriter(new FileWriter("conversao.xml"))){
         writer.write((String)s.value);
         writer.close();
      } catch(IOException e){
         System.err.println("Ocorreu um erro ao escrever no arquivo: " + e.getMessage());
      }

   }
}
