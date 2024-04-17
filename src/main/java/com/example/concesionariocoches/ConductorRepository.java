package com.example.concesionariocoches;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

public interface ConductorRepository extends JpaRepository<Conductor, Integer> {

    List<Conductor> findAll();

    List<Conductor> findByAnyosCarnetGreaterThan(int anyosCarnet);

    List<Conductor> findByFechaNacimientoAfter(Date fechaNacimiento);

    List<Conductor> findByNombreStartingWithAndApellidosStartingWithAndAnyosCarnetLessThan(String nombre, String apellidos, int aniosCarnet);

    List<Conductor> findByAnyosCarnetBetween(int anyosCarnetMin, int anyosCarnetMax);

    @Transactional
    @Modifying
    @Query("UPDATE Conductor c SET c.direccion.calle = :calle, c.direccion.cp = :cp, c.direccion.localidad = :localidad, c.direccion.provincia = :provincia, c.direccion.numero = :numero WHERE c.idConductor = :idConductor")
    void updateDireccion(@Param("idConductor") int idConductor, @Param("calle") String calle, @Param("cp") int cp, @Param("localidad") String localidad, @Param("provincia") String provincia, @Param("numero") int numero);
}
