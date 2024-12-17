package DATABASE;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class XML {
    private final String ruta;
    private static File file = null;

    public XML(String ruta) {
        this.ruta = ruta;
        this.file = new File(ruta);
    }

    // Leer el XML
    public static void leer() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);

            doc.getDocumentElement().normalize();

            Element root = doc.getDocumentElement();
            System.out.println("Nodo raíz: " + root.getNodeName());

            NodeList nodeList = root.getElementsByTagName("entrenamiento");

            int i = 0;
            Node node;
            Element elemento;
            String id, nombre, duracion, nivel;

            while (i < nodeList.getLength()) {
                node = nodeList.item(i);

                if (node != null && node.getNodeType() == Node.ELEMENT_NODE) {
                    elemento = (Element) node;

                    // Obtener valores de los subnodos
                    id = elemento.getAttribute("id");
                    nombre = getNodeValue(elemento, "nombre");
                    duracion = getNodeValue(elemento, "duracion");
                    nivel = getNodeValue(elemento, "nivel");

                    // Imprimir los datos del entrenamiento
                    System.out.println("ID: " + id);
                    System.out.println("Nombre: " + (nombre != null ? nombre : "No especificado"));
                    System.out.println("Duración: " + (duracion != null ? duracion : "0"));
                    System.out.println("Nivel: " + (nivel != null ? nivel : "No especificado"));
                }

                i++;
            }

        } catch (Exception e) {
            System.out.println("Error al leer el archivo XML: " + e.getMessage());
            e.printStackTrace();
        }
    }


    // Escribir en el XML
    public static void escribir(String id, String nombre, String duracion, String nivel) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);

            Element nuevoEntrenamiento = doc.createElement("entrenamiento");
            nuevoEntrenamiento.setAttribute("id", id);

            Element nombreElemento = doc.createElement("nombre");
            nombreElemento.appendChild(doc.createTextNode(nombre));
            nuevoEntrenamiento.appendChild(nombreElemento);

            Element duracionElemento = doc.createElement("duracion");
            duracionElemento.appendChild(doc.createTextNode(duracion));
            nuevoEntrenamiento.appendChild(duracionElemento);

            Element nivelElemento = doc.createElement("nivel");
            nivelElemento.appendChild(doc.createTextNode(nivel));
            nuevoEntrenamiento.appendChild(nivelElemento);

            doc.getDocumentElement().appendChild(nuevoEntrenamiento);

            guardarDocumento(doc);

            System.out.println("Nodo añadido correctamente.");
        } catch (Exception e) {
            System.out.println("Error al añadir el nodo al archivo XML: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Modificar un nodo del XML
    public static void modificar(String id, String nuevoNombre, String nuevaDuracion) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);

            NodeList nodeList = doc.getElementsByTagName("entrenamiento");

            boolean encontrado = false;
            Node node = null;
            Element elemento = null;
            int i = 0;

            while (i < nodeList.getLength() && !encontrado) {
                node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    elemento = (Element) node;

                    if (elemento.getAttribute("id").equals(id)) {
                        elemento.getElementsByTagName("nombre").item(0).setTextContent(nuevoNombre);
                        elemento.getElementsByTagName("duracion").item(0).setTextContent(nuevaDuracion);
                        encontrado = true;
                    }
                }

                i++;
            }

            if (encontrado) {
                guardarDocumento(doc);
                System.out.println("Entrenamiento modificado correctamente.");
            } else {
                System.out.println("Entrenamiento con ID " + id + " no encontrado.");
            }

        } catch (Exception e) {
            System.out.println("Error al modificar el entrenamiento: " + e.getMessage());
            e.printStackTrace();
        }
    }



    // Eliminar un nodo del XML
    public static void eliminar(String id) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);

            NodeList nodeList = doc.getElementsByTagName("entrenamiento");

            boolean encontrado = false;
            Node node = null;
            Element elemento = null;
            int i = 0;

            while (i < nodeList.getLength() && !encontrado) {
                node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    elemento = (Element) node;

                    if (elemento.getAttribute("id").equals(id)) {
                        elemento.getParentNode().removeChild(elemento);
                        encontrado = true;
                    }
                }

                i++;
            }

            if (encontrado) {
                guardarDocumento(doc);
                System.out.println("Entrenamiento eliminado correctamente.");
            } else {
                System.out.println("Entrenamiento con ID " + id + " no encontrado.");
            }

        } catch (Exception e) {
            System.out.println("Error al eliminar el entrenamiento: " + e.getMessage());
            e.printStackTrace();
        }
    }



    // Guardar el documento XML
    public static void guardarDocumento(Document documento) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty("indent", "yes");
            DOMSource source = new DOMSource(documento);
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);

            System.out.println("Documento guardado correctamente.");
        } catch (TransformerException e) {
            System.out.println("Error al guardar el documento: " + e.getMessage());
            e.printStackTrace();
        }
    }
    private static String getNodeValue(Element element, String tagName) {
        NodeList nodeList = element.getElementsByTagName(tagName);
        Node node;
        if (nodeList.getLength() > 0) {
             node = nodeList.item(0);
            if (node != null) {
                return node.getTextContent();
            }
        }
        return null;
    }

    public static File getFile() {
        return file;
    }
}

