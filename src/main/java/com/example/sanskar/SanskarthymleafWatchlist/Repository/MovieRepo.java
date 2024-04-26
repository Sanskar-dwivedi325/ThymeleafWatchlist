package com.example.sanskar.SanskarthymleafWatchlist.Repository;

import com.example.sanskar.SanskarthymleafWatchlist.entityormodel.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepo extends JpaRepository<Movie,Integer> {

}
