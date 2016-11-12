package com.banking.mapping;

public class MappingManager {

    private WriteMapping writeMapping;
    private ReadMapping readMapping;

    public MappingManager() {
        writeMapping = new WriteMapping();
        readMapping = new ReadMapping();
    }

    public WriteMapping getWriteMapping() {
        return writeMapping;
    }

    public ReadMapping getReadMapping() {
        return readMapping;
    }
}

