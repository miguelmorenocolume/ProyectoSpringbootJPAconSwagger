package com.example.concesionariocoches;

import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface CocheRepository extends JpaRepository<Coche, Integer> {

    List<Coche> findAll();

}
