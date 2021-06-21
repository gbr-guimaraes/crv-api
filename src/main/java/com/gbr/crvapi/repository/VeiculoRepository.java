package com.gbr.crvapi.repository;

import java.util.List;

import com.gbr.crvapi.model.Veiculo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    
    public List<Veiculo> findByCpfProp(String cpfProp);

}
