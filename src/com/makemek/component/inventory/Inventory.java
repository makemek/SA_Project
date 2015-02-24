package com.makemek.component.inventory;


import com.makemek.component.movie.Movie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Apipol on 21/02/15.
 */
public class Inventory implements Serializable {

    transient List<Movie> mov = new ArrayList<Movie>(); // not save list for saving disk space. Re-execute command instead

    public void add(Movie movie) {
        mov.add(movie);
    }

    public Movie getMovie(String name) {
        throw new UnsupportedOperationException();
    }

    public Movie getMovie(int id_mov) {
        throw new UnsupportedOperationException();
    }

    public Memento save() {
        return new Memento(mov);
    }

    public void restoreState(Object memento) {
        mov = ((Memento) memento).movies;
    }

    private class Memento {
        private List<Movie> movies;

        public Memento(List<Movie> movies) {
            this.movies = new ArrayList<Movie>(movies);
        }
    }
}
