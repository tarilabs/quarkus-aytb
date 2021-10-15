package net.tarilabs.aytb;

import java.awt.Color;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.tarilabs.aytb.hue.DeviceType;
import net.tarilabs.aytb.hue.GroupsAction;

@Path("hue")
public class HueResource {

    private static final Logger LOG = LoggerFactory.getLogger(HueResource.class);

    @ConfigProperty(name = "hue.username")
    String username;
    @ConfigProperty(name = "hue.group")
    String group;

    @Inject
    @RestClient
    HueClient hueClient;

    @GET
    @Path("pair")
    @Produces(MediaType.APPLICATION_JSON)
    public String pair() {
        DeviceType dt = new DeviceType();
        dt.devicetype = "quarkus_aytb";
        return hueClient.register(dt);
    }

    @GET
    @Path("color/{color}")
    @Produces(MediaType.APPLICATION_JSON)
    public String color(@PathParam("color") String colorString) {
        Color color = java.awt.Color.decode(colorString);
        LOG.debug("{}", color);
        GroupsAction action = GroupsAction.fromColor(color);
        return hueClient.groupsAction(username, group, action);
    }
}
