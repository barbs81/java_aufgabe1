package at.ac.fhcampuswien.fhmdb.models;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    private String title;
    private String description;
    // TODO add more properties here -> DONE
    public enum Genre {ACTION, ADVENTURE, ANIMATION, BIOGRAPHY, COMEDY, CRIME, DRAMA, DOCUMENTARY, FAMILY, FANTASY, HISTORY, HORROR, MUSICAL, MYSTERY, ROMANCE, SCIENCE_FICTION, SPORT, THRILLER, WAR, WESTERN}
    private List <Genre> genres;

    public Movie(String title, String description, List <Genre> genres) {
        this.title = title;
        this.description = description;
        this.genres = genres;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List <Genre> getListGenres(){ return genres; }


    public static List<Movie> initializeMovies(){
        List<Movie> movies = new ArrayList<>();
        // TODO add some dummy data here -> DONE

        List <Genre> felixAndMeira= new ArrayList<>();
        felixAndMeira.add(Genre.DRAMA);
        felixAndMeira.add(Genre.ROMANCE);
        felixAndMeira.add(Genre.COMEDY);
        movies.add(new Movie("Felix and Meira","An unusual roman blossoms between two lost souls who inhabit the same neighborhood but vastly different worlds.", felixAndMeira));

        List <Genre> wonderWoman = new ArrayList<>();
        wonderWoman.add(Genre.ACTION);
        movies.add(new Movie("Wonder Woman", "When a pilot crashes and tells of conflict in the outside world, Diana, an Amazonian warrior in training, leaves home to fight a war, discovering her full powers and true destiny.", wonderWoman));

        List <Genre> starWars = new ArrayList<>();
        starWars.add(Genre.SCIENCE_FICTION);
        movies.add(new Movie("Star Wars","Adventures of characters in a galaxy far, far away. Story about the Skywalker family and their struggle against the evil Sith Lord Palpatine.", starWars));

        List <Genre> shrek = new ArrayList<>();
        shrek.add(Genre.ADVENTURE);
        shrek.add(Genre.ANIMATION);
        shrek.add(Genre.COMEDY);
        movies.add(new Movie("Shrek", "Adventures of an Ogre and his friends.", shrek));

        List <Genre> cinemaParadiso = new ArrayList<>();
        cinemaParadiso.add(Genre.FAMILY);
        movies.add(new Movie("Cinema Paradiso","A filmmaker recalls his childhood when falling in love with the pictures at the cinema of his home village and forms a deep friendship with the cinema's projectionist.", cinemaParadiso));

        return movies;

    }
}
