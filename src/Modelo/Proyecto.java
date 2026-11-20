package Modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;


public class Proyecto implements Serializable {
    private static final long serialVersionUID = 1L;
    private String codigo;
    private String titulo;
    private LocalDate fechaInicio;
    private String unidadDuracion;
    private byte duracionEstimada;
    private Cliente suCliente;
    private Empleado suGerente;
    private ArrayList<Empleado> suEquipo;
    private String estado;

    public Proyecto(String codigo, String titulo, LocalDate fechaInicio,
            String unidadDuracion, byte duracionEstimada, Cliente suCliente,
            Empleado suGerente, ArrayList<Empleado> suEquipo) {
        setCodigo(codigo);
        setTitulo(titulo);
        setFechaInicio(fechaInicio);
        actualizarDuracion(unidadDuracion, duracionEstimada);
        setSuCliente(suCliente);
        setSuGerente(suGerente);
        setSuEquipo(suEquipo);
        estado = "INICIADO";
    }

    public Proyecto(String codigo, String titulo, LocalDate fechaInicio,
            String unidadDuracion, byte duracionEstimada, Cliente suCliente, Empleado suGerente) {
        this(codigo, titulo, fechaInicio, unidadDuracion, duracionEstimada,
                suCliente, suGerente, new ArrayList<Empleado>());
    }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) {
        if (codigo == null || !codigo.matches("[0-9]{4}-[0-9]{3}") || codigo.endsWith("-000")) {
            throw new IllegalArgumentException("El código debe tener formato aaaa-001 a aaaa-999.");
        }
        this.codigo = codigo;
    }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) {
        String valor = Validacion.texto(titulo, "Título");
        if (valor.length() < 20 || valor.length() > 100) {
            throw new IllegalArgumentException("El título debe tener entre 20 y 100 caracteres.");
        }
        this.titulo = valor;
    }
    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = Validacion.requerido(fechaInicio, "Fecha de inicio");
    }
    public String getUnidadDuracion() { return unidadDuracion; }
    public void setUnidadDuracion(String unidadDuracion) {
        actualizarDuracion(unidadDuracion, duracionEstimada);
    }
    public byte getDuracionEstimada() { return duracionEstimada; }
    public void setDuracionEstimada(byte duracionEstimada) {
        actualizarDuracion(unidadDuracion, duracionEstimada);
    }

    /** Cambia ambos valores juntos, sin dejar combinaciones inválidas. */
    public final void actualizarDuracion(String unidad, byte cantidad) {
        boolean valido = ("semanas".equals(unidad) || "años".equals(unidad))
                ? cantidad >= 1 && cantidad <= 3
                : "meses".equals(unidad) && cantidad >= 1 && cantidad <= 35
                    && cantidad != 12 && cantidad != 24;
        if (!valido) throw new IllegalArgumentException(
                "Duración: semanas o años de 1 a 3; meses de 1 a 35, excepto 12 y 24.");
        unidadDuracion = unidad;
        duracionEstimada = cantidad;
    }
    public Cliente getSuCliente() { return suCliente; }
    public void setSuCliente(Cliente suCliente) { this.suCliente = Validacion.requerido(suCliente, "Cliente"); }
    public Empleado getSuGerente() { return suGerente; }
    public void setSuGerente(Empleado suGerente) { this.suGerente = Validacion.requerido(suGerente, "Gerente"); }
    public ArrayList<Empleado> getSuEquipo() { return new ArrayList<Empleado>(suEquipo); }
    public void setSuEquipo(ArrayList<Empleado> equipo) {
        Validacion.requerido(equipo, "Equipo");
        ArrayList<Empleado> copia = new ArrayList<Empleado>();
        for (Empleado integrante : equipo) {
            Validacion.requerido(integrante, "Integrante");
            for (Empleado existente : copia) {
                if (existente.getNumeroIdentificacion().equals(integrante.getNumeroIdentificacion())) {
                    throw new IllegalArgumentException("El integrante ya está registrado en el equipo.");
                }
            }
            copia.add(integrante);
        }
        suEquipo = copia;
    }
    public String getEstado() { return estado; }
    public void setEstado(String estado) {
        if (!Arrays.asList("INICIADO", "EN CURSO", "CANCELADO", "FINALIZADO", "SUSPENDIDO", "APLAZADO").contains(estado)) {
            throw new IllegalArgumentException("Estado no permitido.");
        }
        this.estado = estado;
    }
    public LocalDate determinarFechaFinalizacion() {
        switch (unidadDuracion) {
            case "semanas": return fechaInicio.plusWeeks(duracionEstimada);
            case "meses": return fechaInicio.plusMonths(duracionEstimada);
            default: return fechaInicio.plusYears(duracionEstimada);
        }
    }
    public void agregarIntegrante(Empleado nuevoIntegrante) {
        ArrayList<Empleado> nuevoEquipo = getSuEquipo();
        nuevoEquipo.add(nuevoIntegrante);
        setSuEquipo(nuevoEquipo);
    }
    public Empleado eliminarIntegrante(String numeroIdentificacion) {
        for (int i = 0; i < suEquipo.size(); i++) {
            if (suEquipo.get(i).getNumeroIdentificacion().equals(numeroIdentificacion)) {
                return suEquipo.remove(i);
            }
        }
        return null;
    }
    @Override
    public String toString() {
        String cadena = "PROYECTO " + codigo + " | " + estado
                + "\nTítulo: " + titulo + "\nInicio: " + fechaInicio
                + "\nDuración estimada: " + duracionEstimada + " " + unidadDuracion
                + "\nFinalización estimada: " + determinarFechaFinalizacion()
                + "\nCLIENTE\n" + suCliente + "\nGERENTE\n" + suGerente + "\nEQUIPO";
        if (suEquipo.isEmpty()) cadena += "\nSin integrantes registrados.";
        for (Empleado integrante : suEquipo) cadena += "\n" + integrante;
        return cadena;
    }
}
