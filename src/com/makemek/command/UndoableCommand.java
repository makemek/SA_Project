package com.makemek.command;

/**
 * Created by Apipol on 21/02/15.
 */
public interface UndoableCommand extends Command {
    public void undo();
}
