package com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.controlador;

import com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.config.ManejadorEscenas;
import com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.modelo.Peregrino;
import com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.modelo.SesionUsuario;
import com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.vista.VistaFxml;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.ProcessingInstruction;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

@Controller
public class PeregrinoControlador implements Initializable {
    //SIN TERMINAR

    @Lazy
    @Autowired
    private ManejadorEscenas me;

    @Autowired
    private SesionUsuario su;

    @FXML
    public void cerrarSesion() {
        me.cambiarEscena(VistaFxml.INICIARSESION);
        su.setCredenciales(null);
    }

    @FXML
    public void mostrarAyuda() {
        Alert sinImplementar = new Alert(Alert.AlertType.INFORMATION);
        sinImplementar.setTitle("Ayuda No Implementada");
        sinImplementar.setHeaderText("¡Oops!");
        sinImplementar.setContentText("La ayuda para el usuario aún no está disponible");
        sinImplementar.showAndWait();
    }

    @FXML
    public void editarPeregrino() {
        Alert sinImplementar = new Alert(Alert.AlertType.INFORMATION);
        sinImplementar.setTitle("Edición No Implementada");
        sinImplementar.setHeaderText("¡Oops!");
        sinImplementar.setContentText("La edición aún no está disponible");
        sinImplementar.showAndWait();
    }

//    @FXML
//    public void exportarCarnetXML(Peregrino peregrino) {
//        try {
//            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//            DocumentBuilder db = dbf.newDocumentBuilder();
//            DOMImplementation implementacion = db.getDOMImplementation();
//
//            Document documento = implementacion.createDocument(null, "carnet", null);
//            Element carnet = documento.getDocumentElement();
//
//            documento.setXmlVersion("1.0");
//            ProcessingInstruction pi = documento.createProcessingInstruction("xml-stylesheet", "type=\"text/xml\" href=\"test.xsl\"");
//            documento.insertBefore(pi, carnet);
//
//            Element idCarnet = documento.createElement("id");
//            idCarnet.setTextContent(String.valueOf(p.getId()));
//            carnet.appendChild(idCarnet);
//
//            Element fechaExpedicion = documento.createElement("fechaexp");
//            fechaExpedicion.setTextContent(String.valueOf(p.getCarnet().getFechaexp()));
//            carnet.appendChild(fechaExpedicion);
//
//            Element expedidoEn = documento.createElement("expedidoen");
//            expedidoEn.setTextContent(p.getCarnet().getParadaInicial().getNombre());
//            carnet.appendChild(expedidoEn);
//
//            Element pereg = documento.createElement("peregrino");
//
//            Element nombre = documento.createElement("nombre");
//            nombre.setTextContent(p.getNombre());
//            pereg.appendChild(nombre);
//
//            Element nacionalidad = documento.createElement("nacionalidad");
//            nacionalidad.setTextContent(p.getNacionalidad());
//            pereg.appendChild(nacionalidad);
//
//            Element hoy = documento.createElement("hoy");
//            hoy.setTextContent(LocalDate.now().toString());
//            carnet.appendChild(hoy);
//
//            Element distanciaTotal = documento.createElement("distanciatotal");
//            distanciaTotal.setTextContent(String.valueOf(p.getCarnet().getDistancia()));
//            carnet.appendChild(distanciaTotal);
//
//            Element paradas = documento.createElement("paradas");
//            for(int i = 0; i < p.getListaParadas().size(); i++) {
//                Parada paradaObj = p.getListaParadas().get(i);
//
//                Element parada = documento.createElement("parada");
//
//                Element orden = documento.createElement("orden");
//                orden.setTextContent(String.valueOf(i+1));
//                parada.appendChild(orden);
//
//                Element nomParada = documento.createElement("nombre");
//                nomParada.setTextContent(paradaObj.getNombre());
//                parada.appendChild(nomParada);
//
//                Element regParada = documento.createElement("region");
//                regParada.setTextContent(String.valueOf(paradaObj.getRegion()));
//                parada.appendChild(regParada);
//
//                paradas.appendChild(parada);
//            }
//            carnet.appendChild(paradas);
//
//            Element estancias = documento.createElement("estancias");
//            for(int i = 0; i < p.getListaEstancias().size(); i++) {
//                Estancia estanciaObj = p.getListaEstancias().get(i);
//
//                Element estancia = documento.createElement("estancia");
//
//                Element idEstancia = documento.createElement("id");
//                idEstancia.setTextContent(String.valueOf(estanciaObj.getId()));
//                estancia.appendChild(idEstancia);
//
//                Element fecha = documento.createElement("fecha");
//                fecha.setTextContent(String.valueOf(estanciaObj.getFecha()));
//                estancia.appendChild(fecha);
//
//                Element paradaNombre = documento.createElement("parada");
//                paradaNombre.setTextContent(estanciaObj.getParada().getNombre());
//                estancia.appendChild(paradaNombre);
//
//                Element vip = documento.createElement("vip");
//                if (estanciaObj.isVip()) {
//                    estancia.appendChild(vip);
//                }
//                estancias.appendChild(estancia);
//            }
//            carnet.appendChild(estancias);
//
//            DOMSource source = new DOMSource(documento);
//            File fichero = new File("src/main/exportable/"+p.getNombre().toLowerCase()+".xml");
//            StreamResult sr = new StreamResult(fichero);
//
//            TransformerFactory tf = TransformerFactory.newInstance();
//            Transformer t = tf.newTransformer();
//            t.transform(source, sr);
//
//            System.out.println(">> Carnet exportado con éxito");
//
//        } catch (ParserConfigurationException pce) {
//            System.out.println(">> ERROR - "+pce.getMessage());
//        } catch (TransformerConfigurationException tce) {
//            System.out.println(">> ERROR - "+tce.getMessage());
//        } catch (TransformerException te) {
//            System.out.println(">> ERROR - "+te.getMessage());
//        } catch (Exception e) {
//            System.out.println(">> ERROR - "+e.getMessage());
//        }
//    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
