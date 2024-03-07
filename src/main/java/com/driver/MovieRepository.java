package com.driver;

import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class MovieRepository {

    private HashMap<String, Movie> movieMap;
    private HashMap<String, Director> directorMap;
    private HashMap<String, List<String>> directorMovieMapping;

    public MovieRepository(){
        this.movieMap = new HashMap<String, Movie>();
        this.directorMap = new HashMap<String, Director>();
        this.directorMovieMapping = new HashMap<String, List<String>>();
    }

    public void saveMovie(Movie movie){
        // your code here
        movieMap.put(movie.getName(), movie);
    }

    public void saveDirector(Director director){
        // your code here
        directorMap.put(director.getName(), director);
    }

    public void saveMovieDirectorPair(String movie, String director){
//        if (movieMap.containsKey(movie) && directorMap.containsKey(director)) {
//            List<String> movies = directorMovieMapping.getOrDefault(director, new ArrayList<>());
//            movies.add(movie);
//            directorMovieMapping.put(director, movies);
//        }
        if(movieMap.containsKey(movie) && directorMap.containsKey(director)){
            // your code here
            List<String>movies=new ArrayList<>();
            //list of movies by given director
            if(directorMovieMapping.containsKey((director))){
                movies=directorMovieMapping.get(director);

            }
            movies.add(movie);

            directorMovieMapping.put(director,movies);//updated the director movie pair map

        }
    }

    public Movie findMovie(String name){
        // your code here

        for(Movie m:movieMap.values()){
            if(m.getName().equals(name)){
                return m;
            }
        }
        return null;
    }

    public Director saveDirector(String name){
        for(Director d:directorMap.values()){
//           System.out.println(d.getName()+ " "+ name);
            if(d.getName().equals(name)){
                return d;
            }

        }

        return null;
    }
    public Director findDirector(String director){
        // your code here
        return directorMap.get(director);
    }

    public List<String> getMoviesByDirectorName(String name){
        List<String>movieslist=new ArrayList<>();
        for(String s:directorMovieMapping.keySet()){
            if(s.equals(name)){
                movieslist=directorMovieMapping.get(s);
            }
        }
        return movieslist;
    }

    public List<String> findAllMovies(){
        List<String>movieList=new ArrayList<>();
        movieList.addAll(movieMap.keySet());
        return movieList;
    }

    public void deleteDirector(String name){
        // your code here
        for(String s:directorMap.keySet()){
            if(s.equals(name)){
                directorMap.remove(name);
            }
        }
        //delete movie of this director
        List<String> movieList=new ArrayList<>();//movie list of this director
        for(String s:directorMovieMapping.keySet()){
            if(s.equals(name)){
                movieList=directorMovieMapping.get(s);
                directorMovieMapping.remove(s);
            }
        }
        // remove movies from director movie list
        for(String s:movieList){
            movieMap.remove(s);
        }

    }

    public void deleteAllDirector(){
        // your code here
        for(String s:directorMap.keySet()){
            List<String> movielist=directorMovieMapping.get(s);
            for(String m:movielist){
                movieMap.remove(m);
            }

        }
        directorMap.clear();
    }
}