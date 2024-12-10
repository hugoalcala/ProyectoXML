package DATABASE;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;

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


    public void agregar(Entrenamiento entrenamiento) {
        try {
            File file = new File("XML/entrenamiento.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);

            Element root = doc.getDocumentElement();

// Crear un nuevo elemento "entrenamiento"
            Element nuevoEntrenamiento = doc.createElement("entrenamiento");
            nuevoEntrenamiento.setAttribute("id", String.valueOf(entrenamiento.getId()));

            Element nombre = doc.createElement("nombre");
            nombre.setTextContent(entrenamiento.getNombre());

            Element duracion = doc.createElement("duracion");
            duracion.setTextContent(String.valueOf(entrenamiento.getDuracion()));

            Element nivel = doc.createElement("nivel");
            nivel.setTextContent(entrenamiento.getNivel());

            nuevoEntrenamiento.appendChild(nombre);
            nuevoEntrenamiento.appendChild(duracion);
            nuevoEntrenamiento.appendChild(nivel);

            root.appendChild(nuevoEntrenamiento);

// Guardar los cambios en el archivo XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void eliminar() {
        try{
            File file = new File("XML/entrenamiento.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);
            Element root = doc.getDocumentElement();

            NodeList nodeList = root.getElementsByTagName("entrenamiento");

            while (nodeList.getLength() > 0) {

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void editar(){
        try {
            File file = new File("XML/entrenamiento.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);
            Element root = doc.getDocumentElement();

            NodeList nodeList = root.getElementsByTagName("entrenamiento");

            while (nodeList.getLength() > 0) {

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void leerTodo(){
        try{
            File file = new File("XML/entrenamiento.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);

            NodeList nodeList = doc.getElementsByTagName("entrenamiento");

            while (nodeList.getLength() > 0) {

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
