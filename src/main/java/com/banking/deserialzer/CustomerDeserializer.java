package com.banking.deserialzer;

import com.banking.bank.Customer;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

import java.io.IOException;

public class CustomerDeserializer extends JsonDeserializer<Customer> {

    @Override
    public Customer deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        Root root = jp.readValueAs(Root.class);

        Customer account = new Customer();
        if (root != null && root.customer.getId() != 0) {
            account.setId(root.customer.getId());
        }

        return account;
    }

    private static class Root {

        public Customer customer;
    }

}