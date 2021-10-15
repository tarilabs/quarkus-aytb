package net.tarilabs.aytb.hue;

import java.awt.Color;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GroupsAction {
    private static final Logger LOG = LoggerFactory.getLogger(GroupsAction.class);
    public Boolean on;
    public Short bri = 254;
    public Integer hue = 65535;
    public Short sat = 254;

    public static GroupsAction fromColor(Color color) {
        float[] hsbvals = new float[3];
        Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), hsbvals);
        LOG.debug("{}", Arrays.toString(hsbvals));
        GroupsAction action = new GroupsAction();
        action.hue = (int) (hsbvals[0] * 65535);
        action.sat = (short) (hsbvals[1] * 254);
        action.bri = (short) (hsbvals[2] * 254);
        return action;
    }
}
