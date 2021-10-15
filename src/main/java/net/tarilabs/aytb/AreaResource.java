package net.tarilabs.aytb;

import java.awt.Point;
import java.awt.event.WindowEvent;

import javax.inject.Inject;
import javax.swing.JFrame;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/area")
public class AreaResource {

    @Inject
    CaptureArea area;
    @Inject
    CaptureActionBean action;

    @GET
    @Path("/capture")
    @Produces(MediaType.TEXT_PLAIN)
    public String capture() {
        return action.capture();
    }

    @GET
    @Path("/reset")
    @Produces(MediaType.TEXT_PLAIN)
    public String reset() {
        area.x = 0;
        area.y = 0;
        area.width = 300;
        area.height = 800;
        return area();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String area() {
        JFrame frame = new JFrame("Coordinates");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocation(area.x, area.y);
        frame.setSize(area.width, area.height);
        frame.addWindowListener(new java.awt.event.WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                Point p = frame.getLocationOnScreen();
                area.x = p.x;
                area.y = p.y;
                area.width = frame.getWidth();
                area.height = frame.getHeight();
                System.out.println(area);
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }

        });
        frame.setVisible(true);

        return "Opening at values:\n"+area.toString();
    }
}