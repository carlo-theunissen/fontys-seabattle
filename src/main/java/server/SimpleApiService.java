package server;

import com.google.gson.Gson;
import server.Shared.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/server")
public class SimpleApiService {
    @GET
    @Path("/status")
    public Response getStatus() {
        StatusResponse response = new StatusResponse();
        Gson gson = new Gson();

        response.setStatus(ServerStatusContainer.getStatus().toString());
        response.setExtraInfo(new ExtraInfoStatusResponse());

        String output = gson.toJson(response);
        return Response.status(200).entity(output).build();
    }


    @GET
    @Path("/clients/connected")
    public Response getConnected() {
        ConnectedResponse response = new ConnectedResponse();
        response.setConnected(ServerStatusContainer.getConnected());
        response.setExtraInfo(new ExtraInfoConnectedResponse());
        Gson gson = new Gson();
        String output = gson.toJson(response);
        return Response.status(200).entity(output).build();
    }

}
