package com.makemek.component.inventory;

import com.makemek.command.UndoableCommand;
import com.makemek.component.movie.Movie;

/**
 * Created by Apipol on 21/02/15.
 */
public class AddCommand implements UndoableCommand {

    private Inventory inventory;
    private Movie mov;

    public AddCommand(Inventory inventory, Movie mov) {
        if(inventory == null || mov == null)
            throw new NullPointerException();

        this.inventory = inventory;
        this.mov = mov;

    }

    @Override
    public void execute() {
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

    @Override
    public Object getInvoker() {
        return inventory;
    }

}
