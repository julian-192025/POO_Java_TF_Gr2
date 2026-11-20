package Pruebas;

import Modelo.*;
import java.time.LocalDate;


public class DepuracionProyecto {
    public static void main(String[] args) {
        Cliente cliente = new PersonaNatural("DEMO-C", "Cliente de demostración", "Cali", "5550100", "demo@example.com");
        Empleado gerente = new Empleado("DEMO-G", "Gerente de demostración", "Ingeniería", (byte) 3);
        Proyecto proyecto = new Proyecto("2026-001", "Proyecto para revisar fechas", LocalDate.of(2026, 1, 31),
                "meses", (byte) 1, cliente, gerente);
        LocalDate fin = proyecto.determinarFechaFinalizacion();
        System.out.println("Inicio: " + proyecto.getFechaInicio());
        System.out.println("Duracion: " + proyecto.getDuracionEstimada() + " " + proyecto.getUnidadDuracion());
        System.out.println("Fin esperado y obtenido: 2026-02-28 / " + fin);
        if (!fin.equals(LocalDate.of(2026, 2, 28))) throw new AssertionError("Fecha incorrecta");
    }
}
