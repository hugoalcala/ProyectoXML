package DAO;

import DATABASE.Entrenamiento;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class EntrenamientoDAO {
    private final File file;

    public EntrenamientoDAO() {
        this.file = new File("XML/entrenamiento.xml"); // Ruta al archivo XML
        if (!file.exists()) {
            inicializarArchivo(); // Crear archivo si no existe
        }
    }

    // Leer todos los entrenamientos desde el XML
    public List<Entrenamiento> getAllEntrenamientos() {
        List<Entrenamiento> entrenamientos = new ArrayList<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);

            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("entrenamiento");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    // Manejar nodos nulos para cada subnodo
                    String idText = getNodeValue(element, "id");
                    String nombre = getNodeValue(element, "nombre");
                    String duracionText = getNodeValue(element, "duracion");
                    String nivel = getNodeValue(element, "nivel");
                    
                    int id = Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent());
                    String nombre = element.getElementsByTagName("nombre").item(0).getTextContent();
                    int duracion = Integer.parseInt(element.getElementsByTagName("duracion").item(0).getTextContent());
                    String nivel = element.getElementsByTagName("nivel").item(0).getTextContent();

                    entrenamientos.add(new Entrenamiento(id, nombre, duracion, nivel));
                }
            }
        } catch (Exception e) {
            System.err.println("Error al leer el archivo XML: " + e.getMessage());
            e.printStackTrace();
        }
        return entrenamientos;
    }

    private String getNodeValue(Element element, String id) {

    }

    // Añadir un entrenamiento al XML
    public void addEntrenamiento(Entrenamiento entrenamiento) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);

            Element root = doc.getDocumentElement();

            Element nuevoEntrenamiento = doc.createElement("entrenamiento");

            Element idElement = doc.createElement("id");
            idElement.appendChild(doc.createTextNode(String.valueOf(entrenamiento.getId())));
            nuevoEntrenamiento.appendChild(idElement);

            Element nombreElement = doc.createElement("nombre");
            nombreElement.appendChild(doc.createTextNode(entrenamiento.getNombre()));
            nuevoEntrenamiento.appendChild(nombreElement);

            Element duracionElement = doc.createElement("duracion");
            duracionElement.appendChild(doc.createTextNode(String.valueOf(entrenamiento.getDuracion())));
            nuevoEntrenamiento.appendChild(duracionElement);

            Element nivelElement = doc.createElement("nivel");
            nivelElement.appendChild(doc.createTextNode(entrenamiento.getNivel()));
            nuevoEntrenamiento.appendChild(nivelElement);

            root.appendChild(nuevoEntrenamiento);

            guardarDocumento(doc);

        } catch (Exception e) {
            System.err.println("Error al añadir el entrenamiento al archivo XML: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Inicializar archivo XML vacío si no existe
    private void inicializarArchivo() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            Element root = doc.createElement("listaEntrenamientos");
            doc.appendChild(root);

            guardarDocumento(doc);

            System.out.println("Archivo XML inicializado correctamente.");
        } catch (Exception e) {
            System.err.println("Error al inicializar el archivo XML: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Guardar documento XML en el archivo
    private void guardarDocumento(Document doc) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(file);
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);

            System.out.println("Archivo XML guardado correctamente.");
        } catch (TransformerException e) {
            System.err.println("Error al guardar el archivo XML: " + e.getMessage());
            e.printStackTrace();
        }
    }
    // Actualizar un entrenamiento por ID
    public void updateEntrenamiento(int id, Entrenamiento nuevoEntrenamiento) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);

            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("entrenamiento");

            boolean encontrado = false;

            // Buscar el nodo con el ID especificado
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    int currentId = Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent());
                    if (currentId == id) {
                        // Actualizar los valores del nodo
                        element.getElementsByTagName("nombre").item(0).setTextContent(nuevoEntrenamiento.getNombre());
                        element.getElementsByTagName("duracion").item(0).setTextContent(String.valueOf(nuevoEntrenamiento.getDuracion()));
                        element.getElementsByTagName("nivel").item(0).setTextContent(nuevoEntrenamiento.getNivel());
                        encontrado = true;
                        break;
                    }
                }
            }

            if (encontrado) {
                guardarDocumento(doc);
                System.out.println("Entrenamiento actualizado correctamente.");
            } else {
                System.out.println("Entrenamiento con ID " + id + " no encontrado.");
            }
        } catch (Exception e) {
            System.err.println("Error al actualizar el entrenamiento: " + e.getMessage());
            e.printStackTrace();
        }
    }
    // Eliminar un entrenamiento por ID
    public void deleteEntrenamiento(int id) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);

            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("entrenamiento");

            boolean encontrado = false;

            // Buscar y eliminar el nodo con el ID especificado
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    int currentId = Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent());
                    if (currentId == id) {
                        element.getParentNode().removeChild(element);
                        encontrado = true;
                        break;
                    }
                }
            }

            if (encontrado) {
                guardarDocumento(doc);
                System.out.println("Entrenamiento eliminado correctamente.");
            } else {
                System.out.println("Entrenamiento con ID " + id + " no encontrado.");
            }
        } catch (Exception e) {
            System.err.println("Error al eliminar el entrenamiento: " + e.getMessage());
            e.printStackTrace();
        }
    }


    // Mostrar estadísticas sobre los entrenamientos
    public void mostrarEstadisticas() {
        try {
            // Leer todos los entrenamientos
            List<Entrenamiento> entrenamientos = getAllEntrenamientos();

            // Si no hay entrenamientos, mostrar mensaje
            if (entrenamientos.isEmpty()) {
                System.out.println("No hay entrenamientos registrados.");
                return;
            }

            // Calcular estadísticas
            int totalEntrenamientos = entrenamientos.size();
            int sumaDuraciones = entrenamientos.stream().mapToInt(Entrenamiento::getDuracion).sum();
            double promedioDuracion = (double) sumaDuraciones / totalEntrenamientos;

            // Mostrar estadísticas
            System.out.println("Estadísticas:");
            System.out.println("Número total de entrenamientos: " + totalEntrenamientos);
            System.out.println("Duración total: " + sumaDuraciones + " minutos");
            System.out.printf("Promedio de duración: %.2f minutos%n", promedioDuracion);

        } catch (Exception e) {
            System.err.println("Error al calcular las estadísticas: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
