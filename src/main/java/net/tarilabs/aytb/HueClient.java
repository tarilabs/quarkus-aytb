package net.tarilabs.aytb;

import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import net.tarilabs.aytb.hue.DeviceType;
import net.tarilabs.aytb.hue.GroupsAction;

@Path("/api")
@RegisterRestClient
public interface HueClient {
    
    @POST
    public String register(DeviceType dt);

    @PUT
    @Path("/{username}/groups/{id}/action")
    public String groupsAction(@PathParam("username") String username, @PathParam("id") String id, GroupsAction ga);

}
