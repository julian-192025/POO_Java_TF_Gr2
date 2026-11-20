package Modelo;

import java.io.Serializable;


public abstract class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;
    protected String numeroIdentificacion;
    protected String nombre;
    protected String nombreCiudad;

    public Cliente(String numeroIdentificacion, String nombre, String nombreCiudad) {
        setNumeroIdentificacion(numeroIdentificacion);
        setNombre(nombre);
        setNombreCiudad(nombreCiudad);
    }

    public String getNumeroIdentificacion() { return numeroIdentificacion; }
    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = Validacion.texto(numeroIdentificacion, "numeroIdentificacion");
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) {
        this.nombre = Validacion.texto(nombre, "nombre");
    }

    public String getNombreCiudad() { return nombreCiudad; }
    public void setNombreCiudad(String nombreCiudad) {
        this.nombreCiudad = Validacion.texto(nombreCiudad, "nombreCiudad");
    }

    @Override
    public String toString() {
        return "Cliente" + "\n  numeroIdentificacion: " + numeroIdentificacion + "\n  nombre: " + nombre + "\n  nombreCiudad: " + nombreCiudad;
    }
}
