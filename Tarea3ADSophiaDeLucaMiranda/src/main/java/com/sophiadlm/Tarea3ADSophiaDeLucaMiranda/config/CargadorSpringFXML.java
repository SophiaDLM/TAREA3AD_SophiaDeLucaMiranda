package com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.config;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ResourceBundle;

@Component
public class CargadorSpringFXML {
	private final ResourceBundle rb;
    private final ApplicationContext ac;

    @Autowired
    public CargadorSpringFXML(ResourceBundle rb, ApplicationContext ac) {
        this.rb = rb;
        this.ac = ac;
    }

    public Parent cargar(String rutaFxml) throws IOException {
        FXMLLoader cargador = new FXMLLoader();

        cargador.setControllerFactory(ac::getBean);
        cargador.setResources(rb);
        cargador.setLocation(getClass().getResource(rutaFxml));

        return cargador.load();
    }
}