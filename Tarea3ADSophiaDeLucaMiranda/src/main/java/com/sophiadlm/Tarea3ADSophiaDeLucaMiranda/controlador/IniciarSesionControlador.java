package com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.controlador;

import com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.config.ManejadorEscenas;
import com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.modelo.*;
import com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.servicios.CarnetServicio;
import com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.servicios.CredencialesServicio;
import com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.servicios.ParadaServicio;
import com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.servicios.PeregrinoServicio;
import com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.vista.VistaFxml;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/***
 *
 */
@Controller
public class IniciarSesionControlador implements Initializable {

    //Elementos relacionados con el archivo FXML:
    @FXML
    private GridPane panelPrincipal;

    @FXML
    private TextField tfUsuario;

    @FXML
    private PasswordField pfContraseña;

    @FXML
    private GridPane panelRegistrarse;

    @FXML
    private TextField tfUsuarioP;

    @FXML
    private PasswordField pfContraseñaP;

    @FXML
    private TextField tfNombre;

    @FXML
    private ChoiceBox<String> cbNacionalidad;

    @FXML
    private ChoiceBox<String> cbParadaInicial;

    //Elementos relacionados con el manejo de las escenas:
    @Lazy
    @Autowired
    private ManejadorEscenas me;

    //Elementos relacionados con la manipulación de la base de datos:
    @Autowired
    private CredencialesServicio cs;

    @Autowired
    private SesionUsuario su;

    @Autowired
    private PeregrinoServicio pes;

    @Autowired
    private CarnetServicio cas;

    @Autowired
    private ParadaServicio pas;


    @FXML
    public void mostrarAyuda() {
        Alert sinImplementar = new Alert(Alert.AlertType.INFORMATION);
        sinImplementar.setTitle("Ayuda No Implementada");
        sinImplementar.setHeaderText("¡Oops!");
        sinImplementar.setContentText("La ayuda para el usuario aún no está disponible");
        sinImplementar.showAndWait();
    }

    @FXML
    public void volver() {
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmación Para Volver");
        confirmacion.setContentText("¿Está seguro que desea volver a la pantalla principal?");

        ButtonType confirmar = confirmacion.showAndWait().orElse(ButtonType.CANCEL);

        if (confirmar == ButtonType.OK) {
            cambiarPanelRegistrarse();
        }
    }

    @FXML
    private void cambiarPanelIniciarSesion() {
        panelPrincipal.setVisible(true);
        panelRegistrarse.setVisible(false);
    }

    @FXML
    private void cambiarPanelRegistrarse() {
        panelPrincipal.setVisible(false);
        panelRegistrarse.setVisible(true);
    }

    @FXML
    public void iniciarSesion() {
        Credenciales credenciales = cs.encontrarPorNombreUsuario(tfUsuario.getText());
        su.setCredenciales(credenciales);

        if(cs.autenticar(tfUsuario.getText(), pfContraseña.getText()).equals(TipoUsuario.ADMINISTRADOR)) {
            me.cambiarEscena(VistaFxml.ADMINISTRADOR);
        } else if(cs.autenticar(tfUsuario.getText(), pfContraseña.getText()).equals(TipoUsuario.PARADA)) {
            me.cambiarEscena(VistaFxml.PARADA);
        } else if(cs.autenticar(tfUsuario.getText(), pfContraseña.getText()).equals(TipoUsuario.PEREGRINO)) {
            me.cambiarEscena(VistaFxml.PEREGRINO);
        } else {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Error");
            error.setHeaderText("No se ha encontrado al usuario");
            error.setContentText("Regístrese o asegúrese que ha introducido la contraseña correctamente");
            error.showAndWait();
        }
    }

