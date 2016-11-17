package com.banking.mapping;

import com.banking.Configuration;

public class MappingManager {

    private WriteMapping writeMapping;
    private ReadMapping readMapping;
    private Configuration configuration;

    public MappingManager() {
        writeMapping = new WriteMapping();
        readMapping = new ReadMapping();
        configuration = new Configuration();
    }

    public WriteMapping getWriteMapping() {
        return writeMapping;
    }

    public ReadMapping getReadMapping() {
        //Don't interact with DB if testing
        if (configuration.isTesting()) {
            return null;
        }

        return readMapping;
    }
}

