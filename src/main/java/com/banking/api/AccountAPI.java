package com.banking.api;

import com.banking.bank.Account;
import com.banking.bank.Customer;
import com.banking.bank.Transaction;
import com.banking.controller.InteractionController;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.util.List;

/**
 * Created by Ian C on 01/12/2016.
 */

@Path("/account")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class AccountAPI {

    private InteractionController interactionController;

    public AccountAPI() {
        interactionController = new InteractionController();
    }

    @GET
    @Path("/{id}")
    public Account getAccount(@PathParam("id") int id, @Context UriInfo info) {
        String accountNumber = info.getQueryParameters().getFirst("accountNumber");

        if (accountNumber != null) {
            Account account = interactionController.getAccount(Integer.parseInt(accountNumber));

            if(account != null) {
                return account;
            }
        }

        return null;
    }

    @POST
    @Path("/{id}")
    public Account createAccount(@PathParam("id") int id) {
        return interactionController.addAccount(id);
    }

    @GET
    @Path("transactions")
    public List<Transaction> getTransactions(@Context UriInfo data) {
        int customerId = Integer.valueOf(data.getQueryParameters().getFirst("customerId"));
        int accountNumber = Integer.valueOf(data.getQueryParameters().getFirst("accountNumber"));
        int sortCode = Integer.valueOf(data.getQueryParameters().getFirst("sortCode"));

        Customer customer = interactionController.getCustomerById(customerId);

        if (customer != null) {
            Account account =  customer.getAccount(accountNumber, sortCode);

            if (account != null) {
                return  account.getTransactions();
            }
        }

        return null;
    }
}
