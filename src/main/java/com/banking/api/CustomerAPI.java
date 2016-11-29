package com.banking.api;

import com.banking.bank.Account;
import com.banking.bank.Customer;
import com.banking.controller.InteractionController;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/customer")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class CustomerAPI {

    private InteractionController interactionController;

    public CustomerAPI() {
        interactionController = new InteractionController();
    }

    @GET
    @Path("/{id}")
    public Customer getCustomer(@PathParam("id") int id, @Context UriInfo info) {
        String email = info.getQueryParameters().getFirst("email");
        String password = info.getQueryParameters().getFirst("password");

        Customer customer = interactionController.getCustomer(email, password);

        return customer;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Customer createCustomer(Account customer) {
        if(customer == null) {
            return new Customer();
        }

        return null;
    }
}
