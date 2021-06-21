package com.gbr.crvapi.controller;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import com.gbr.crvapi.model.Rodizio;
import com.gbr.crvapi.model.Veiculo;
import com.gbr.crvapi.repository.VeiculoRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/veiculos")
public class RodizioController {
    
    @Autowired
    VeiculoRepository veiculoRepository;

    @GetMapping("/{cpfProp}")
    public ResponseEntity<?> listar(@PathVariable String cpfProp){
        List<Veiculo> listaVeiculo = veiculoRepository.findByCpfProp(cpfProp);
        List<Rodizio> listaRodizio = new ArrayList<Rodizio>();

        for(Veiculo item : listaVeiculo){
            Rodizio r = new Rodizio();
            BeanUtils.copyProperties(item, r);

            switch(r.getAno().toCharArray()[3]){
                case '0': { r.setDiaRodizio("segunda-feira"); break;}
                case '1': { r.setDiaRodizio("segunda-feira"); break;}
                case '2': { r.setDiaRodizio("terca-feira"); break;}
                case '3': { r.setDiaRodizio("terca-feira"); break;}
                case '4': { r.setDiaRodizio("quarta-feira"); break;}
                case '5': { r.setDiaRodizio("quarta-feira"); break;}
                case '6': { r.setDiaRodizio("quinta-feira"); break;}
                case '7': { r.setDiaRodizio("quinta-feira"); break;}
                case '8': { r.setDiaRodizio("sexta-feira"); break;}
                case '9': { r.setDiaRodizio("sexta-feira"); break;}
            }

            Calendar cal = new GregorianCalendar();
		    if(new DateFormatSymbols().getWeekdays()[cal.get(Calendar.DAY_OF_WEEK)]
            .equals(r.getDiaRodizio())){
                r.setRodizioAtivo(true);
            }
            else{
                r.setRodizioAtivo(false);
            }

            listaRodizio.add(r);
        }
        if(listaRodizio.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("Nenhuma Correspondencia foi encontrada para sua busca :(");
        }
        else{
            return ResponseEntity.status(HttpStatus.OK).body(listaRodizio);
        }
    }
    
}


