package com.makemek.command;

import java.io.Serializable;

/**
 * Created by Apipol on 21/02/15.
 */
public interface Command extends Serializable {
    public void execute();
}
