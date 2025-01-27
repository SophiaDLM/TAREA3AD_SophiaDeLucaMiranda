package com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.config;

import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.io.IOException;
import java.util.ResourceBundle;

@Configuration
public class AppJavaConfig {
    @Autowired
    CargadorSpringFXML cSpringFXML;

    @Bean
    public ResourceBundle rb() {
        return ResourceBundle.getBundle("bundle");
    }

    @Bean
    @Lazy(value = true)
    public ManejadorEscenas me(Stage escena) throws IOException {
        return new ManejadorEscenas(cSpringFXML, escena);
    }
}
