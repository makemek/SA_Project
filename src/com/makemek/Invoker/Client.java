package com.makemek.Invoker;

import com.makemek.command.service.CareTaker;
import com.makemek.command.service.CommandManager;
import com.makemek.command.service.CommandService;
import com.makemek.component.inventory.AddCommand;
import com.makemek.component.inventory.Inventory;
import com.makemek.component.movie.Movie;
import com.makemek.component.movie.SellCommand;

/**
 * Created by Apipol on 21/02/15.
 */
public class Client {
    public static void main(String[] args) {

        Inventory inventory = new Inventory();
        Movie dragon = new Movie();

        CommandService service = new CommandManager();

        for(int sale = 0; sale < 1000; ++sale) {
            service.storeAndExecute(new AddCommand(inventory, new Movie()));
            service.storeAndExecute(new SellCommand(dragon));
        }

        service.undo();
        service.undo();
        service.undo();
        service.redo();
        service.redo();
        service.redo();
        service.redo();

        String inventoryExportFilename = "inventory.ser";
        String movieExportFilename = "movie.ser";
        service._export(inventoryExportFilename, inventory);
        service._export(movieExportFilename, dragon);

        CommandManager importedService = new CommandManager();
        inventory = importedService._import(inventoryExportFilename, Inventory.class);
        Movie movie = importedService._import(movieExportFilename, Movie.class);

        importedService.undo();
        importedService.undo();
        importedService.undo();

        inventory.add(dragon);
        CareTaker<Object> careTaker = new CareTaker<Object>();
        careTaker.save(inventory.save());
        inventory.add(new Movie());
        inventory.restoreState(careTaker.previous());



    }
}
