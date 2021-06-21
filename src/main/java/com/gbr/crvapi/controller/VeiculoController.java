package com.gbr.crvapi.controller;

import com.gbr.crvapi.model.Veiculo;
import com.gbr.crvapi.repository.UserRepository;
import com.gbr.crvapi.repository.VeiculoRepository;
import com.gbr.crvapi.services.FipeApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/veiculos")
public class VeiculoController {
    
    @Autowired
    VeiculoRepository veiculoRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    FipeApi fipeApi;

    @PostMapping
    public ResponseEntity<?> addVeiculo(@RequestBody Veiculo veiculo){

        if(veiculo.getMarca().isEmpty() || veiculo.getModelo().isEmpty() || veiculo.getAno()
        .isEmpty() || veiculo.getCpfProp().isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Dados invalidos!");
        }

        veiculo.setValor(fipeApi.apiValor(veiculo.getMarca(), veiculo.getModelo(), 
        veiculo.getAno()));
        if(veiculo.getValor() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body("Veiculo não cadastrado na tabela Fipe");
        }

        if(!userRepository.findById(veiculo.getCpfProp()).isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario nao existe");
        }       
        veiculo.setUser(userRepository.getById(veiculo.getCpfProp()));

        return ResponseEntity.status(HttpStatus.CREATED).body(veiculoRepository.save(veiculo));
    }
}
