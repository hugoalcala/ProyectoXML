package UI;
import DATABASE.Entrenamiento;
import DAO.EntrenamientoDAO;

public class Menu {
    private final DAO.EntrenamientoDAO entrenamientoDAO;

    public Menu(DAO.EntrenamientoDAO entrenamientoDAO) {
        this.entrenamientoDAO = entrenamientoDAO;
    }

    public void mostrarMenu() {
        int opcion;

        do {
            opcion = Lector.pedirInt("Menu" +
                    "\nIntroduzca una opción" +
                    "\n1 Ver entrenamientos" +
                    "\n2 Añadir entrenamiento" +
                    "\n3 Modificar entrenamiento" +
                    "\n4 Eliminar entrenamiento" +
                    "\n5 Ver estadisticas" +
                    "\n0 Salir");


            switch (opcion) {
                case 1 -> verEntrenamientos();
                case 2 -> añadirEntrenamiento();
                case 3 -> modificarEntrenamiento();
                case 4 -> eliminarEntrenamiento();
                case 5 -> entrenamientoDAO.mostrarEstadisticas();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void verEntrenamientos() {
        System.out.println("Lista de entrenamientos: ");
        entrenamientoDAO.mostrarEntrenamientos();
    }

    private void añadirEntrenamiento() {
        int id = Lector.pedirInt("Ingrese el ID del entrenamiento: ");
        String nombre = Lector.pedirString("Ingrese el nombre del entrenamiento: ");
        int duracion = Lector.pedirInt("Ingrese la duracion del entrenamiento: ");
        String nivel = Lector.pedirString("Ingrese el nivel del entrenamiento: ");

        entrenamientoDAO.addEntrenamiento(id, nombre, duracion, nivel);
    }
    private void modificarEntrenamiento() {
        int id = Lector.pedirInt("Ingrese el ID del entrenamiento a modificar:");
        String nombre = Lector.pedirString("Ingrese el nuevo nombre:");
        int duracion = Lector.pedirInt("Ingrese la nueva duración:");
        String nivel = Lector.pedirString("Ingrese el nuevo nivel:");

        entrenamientoDAO.modificarEntrenamiento(id, nombre, duracion);
    }

    private void eliminarEntrenamiento() {
        int id = Lector.pedirInt("Ingrese el ID del entrenamiento a eliminar:");
        entrenamientoDAO.eliminarEntrenamiento(id);
    }
    private void mostrarEstadisticas() {
        entrenamientoDAO.mostrarEstadisticas();
    }
}