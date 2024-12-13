package DATABASE;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class XML {
    private final String ruta;
    public XML(String ruta){
        this.ruta = ruta;
    }
    //Leer el XML
    public void leer(){
        try{
           File file = new File(ruta);
           DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
           DocumentBuilder builder = factory.newDocumentBuilder();
           Document doc = builder.parse(file);

           doc.getDocumentElement().normalize();

           Element root = doc.getDocumentElement();
           System.out.println("Nodo raiz: " + root.getNodeName());

           NodeList nodeList = root.getElementsByTagName("entrenamiento");

           int i = 0;
           while (i < nodeList.getLength()){
               Node node = nodeList.item(i);
               if (node.getNodeType() == Node.ELEMENT_NODE){
                   Element elemento = (Element) node;

                   System.out.println("ID: " + elemento.getElementsByTagName("id").item(0).getTextContent());
                   System.out.println("Nombre: " + elemento.getElementsByTagName("nombre").item(0).getTextContent());
                   System.out.println("Duración: " + elemento.getElementsByTagName("duracion").item(0).getTextContent());
               }
           }

        }catch (Exception e){
            System.out.println("Error al leer el archivo XML: " + e.getMessage());
            e.printStackTrace();
        }
    }
    //Escribir en el xml
    public void escribir(String id, String nombre, String duracion){
        try{
            File file = new File(ruta);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);

            Element nuevoEntrenamiento = doc.createElement("entrenamiento");

            Element idElemento = doc.createElement("id");
            idElemento.appendChild(doc.createTextNode(String.valueOf(id)));
            nuevoEntrenamiento.appendChild(idElemento);

            Element nombreElemento = doc.createElement("nombre");
            nombreElemento.appendChild(doc.createTextNode(nombre));
            nuevoEntrenamiento.appendChild(nombreElemento);

            Element duracionElemento = doc.createElement("duracion");
            duracionElemento.appendChild(doc.createTextNode(duracion));
            nuevoEntrenamiento.appendChild(duracionElemento);

            doc.getDocumentElement().appendChild(nuevoEntrenamiento);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);

            System.out.println("Nodo añadido correctamente");
        }catch (Exception e){
            System.out.println("Error al añadir el nodo al archivo XML: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
