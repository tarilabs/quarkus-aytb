package net.tarilabs.aytb;

import javax.inject.Singleton;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@Singleton
public class CaptureArea {

    @ConfigProperty(name = "capturearea.x")
    int x;
    @ConfigProperty(name = "capturearea.y")
    int y;
    @ConfigProperty(name = "capturearea.width")
    int width;
    @ConfigProperty(name = "capturearea.height")
    int height;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder()
        .append("capturearea.x=").append(x).append("\n")
        .append("capturearea.y=").append(y).append("\n")
        .append("capturearea.width=").append(width).append("\n")
        .append("capturearea.height=").append(height).append("\n");
        return sb.toString();
    }
}
