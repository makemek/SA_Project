package com.makemek.component.inventory;

import com.makemek.command.UndoableCommand;
import com.makemek.component.movie.Movie;

import java.util.ArrayList;

/**
 * Created by Apipol on 21/02/15.
 */
public class AddCommand implements UndoableCommand {

    private Inventory inventory;
    private Movie mov;

//    private Memento memento;

    public AddCommand(Inventory inventory, Movie mov) {
        if(inventory == null || mov == null)
            throw new NullPointerException();

        this.inventory = inventory;
        this.mov = mov;

//        memento = new Memento(inventory.mov);
    }

    @Override
    public void execute() {
        if(inventory.mov == null) // resurrect purpose only
            inventory.mov = new ArrayList<Movie>();

        inventory.add(mov);
    }

    @Override
    public void undo()
    {
        int lastIdx = inventory.mov.size() - 1;
        if(inventory.mov.get(lastIdx) == mov)
            inventory.mov.remove(lastIdx);
        else
            throw new IllegalArgumentException("Reference not match: "
                    + mov.hashCode() + "!=" + inventory.mov.get(lastIdx).hashCode());
    }

//    private class Memento implements Serializable {
//        private List<Movie> lst;
//
//        public Memento(List<Movie> lst) {
//            this.lst = new ArrayList<Movie>(lst);
//        }
//    }
}
