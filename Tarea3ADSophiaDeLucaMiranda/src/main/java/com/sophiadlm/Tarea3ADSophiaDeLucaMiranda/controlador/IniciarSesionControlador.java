package com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.controlador;

import com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.config.ManejadorEscenas;
import com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.modelo.Credenciales;
import com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.modelo.Parada;
import com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.modelo.TipoUsuario;
import com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.servicios.CredencialesServicio;
import com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.vista.VistaFxml;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class IniciarSesionControlador implements Initializable {
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

    @Autowired
    private CredencialesServicio credServicio;

    @Lazy
    @Autowired
    private ManejadorEscenas me;

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
            me.cambiarEscena(VistaFxml.INICIARSESION);
        }
    }

    @FXML
    public void iniciarSesion() {
        if(credServicio.autenticar(tfUsuario.getText(), pfContraseña.getText()).equals(TipoUsuario.ADMINISTRADOR)) {
            me.cambiarEscena(VistaFxml.ADMINISTRADOR);
        } else if(credServicio.autenticar(tfUsuario.getText(), pfContraseña.getText()).equals(TipoUsuario.PARADA)) {
            me.cambiarEscena(VistaFxml.PARADA);
        } else if(credServicio.autenticar(tfUsuario.getText(), pfContraseña.getText()).equals(TipoUsuario.PEREGRINO)) {
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
        String nombre = tfNombre.getText();
        char region = tfRegion.getText().charAt(0);
        String usuario = tfUsuario.getText();
        String contraseña = pfContraseña.getText();

        if(validarNombre(nombre)) {
            if(Character.isLetter(region)) {
                if(usuario.matches("[a-zA-Z0-9_]+")) {
                    if(contraseña.matches("[a-zA-Z0-9_]{8}")) {
                        Credenciales nuevasCredenciales = new Credenciales(usuario, contraseña, TipoUsuario.PARADA);
                        nuevasCredenciales = cs.guardar(nuevasCredenciales);

                        Parada nuevaParada = new Parada(nuevasCredenciales.getId(), nombre, region, usuario);
                        nuevaParada = ps.guardar(nuevaParada);

                        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
                        confirmacion.setTitle("Operación exitosa");
                        confirmacion.setHeaderText("Se ha registrado el usuario y la parada exitosamente");

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

            } else {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("Error");
                error.setHeaderText("Región inválida");
                error.setContentText("La región solo puede ser una letra");
                error.showAndWait();
            }

        } else {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Error");
            error.setHeaderText(null);
            error.setContentText("El nombre no puede estar vacio");
            error.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
