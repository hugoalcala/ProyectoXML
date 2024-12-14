package DAO;

import DATABASE.XML;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class EntrenamientoDAO {
    private final XML xml;

    public EntrenamientoDAO() {
        this.xml = new XML("XML/entrenamiento.xml");
        if (!xml.getFile().exists()){
            inicializarArchivo();
        }
    }
    public void mostrarEntrenamientos() {
        XML.leer();
    }

    public void addEntrenamiento(int id, String nombre, int duracion, String nivel) {
        XML.escribir(String.valueOf(id), nombre, String.valueOf(duracion), nivel);
    }

    public void modificarEntrenamiento(int id, String nuevoNombre, int nuevaDuracion) {
        XML.modificar(String.valueOf(id), nuevoNombre, String.valueOf(nuevaDuracion));
    }

    public void eliminarEntrenamiento(int id) {
        XML.eliminar(String.valueOf(id));
    }
    public void mostrarEstadisticas() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xml.getFile());

            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("entrenamiento");

            if (nodeList == null || nodeList.getLength() == 0) {
                System.out.println("No hay entrenamientos registrados.");
                return;
            }

            int totalEntrenamientos = nodeList.getLength();
            int sumaDuracion = 0;
            int i = 0;
            Node node;
            Element elemento;
            int duracion;

            while (i < nodeList.getLength()) {
                node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    elemento = (Element) node;
                    duracion = Integer.parseInt(elemento.getElementsByTagName("duracion").item(0).getTextContent());
                    sumaDuracion += duracion;
                }

                i++;
            }

            double promedioDuracion = (double) sumaDuracion / totalEntrenamientos;

            // Mostrar las estadísticas
            System.out.println("=== Estadísticas de Entrenamientos ===");
            System.out.println("Total de entrenamientos: " + totalEntrenamientos);
            System.out.println("Duración total: " + sumaDuracion + " minutos");
            System.out.printf("Promedio de duración: %.2f minutos%n", promedioDuracion);

        } catch (Exception e) {
            System.out.println("Error al calcular las estadísticas: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void inicializarArchivo() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            Element root = doc.createElement("listaEntrenamientos");
            doc.appendChild(root);

            xml.guardarDocumento(doc);

            System.out.println("Archivo XML inicializado correctamente.");
        } catch (Exception e) {
            System.err.println("Error al inicializar el archivo XML: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
