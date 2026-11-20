package Modelo;

import java.io.Serializable;


public final class Empresa extends Cliente {
    private static final long serialVersionUID = 1L;
    private Contacto suContacto;

    public Empresa(String numeroIdentificacion, String nombre, String nombreCiudad, Contacto suContacto) {
        super(numeroIdentificacion, nombre, nombreCiudad);
        setSuContacto(suContacto);
    }

    public Contacto getSuContacto() { return suContacto; }
    public void setSuContacto(Contacto suContacto) {
        this.suContacto = Validacion.requerido(suContacto, "Contacto");
    }

    @Override
    public String toString() {
        return "Empresa" + " [" + super.toString() + "]" + "\n  suContacto: " + suContacto;
    }
}
