package com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.modelo;

import org.springframework.stereotype.Component;

@Component
public class SesionUsuario {
    private Credenciales credenciales;

    public Credenciales getCredenciales() {
        return credenciales;
    }

    public void setCredenciales(Credenciales credenciales) {
        this.credenciales = credenciales;
    }
}
