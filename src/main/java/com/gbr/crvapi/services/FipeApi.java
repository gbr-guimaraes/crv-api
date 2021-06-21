package com.gbr.crvapi.services;

import org.springframework.stereotype.Service;

@Service
public interface FipeApi {

    public String apiValor(String marca, String modelo, String ano);
    
}
