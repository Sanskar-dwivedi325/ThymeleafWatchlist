package com.example.sanskar.SanskarthymleafWatchlist.Service;

import com.example.sanskar.SanskarthymleafWatchlist.Repository.MovieRepo;
import com.example.sanskar.SanskarthymleafWatchlist.entityormodel.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatabaseService {
    @Autowired
    MovieRepo movieRepo;
    @Autowired
    RatingService ratingService;
    public void create(Movie movie){

        String rating= ratingService.getRating(movie.getTitle());


        if(rating!=""){
           movie.setRating(Float.parseFloat(rating));
        }



           movieRepo.save(movie);

    }

    public List<Movie> getAllMovies(){
        return movieRepo.findAll();
    }

    public Movie getMoviebyId(Integer id){
        return  movieRepo.findById(id).get();
    }

    public void update(Movie movie, Integer id) {
        Movie toBeUpdated = getMoviebyId(id);
        toBeUpdated.setTitle(movie.getTitle());
        toBeUpdated.setRating(movie.getRating());
        toBeUpdated.setComment(movie.getComment());
        toBeUpdated.setPriority(movie.getPriority());

        movieRepo.save(toBeUpdated);
    }

    public void delete(Integer id){
      Movie movie=getMoviebyId(id);
      movieRepo.delete(movie);
    }
}
