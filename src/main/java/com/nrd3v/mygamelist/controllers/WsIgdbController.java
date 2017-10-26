package com.nrd3v.mygamelist.controllers;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class WsIgdbController {

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ResponseEntity<String> search(@RequestParam(name = "name", required = false) String name,
                                         @RequestParam(name = "id", required = false) String id) {
        String url = "https://api-2445582011268.apicast.io/games";
        if (name != null) {
            url = url + "/?search=" + name + "&fields=id";
        } else {
            url = url + "/" + id + "?fields=cover,id,name,developers,first_release_date,platforms";
        }
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-key", "57fba5a28de85e10d8c6ae0c5750ca43");
        headers.add("Accept", MediaType.APPLICATION_JSON.getType());
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        return restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
    }
}
