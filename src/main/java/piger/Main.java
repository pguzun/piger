package piger;

import com.google.common.io.Resources;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.ParserRuleContext;
import ro.pguzun.piger.Java8Lexer;
import ro.pguzun.piger.Java8Parser;

import java.io.InputStream;

public class Main {

    public static void main(String[] args) {
        
        new Main().parseFile("HelloWorld.java");

    }

    public void parseFile(String f) {
        System.out.println(getClass().getResource("."));
        
        try (InputStream in= getClass().getResourceAsStream("HelloWorld.java")){
            // Create a scanner that reads from the input stream passed to us
            
            Lexer lexer = new Java8Lexer(CharStreams.fromStream(in));

            CommonTokenStream tokens = new CommonTokenStream(lexer);
            // long start = System.currentTimeMillis();
            // tokens.fill(); // load all and check time
            // long stop = System.currentTimeMillis();
            // lexerTime += stop-start;

            // Create a parser that reads from the scanner
            Java8Parser parser = new Java8Parser(tokens);
//            if (diag)
//                parser.addErrorListener(new DiagnosticErrorListener());
//            if (bail)
//                parser.setErrorHandler(new BailErrorStrategy());
//            if (SLL)
//                parser.getInterpreter().setPredictionMode(PredictionMode.SLL);

            // start parsing at the compilationUnit rule
            ParserRuleContext t = parser.compilationUnit();
//            if (notree)
//                parser.setBuildParseTree(false);
//            if (gui)
//                t.inspect(parser);
//            if (printTree)
                System.out.println(t.toStringTree(parser));
        } catch (Exception e) {
            System.err.println("parser exception: " + e);
            e.printStackTrace(); // so we can get stack trace
        }
    }
}
