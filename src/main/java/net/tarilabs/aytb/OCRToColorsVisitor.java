package net.tarilabs.aytb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.Color;
import net.tarilabs.aytb.BotParser.Color_commandContext;
import net.tarilabs.aytb.BotParser.Rgb_commandContext;

public class OCRToColorsVisitor extends BotBaseVisitor<Void> {

    private static final Logger LOG = LoggerFactory.getLogger(OCRToColorsVisitor.class);

    private static final Map<String, Color> cssColors = new HashMap<>();
    private List<Color> colors;

    public OCRToColorsVisitor(List<Color> colors) {
        this.colors = colors;
    }

    public List<Color> getColors() {
        return colors;
    }

    @Override
    public Void visitColor_command(Color_commandContext ctx) {
        try {
            Color color = cssColors.get(ctx.color.getText().toUpperCase());
            if (color != null) {
                colors.add(color);
            } else {
                LOG.warn("Not a valid color: {}", ctx.color.getText());
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return super.visitColor_command(ctx);
    }

    @Override
    public Void visitRgb_command(Rgb_commandContext ctx) {
        try {
            String rgbText = ctx.rgb.getText().toUpperCase().replace('O','0');
            Color color = Color.decode("#" + rgbText);
            colors.add(color);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return super.visitRgb_command(ctx);
    }

    static {
        cssColors.put("PINK", java.awt.Color.decode("#FFC0CB"));
        cssColors.put("LIGHTPINK", java.awt.Color.decode("#FFB6C1"));
        cssColors.put("HOTPINK", java.awt.Color.decode("#FF69B4"));
        cssColors.put("DEEPPINK", java.awt.Color.decode("#FF1493"));
        cssColors.put("PALEVIOLETRED", java.awt.Color.decode("#DB7093"));
        cssColors.put("MEDIUMVIOLETRED", java.awt.Color.decode("#C71585"));
        cssColors.put("LAVENDER", java.awt.Color.decode("#E6E6FA"));
        cssColors.put("THISTLE", java.awt.Color.decode("#D8BFD8"));
        cssColors.put("PLUM", java.awt.Color.decode("#DDA0DD"));
        cssColors.put("ORCHID", java.awt.Color.decode("#DA70D6"));
        cssColors.put("VIOLET", java.awt.Color.decode("#EE82EE"));
        cssColors.put("FUCHSIA", java.awt.Color.decode("#FF00FF"));
        cssColors.put("MAGENTA", java.awt.Color.decode("#FF00FF"));
        cssColors.put("MEDIUMORCHID", java.awt.Color.decode("#BA55D3"));
        cssColors.put("DARKORCHID", java.awt.Color.decode("#9932CC"));
        cssColors.put("DARKVIOLET", java.awt.Color.decode("#9400D3"));
        cssColors.put("BLUEVIOLET", java.awt.Color.decode("#8A2BE2"));
        cssColors.put("DARKMAGENTA", java.awt.Color.decode("#8B008B"));
        cssColors.put("PURPLE", java.awt.Color.decode("#800080"));
        cssColors.put("MEDIUMPURPLE", java.awt.Color.decode("#9370DB"));
        cssColors.put("MEDIUMSLATEBLUE", java.awt.Color.decode("#7B68EE"));
        cssColors.put("SLATEBLUE", java.awt.Color.decode("#6A5ACD"));
        cssColors.put("DARKSLATEBLUE", java.awt.Color.decode("#483D8B"));
        cssColors.put("REBECCAPURPLE", java.awt.Color.decode("#663399"));
        cssColors.put("INDIGO", java.awt.Color.decode("#4B0082"));
        cssColors.put("LIGHTSALMON", java.awt.Color.decode("#FFA07A"));
        cssColors.put("SALMON", java.awt.Color.decode("#FA8072"));
        cssColors.put("DARKSALMON", java.awt.Color.decode("#E9967A"));
        cssColors.put("LIGHTCORAL", java.awt.Color.decode("#F08080"));
        cssColors.put("INDIANRED", java.awt.Color.decode("#CD5C5C"));
        cssColors.put("CRIMSON", java.awt.Color.decode("#DC143C"));
        cssColors.put("RED", java.awt.Color.decode("#FF0000"));
        cssColors.put("FIREBRICK", java.awt.Color.decode("#B22222"));
        cssColors.put("DARKRED", java.awt.Color.decode("#8B0000"));
        cssColors.put("ORANGE", java.awt.Color.decode("#FFA500"));
        cssColors.put("DARKORANGE", java.awt.Color.decode("#FF8C00"));
        cssColors.put("CORAL", java.awt.Color.decode("#FF7F50"));
        cssColors.put("TOMATO", java.awt.Color.decode("#FF6347"));
        cssColors.put("ORANGERED", java.awt.Color.decode("#FF4500"));
        cssColors.put("GOLD", java.awt.Color.decode("#FFD700"));
        cssColors.put("YELLOW", java.awt.Color.decode("#FFFF00"));
        cssColors.put("LIGHTYELLOW", java.awt.Color.decode("#FFFFE0"));
        cssColors.put("LEMONCHIFFON", java.awt.Color.decode("#FFFACD"));
        cssColors.put("LIGHTGOLDENRODYELLOW", java.awt.Color.decode("#FAFAD2"));
        cssColors.put("PAPAYAWHIP", java.awt.Color.decode("#FFEFD5"));
        cssColors.put("MOCCASIN", java.awt.Color.decode("#FFE4B5"));
        cssColors.put("PEACHPUFF", java.awt.Color.decode("#FFDAB9"));
        cssColors.put("PALEGOLDENROD", java.awt.Color.decode("#EEE8AA"));
        cssColors.put("KHAKI", java.awt.Color.decode("#F0E68C"));
        cssColors.put("DARKKHAKI", java.awt.Color.decode("#BDB76B"));
        cssColors.put("GREENYELLOW", java.awt.Color.decode("#ADFF2F"));
        cssColors.put("CHARTREUSE", java.awt.Color.decode("#7FFF00"));
        cssColors.put("LAWNGREEN", java.awt.Color.decode("#7CFC00"));
        cssColors.put("LIME", java.awt.Color.decode("#00FF00"));
        cssColors.put("LIMEGREEN", java.awt.Color.decode("#32CD32"));
        cssColors.put("PALEGREEN", java.awt.Color.decode("#98FB98"));
        cssColors.put("LIGHTGREEN", java.awt.Color.decode("#90EE90"));
        cssColors.put("MEDIUMSPRINGGREEN", java.awt.Color.decode("#00FA9A"));
        cssColors.put("SPRINGGREEN", java.awt.Color.decode("#00FF7F"));
        cssColors.put("MEDIUMSEAGREEN", java.awt.Color.decode("#3CB371"));
        cssColors.put("SEAGREEN", java.awt.Color.decode("#2E8B57"));
        cssColors.put("FORESTGREEN", java.awt.Color.decode("#228B22"));
        cssColors.put("GREEN", java.awt.Color.decode("#00FF00")); // CHANGED
        cssColors.put("DARKGREEN", java.awt.Color.decode("#006400"));
        cssColors.put("YELLOWGREEN", java.awt.Color.decode("#9ACD32"));
        cssColors.put("OLIVEDRAB", java.awt.Color.decode("#6B8E23"));
        cssColors.put("DARKOLIVEGREEN", java.awt.Color.decode("#556B2F"));
        cssColors.put("MEDIUMAQUAMARINE", java.awt.Color.decode("#66CDAA"));
        cssColors.put("DARKSEAGREEN", java.awt.Color.decode("#8FBC8F"));
        cssColors.put("LIGHTSEAGREEN", java.awt.Color.decode("#20B2AA"));
        cssColors.put("DARKCYAN", java.awt.Color.decode("#008B8B"));
        cssColors.put("TEAL", java.awt.Color.decode("#008080"));
        cssColors.put("AQUA", java.awt.Color.decode("#00FFFF"));
        cssColors.put("CYAN", java.awt.Color.decode("#00FFFF"));
        cssColors.put("LIGHTCYAN", java.awt.Color.decode("#E0FFFF"));
        cssColors.put("PALETURQUOISE", java.awt.Color.decode("#AFEEEE"));
        cssColors.put("AQUAMARINE", java.awt.Color.decode("#7FFFD4"));
        cssColors.put("TURQUOISE", java.awt.Color.decode("#40E0D0"));
        cssColors.put("MEDIUMTURQUOISE", java.awt.Color.decode("#48D1CC"));
        cssColors.put("DARKTURQUOISE", java.awt.Color.decode("#00CED1"));
        cssColors.put("CADETBLUE", java.awt.Color.decode("#5F9EA0"));
        cssColors.put("STEELBLUE", java.awt.Color.decode("#4682B4"));
        cssColors.put("LIGHTSTEELBLUE", java.awt.Color.decode("#B0C4DE"));
        cssColors.put("LIGHTBLUE", java.awt.Color.decode("#ADD8E6"));
        cssColors.put("POWDERBLUE", java.awt.Color.decode("#B0E0E6"));
        cssColors.put("LIGHTSKYBLUE", java.awt.Color.decode("#87CEFA"));
        cssColors.put("SKYBLUE", java.awt.Color.decode("#87CEEB"));
        cssColors.put("CORNFLOWERBLUE", java.awt.Color.decode("#6495ED"));
        cssColors.put("DEEPSKYBLUE", java.awt.Color.decode("#00BFFF"));
        cssColors.put("DODGERBLUE", java.awt.Color.decode("#1E90FF"));
        cssColors.put("ROYALBLUE", java.awt.Color.decode("#4169E1"));
        cssColors.put("BLUE", java.awt.Color.decode("#0000FF"));
        cssColors.put("MEDIUMBLUE", java.awt.Color.decode("#0000CD"));
        cssColors.put("DARKBLUE", java.awt.Color.decode("#00008B"));
        cssColors.put("NAVY", java.awt.Color.decode("#000080"));
        cssColors.put("MIDNIGHTBLUE", java.awt.Color.decode("#191970"));
        cssColors.put("CORNSILK", java.awt.Color.decode("#FFF8DC"));
        cssColors.put("BLANCHEDALMOND", java.awt.Color.decode("#FFEBCD"));
        cssColors.put("BISQUE", java.awt.Color.decode("#FFE4C4"));
        cssColors.put("NAVAJOWHITE", java.awt.Color.decode("#FFDEAD"));
        cssColors.put("WHEAT", java.awt.Color.decode("#F5DEB3"));
        cssColors.put("BURLYWOOD", java.awt.Color.decode("#DEB887"));
        cssColors.put("TAN", java.awt.Color.decode("#D2B48C"));
        cssColors.put("ROSYBROWN", java.awt.Color.decode("#BC8F8F"));
        cssColors.put("SANDYBROWN", java.awt.Color.decode("#F4A460"));
        cssColors.put("GOLDENROD", java.awt.Color.decode("#DAA520"));
        cssColors.put("DARKGOLDENROD", java.awt.Color.decode("#B8860B"));
        cssColors.put("PERU", java.awt.Color.decode("#CD853F"));
        cssColors.put("CHOCOLATE", java.awt.Color.decode("#D2691E"));
        cssColors.put("OLIVE", java.awt.Color.decode("#808000"));
        cssColors.put("SADDLEBROWN", java.awt.Color.decode("#8B4513"));
        cssColors.put("SIENNA", java.awt.Color.decode("#A0522D"));
        cssColors.put("BROWN", java.awt.Color.decode("#A52A2A"));
        cssColors.put("MAROON", java.awt.Color.decode("#800000"));
        cssColors.put("WHITE", java.awt.Color.decode("#FFFFFF"));
        cssColors.put("SNOW", java.awt.Color.decode("#FFFAFA"));
        cssColors.put("HONEYDEW", java.awt.Color.decode("#F0FFF0"));
        cssColors.put("MINTCREAM", java.awt.Color.decode("#F5FFFA"));
        cssColors.put("AZURE", java.awt.Color.decode("#F0FFFF"));
        cssColors.put("ALICEBLUE", java.awt.Color.decode("#F0F8FF"));
        cssColors.put("GHOSTWHITE", java.awt.Color.decode("#F8F8FF"));
        cssColors.put("WHITESMOKE", java.awt.Color.decode("#F5F5F5"));
        cssColors.put("SEASHELL", java.awt.Color.decode("#FFF5EE"));
        cssColors.put("BEIGE", java.awt.Color.decode("#F5F5DC"));
        cssColors.put("OLDLACE", java.awt.Color.decode("#FDF5E6"));
        cssColors.put("FLORALWHITE", java.awt.Color.decode("#FFFAF0"));
        cssColors.put("IVORY", java.awt.Color.decode("#FFFFF0"));
        cssColors.put("ANTIQUEWHITE", java.awt.Color.decode("#FAEBD7"));
        cssColors.put("LINEN", java.awt.Color.decode("#FAF0E6"));
        cssColors.put("LAVENDERBLUSH", java.awt.Color.decode("#FFF0F5"));
        cssColors.put("MISTYROSE", java.awt.Color.decode("#FFE4E1"));
        cssColors.put("GAINSBORO", java.awt.Color.decode("#DCDCDC"));
        cssColors.put("LIGHTGRAY", java.awt.Color.decode("#D3D3D3"));
        cssColors.put("SILVER", java.awt.Color.decode("#C0C0C0"));
        cssColors.put("DARKGRAY", java.awt.Color.decode("#A9A9A9"));
        cssColors.put("DIMGRAY", java.awt.Color.decode("#696969"));
        cssColors.put("GRAY", java.awt.Color.decode("#808080"));
        cssColors.put("LIGHTSLATEGRAY", java.awt.Color.decode("#778899"));
        cssColors.put("SLATEGRAY", java.awt.Color.decode("#708090"));
        cssColors.put("DARKSLATEGRAY", java.awt.Color.decode("#2F4F4F"));
        cssColors.put("BLACK", java.awt.Color.decode("#000000"));
    }
}