package com.makemek.command.service;

import com.makemek.command.UndoableCommand;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created by Apipol on 21/02/15.
 */
public class CommandManager implements CommandService {

    private CareTaker<UndoableCommand> careTaker = new CareTaker<UndoableCommand>();
    //private ListIterator<Memento> itr = careTaker.listIterator();

    @Override
    public void storeAndExecute(UndoableCommand cmd) {
        careTaker.save(cmd);
        cmd.execute();
    }

    @Override
    public void undo() {
        UndoableCommand cmd = careTaker.previous();
        if(cmd != null)
            cmd.undo();
    }

    @Override
    public void redo() {
        UndoableCommand cmd = careTaker.next();
        if(cmd != null)
            cmd.execute();
    }

    @Override
    public void _import(String filename) {
        try {
            Iterator<UndoableCommand> it = careTaker.readFromDisk(filename);

            // execute all commands
            while(it.hasNext()) {
                it.next().execute();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void _export(String filename) {
        try {
            careTaker.saveToDisk(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
