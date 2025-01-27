package com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.controlador;

import com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.config.ManejadorEscenas;
import com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.modelo.TipoUsuario;
import com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.servicios.CredencialesServicio;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
    private Button btnIniciarSesion;


    @Autowired
    private CredencialesServicio credServicio;

    @Lazy
    @Autowired
    private ManejadorEscenas me;

//    @FXML
//    private void IniciarSesion() {
//        if(credServicio.autenticar(tfUsuario.getText(), pfContraseña.getText()).equals(TipoUsuario.ADMINISTRADOR)) {
//            me.cambiarEscena(null);
//        }
//    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
