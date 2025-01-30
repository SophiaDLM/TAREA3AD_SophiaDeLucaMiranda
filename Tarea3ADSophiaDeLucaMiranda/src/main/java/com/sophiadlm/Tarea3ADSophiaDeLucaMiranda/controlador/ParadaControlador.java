package com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.controlador;

import com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.config.ManejadorEscenas;
import com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.modelo.Parada;
import com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.modelo.SesionUsuario;
import com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.vista.VistaFxml;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class ParadaControlador implements Initializable {
    @Autowired
    private SesionUsuario su;

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
    public void exportarDatosParada(Parada paradaActual) {

    }


    @FXML
    public void sellarAlojarse() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
