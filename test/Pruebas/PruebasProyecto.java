package Pruebas;

import Modelo.*;
import Vista.UsaProyecto;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.*;
import javax.swing.SwingUtilities;


public class PruebasProyecto {
    private static int total;
    private static final Cliente PERSONA = new PersonaNatural("C1", "Cliente de prueba", "Cali", "5550100", "cliente@example.com");
    private static final Cliente EMPRESA = new Empresa("C2", "Empresa de prueba", "Cali", new Contacto("Contacto de prueba", "Coordinación", "5550200", "contacto@example.com"));
    private static final Empleado GERENTE = empleado("G1");

    private static Empleado empleado(String id) { return new Empleado(id, "Empleado " + id, "Ingeniería", (byte) 5); }
    private static Proyecto proyecto(String codigo, Cliente cliente) {
        return new Proyecto(codigo, "Sistema de información de prueba", LocalDate.of(2026, 1, 31), "meses", (byte) 1, cliente, GERENTE);
    }
    private static void verificar(String caso, boolean condicion) {
        if (!condicion) throw new AssertionError("FALLO: " + caso);
        total++;
        System.out.println(String.format("OK %02d - %s", total, caso));
    }
    private static void rechaza(String caso, Runnable accion) {
        try { accion.run(); }
        catch (IllegalArgumentException | IllegalStateException ex) { verificar(caso, true); return; }
        throw new AssertionError("FALLO: debía rechazar " + caso);
    }
    private static class VistaPrueba extends UsaProyecto {
        final Queue<String> respuestas = new LinkedList<String>();
        final Queue<Empleado> empleados = new LinkedList<Empleado>();
        final Queue<String[]> formularios = new LinkedList<String[]>();
        boolean cancelarEmpleado;
        boolean confirmacion;
        VistaPrueba(Path carpeta) { super(carpeta); }
        @Override protected String solicitar(String mensaje) { return respuestas.remove(); }
        @Override protected String elegir(String mensaje, String[] opciones) { return respuestas.remove(); }
        @Override protected boolean confirmar(String mensaje) { return confirmacion; }
        @Override protected void informar(String mensaje) { }
        @Override protected String[] formulario(String titulo, String[] etiquetas, String[] iniciales) { return formularios.remove(); }
        @Override protected Cliente capturarCliente(ArrayList<Proyecto> datos) { return PERSONA; }
        @Override protected Empleado capturarEmpleado(String titulo) {
            if (cancelarEmpleado || empleados.isEmpty()) throw new Cancelacion();
            return empleados.remove();
        }
        String cancelarEquipo(ArrayList<Proyecto> datos) {
            try { return registrarEquipoProyectoDadoCodigo(datos, datos.get(0).getCodigo()); }
            catch (Cancelacion ex) { return "cancelado"; }
        }
    }
    public static void main(String[] args) throws Exception {
        System.setOut(new java.io.PrintStream(System.out, true, "UTF-8"));
        System.out.println("PRUEBAS POO_Java_TF_Gr2");
        System.out.println("Fecha de ejecución: " + java.time.ZonedDateTime.now(java.time.ZoneId.of("America/Bogota")));
        System.out.println("Java: " + System.getProperty("java.version"));
        Path carpeta = Files.createTempDirectory("pruebas-proyecto-");
        final Throwable[] error = new Throwable[1];
        SwingUtilities.invokeAndWait(new Runnable() {
            @Override public void run() {
                VistaPrueba vista = new VistaPrueba(carpeta);
                try { ejecutar(vista, carpeta); }
                catch (Throwable ex) { error[0] = ex; }
                finally { vista.dispose(); }
            }
        });
        if (error[0] != null) throw new AssertionError("Pruebas fallidas", error[0]);
        System.out.println("RESULTADO: " + total + " comprobaciones correctas; 0 fallos.");
        System.out.println("Los archivos de prueba se generaron en una carpeta temporal aislada.");
    }
    private static void ejecutar(VistaPrueba v, Path carpeta) throws Exception {
        Proyecto p = proyecto("2026-001", PERSONA);
        ArrayList<Proyecto> datos = new ArrayList<Proyecto>();
        datos.add(p);
        verificar("Estado inicial INICIADO y equipo vacío", p.getEstado().equals("INICIADO") && p.getSuEquipo().isEmpty());
        verificar("Un mes desde enero 31 termina en febrero 28", p.determinarFechaFinalizacion().equals(LocalDate.of(2026, 2, 28)));
        p.actualizarDuracion("semanas", (byte) 3);
        verificar("Tres semanas equivalen a 21 días", p.determinarFechaFinalizacion().equals(LocalDate.of(2026, 2, 21)));
        p.setFechaInicio(LocalDate.of(2024, 2, 29));
        p.actualizarDuracion("años", (byte) 1);
        verificar("Cambio de año bisiesto", p.determinarFechaFinalizacion().equals(LocalDate.of(2025, 2, 28)));
        for (int meses : new int[]{1, 11, 13, 23, 25, 35}) {
            p.actualizarDuracion("meses", (byte) meses);
            verificar("Duración permitida: " + meses + " meses", p.getDuracionEstimada() == meses);
        }
        for (int meses : new int[]{0, 12, 24, 36, -1}) {
            rechaza("Duración inválida: " + meses + " meses", () -> p.actualizarDuracion("meses", (byte) meses));
        }
        rechaza("No admite cuatro semanas", () -> p.actualizarDuracion("semanas", (byte) 4));
        rechaza("No admite cuatro años", () -> p.actualizarDuracion("años", (byte) 4));
        rechaza("No admite una unidad distinta", () -> p.actualizarDuracion("dias", (byte) 1));
        rechaza("Setter conserva duración válida", () -> p.setUnidadDuracion("semanas"));
        verificar("Fallo de duración no altera el objeto", p.getUnidadDuracion().equals("meses") && p.getDuracionEstimada() == 35);
        p.setTitulo(String.join("", Collections.nCopies(20, "a")));
        verificar("Título mínimo de 20 caracteres", p.getTitulo().length() == 20);
        p.setTitulo(String.join("", Collections.nCopies(100, "a")));
        verificar("Título máximo de 100 caracteres", p.getTitulo().length() == 100);
        rechaza("Título corto", () -> p.setTitulo("corto"));
        rechaza("Título de 101 caracteres", () -> p.setTitulo(String.join("", Collections.nCopies(101, "a"))));
        rechaza("Código sin guion", () -> p.setCodigo("2026001"));
        rechaza("Consecutivo cero", () -> p.setCodigo("2026-000"));
        rechaza("Cliente obligatorio", () -> p.setSuCliente(null));
        rechaza("Gerente obligatorio", () -> p.setSuGerente(null));
        rechaza("Experiencia negativa", () -> new Empleado("1", "Nombre", "Título", (byte) -1));
        rechaza("Contacto obligatorio de empresa", () -> new Empresa("1", "Empresa", "Cali", null));
        verificar("Polimorfismo muestra contacto de empresa", EMPRESA.toString().contains("contacto@example.com"));
        verificar("Polimorfismo muestra datos de persona", PERSONA.toString().contains("cliente@example.com"));
        p.agregarIntegrante(empleado("E1"));
        rechaza("Integrante duplicado por identificación", () -> p.agregarIntegrante(empleado("E1")));
        rechaza("Integrante nulo", () -> p.agregarIntegrante(null));
        ArrayList<Empleado> copia = p.getSuEquipo();
        copia.clear();
        verificar("El getter no permite vaciar el equipo original", p.getSuEquipo().size() == 1);
        verificar("Retiro devuelve el empleado", p.eliminarIntegrante("E1").getNumeroIdentificacion().equals("E1"));
        verificar("Retiro inexistente devuelve null", p.eliminarIntegrante("E1") == null);
        for (String estado : new String[]{"INICIADO", "EN CURSO", "CANCELADO", "FINALIZADO", "SUSPENDIDO", "APLAZADO"}) {
            p.setEstado(estado);
            verificar("Estado permitido: " + estado, p.getEstado().equals(estado));
        }
        rechaza("Estado desconocido", () -> p.setEstado("ACTIVO"));
        verificar("Primer consecutivo del año", v.generarCodigoProyecto(new ArrayList<Proyecto>(), 2026).equals("2026-001"));
        datos.add(proyecto("2026-015", EMPRESA));
        datos.add(proyecto("2025-999", PERSONA));
        verificar("Consecutivo usa máximo, no tamaño", v.generarCodigoProyecto(datos, 2026).equals("2026-016"));
        verificar("Reinicio anual del consecutivo", v.generarCodigoProyecto(datos, 2027).equals("2027-001"));
        rechaza("Límite de 999 proyectos por año", () -> v.generarCodigoProyecto(datos, 2025));
        verificar("Consulta de todos incluye los tres proyectos", v.consultarTodosProyectos(datos).contains("(3)"));
        p.setEstado("EN CURSO");
        verificar("Filtro EN CURSO excluye los demás", v.consultarProyectosEnCurso(datos).contains("2026-001") && !v.consultarProyectosEnCurso(datos).contains("2026-015"));
        verificar("Primer proyecto", v.consultarPrimerProyecto(datos).contains("2026-001"));
        verificar("Último proyecto", v.consultarUltimoProyecto(datos).contains("2025-999"));
        verificar("Búsqueda por código exacto", v.consultarProyectoDadoCodigo(datos, "2026-015").contains("C2"));
        verificar("Código no encontrado", v.consultarProyectoDadoCodigo(datos, "2026-888").contains("no encontrado"));
        verificar("Consulta vacía", v.consultarPrimerProyecto(new ArrayList<Proyecto>()).contains("No hay"));
        verificar("Gerente del proyecto", v.consultarGerenteProyectoDadoCodigo(datos, "2026-001").contains("G1"));
        verificar("Equipo vacío", v.consultarEquipoProyectoDadoCodigo(datos, "2026-001").contains("Sin integrantes"));
        verificar("Filtro de gerente con tres coincidencias", v.consultarProyectosDadoNumIDEGerente(datos, "G1").contains("(3)"));
        verificar("Filtro de cliente con dos coincidencias", v.consultarProyectosDadoNumIDECliente(datos, "C1").contains("(2)"));
        verificar("Filtro Empresa", v.consultarProyectosDadoTipoCliente(datos, "Empresa").contains("2026-015") && !v.consultarProyectosDadoTipoCliente(datos, "Empresa").contains("2026-001"));
        verificar("Filtro PersonaNatural", v.consultarProyectosDadoTipoCliente(datos, "PersonaNatural").contains("(2)"));
        rechaza("Tipo de cliente incorrecto", () -> v.consultarProyectosDadoTipoCliente(datos, "Otro"));
        v.empleados.add(empleado("E1"));
        v.registrarIntegranteProyectoDadoCodigo(datos, "2026-001");
        verificar("Registro de integrante desde la vista", p.getSuEquipo().size() == 1);
        v.respuestas.add("2"); v.empleados.add(empleado("E2")); v.empleados.add(empleado("E3"));
        v.registrarEquipoProyectoDadoCodigo(datos, "2026-001");
        verificar("Registro de equipo conserva integrantes previos", p.getSuEquipo().size() == 3);
        v.respuestas.add("2"); v.empleados.add(empleado("E4"));
        verificar("Cancelación de equipo no agrega parcialmente", v.cancelarEquipo(datos).equals("cancelado") && p.getSuEquipo().size() == 3);
        v.respuestas.add("E2"); v.confirmacion = true;
        verificar("Retiro desde la vista", v.eliminarIntegranteProyectoDadoCodigo(datos, "2026-001").contains("retirado") && p.getSuEquipo().size() == 2);
        v.respuestas.add("FINALIZADO");
        v.actualizarEstadoProyectoDadoCodigo(datos, "2026-001");
        verificar("Cambio de estado desde la vista", p.getEstado().equals("FINALIZADO"));
        verificar("Proyecto inexistente no solicita empleados", v.registrarIntegranteProyectoDadoCodigo(datos, "2026-888").contains("no encontrado"));
        v.formularios.add(new String[]{"Registro completo de proyecto", "2026-11-20", "1"});
        v.respuestas.add("meses"); v.empleados.add(GERENTE); v.confirmacion = false;
        v.registrarProyecto(datos);
        verificar("Registro completo desde la vista", datos.size() == 4 && datos.get(3).getEstado().equals("INICIADO"));
        v.formularios.add(new String[]{"Registro cancelado de proyecto", "2026-11-20", "1"});
        v.respuestas.add("meses"); v.cancelarEmpleado = true;
        v.registrarProyecto(datos);
        verificar("Cancelación del registro no agrega proyecto", datos.size() == 4);
        v.cancelarEmpleado = false;
        v.formularios.add(new String[]{"Registro con fecha incorrecta", "2026-02-30", "1"}); v.respuestas.add("meses");
        rechaza("Fecha imposible en formulario", () -> v.registrarProyecto(datos));
        verificar("Fecha inválida no modifica la lista", datos.size() == 4);
        rechaza("Archivo inexistente reporta error", () -> v.leerArchivoDeObjetosProyectos());
        v.generarArchivoDeObjetosProyectos(datos);
        ArrayList<Proyecto> leidos = v.leerArchivoDeObjetosProyectos();
        verificar("Persistencia recupera todos los proyectos", leidos.size() == 4);
        verificar("Persistencia conserva equipo y estado", leidos.get(0).getSuEquipo().size() == 2 && leidos.get(0).getEstado().equals("FINALIZADO"));
        verificar("Persistencia conserva subclase Empresa y contacto", leidos.get(1).getSuCliente() instanceof Empresa && leidos.get(1).getSuCliente().toString().contains("contacto@example.com"));
        verificar("Persistencia conserva cliente compartido", leidos.get(0).getSuCliente() == leidos.get(2).getSuCliente());
        verificar("Consecutivo continúa después de leer", v.generarCodigoProyecto(leidos).equals(v.generarCodigoProyecto(datos)));
        v.generarArchivoDeObjetosClientes(datos);
        verificar("Lectura de clientes elimina repetidos por identificación", v.leerArchivoDeObjetosClientes().size() == 2);
        byte[] anterior = Files.readAllBytes(carpeta.resolve("datosProyectos"));
        rechaza("Guardar lista vacía no borra archivo anterior", () -> v.generarArchivoDeObjetosProyectos(new ArrayList<Proyecto>()));
        verificar("Archivo anterior permanece intacto", Arrays.equals(anterior, Files.readAllBytes(carpeta.resolve("datosProyectos"))));
        Files.write(carpeta.resolve("datosProyectos"), Arrays.copyOf(anterior, anterior.length - 7));
        rechaza("Archivo truncado no se acepta como lectura completa", () -> v.leerArchivoDeObjetosProyectos());
        ArchivoObjetos.escribir(carpeta.resolve("datosProyectos"), new ArrayList<Cliente>(Arrays.asList(PERSONA)));
        rechaza("Archivo con tipo de objeto incorrecto", () -> v.leerArchivoDeObjetosProyectos());
        datos.add(datos.get(0)); v.generarArchivoDeObjetosProyectos(datos);
        rechaza("Lectura rechaza códigos duplicados", () -> v.leerArchivoDeObjetosProyectos());
    }
}
