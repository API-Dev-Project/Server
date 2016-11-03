package com.graham.api;

import com.google.gson.*;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.lang.reflect.Type;
import java.util.UUID;

@Path("/temperature")
public class Customer {


    @GET
    @Path("/convert")
    @Produces(MediaType.APPLICATION_JSON)
    public Response convertTemperature(@Context UriInfo data) {
        return Response.status(200).entity("\nAccount created\n").build();
    }

}
