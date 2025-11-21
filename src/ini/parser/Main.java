package ini.parser;

import java.io.FileReader;

public class Main {
    public static void main(String[] args) {
        try {
            String filePath = "config.ini";
            if (args.length > 0) {
                filePath = args[0];
            }

            Lexer lexer = new Lexer(new FileReader(filePath));
            Parser parser = new Parser(lexer);
            java_cup.runtime.Symbol result = parser.parse();

            System.out.println("Parsing complete. No errors found.");

            if (result != null && result.value instanceof java.util.HashMap) {
                java.util.HashMap<String, Object> config = (java.util.HashMap<String, Object>) result.value;
                for (java.util.Map.Entry<String, Object> sectionEntry : config.entrySet()) {
                    System.out.println("[" + sectionEntry.getKey() + "]");
                    if (sectionEntry.getValue() instanceof java.util.HashMap) {
                        java.util.HashMap<String, Object> properties = (java.util.HashMap<String, Object>) sectionEntry.getValue();
                        for (java.util.Map.Entry<String, Object> propertyEntry : properties.entrySet()) {
                            System.out.println(propertyEntry.getKey() + " = " + propertyEntry.getValue());
                        }
                    }
                }
            }

        } catch (Exception e) {
            System.err.println("Error during parsing: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
