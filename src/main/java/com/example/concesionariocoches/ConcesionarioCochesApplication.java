package com.example.concesionariocoches;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;


@SpringBootApplication
public class ConcesionarioCochesApplication implements CommandLineRunner {
    @Autowired
    private CocheRepository cocheRepository;
    @Autowired
    private ConductorRepository conductorRepository;
    @Autowired
    private ConcesionarioController concesionarioController;

    public static void main(String[] args) {
        SpringApplication.run(ConcesionarioCochesApplication.class, args);
    }


    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("========== Menú Principal ==========");
            System.out.println("1. Insertar los registros iniciales");
            System.out.println("2. SELECT toda la info de todos los conductores");
            System.out.println("3. SELECT toda la info de todos los coches");
            System.out.println("4. SELECT toda la info de los coches conducidos por un conductor específico");
            System.out.println("5. SELECT toda la info de los conductores de un coche determinado");
            System.out.println("6. SELECT toda la info de los coches cuyo conductor tenga más de 10 años de carnet");
            System.out.println("7. SELECT toda la info de los coches cuyo conductor tenga menos de 25 años");
            System.out.println("8. SELECT toda la info de los conductores cuyo nombre empieza por J, su apellido por E y tienen menos de 5 años de carnet");
            System.out.println("9. SELECT toda la info de los conductores que tienen entre 3 y 6 años de carnet");
            System.out.println("10. UPDATE la dirección de un conductor");
            System.out.println("11. DELETE un coche");
            System.out.println("12. Salir");
            System.out.println("====================================");
            opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    System.out.println("1. Insertar los registros iniciales");
                    insertarRegistrosIniciales();
                    break;
                case 2:
                    System.out.println("2. SELECT toda la info de todos los conductores");
                    List<Conductor> todosLosConductores = concesionarioController.getAllConductores();
                    imprimirInfoConductores(todosLosConductores);
                    break;
                case 3:
                    System.out.println("3. SELECT toda la info de todos los coches");
                    List<Coche> todosLosCoches = concesionarioController.getAllCoches();
                    imprimirInfoCoches(todosLosCoches);
                    break;
                case 4:
                    System.out.println("4. SELECT toda la info de los coches conducidos por un conductor específico");
                    System.out.println("Ingrese la ID del conductor:");
                    int idConductor = scanner.nextInt();
                    System.out.println("Los coches conducidos por el usuario con id: " + idConductor + " son:");
                    List<Coche> cochesConductor = concesionarioController.getCochesByConductor(idConductor);
                    if (!cochesConductor.isEmpty()) {
                        imprimirInfoCoches(cochesConductor);
                    } else {
                        System.out.println("No se ha encontrado al conductor");
                    }
                    break;
                case 5:
                    System.out.println("5. SELECT toda la info de los conductores de un coche determinado");
                    System.out.println("Ingrese la ID del coche:)");
                    int idCoche = scanner.nextInt();
                    List<Conductor> conductoresCoche = concesionarioController.getConductoresByCoche(idCoche);
                    System.out.println("Los conductores del coche con id: " + idCoche + " son:");
                    if (!conductoresCoche.isEmpty()) {
                        imprimirInfoConductores(conductoresCoche);
                    } else {
                        System.out.println("No se ha encontrado el coche");
                    }
                    break;
                case 6:
                    System.out.println("6. SELECT toda la info de los coches cuyo conductor tenga más de 10 años de carnet");
                    List<Coche> cochesConConductorMasDe10AnosCarnet = concesionarioController.getCochesConConductorMasDe10AnosCarnet();
                    imprimirInfoCoches(cochesConConductorMasDe10AnosCarnet);
                    break;
                case 7:
                    System.out.println("7. SELECT toda la info de los coches cuyo conductor tenga menos de 25 años");
                    List<Coche> cochesConConductorMenosDe25Anos = concesionarioController.getCochesConConductorMenosDe25Anos();
                    imprimirInfoCoches(cochesConConductorMenosDe25Anos);
                    break;
                case 8:
                    System.out.println("7. SELECT toda la info de los conductores cuyo nombre empieza por J, su apellido por E y tienen menos de 5 años de carnet.");
                    List<Conductor> conductoresFiltro = concesionarioController.getConductoresPorFiltro();
                    imprimirInfoConductores(conductoresFiltro);
                    break;
                case 9:
                    System.out.println("9. SELECT toda la info de los conductores que tienen entre 3 y 6 años de carnet");
                    List<Conductor> conductoresEntre3y6 = concesionarioController.getConductoresConCarnetEntre3y6Anos();
                    imprimirInfoConductores(conductoresEntre3y6);
                    break;
                case 10:
                    System.out.println("10. UPDATE la dirección de un conductor");
                    System.out.println("Ingrese la ID del conductor:");
                    int idConductorUpdate = scanner.nextInt();
                    System.out.println("Ingrese la nueva dirección del conductor:");
                    System.out.println("Calle:");
                    String nuevaCalle = scanner.next();
                    System.out.println("Código Postal:");
                    int nuevoCp = scanner.nextInt();
                    System.out.println("Localidad:");
                    String nuevaLocalidad = scanner.next();
                    System.out.println("Provincia:");
                    String nuevaProvincia = scanner.next();
                    System.out.println("Número:");
                    int nuevoNumero = scanner.nextInt();

