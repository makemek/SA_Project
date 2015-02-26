package com.makemek.command.service;

import com.makemek.command.UndoableCommand;

/**
 * Created by Apipol on 21/02/15.
 */
public interface CommandService {

    public void storeAndExecute(UndoableCommand cmd);
    public void undo();
    public void redo();
    public<T> void _export(String filename, T obj);
}
