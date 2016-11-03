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
public class EntryPoint {

    enum Params {
        TEMPERATURE("temp"),
        METRIC("metric"),
        LENGTH("length");

        String param;
        Params(String param) {
            this.param = param;
        }

        String getValue() {
            return param;
        }
    }

    private TemperatureConverter converter;

    @GET
    @Path("/convert")
    @Produces(MediaType.APPLICATION_JSON)
    public Response convertTemperature(@Context UriInfo data) {
        Gson gson = new Gson();

        String temperature = data.getQueryParameters().getFirst(Params.TEMPERATURE.toString());
        String metric = data.getQueryParameters().getFirst(Params.METRIC.toString());

        converter = new TemperatureConverter(Integer.valueOf(temperature), TemperatureConverter.Type.valueOf(metric.toUpperCase()));
        return Response.status(200).entity(gson.toJson(converter)).build();
    }

    @GET
    @Path("/random")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getRandomString(@Context UriInfo data) {

        int len = Integer.valueOf(data.getQueryParameters().getFirst(Params.LENGTH.getValue()));
        Randomizer random = new Randomizer(len);

        return Response.status(200).entity(random.getString()).build();
    }

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response register(String entity) {
        User user = new Gson().fromJson(entity, User.class);

        return Response.status(200).entity("\nAccount created\n").build();
    }
}
