package com.example.isdbackend.controller;



import com.example.isdbackend.model.Provider;
import com.example.isdbackend.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.isdbackend.model.Provider;
import com.example.isdbackend.service.ProviderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
@RequestMapping("/provider")
public class ProviderController {
    private final ProviderService providerService;

    @Autowired
    public ProviderController(ProviderService providerService) {
        this.providerService = providerService;
    }

    @PostMapping
    public void addProvider(@RequestBody Provider provider){
        providerService.addProvider(provider);
    }

    @GetMapping("/all")
    public List<Provider> getAllProviders(){
        return providerService.findAllProviders();
    }


    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateProvider(@RequestBody Provider provider){
        providerService.updateProvider(provider);
    }


    @DeleteMapping("/{id}")
    public void deleteProvider(@PathVariable("id") int id) {
        providerService.deleteProvider(id);
    }


}