                    Direccion nuevaDireccion = new Direccion();
                    nuevaDireccion.setCalle(nuevaCalle);
                    nuevaDireccion.setCp(nuevoCp);
                    nuevaDireccion.setLocalidad(nuevaLocalidad);
                    nuevaDireccion.setProvincia(nuevaProvincia);
                    nuevaDireccion.setNumero(nuevoNumero);
                    concesionarioController.updateDireccionConductor(idConductorUpdate, nuevaDireccion);
                    break;
                case 11:
                    System.out.println("11. DELETE un coche");
                    System.out.println("Ingrese la ID del coche a eliminar:");
                    int idCocheDelete = scanner.nextInt();
                    concesionarioController.deleteCoche(idCocheDelete);
                    break;
                case 12:
                    System.out.println("Programa finalizado...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }

        } while (opcion != 12);

        scanner.close();
    }

    private void insertarRegistrosIniciales() {

        // COCHES

        Coche mercedesAMG = new Coche();
        mercedesAMG.setMatricula("AB123CD");
        mercedesAMG.setMarca("Mercedes");
        mercedesAMG.setModelo("AMG");

        Coche bmwX6 = new Coche();
        bmwX6.setMatricula("XY987ZA");
        bmwX6.setMarca("BMW");
        bmwX6.setModelo("X6");

        Coche opelCorsa = new Coche();
        opelCorsa.setMatricula("LM456OP");
        opelCorsa.setMarca("Opel");
        opelCorsa.setModelo("Corsa");

        Coche peugeot2008 = new Coche();
        peugeot2008.setMatricula("PQ789RS");
        peugeot2008.setMarca("Peugeot");
        peugeot2008.setModelo("2008");

        Coche fordFiesta = new Coche();
        fordFiesta.setMatricula("JK321UV");
        fordFiesta.setMarca("Ford");
        fordFiesta.setModelo("Fiesta");

        Coche renaultClio = new Coche();
        renaultClio.setMatricula("CD654FG");
        renaultClio.setMarca("Renault");
        renaultClio.setModelo("Clio");

        Coche nissanGTR = new Coche();
        nissanGTR.setMatricula("WX987YZ");
        nissanGTR.setMarca("Nissan");
        nissanGTR.setModelo("GTR");

        Coche lamborghiniUrus = new Coche();
        lamborghiniUrus.setMatricula("MN123AB");
        lamborghiniUrus.setMarca("Lamborghini");
        lamborghiniUrus.setModelo("Urus");

        Coche suzukiJimny = new Coche();
        suzukiJimny.setMatricula("EF456GH");
        suzukiJimny.setMarca("Suzuki");
        suzukiJimny.setModelo("Jimny");

        Coche volkswagenGolf = new Coche();
        volkswagenGolf.setMatricula("UV789IJ");
        volkswagenGolf.setMarca("Volkswagen");
        volkswagenGolf.setModelo("Golf");

        //CONDUCTORES

        Conductor miguel = new Conductor();
        miguel.setNombre("Miguel");
        miguel.setApellidos("Moreno Columé");
        Direccion direccionMiguel = new Direccion();
        direccionMiguel.setCalle("Cañaveral");
        direccionMiguel.setCp(21449);
        direccionMiguel.setLocalidad("Lepe");
        direccionMiguel.setProvincia("Huelva");
        direccionMiguel.setNumero(678568345);
        miguel.setDireccion(direccionMiguel);
        miguel.setAnyosCarnet(1);
        miguel.setFechaNacimiento(Date.valueOf("2003-11-02"));
        miguel.getCoches().add(lamborghiniUrus);
        miguel.getCoches().add(opelCorsa);
        miguel.getCoches().add(peugeot2008);

        Conductor jaime = new Conductor();
        jaime.setNombre("Jaime");
        jaime.setApellidos("Escobar García");
        Direccion direccionJaime = new Direccion();
        direccionJaime.setCalle("Barrios");
        direccionJaime.setCp(21410);
        direccionJaime.setLocalidad("Isla Cristina");
        direccionJaime.setProvincia("Huelva");
        direccionJaime.setNumero(655498697);
        jaime.setDireccion(direccionJaime);
        jaime.setAnyosCarnet(4);
        jaime.setFechaNacimiento(Date.valueOf("1997-07-21"));
        jaime.getCoches().add(mercedesAMG);
        jaime.getCoches().add(volkswagenGolf);

        Conductor gonzalo = new Conductor();
        gonzalo.setNombre("Gonzalo");
        gonzalo.setApellidos("Gómez Rodríguez");
        Direccion direccionGonzalo = new Direccion();
        direccionGonzalo.setCalle("Buena Vista");
        direccionGonzalo.setCp(21400);
        direccionGonzalo.setLocalidad("Ayamonte");
        direccionGonzalo.setProvincia("Huelva");
        direccionGonzalo.setNumero(695325698);
        gonzalo.setDireccion(direccionGonzalo);
        gonzalo.setAnyosCarnet(13);
        gonzalo.setFechaNacimiento(Date.valueOf("1976-01-12"));
        gonzalo.getCoches().add(fordFiesta);
        gonzalo.getCoches().add(nissanGTR);

        Conductor enrique = new Conductor();
        enrique.setNombre("Enrique");
        enrique.setApellidos("López Gordillo");
        Direccion direccionEnrique = new Direccion();
        direccionEnrique.setCalle("Arnau");
        direccionEnrique.setCp(21410);
        direccionEnrique.setLocalidad("Isla Cristina");
        direccionEnrique.setProvincia("Huelva");
        direccionEnrique.setNumero(644576871);
        enrique.setDireccion(direccionEnrique);
        enrique.setAnyosCarnet(5);
        enrique.setFechaNacimiento(Date.valueOf("1996-09-23"));
        enrique.getCoches().add(renaultClio);
        enrique.getCoches().add(bmwX6);
        enrique.getCoches().add(suzukiJimny);

        conductorRepository.saveAndFlush(miguel);
        conductorRepository.saveAndFlush(jaime);
        conductorRepository.saveAndFlush(gonzalo);
        conductorRepository.saveAndFlush(enrique);

    }

    private void imprimirInfoConductores(List<Conductor> conductores) {
        for (Conductor conductor : conductores) {
            System.out.println("ID Conductor: " + conductor.getIdConductor());
            System.out.println("Nombre: " + conductor.getNombre());
            System.out.println("Apellidos: " + conductor.getApellidos());
            System.out.println("Calle: " + conductor.getDireccion().getCalle());
            System.out.println("CP: " + conductor.getDireccion().getCp());
            System.out.println("Localidad: " + conductor.getDireccion().getLocalidad());
            System.out.println("Provincia: " + conductor.getDireccion().getProvincia());
            System.out.println("Número: " + conductor.getDireccion().getNumero());
            System.out.println("Años de Carnet: " + conductor.getAnyosCarnet());
            System.out.println("Fecha de Nacimiento: " + conductor.getFechaNacimiento());

            System.out.println("Coches:");
            for (Coche coche : conductor.getCoches()) {
                System.out.println("  Modelo: " + coche.getModelo());
                System.out.println("  Matrícula: " + coche.getMatricula());
                System.out.println("  Marca: " + coche.getMarca());
                System.out.println("");
            }
            System.out.println("====================================");
        }
    }

    private void imprimirInfoCoches(List<Coche> coches) {
        for (Coche coche : coches) {
            System.out.println("ID Coche: " + coche.getIdCoche());
            System.out.println("Matrícula: " + coche.getMatricula());
            System.out.println("Marca: " + coche.getMarca());
            System.out.println("Modelo: " + coche.getModelo());

            System.out.println("Conductores:");
            for (Conductor conductor : coche.getConductores()) {
                System.out.println("  Nombre del Conductor: " + conductor.getNombre());
                System.out.println("");
            }
            System.out.println("====================================");
        }
    }
}




