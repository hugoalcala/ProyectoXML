package DATABASE;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class ListaEntrenamiento {
    private ArrayList<Entrenamiento> entrenamientoList;

    public ListaEntrenamiento() {
        this.entrenamientoList =new ArrayList<Entrenamiento>();
    }

    public ArrayList<Entrenamiento> getEntrenamientoList() {
        return entrenamientoList;
    }

    public void setEntrenamientoList(ArrayList<Entrenamiento> entrenamientoList) {
        this.entrenamientoList = entrenamientoList;
    }

    @Override
    public String toString() {
        return "ListaEntrenamiento{" +
                "entrenamientoList=" + entrenamientoList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ListaEntrenamiento that = (ListaEntrenamiento) o;
        return Objects.equals(entrenamientoList, that.entrenamientoList);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(entrenamientoList);
    }
}
