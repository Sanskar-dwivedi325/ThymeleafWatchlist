package com.example.sanskar.SanskarthymleafWatchlist.Service;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RatingService {

    String apiUrl="https://www.omdbapi.com/?apikey=4288878&t=";

    public String getRating(String title){


        try{
            RestTemplate restTemplate=new RestTemplate();

            ResponseEntity<ObjectNode> response=restTemplate.getForEntity(apiUrl+title,ObjectNode.class);
            ObjectNode jsonNodes=response.getBody();
            return jsonNodes.path("imdbRating").asText();

        }

        catch (Exception e){
            return null;
        }
    }
}
