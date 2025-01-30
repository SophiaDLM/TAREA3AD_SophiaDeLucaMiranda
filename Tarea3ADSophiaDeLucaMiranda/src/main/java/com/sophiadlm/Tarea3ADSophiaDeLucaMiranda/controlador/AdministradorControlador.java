package com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.controlador;

import com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.config.ManejadorEscenas;
import com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.modelo.Credenciales;
import com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.modelo.Parada;
import com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.modelo.SesionUsuario;
import com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.modelo.TipoUsuario;
import com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.servicios.CredencialesServicio;
import com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.servicios.ParadaServicio;
import com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.vista.VistaFxml;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class AdministradorControlador implements Initializable {
    @FXML
    private TextField tfNombre;

    @FXML
    private TextField tfRegion;

    @FXML
    private TextField tfUsuario;

    @FXML
    private PasswordField pfContraseña;

    @Lazy
    @Autowired
    private ManejadorEscenas me;

    @Autowired
    private CredencialesServicio cs;

    @Autowired
    private ParadaServicio ps;


    @FXML
    public void mostrarAyuda() {
        Alert sinImplementar = new Alert(Alert.AlertType.INFORMATION);
        sinImplementar.setTitle("Ayuda No Implementada");
        sinImplementar.setHeaderText("¡Oops!");
        sinImplementar.setContentText("La ayuda para el usuario aún no está disponible");
        sinImplementar.showAndWait();
    }

    @FXML
    public void cerrarSesion() {
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmación Cerrar Sesión");
        confirmacion.setContentText("¿Está seguro que desea cerrar sesión?");

        ButtonType confirmar = confirmacion.showAndWait().orElse(ButtonType.CANCEL);

        if (confirmar == ButtonType.OK) {
            me.cambiarEscena(VistaFxml.INICIARSESION);
        }
    }

    //SACAR MÉTODO PARA LAS ALERTAS Y EVITAR REPETICIÓN DE CÓDIGO
    @FXML
    public void nuevaParada() {
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

    private boolean validarNombre(String nombre) {
        if(nombre.matches("[a-zA-Z ]+")) {
            String nombreSinEspacios = nombre.trim();
            if(nombreSinEspacios.length() > 0) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
