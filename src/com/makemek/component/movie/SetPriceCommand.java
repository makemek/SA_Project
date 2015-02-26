package com.makemek.component.movie;

import com.makemek.command.UndoableCommand;

import java.io.Serializable;

/**
 * Created by Apipol on 21/02/15.
 */
public class SetPriceCommand implements UndoableCommand {

    private Movie mov;
    private int price;

    private Memento memento;

    public SetPriceCommand(Movie mov, int price) {
        this.mov =  mov;
        this.price = price;

        memento = new Memento(mov);
    }

    @Override
    public void execute() {
        mov.setPrice(price);
    }

    @Override
    public void undo() {
        mov.setPrice(memento.price);
    }

    @Override
    public Object getInvoker() {
        return mov;
    }

    private class Memento implements Serializable {
        private int price;

        public Memento(Movie mov) {price = mov.price;}
    }
}
