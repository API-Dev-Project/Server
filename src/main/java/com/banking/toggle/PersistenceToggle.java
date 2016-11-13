package com.banking.toggle;

/**
 * Created by Graham Murray on 13/11/16.
 */
public class PersistenceToggle implements Toggle{

    /*
     * Toggle used to stop persisting data
     * if a test is being executed
     */
    @Override
    public boolean isToggleOn() {
        return true;
    }
}
