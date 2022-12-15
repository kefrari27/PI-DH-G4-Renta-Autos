package com.dh.backend_G4.repository;

import com.dh.backend_G4.model.Politica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPoliticaRepository extends JpaRepository<Politica, Long> {
}
