package BUSINESS_LOGIC;

import DAO.EntrenamientoDAO;
import UI.Menu;

public class Controlador {
    public  void procesar() {
        // Crear una instancia de EntrenamientoDAO
        EntrenamientoDAO entrenamientoDAO = new EntrenamientoDAO();

        // Crear una instancia del menú con el DAO
        Menu menu = new Menu(entrenamientoDAO);

        // Procesar el menú
        menu.mostrarMenu();
    }
}
