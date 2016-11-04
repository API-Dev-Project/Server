package com.banking.api;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/customer")
public class CustomerAPI {


    @GET
    @Path("/new")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCustomer(@Context UriInfo data) {
        return Response.status(200).entity("\nAccount created\n").build();
    }

}
