package com.sophiadlm.Tarea3ADSophiaDeLucaMiranda;

import com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.config.ManejadorEscenas;
import com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.vista.VistaFxml;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

//FALTA DE COMMITS DEBIDO A QUE TUVE UN PROBLEMA CON ECLIPSE Y TUVE QUE HACER EL RESTO DEL PROYECTO EN INTELLIJ
@SpringBootApplication
public class Tarea3AdSophiaDeLucaMirandaApplication extends Application {
	protected ConfigurableApplicationContext contextoAplicacion;
	protected ManejadorEscenas me;

	@Override
	public void init() throws Exception {
		contextoAplicacion = contextoAplicacionSpringBoot();
	}

	public static void main(final String[] args) {
		Application.launch(args);
		System.out.println("\nFUNCIONANDO\n");
	}

	@Override
	public void start(Stage escenaPrincipal) throws IOException {
		me = contextoAplicacion.getBean(ManejadorEscenas.class, escenaPrincipal);
		mostrarEscenaPrincipal();
	}

	protected void mostrarEscenaPrincipal() {
		me.cambiarEscena(VistaFxml.INICIARSESION);
	}

	private ConfigurableApplicationContext contextoAplicacionSpringBoot() {
		SpringApplicationBuilder constructor = new SpringApplicationBuilder(Tarea3AdSophiaDeLucaMirandaApplication.class);
		String[] args = getParameters().getRaw().stream().toArray(String[]::new);
		return constructor.run(args);
	}
}