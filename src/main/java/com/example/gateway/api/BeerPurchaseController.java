package com.soldBeer.api;

import com.example.gateway.dto.SoldBeer;
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
@RequestMapping("/beerSale")
@AllArgsConstructor
@NoArgsConstructor
public class BeerPurchaseController {

    private final String url = "http://10.99.144.4:8085/beerSale";

    @PostMapping()
    public SoldBeer addSale(@RequestBody SoldBeer beer) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<SoldBeer> result =
                restTemplate.postForEntity(url, beer, SoldBeer.class);
        return result.getBody();
    }

    @GetMapping()
    public List<SoldBeer> getAll() {
        rRestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<SoldBeer>> result =
                restTemplate.getForObject(url, HttpMethod.GET, null,
                        new ParameterizedTypeReference<>() {
                        });
        return result.getBody();
    }

    @GetMapping("/{sale_id}")
    public SoldBeer getById(@PathVariable(value = "sale_id") UUID id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<SoldBeer> result =
                restTemplate.exchange(url + "/" + id.toString(),
                        HttpMethod.GET, null,
                        new ParameterizedTypeReference<>() {
                        });
        return result.getBody();
    }

    @DeleteMapping("/{sale_id}")
    public ResponseEntity<Void> deleteById(@PathVariable(value = "sale_id") UUID id) {
        restTemplate.delete(url + "/" + id.toString());
        return ResponseEntity.noContent().build();
    }
}