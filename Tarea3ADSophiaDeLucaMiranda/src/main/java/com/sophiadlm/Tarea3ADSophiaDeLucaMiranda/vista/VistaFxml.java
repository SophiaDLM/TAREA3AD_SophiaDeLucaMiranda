package com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.vista;

import java.util.ResourceBundle;

public enum VistaFxml {

    INICIARSESION {
        @Override
        public String getTitulo() {
            return getStringDelResourceBundle("iniciarsesion.titulo");
        }

        @Override
        public String getArchivoFxml() {
            return "/fxml/IniciarSesion.fxml";
        }
    },

    ADMINISTRADOR {
        @Override
        public String getTitulo() {
            return getStringDelResourceBundle("administrador.titulo");
        }

        @Override
        public String getArchivoFxml() {
            return "/fxml/Administrador.fxml";
        }
    },

    PEREGRINO {
        @Override
        public String getTitulo() {
            return getStringDelResourceBundle("peregrino.titulo");
        }

        @Override
        public String getArchivoFxml() {
            return "/fxml/Peregrino.fxml";
        }
    },

    PARADA {
        @Override
        public String getTitulo() {
            return getStringDelResourceBundle("parada.titulo");
        }

        @Override
        public String getArchivoFxml() {
            return "/fxml/Parada.fxml";
        }
    };

    public abstract String getTitulo();

    public abstract String getArchivoFxml();

    public String getStringDelResourceBundle(String clave) {
        return ResourceBundle.getBundle("bundle").getString(clave);
    }
}
