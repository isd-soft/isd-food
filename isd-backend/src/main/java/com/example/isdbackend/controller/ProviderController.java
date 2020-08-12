package com.example.isdbackend.controller;

import com.example.isdbackend.model.Provider;
import com.example.isdbackend.service.ProviderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("provider")
public class ProviderController {
    private ProviderService providerService;

    @RequestMapping(value = "/getAllProviders", method = RequestMethod.POST)
    public List<Provider> getAllProviders(){
        return providerService.findAll();
    }

}
