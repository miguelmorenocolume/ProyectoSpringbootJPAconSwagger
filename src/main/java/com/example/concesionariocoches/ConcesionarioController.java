package com.example.concesionariocoches;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/concesionario")
public class ConcesionarioController {

    @Autowired
    private ConductorRepository conductorRepository;

    @Autowired
    private CocheRepository cocheRepository;

    @GetMapping("/conductores")
    public List<Conductor> getAllConductores() {
        return conductorRepository.findAll();
    }

    @GetMapping("/coches")
    public List<Coche> getAllCoches() {
        return cocheRepository.findAll();
    }

    @GetMapping("/coches/conductor/{idConductor}")
    public List<Coche> getCochesByConductor(@PathVariable int idConductor) {
        Conductor conductor = conductorRepository.findById(idConductor).orElse(null);
        if (conductor != null) {
            return List.copyOf(conductor.getCoches());
        } else {
            System.out.println("No se encuentra ese conductor en la base de datos");
            return null;
        }
    }

    @GetMapping("/conductores/coche/{idCoche}")
    public List<Conductor> getConductoresByCoche(@PathVariable int idCoche) {
        Coche coche = cocheRepository.findById(idCoche).orElse(null);
        if (coche != null) {
            return List.copyOf(coche.getConductores());
        } else {
            System.out.println("No se encuentra ese coche en la base de datos");
            return null;
        }
    }

    @GetMapping("/coches/conductormas10anoscarnet")
    public List<Coche> getCochesConConductorMasDe10AnosCarnet() {
        List<Conductor> conductoresMasDe10Anos = conductorRepository.findByAnyosCarnetGreaterThan(10);
        List<Coche> coches = new ArrayList<>();
        for (Conductor conductor : conductoresMasDe10Anos) {
            coches.addAll(conductor.getCoches());
        }
        return coches;
    }

    @GetMapping("/coches/conductormenos25anos")
    public List<Coche> getCochesConConductorMenosDe25Anos() {
        List<Conductor> conductoresMenosDe25Anos = conductorRepository.findByFechaNacimientoAfter(Date.valueOf(LocalDate.now().minusYears(25)));
        List<Coche> coches = new ArrayList<>();
        for (Conductor conductor : conductoresMenosDe25Anos) {
            coches.addAll(conductor.getCoches());
        }
        return coches;
    }
    @GetMapping("/conductores/filtro")
    public List<Conductor> getConductoresPorFiltro() {
        return conductorRepository.findByNombreStartingWithAndApellidosStartingWithAndAnyosCarnetLessThan("J", "E", 5);
    }

    @GetMapping("/conductores/carnetentre3y6")
    public List<Conductor> getConductoresConCarnetEntre3y6Anos() {
        return conductorRepository.findByAnyosCarnetBetween(3, 6);
    }

    @PutMapping("/conductores/{idConductor}/direccion")
    public ResponseEntity<String> updateDireccionConductor(@PathVariable int idConductor, @RequestBody Direccion nuevaDireccion) {
        try {
            conductorRepository.updateDireccion(idConductor, nuevaDireccion.getCalle(), nuevaDireccion.getCp(), nuevaDireccion.getLocalidad(), nuevaDireccion.getProvincia(), nuevaDireccion.getNumero());
            return new ResponseEntity<>("Dirección actualizada correctamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar la dirección del conductor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/coches/{idCoche}")
    public ResponseEntity<String> deleteCoche(@PathVariable int idCoche) {
        try {
            Coche cocheToDelete = new Coche();
            cocheToDelete.setIdCoche(idCoche);
            cocheRepository.delete(cocheToDelete);
            return new ResponseEntity<>("Coche eliminado correctamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el coche", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}



