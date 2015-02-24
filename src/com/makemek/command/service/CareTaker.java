package com.makemek.command.service;

import java.io.*;
import java.util.*;

/**
 * Created by Apipol on 21/02/15.
 */
public class CareTaker<T> {

    List<T> list = new Stack<T>();
    private ListIterator<T> itr = list.listIterator();

    public void saveToDisk(String filename) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(filename);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);

        // cleanup
        while(itr.hasNext()) {
            itr.next();
            itr.remove();
        }

        out.writeObject(list);
        out.close();
        fileOut.close();
        System.out.println("Serialized data is saved in /" + filename);
    }

    public ListIterator<T> readFromDisk(String filename) throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream(filename);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        list = (List<T>) in.readObject();
        itr = list.listIterator();
        return itr;
    }

    public void save(T obj) {
        if(itr.hasNext())
        {
            itr.next();
            itr.set(obj);
        }
        else {
            itr.add(obj);
        }
    }

    public T previous() {
        if(itr.hasPrevious())
            return itr.previous();
        else
            return null;
    }

    public T next() {
        if(itr.hasNext())
            return itr.next();
        else
            return null;
    }
}
