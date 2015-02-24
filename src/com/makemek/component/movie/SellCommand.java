package com.makemek.component.movie;

import com.makemek.command.UndoableCommand;

import java.io.Serializable;

/**
 * Created by Apipol on 21/02/15.
 */
public class SellCommand implements UndoableCommand {

    private Movie mov;

    private Memento memento;

    public SellCommand(Movie mov) {
        this.mov = mov;
        memento = new Memento(this.mov);
    }

    @Override
    public void execute() {
        mov.sell();
    }

    @Override
    public void undo() {
        mov.quantity = memento.quantity;
    }

    private class Memento implements Serializable {
        int quantity;

        public Memento(Movie mov) {
            quantity = mov.quantity;
        }
    }
}