    //EDITAR MAÑANA
    @FXML
    public void nuevoPeregrino() {
        try {
            String usuario = tfUsuarioP.getText();
            String contraseña = pfContraseñaP.getText();
            String nombre = tfNombre.getText();
            String nacionalidad = cbNacionalidad.getSelectionModel().getSelectedItem();
            String paradaSeleccionada = cbParadaInicial.getSelectionModel().getSelectedItem();

            if (usuario.matches("[a-zA-Z0-9_]+")) {
                if (contraseña.matches("[a-zA-Z0-9_]{8}")) {
                    if (validarNombre(nombre)) {
                        if (nacionalidad != null) {
                            if (paradaSeleccionada != null) {
                                String[] campos = paradaSeleccionada.split(" - ");
                                Long idParada = Long.parseLong(campos[0]);

                                Parada paradaInicial = pas.encontrarPorId(idParada);
                                if (paradaInicial != null) {
                                    Credenciales nuevasCredenciales = new Credenciales(usuario, contraseña, TipoUsuario.PEREGRINO);
                                    nuevasCredenciales = cs.guardar(nuevasCredenciales);

                                    Peregrino nuevoPeregrino = new Peregrino(nuevasCredenciales.getId(), nombre, nacionalidad);
                                    nuevoPeregrino = pes.guardar(nuevoPeregrino);

                                    Carnet nuevoCarnet = new Carnet(nuevoPeregrino.getId()); //NOOO TOCAR POR NADA DEL MUNDO
                                    nuevoCarnet.setParadaInicial(paradaInicial);
                                    nuevoCarnet = cas.guardar(nuevoCarnet);

                                    //AGREGAR PEREGRINO-PARADA INSERCIÓN DE DATOS -> DESPUÉS CUANDO SE PONGA A PUNTA EL CÓDIGO

                                    //NO LANZA LA ALERTA ????
                                    Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
                                    confirmacion.setTitle("Operación exitosa");
                                    confirmacion.setHeaderText("Se ha registrado el usuario y la parada exitosamente");

                                } else {
                                    Alert error = new Alert(Alert.AlertType.ERROR);
                                    error.setTitle("Error");
                                    error.setHeaderText("Parada inicial inválida");
                                    error.setContentText("La parada inicial no ha sido encontrada en la base de datos");
                                    error.showAndWait();
                                }

                            } else {
                                Alert error = new Alert(Alert.AlertType.ERROR);
                                error.setTitle("Error");
                                error.setHeaderText("Parada inicial inválida");
                                error.setContentText("La parada inicial no puede estar vacia");
                                error.showAndWait();
                            }

                        } else {
                            Alert error = new Alert(Alert.AlertType.ERROR);
                            error.setTitle("Error");
                            error.setHeaderText("Nacionalidad inválida");
                            error.setContentText("La nacionalidad no puede estar vacia");
                            error.showAndWait();
                        }

                    } else {
                        Alert error = new Alert(Alert.AlertType.ERROR);
                        error.setTitle("Error");
                        error.setHeaderText("Nombre inválido");
                        error.setContentText("El nombre no puede estar vacio");
                        error.showAndWait();
                    }

                } else {
                    Alert error = new Alert(Alert.AlertType.ERROR);
                    error.setTitle("Error");
                    error.setHeaderText("Contraseña inválida");
                    error.setContentText("Debe contener números, letras, símbolos especiales como _, ! o ? y una longitud de 8 caracteres");
                    error.showAndWait();

                }

            } else {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("Error");
                error.setHeaderText("Nombre de usuario inválido");
                error.setContentText("El usuario solo puede tener números, letras y guión bajo");
                error.showAndWait();
            }

        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cambiarPanelIniciarSesion();

        List<String> paisesNacionalidad = leerPaisesXML();
        cbNacionalidad.getItems().addAll(paisesNacionalidad);

        List<Parada> listaParadas = pas.encontrarTodos();
        ObservableList<String> elementosParada = FXCollections.observableArrayList();

        for(Parada indice: listaParadas) {
            String campos = indice.getId() + " - " + indice.getNombre() + " - " + indice.getRegion() + " - " +indice.getResponsable();
            elementosParada.add(campos);
        }

        cbParadaInicial.getItems().addAll(elementosParada);
    }

    private boolean validarNombre(String nombre) {
        if(nombre.matches("[a-zA-Z ]+")) {
            String nombreSinEspacios = nombre.trim();
            if(!nombreSinEspacios.isEmpty()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private List<String> leerPaisesXML() {
        List<String> listaPaisesXML = new ArrayList<>();

        try {
            File archivoXML = new File("C:\\Users\\Sophi\\Downloads\\TAREA3AD_SophiaDeLucaMiranda-master\\TAREA3AD_SophiaDeLucaMiranda-master\\Tarea3ADSophiaDeLucaMiranda\\src\\main\\resources\\paises.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document documento = db.parse(archivoXML);

            NodeList listaPaises = documento.getElementsByTagName("pais");

            for(int i = 0; i < listaPaises.getLength(); i++) {
                Element elementoPais = (Element) listaPaises.item(i);

                String idPais = elementoPais.getElementsByTagName("id").item(0).getTextContent();
                String nombrePais = elementoPais.getElementsByTagName("nombre").item(0).getTextContent();

                listaPaisesXML.add(idPais + " - " + nombrePais);
            }

        } catch (ParserConfigurationException pce) {
            System.out.println("Error: " + pce.getMessage());
        } catch (IOException ioe) {
            System.out.println("Error: " + ioe.getMessage());
        } catch (SAXException saxe) {
            System.out.println("Error: " + saxe.getMessage());
        }

        return listaPaisesXML;
    }
}
