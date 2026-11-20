package Modelo;

import java.io.Serializable;


public final class PersonaNatural extends Cliente {
    private static final long serialVersionUID = 1L;
    private String numeroTelefono;
    private String correoElectronico;

    public PersonaNatural(String numeroIdentificacion, String nombre, String nombreCiudad, String numeroTelefono, String correoElectronico) {
        super(numeroIdentificacion, nombre, nombreCiudad);
        setNumeroTelefono(numeroTelefono);
        setCorreoElectronico(correoElectronico);
    }

    public String getNumeroTelefono() { return numeroTelefono; }
    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = Validacion.texto(numeroTelefono, "numeroTelefono");
    }

    public String getCorreoElectronico() { return correoElectronico; }
    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = Validacion.texto(correoElectronico, "correoElectronico");
    }

    @Override
    public String toString() {
        return "PersonaNatural" + " [" + super.toString() + "]" + "\n  numeroTelefono: " + numeroTelefono + "\n  correoElectronico: " + correoElectronico;
    }
}
