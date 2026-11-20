package Modelo;

import java.io.Serializable;


public class Contacto implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nombreCompleto;
    private String cargo;
    private String numeroTelefono;
    private String correoElectronico;

    public Contacto(String nombreCompleto, String cargo, String numeroTelefono, String correoElectronico) {
        setNombreCompleto(nombreCompleto);
        setCargo(cargo);
        setNumeroTelefono(numeroTelefono);
        setCorreoElectronico(correoElectronico);
    }

    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = Validacion.texto(nombreCompleto, "nombreCompleto");
    }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) {
        this.cargo = Validacion.texto(cargo, "cargo");
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
        return "Contacto" + "\n  nombreCompleto: " + nombreCompleto + "\n  cargo: " + cargo + "\n  numeroTelefono: " + numeroTelefono + "\n  correoElectronico: " + correoElectronico;
    }
}
