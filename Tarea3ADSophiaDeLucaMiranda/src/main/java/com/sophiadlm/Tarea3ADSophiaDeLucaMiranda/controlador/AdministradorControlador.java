package com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.controlador;

import com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.config.ManejadorEscenas;
import com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.modelo.Credenciales;
import com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.modelo.Parada;
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

/***
 * Clase AdministradorControlador que se encarga de manejar las acciones
 * disponibles de un administrador como lo es acceder a la ayuda de usuario
 * (no implementada aún), al método para añadir una nueva parada a la base de datos
 * y a cerrar sesión si así lo desea.
 *
 * Esta clase implementa Initializable para el uso de JavaFX.
 */
@Controller
public class AdministradorControlador implements Initializable {

    //Elementos relacionados con el archivo FXML:
    @FXML
    private TextField tfNombre;

    @FXML
    private TextField tfRegion;

    @FXML
    private TextField tfUsuario;

    @FXML
    private PasswordField pfContraseña;

    //Elementos relacionados con el manejo de las escenas:
    @Lazy
    @Autowired
    private ManejadorEscenas me;

    //Elementos relacionados con la manipulación de la base de datos:
    @Autowired
    private CredencialesServicio cs;

    @Autowired
    private ParadaServicio ps;


    /***
     * Método mostrarAyuda que, como está incompleto, sólo se encarga
     * de mostrar una alerta informativa.
     */
    @FXML
    public void mostrarAyuda() {
        Alert sinImplementar = new Alert(Alert.AlertType.INFORMATION);
        sinImplementar.setTitle("Ayuda No Implementada");
        sinImplementar.setHeaderText("¡Oops!");
        sinImplementar.setContentText("La ayuda para el usuario aún no está disponible");
        sinImplementar.showAndWait();
    }


    /***
     * Método cerrarSesion que lanza una alerta pidiendo al usuario que
     * confirme su decisión. Si el usuario pulsa en el botón de aceptar, se
     * camibia la ventana a la de INICIARSESION.
     *
     * En este método no se maneja en sí la sesión del usuario, puesto que
     * sólo hay un administrador general y en ninguno de los casos de uso se
     * especifica que se deba mostrar información suya en pantalla (Además de
     * que esto supondría un grave problema en cuanto a seguridad).
     */
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


    /***
     * Método initialize que no ha sido utilizado porque en este caso no es
     * necesario, pero es obligatorio implementarlo. En casos en los que se necesite
     * cargar algún dato en la pantalla apenas arranque la aplicación, sería
     * dentro de este método que se colocaría.
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    /***
     * MODIFICAR LUEGO
     * @param nombre
     * @return
     */
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
}
