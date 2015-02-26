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

    public<T>  T _import(String filename, Class<T> type) {
        try {
            Iterator<UndoableCommand> it = new CareTaker<UndoableCommand>().readFromDisk(filename);

            // get first commands and execute
            UndoableCommand cmd = it.next();
            cmd.execute();

            // execute all commands
            while(it.hasNext()) {
                it.next().execute();
            }

            return type.cast(cmd.getInvoker());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        throw new ClassCastException("Cannot cast to type: " + type.getName());
    }

    public<T> void _export(String filename, T obj) {
        try {
            CareTaker<UndoableCommand> taker = new CareTaker<UndoableCommand>();
            for(UndoableCommand cmd: careTaker.list)
                if(cmd.getInvoker() == obj)
                    taker.save(cmd);

            taker.saveToDisk(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
