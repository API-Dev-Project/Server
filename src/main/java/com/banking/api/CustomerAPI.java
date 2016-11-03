package com.banking.api;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/temperature")
public class CustomerAPI {


    @GET
    @Path("/convert")
    @Produces(MediaType.APPLICATION_JSON)
    public Response convertTemperature(@Context UriInfo data) {
        return Response.status(200).entity("\nAccountImpl created\n").build();
    }

}
