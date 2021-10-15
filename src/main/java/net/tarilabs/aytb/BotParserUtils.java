package net.tarilabs.aytb;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;

public class BotParserUtils {
    
    public static List<Color> parse(String text) {
        CharStream input = CharStreams.fromString(text);
        BotLexer lexer = new BotLexer( input );
        CommonTokenStream tokens = new CommonTokenStream( lexer );
        BotParser parser = new BotParser( tokens );
        ParserRuleContext commands = parser.parse();
        ArrayList<Color> colors = new ArrayList<>();
        BotVisitor<Void> visitor = new OCRToColorsVisitor(colors);
        commands.accept(visitor);
        return colors;
    }
}
