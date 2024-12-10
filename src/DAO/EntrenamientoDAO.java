package DAO;

import java.io.File;

public class EntrenamientoDAO {
    private final File file;
    private DATABASE.ListaEntrenamiento listaEntrenamiento;

    public EntrenamientoDAO(String filePath) {
        this.file = new File(filePath);
        this.listaEntrenamiento = new DATABASE.ListaEntrenamiento();

        if (file.exists()) {
            cargar();
        }
    }
    //Cargarlos entrenamientos desde el archivo XML
    public void cargar() {
        try{

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void guardar() {
        try{

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
