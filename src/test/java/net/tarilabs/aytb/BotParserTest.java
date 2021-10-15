package net.tarilabs.aytb;

import static org.assertj.core.api.Assertions.assertThat;

import java.awt.Color;
import java.util.List;

import org.junit.jupiter.api.Test;

//@QuarkusTest
public class BotParserTest {

    @Test
    public void testBasic() {
        List<Color> colors = BotParserUtils.parse("ciao /rgb 01ae02 asd /color cyan blue /RgB #01Ee98");
        assertThat(colors).containsExactly(Color.decode("#01ae02"), Color.decode("#00FFFF"), Color.decode("#01Ee98"));
    }

    @Test
    public void testOscarAsZero() {
        List<Color> colors = BotParserUtils.parse("ciao /rgb O1ae02");
        assertThat(colors).containsExactly(Color.decode("#01ae02"));
    }

    @Test
    public void testRGBOptionalHash() {
        List<Color> colors = BotParserUtils.parse("ciao /rgb #01ae02");
        assertThat(colors).containsExactly(Color.decode("#01ae02"));
    }

}