package UI;
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
                case 1 -> dao.getAllEntrenamientos();
                case 2 -> añadirEntrenamiento();
                case 3 -> modificarEntrenamiento();
                case 4 -> eliminarEntrenamiento();
                case 5 -> dao.mostrarEstadisticas();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }
    private void añadirEntrenamiento() {
        int id = Lector.pedirInt("Ingrese el ID:");
        String nombre = Lector.pedirString("Ingrese el nombre:");
        int duracion = Lector.pedirInt("Ingrese la duración:");
        String nivel = Lector.pedirString("Ingrese el nivel:");

        dao.addEntrenamiento(new Entrenamiento(id, nombre, duracion, nivel));
        System.out.println("Entrenamiento añadido correctamente.");
    }
    private void modificarEntrenamiento() {
        int id = Lector.pedirInt("Ingrese el ID del entrenamiento a modificar:");
        String nombre = Lector.pedirString("Ingrese el nuevo nombre:");
        int duracion = Lector.pedirInt("Ingrese la nueva duración:");
        String nivel = Lector.pedirString("Ingrese el nuevo nivel:");

        dao.updateEntrenamiento(id, new Entrenamiento(id, nombre, duracion, nivel));
        System.out.println("Entrenamiento modificado correctamente.");
    }

    private void eliminarEntrenamiento() {
        int id = Lector.pedirInt("Ingrese el ID del entrenamiento a eliminar:");
        dao.deleteEntrenamiento(id);
        System.out.println("Entrenamiento eliminado correctamente.");
    }
}