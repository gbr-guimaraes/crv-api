package com.gbr.crvapi.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class Rodizio extends Veiculo{
    
    private String diaRodizio;
    private boolean rodizioAtivo;
}
