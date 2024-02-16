package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class MovieRepository {
    HashMap<String,Movie> movieDB = new HashMap<>();
    HashMap<String,Director> directorDB = new HashMap<>();
    HashMap<String, List<String>> movieDirectorDB = new HashMap<>();
    public String addMovie(Movie movie){
        movieDB.put(movie.getName(),movie);
        return "New movie added successfully";
    }
    public String addDirector(Director director){
        directorDB.put(director.getName(),director);
        return "New director added successfully";
    }
    public String addMovieDirectorPair(String movie,String director){
        List<String> movieList = movieDirectorDB.getOrDefault(director,new ArrayList<>());
        movieList.add(movie);
        Director directorObj = directorDB.get(director);
        directorObj.setNumberOfMovies(movieList.size());
        movieDirectorDB.put(director,movieList);
        return "New movie-director pair added successfully";
    }
    public Movie getMovieByName(String name){
        if(movieDB.containsKey(name)==true){
            return movieDB.get(name);
        }
        return null;
    }
    public Director getDirectorByName(String name){
        if(directorDB.containsKey(name)==true){
            return directorDB.get(name);
        }
        return null;
    }
    public List<String> getMoviesByDirectorName(String director){
        List<String> movieList = movieDirectorDB.getOrDefault(director,new ArrayList<>());
        return movieList;
    }
    public List<String> findAllMovies(){
        List<String> movieList = new ArrayList<>();
        for(String movieName:movieDB.keySet()){
            movieList.add(movieName);
        }
        return movieList;
    }
    public String deleteDirectorByName(String director){
        List<String> movieList = movieDirectorDB.getOrDefault(director,new ArrayList<>());
        movieDirectorDB.remove(director);
        directorDB.remove(director);
        for(String movieName:movieList){
            movieDB.remove(movieName);
        }
        return director + " removed successfully";
    }
    public String deleteAllDirectors(){

        for(String directorName:directorDB.keySet()){
            List<String> movieList = movieDirectorDB.getOrDefault(directorName,new ArrayList<>());
            for(String movieName:movieList){
                movieDB.remove(movieName);
            }
        }

        directorDB.clear();
        movieDirectorDB.clear();

        return "All directors deleted successfully";
    }
}