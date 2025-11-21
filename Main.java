import java.io.*;

class Main {
 public static void main(String[] args) throws Exception {
      String input = "zHello";
      StringReader reader = new StringReader(input);
      Scanner lexer = new Scanner(reader);
      Parser p = new Parser(lexer);
      p.parse();
   }
}