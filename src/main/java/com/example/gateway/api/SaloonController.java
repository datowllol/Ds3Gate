package com.saloon.api;


import com.example.gateway.dto.Saloon;
import com.example.gateway.dto.FreeTable;
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
@RequestMapping("/saloon")
@AllArgsConstructor
@NoArgsConstructor
public class SaloonController {
    private final String url = "http://10.107.230.201:8084/saloon";

    @PostMapping()
    public Saloon addSaloon(@RequestBody Saloon saloon) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Saloon> result =
                restTemplate.postForEntity(url, saloon, Saloon.class);
        return result.getBody();
    }

    @PostMapping()
    public Saloon getByNumber(@RequestBody Visitors visitorsDTO) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Saloon> result =
                restTemplate.postForEntity(url, visitorsDTO, Saloon.class);
        return result.getBody();
    }

    @PostMapping()
    public FreeTable setFreeTable(@RequestBody FreeTable freeTable) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<FreeTable> result =
                restTemplate.postForEntity(url, freeTable, FreeTable.class);
        return result.getBody();
    }

    @GetMapping()
    public List<Saloon> getAll() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Saloon>> result =
                restTemplate.getForObject(url, HttpMethod.GET, null,
                        new ParameterizedTypeReference<>() {
                        });
        return result.getBody();
    }


    @GetMapping("/{tableID}")
    public Saloon getById(@PathVariable(value = "tableID") UUID id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Saloon> result =
                restTemplate.exchange(url + "/" + id.toString(),
                        HttpMethod.GET, null,
                        new ParameterizedTypeReference<>() {
                        });
        return result.getBody();
    }

    @DeleteMapping("/{tableID}")
    public ResponseEntity<Void> deleteSaloonById(@PathVariable(value = "tableID") UUID id) {
        restTemplate.delete(url + "/" + id.toString());
        return ResponseEntity.noContent().build();
    }
}