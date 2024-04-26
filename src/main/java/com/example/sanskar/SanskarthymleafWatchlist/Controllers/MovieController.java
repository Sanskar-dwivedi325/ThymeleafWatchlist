package com.example.sanskar.SanskarthymleafWatchlist.Controllers;

import com.example.sanskar.SanskarthymleafWatchlist.Service.DatabaseService;
import com.example.sanskar.SanskarthymleafWatchlist.entityormodel.Movie;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MovieController {
    @Autowired
    DatabaseService databaseService;
    @GetMapping("/watchlistItemForm")
    public ModelAndView showWatchListItemForm(@RequestParam(required = false) Integer id){
    String viewName="watchlistItemForm";
    Map<String,Object> model=new HashMap<>();
    if(id==null){
   model.put("watchlistItem",new Movie());}
    else{
        model.put("watchlistItem",databaseService.getMoviebyId(id));
    }



    return new ModelAndView(viewName,model);

}

    @PostMapping("/watchlistItemForm")
    public ModelAndView submitWatchlistForm(@Valid @ModelAttribute("watchlistItem") Movie movie, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            System.out.println(bindingResult.hasErrors());
            // if errors are there, redisplay the form and let user enter again"
            return new ModelAndView("watchlistItemForm");
        }

        Integer id = movie.getId();
        if(id == null) {
            databaseService.create(movie);
        } else {
            databaseService.update(movie, id);
        }



        RedirectView rd = new RedirectView();
        rd.setUrl("/watchlist");

    return new ModelAndView(rd);

}
     @GetMapping("/watchlist")
    public ModelAndView getWatchlist(){
        String viewname="watchlist";
         Map<String,Object> model=new HashMap<>();
         List<Movie> movieList=new ArrayList<>();
         movieList.addAll(databaseService.getAllMovies());
         model.put("watchlistRows",movieList);
         model.put("noOfMovies",movieList.size());
         return new ModelAndView(viewname,model);
    }
    @GetMapping("/watchlistItemDelete")
    public RedirectView deleteMovie(@RequestParam Integer id){



        databaseService.delete(id);


        RedirectView rd = new RedirectView();
        rd.setUrl("/watchlist");

        return rd;

    }

}
