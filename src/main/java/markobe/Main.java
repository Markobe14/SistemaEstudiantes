package markobe;

import markobe.datos.EstudianteDAO;
import markobe.dominio.Estudiante;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        var salir = false;
        var consola = new Scanner(System.in);
        // Se crea una instancia de clase de servicio
        var estudianteDao = new EstudianteDAO();
        while(!salir){
            try{
                mostrarMenu();
                salir = ejecutarOpciones(consola, estudianteDao);

            } catch (Exception e){
                System.out.println("Ocurrio un error al ejecutar operacion: " + e.getMessage());
            }
            System.out.println();
        }
    }

    private static void mostrarMenu(){
        System.out.print("""
                *** Sistema de Estudiantes ***
                1. Listar Estudiantes
                2. Buscar Estudiante
                3. Agregar Estudiante
                4. Modificar Estudiante
                5. Eliminar Estudiante
                6. Salir
                Elige una opcion: 
                """);
    }

    private static boolean ejecutarOpciones(Scanner consola, EstudianteDAO estudianteDAO){
        var opcion = Integer.parseInt(consola.nextLine());
        var salir = false;
        switch (opcion){
            case 1 -> { //Listar estudiantes
                System.out.println("Listado de Estudiantes...");
                var estudiantes = estudianteDAO.listarEstudiantes();
                estudiantes.forEach(System.out::println);
            }
            case 2 -> { //Buscar estudiante por Id
                System.out.print("Introduce el id_estudiante a buscar: ");
                var idEstudiante = Integer.parseInt(consola.nextLine());
                var estudiante = new Estudiante(idEstudiante);
                var encontrado = estudianteDAO.buscarEstudiantePorId(estudiante);
                if (encontrado)
                    System.out.println("Estudiante encontrado: " + estudiante);
                else
                    System.out.println("Estudiante NO encontrado: " + estudiante);
            }
            case 3 -> { //Agregar Estudiante
                System.out.println("Agregar Estudiante...");
                System.out.print("Nombre: ");
                var nombre = consola.nextLine();
                System.out.print("Apellido: ");
                var apellido = consola.nextLine();
                System.out.print("Telefono: ");
                var telefono = consola.nextLine();
                System.out.print("Email: ");
                var email = consola.nextLine();

                var estudiante = new Estudiante(nombre, apellido, telefono, email);
                var agregado = estudianteDAO.agregarEstudiante(estudiante);
                if (agregado)
                    System.out.println("Estudiante agregado: " + estudiante);
                else
                    System.out.println("Estudiante NO agregado: " + estudiante);
            }
            case 4 -> { //Modificar Estudiante
                System.out.println("Modificar Estudiante...");
                System.out.print("Introduce el id_estudiante a modificar: ");
                var idEstudiante = Integer.parseInt(consola.nextLine());
                System.out.println("Introduce los nuevos datos...");
                System.out.print("Nombre: ");
                var nombre = consola.nextLine();
                System.out.print("Apellido: ");
                var apellido = consola.nextLine();
                System.out.print("Telefono: ");
                var telefono = consola.nextLine();
                System.out.print("Email: ");
                var email = consola.nextLine();

                var estudiante = new Estudiante(idEstudiante, nombre, apellido, telefono,email);
                var modificado = estudianteDAO.modificarEstudiante(estudiante);
                if (modificado)
                    System.out.println("Estudiante modificado: " + estudiante);
                else
                    System.out.println("Estudiante NO modificado: " + estudiante);
            }
            case 5 -> { //Eliminar Estudiante
                System.out.println("Eliminar Estudiante...");
                System.out.print("Introduce el Id_estudiante a eliminar: ");
                var idEstudiante = Integer.parseInt(consola.nextLine());
                var estudiante = new Estudiante(idEstudiante);
                var eliminado = estudianteDAO.eliminarEstudiante(estudiante);
                if (eliminado)
                    System.out.println("Estudiante eliminado: " + estudiante);
                else
                    System.out.println("Estudiante NO eliminado: " + estudiante);
            }
            case 6 -> { //Salir
                System.out.println("Hasta pronto!");
                salir = true;
            }
            default -> System.out.println("Opcion no reconocida");
        }
        return salir;
    }

}