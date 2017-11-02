package com.nrd3v.mygamelist.controllers;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RestController
@RequestMapping("/api")
public class WsGiantbombController {

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ResponseEntity<String> search(@RequestParam(name = "name", required = false) String name,
                                         @RequestParam(name = "id", required = false) Integer id) {
        String url = "https://www.giantbomb.com/api/";
        String apiKey = "21de7a214765bf8391668c73ac45f913aff0d9c6";
        String fields = "id,name,original_release_date,image,platforms";
        if (name != null) {
            url = url + "search/?api_key=" + apiKey +
                  "&format=json&query=" + name + "&resources=game" +
                  "&field_list=" + fields;
        } else {
            url = url + "game/3030-" + id + "/?api_key=" + apiKey +
                  "&format=json&field_list=" + fields + ",developers";
        }
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        return restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
    }
}
