package com.placeManage.api;


import com.example.gateway.dto.Visitors;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/visitors")
@AllArgsConstructor
@NoArgsConstructor
public class PlaceManageController {

    private final String url = "http://10.100.60.224:8083/visitors";

    @PostMapping()
    public Visitors addVisitors(@RequestBody Visitors visitors) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Visitors> result =
                restTemplate.postForEntity(url, visitors, Visitors.class);
        return result.getBody();
    }

    @GetMapping()
    public List<Visitors> getAll() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Visitors>> result =
                restTemplate.getForObject(url, HttpMethod.GET, null,
                        new ParameterizedTypeReference<>() {
                        });
        return result.getBody();
    }

    @GetMapping("/{visitorId}")
    public Visitors getById(@PathVariable(value = "visitorId") UUID id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Visitors> result =
                restTemplate.exchange(url + "/" + id.toString(),
                        HttpMethod.GET, null,
                        new ParameterizedTypeReference<>() {
                        });
        return result.getBody();
    }


    @DeleteMapping("/{visitorId}")
    public Visitors visitorsLeavePub(@PathVariable(value = "visitorId") UUID id) {
        return restTemplate.delete(url + "/" + id.toString());
    }
}
