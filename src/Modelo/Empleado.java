package Modelo;

import java.io.Serializable;


public class Empleado implements Serializable {
    private static final long serialVersionUID = 1L;
    private String numeroIdentificacion;
    private String nombreCompleto;
    private String tituloProfesional;
    private byte añosExperiencia;

    public Empleado(String numeroIdentificacion, String nombreCompleto, String tituloProfesional, byte aniosExperiencia) {
        setNumeroIdentificacion(numeroIdentificacion);
        setNombreCompleto(nombreCompleto);
        setTituloProfesional(tituloProfesional);
        setAñosExperiencia(aniosExperiencia);
    }

    public String getNumeroIdentificacion() { return numeroIdentificacion; }
    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = Validacion.texto(numeroIdentificacion, "numeroIdentificacion");
    }

    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = Validacion.texto(nombreCompleto, "nombreCompleto");
    }

    public String getTituloProfesional() { return tituloProfesional; }
    public void setTituloProfesional(String tituloProfesional) {
        this.tituloProfesional = Validacion.texto(tituloProfesional, "tituloProfesional");
    }

    public byte getAñosExperiencia() { return añosExperiencia; }
    public void setAñosExperiencia(byte aniosExperiencia) {
        if (aniosExperiencia < 0) throw new IllegalArgumentException("La experiencia no puede ser negativa.");
        this.añosExperiencia = aniosExperiencia;
    }

    @Override
    public String toString() {
        return "Empleado" + "\n  numeroIdentificacion: " + numeroIdentificacion + "\n  nombreCompleto: " + nombreCompleto + "\n  tituloProfesional: " + tituloProfesional + "\n  aniosExperiencia: " + añosExperiencia;
    }
}
