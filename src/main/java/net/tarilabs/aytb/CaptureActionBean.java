package net.tarilabs.aytb;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import javax.enterprise.context.ApplicationScoped;
import javax.imageio.ImageIO;
import javax.inject.Inject;

import io.quarkus.tika.TikaParser;

@ApplicationScoped
public class CaptureActionBean {

    @Inject
    CaptureArea area;
    @Inject
    TikaParser parser;
    
    public String capture() {
        try {
            Robot robot = new Robot();
            BufferedImage screenShot = robot.createScreenCapture(new Rectangle(area.x, area.y, area.width, area.height));
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(screenShot, "png", os);
            os.close();
            InputStream is = new ByteArrayInputStream(os.toByteArray());
            String text = parser.getText(is);
            is.close();
            return text;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
