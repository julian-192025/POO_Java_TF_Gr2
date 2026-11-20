package Vista;

import Modelo.*;
import java.awt.*;
import java.awt.event.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import javax.swing.*;

/** Ventana principal y operaciones solicitadas en el diagrama. */
public class UsaProyecto extends JFrame {
    private static final long serialVersionUID = 1L;
    private static ArrayList<Proyecto> losProyectos = new ArrayList<Proyecto>();
    private static ArrayList<Cliente> losClientes = new ArrayList<Cliente>();
    private boolean cambiosPendientes;
    private final Path carpetaDatos;

    public UsaProyecto() { this(Paths.get(".")); }

    /** Permite ejecutar las pruebas en una carpeta temporal independiente. */
    public UsaProyecto(Path carpetaDatos) {
        this.carpetaDatos = carpetaDatos;
        initComponents();
        getContentPane().setBackground(new Color(244, 247, 250));
        ((JPanel) getContentPane()).setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 24));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 14, 0));
        panelAcciones.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 14));
        for (Component control : panelAcciones.getComponents()) {
            control.setFont(new Font("SansSerif", Font.PLAIN, 14));
        }
        txtResultado.setFont(new Font("SansSerif", Font.PLAIN, 15));
        txtResultado.setMargin(new Insets(14, 14, 14, 14));
        lblEstado.setBorder(BorderFactory.createEmptyBorder(12, 0, 0, 0));
        mostrar("BIENVENIDO AL GESTOR DE PROYECTOS\n\n"
                + "1. Registre un proyecto con su cliente y gerente.\n"
                + "2. Consulte y gestione el equipo y el estado.\n"
                + "3. Guarde los proyectos antes de salir.\n\n"
                + "El registro de equipo es opcional.\n"
                + "Los datos se guardan en la carpeta del proyecto.\n"
                + "Archivos: datosProyectos y datosClientes.");
        addWindowListener(new WindowAdapter() {
            @Override public void windowClosing(WindowEvent e) { salir(); }
        });
        setSize(1140, 780);
        setMinimumSize(new Dimension(940, 650));
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        panelAcciones = new javax.swing.JPanel();
        btnAccion0 = new javax.swing.JButton();
        btnAccion1 = new javax.swing.JButton();
        btnAccion2 = new javax.swing.JButton();
        btnAccion3 = new javax.swing.JButton();
        btnAccion4 = new javax.swing.JButton();
        btnAccion5 = new javax.swing.JButton();
        btnAccion6 = new javax.swing.JButton();
        btnAccion7 = new javax.swing.JButton();
        btnAccion8 = new javax.swing.JButton();
        btnAccion9 = new javax.swing.JButton();
        btnAccion10 = new javax.swing.JButton();
        btnAccion11 = new javax.swing.JButton();
        btnAccion12 = new javax.swing.JButton();
        btnAccion13 = new javax.swing.JButton();
        btnAccion14 = new javax.swing.JButton();
        btnAccion15 = new javax.swing.JButton();
        btnAccion16 = new javax.swing.JButton();
        btnAccion17 = new javax.swing.JButton();
        btnAccion18 = new javax.swing.JButton();
        btnAccion19 = new javax.swing.JButton();
        scrollResultado = new javax.swing.JScrollPane();
        txtResultado = new javax.swing.JTextArea();
        lblEstado = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("POO_Java_TF_Gr2 | Gestión de proyectos");

        lblTitulo.setText("Gestión de proyectos");
        getContentPane().add(lblTitulo, java.awt.BorderLayout.NORTH);

        panelAcciones.setLayout(new java.awt.GridLayout(0, 1, 0, 4));

        btnAccion0.setText("Registrar proyecto");
        btnAccion0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAccion0ActionPerformed(evt);
            }
        });
        panelAcciones.add(btnAccion0);

        btnAccion1.setText("Consultar todos");
        btnAccion1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAccion1ActionPerformed(evt);
            }
        });
        panelAcciones.add(btnAccion1);

        btnAccion2.setText("Proyectos en curso");
        btnAccion2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAccion2ActionPerformed(evt);
            }
        });
        panelAcciones.add(btnAccion2);

        btnAccion3.setText("Primer proyecto");
        btnAccion3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAccion3ActionPerformed(evt);
            }
        });
        panelAcciones.add(btnAccion3);

        btnAccion4.setText("Último proyecto");
        btnAccion4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAccion4ActionPerformed(evt);
            }
        });
        panelAcciones.add(btnAccion4);

        btnAccion5.setText("Buscar por código");
        btnAccion5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAccion5ActionPerformed(evt);
            }
        });
        panelAcciones.add(btnAccion5);

        btnAccion6.setText("Consultar gerente");
        btnAccion6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAccion6ActionPerformed(evt);
            }
        });
        panelAcciones.add(btnAccion6);

        btnAccion7.setText("Consultar equipo");
        btnAccion7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAccion7ActionPerformed(evt);
            }
        });
        panelAcciones.add(btnAccion7);

        btnAccion8.setText("Proyectos de un gerente");
        btnAccion8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAccion8ActionPerformed(evt);
            }
        });
        panelAcciones.add(btnAccion8);

        btnAccion9.setText("Proyectos de un cliente");
        btnAccion9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAccion9ActionPerformed(evt);
            }
        });
        panelAcciones.add(btnAccion9);

        btnAccion10.setText("Proyectos por tipo de cliente");
        btnAccion10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAccion10ActionPerformed(evt);
            }
        });
        panelAcciones.add(btnAccion10);

        btnAccion11.setText("Agregar integrante");
        btnAccion11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAccion11ActionPerformed(evt);
            }
        });
        panelAcciones.add(btnAccion11);

        btnAccion12.setText("Agregar equipo");
        btnAccion12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAccion12ActionPerformed(evt);
            }
        });
        panelAcciones.add(btnAccion12);

        btnAccion13.setText("Retirar integrante");
        btnAccion13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAccion13ActionPerformed(evt);
            }
        });
        panelAcciones.add(btnAccion13);

        btnAccion14.setText("Actualizar estado");
        btnAccion14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAccion14ActionPerformed(evt);
            }
        });
        panelAcciones.add(btnAccion14);

        btnAccion15.setText("Guardar proyectos");
        btnAccion15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAccion15ActionPerformed(evt);
            }
        });
        panelAcciones.add(btnAccion15);

        btnAccion16.setText("Guardar clientes");
        btnAccion16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAccion16ActionPerformed(evt);
            }
        });
        panelAcciones.add(btnAccion16);

        btnAccion17.setText("Leer proyectos");
        btnAccion17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAccion17ActionPerformed(evt);
            }
        });
        panelAcciones.add(btnAccion17);

        btnAccion18.setText("Leer clientes");
        btnAccion18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAccion18ActionPerformed(evt);
            }
        });
        panelAcciones.add(btnAccion18);

        btnAccion19.setText("Salir");
        btnAccion19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAccion19ActionPerformed(evt);
            }
        });
        panelAcciones.add(btnAccion19);

        getContentPane().add(panelAcciones, java.awt.BorderLayout.WEST);

        txtResultado.setEditable(false);
        txtResultado.setColumns(64);
        txtResultado.setRows(30);
        txtResultado.setLineWrap(true);
        txtResultado.setWrapStyleWord(true);
        scrollResultado.setViewportView(txtResultado);

        getContentPane().add(scrollResultado, java.awt.BorderLayout.CENTER);

        lblEstado.setText("Sin proyectos registrados");
        getContentPane().add(lblEstado, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAccion0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAccion0ActionPerformed
        ejecutar(0);
    }//GEN-LAST:event_btnAccion0ActionPerformed

    private void btnAccion1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAccion1ActionPerformed
        ejecutar(1);
    }//GEN-LAST:event_btnAccion1ActionPerformed

    private void btnAccion2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAccion2ActionPerformed
        ejecutar(2);
    }//GEN-LAST:event_btnAccion2ActionPerformed

    private void btnAccion3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAccion3ActionPerformed
        ejecutar(3);
    }//GEN-LAST:event_btnAccion3ActionPerformed

    private void btnAccion4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAccion4ActionPerformed
        ejecutar(4);
    }//GEN-LAST:event_btnAccion4ActionPerformed

    private void btnAccion5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAccion5ActionPerformed
        ejecutar(5);
    }//GEN-LAST:event_btnAccion5ActionPerformed

    private void btnAccion6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAccion6ActionPerformed
        ejecutar(6);
    }//GEN-LAST:event_btnAccion6ActionPerformed

    private void btnAccion7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAccion7ActionPerformed
        ejecutar(7);
    }//GEN-LAST:event_btnAccion7ActionPerformed

    private void btnAccion8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAccion8ActionPerformed
        ejecutar(8);
    }//GEN-LAST:event_btnAccion8ActionPerformed

    private void btnAccion9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAccion9ActionPerformed
        ejecutar(9);
    }//GEN-LAST:event_btnAccion9ActionPerformed

    private void btnAccion10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAccion10ActionPerformed
        ejecutar(10);
    }//GEN-LAST:event_btnAccion10ActionPerformed

    private void btnAccion11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAccion11ActionPerformed
        ejecutar(11);
    }//GEN-LAST:event_btnAccion11ActionPerformed

    private void btnAccion12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAccion12ActionPerformed
        ejecutar(12);
    }//GEN-LAST:event_btnAccion12ActionPerformed

    private void btnAccion13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAccion13ActionPerformed
        ejecutar(13);
    }//GEN-LAST:event_btnAccion13ActionPerformed

    private void btnAccion14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAccion14ActionPerformed
        ejecutar(14);
    }//GEN-LAST:event_btnAccion14ActionPerformed

    private void btnAccion15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAccion15ActionPerformed
        ejecutar(15);
    }//GEN-LAST:event_btnAccion15ActionPerformed

    private void btnAccion16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAccion16ActionPerformed
        ejecutar(16);
    }//GEN-LAST:event_btnAccion16ActionPerformed

    private void btnAccion17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAccion17ActionPerformed
        ejecutar(17);
    }//GEN-LAST:event_btnAccion17ActionPerformed

    private void btnAccion18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAccion18ActionPerformed
        ejecutar(18);
    }//GEN-LAST:event_btnAccion18ActionPerformed

    private void btnAccion19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAccion19ActionPerformed
        ejecutar(19);
    }//GEN-LAST:event_btnAccion19ActionPerformed



    private void ejecutar(int opcion) {
        try {
            switch (opcion) {
                case 0: {
                    int cantidad = losProyectos.size();
                    registrarProyecto(losProyectos);
                    if (losProyectos.size() > cantidad) {
                        cambiosPendientes = true;
                        mostrar("Proyecto registrado.\n\n" + consultarUltimoProyecto(losProyectos));
                    } else mostrar("Registro cancelado. No se agregaron datos.");
                    break;
                }
                case 1: mostrar(consultarTodosProyectos(losProyectos)); break;
                case 2: mostrar(consultarProyectosEnCurso(losProyectos)); break;
                case 3: mostrar(consultarPrimerProyecto(losProyectos)); break;
                case 4: mostrar(consultarUltimoProyecto(losProyectos)); break;
                case 5: mostrar(consultarProyectoDadoCodigo(losProyectos, pedirCodigo())); break;
                case 6: mostrar(consultarGerenteProyectoDadoCodigo(losProyectos, pedirCodigo())); break;
                case 7: mostrar(consultarEquipoProyectoDadoCodigo(losProyectos, pedirCodigo())); break;
                case 8: mostrar(consultarProyectosDadoNumIDEGerente(losProyectos, solicitar("Identificación del gerente"))); break;
                case 9: mostrar(consultarProyectosDadoNumIDECliente(losProyectos, solicitar("Identificación del cliente"))); break;
                case 10: mostrar(consultarProyectosDadoTipoCliente(losProyectos,
                        elegir("Tipo de cliente", new String[]{"Empresa", "PersonaNatural"}))); break;
                case 11: mostrar(registrarIntegranteProyectoDadoCodigo(losProyectos, pedirCodigo())); break;
                case 12: mostrar(registrarEquipoProyectoDadoCodigo(losProyectos, pedirCodigo())); break;
                case 13: mostrar(eliminarIntegranteProyectoDadoCodigo(losProyectos, pedirCodigo())); break;
                case 14: mostrar(actualizarEstadoProyectoDadoCodigo(losProyectos, pedirCodigo())); break;
                case 15:
                    if (autorizarArchivo("datosProyectos")) {
                        generarArchivoDeObjetosProyectos(losProyectos);
                        cambiosPendientes = false;
                        mostrar("Proyectos guardados en el archivo datosProyectos.");
                    }
                    break;
                case 16:
                    if (autorizarArchivo("datosClientes")) {
                        generarArchivoDeObjetosClientes(losProyectos);
                        mostrar("Clientes guardados en el archivo datosClientes.");
                    }
                    break;
                case 17:
                    if (!losProyectos.isEmpty() && !confirmar("Leer el archivo reemplazará los proyectos en memoria. ¿Continuar?")) return;
                    ArrayList<Proyecto> leidos = leerArchivoDeObjetosProyectos();
                    losProyectos = leidos;
                    losClientes = extraerClientes(leidos);
                    cambiosPendientes = false;
                    mostrar("Archivo de proyectos leído.\n\n" + consultarTodosProyectos(losProyectos));
                    break;
                case 18:
                    losClientes = leerArchivoDeObjetosClientes();
                    String cadena = "CLIENTES LEÍDOS: " + losClientes.size() + "\n";
                    for (Cliente cliente : losClientes) cadena += "\n" + cliente + "\n";
                    mostrar(cadena);
                    break;
                default: salir();
            }
            actualizarResumen();
        } catch (Cancelacion ex) {
            mostrar("Operación cancelada. No se modificaron datos.");
        } catch (IllegalArgumentException | IllegalStateException ex) {
            informar(ex.getMessage());
        }
    }

    private void mostrar(String texto) {
        txtResultado.setText(texto);
        txtResultado.setCaretPosition(0);
        actualizarResumen();
    }
    private void actualizarResumen() {
        lblEstado.setText("Proyectos: " + losProyectos.size() + "   |   Clientes: "
                + extraerClientes(losProyectos).size() + "   |   "
                + (cambiosPendientes ? "Cambios sin guardar" : "Sin cambios pendientes"));
    }
    private void salir() {
        if (!cambiosPendientes || confirmar("Hay cambios sin guardar. ¿Salir sin guardarlos?")) dispose();
    }
    protected void informar(String mensaje) { JOptionPane.showMessageDialog(this, mensaje, "Gestión de proyectos", JOptionPane.INFORMATION_MESSAGE); }
    protected boolean confirmar(String mensaje) {
        return JOptionPane.showConfirmDialog(this, mensaje, "Confirmar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }
    private boolean autorizarArchivo(String nombre) {
        return !Files.exists(carpetaDatos.resolve(nombre)) || confirmar("El archivo " + nombre + " ya existe. ¿Reemplazarlo?");
    }
    private String pedirCodigo() { return solicitar("Código del proyecto (aaaa-001)"); }
    protected String solicitar(String mensaje) {
        String dato = JOptionPane.showInputDialog(this, mensaje, "Datos", JOptionPane.QUESTION_MESSAGE);
        if (dato == null) throw new Cancelacion();
        if (dato.trim().isEmpty()) throw new IllegalArgumentException("El dato es obligatorio.");
        return dato.trim();
    }
    protected String elegir(String mensaje, String[] opciones) {
        Object dato = JOptionPane.showInputDialog(this, mensaje, "Seleccionar", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
        if (dato == null) throw new Cancelacion();
        return dato.toString();
    }
    protected String[] formulario(String titulo, String[] etiquetas, String[] iniciales) {
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        JTextField[] campos = new JTextField[etiquetas.length];
        for (int i = 0; i < campos.length; i++) {
            panel.add(new JLabel(etiquetas[i]));
            campos[i] = new JTextField(iniciales[i], 28);
            panel.add(campos[i]);
        }
        if (JOptionPane.showConfirmDialog(this, panel, titulo, JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE) != JOptionPane.OK_OPTION) throw new Cancelacion();
        String[] datos = new String[campos.length];
        for (int i = 0; i < campos.length; i++) {
            datos[i] = campos[i].getText().trim();
            if (datos[i].isEmpty()) throw new IllegalArgumentException(etiquetas[i] + " es obligatorio.");
        }
        return datos;
    }
    protected Empleado capturarEmpleado(String titulo) {
        String[] d = formulario(titulo, new String[]{"Identificación", "Nombre completo", "Título profesional", "Años de experiencia (0 a 127)"},
                new String[]{"", "", "", "0"});
        try { return new Empleado(d[0], d[1], d[2], Byte.parseByte(d[3])); }
        catch (NumberFormatException ex) { throw new IllegalArgumentException("La experiencia debe ser un entero entre 0 y 127."); }
    }
    protected Cliente capturarCliente(ArrayList<Proyecto> proyectos) {
        String id = solicitar("Identificación del cliente (si existe se reutiliza)");
        for (Proyecto proyecto : proyectos) if (proyecto.getSuCliente().getNumeroIdentificacion().equals(id)) return proyecto.getSuCliente();
        for (Cliente cliente : losClientes) if (cliente.getNumeroIdentificacion().equals(id)) return cliente;
        String tipo = elegir("Tipo de cliente", new String[]{"Empresa", "PersonaNatural"});
        String[] d = formulario("Cliente", new String[]{"Nombre / razón social", "Ciudad"}, new String[]{"", ""});
        if (tipo.equals("Empresa")) {
            String[] c = formulario("Contacto de la empresa", new String[]{"Nombre completo", "Cargo", "Teléfono", "Correo electrónico"}, new String[]{"", "", "", ""});
            return new Empresa(id, d[0], d[1], new Contacto(c[0], c[1], c[2], c[3]));
        }
        String[] c = formulario("Contacto de la persona natural", new String[]{"Teléfono", "Correo electrónico"}, new String[]{"", ""});
        return new PersonaNatural(id, d[0], d[1], c[0], c[1]);
    }

    public ArrayList<Proyecto> registrarProyecto(ArrayList<Proyecto> datos) {
        try {
            String codigo = generarCodigoProyecto(datos);
            String[] d = formulario("Nuevo proyecto " + codigo,
                    new String[]{"Título (20 a 100 caracteres)", "Fecha de inicio (aaaa-mm-dd)", "Duración estimada (entero)"},
                    new String[]{"", LocalDate.now().toString(), "1"});
            String unidad = elegir("Unidad de duración", new String[]{"semanas", "meses", "años"});
            LocalDate inicio;
            byte duracion;
            try {
                if (!d[1].matches("[0-9]{4}-[0-9]{2}-[0-9]{2}")) throw new DateTimeParseException("Formato", d[1], 0);
                inicio = LocalDate.parse(d[1]);
                duracion = Byte.parseByte(d[2]);
            } catch (DateTimeParseException | NumberFormatException ex) {
                throw new IllegalArgumentException("Revise la fecha (aaaa-mm-dd) y la duración entera.");
            }
            Cliente cliente = capturarCliente(datos);
            Empleado gerente = capturarEmpleado("Gerente del proyecto");
            Proyecto nuevo = new Proyecto(codigo, d[0], inicio, unidad, duracion, cliente, gerente);
            if (confirmar("¿Registrar el equipo ahora? Puede hacerlo después.")) {
                nuevo.setSuEquipo(capturarEquipo(new ArrayList<Empleado>()));
            }
            datos.add(nuevo); // Se agrega únicamente cuando termina todo el registro.
            return datos;
        } catch (Cancelacion ex) { return datos; }
    }

    public String generarCodigoProyecto(ArrayList<Proyecto> datos) {
        return generarCodigoProyecto(datos, LocalDate.now().getYear());
    }
    /** Sobrecarga para verificar el reinicio del consecutivo en un año concreto. */
    public String generarCodigoProyecto(ArrayList<Proyecto> datos, int anio) {
        if (anio < 1000 || anio > 9999) throw new IllegalArgumentException("Año fuera de rango.");
        int mayor = 0;
        String prefijo = anio + "-";
        for (Proyecto proyecto : datos) {
            if (proyecto.getCodigo().startsWith(prefijo)) {
                int numero = Integer.parseInt(proyecto.getCodigo().substring(5));
                if (numero > mayor) mayor = numero;
            }
        }
        if (mayor >= 999) throw new IllegalStateException("Se alcanzaron los 999 códigos del año.");
        return String.format(java.util.Locale.ROOT, "%04d-%03d", anio, mayor + 1);
    }
    private Proyecto buscarProyecto(ArrayList<Proyecto> datos, String codigo) {
        for (Proyecto proyecto : datos) if (proyecto.getCodigo().equals(codigo)) return proyecto;
        return null;
    }
    private String noEncontrado(ArrayList<Proyecto> datos) {
        return datos.isEmpty() ? "No hay proyectos registrados." : "Proyecto no encontrado.";
    }
    private String listar(ArrayList<Proyecto> datos, String titulo) {
        if (datos.isEmpty()) return "No hay proyectos registrados o no se encontraron coincidencias.";
        String cadena = titulo + " (" + datos.size() + ")\n";
        for (Proyecto proyecto : datos) cadena += "\n" + proyecto + "\n----------------------------------------------------\n";
        return cadena;
    }
    public String consultarTodosProyectos(ArrayList<Proyecto> datos) { return listar(datos, "TODOS LOS PROYECTOS"); }
    public String consultarProyectosEnCurso(ArrayList<Proyecto> datos) {
        ArrayList<Proyecto> resultado = new ArrayList<Proyecto>();
        for (Proyecto proyecto : datos) if (proyecto.getEstado().equals("EN CURSO")) resultado.add(proyecto);
        return listar(resultado, "PROYECTOS EN CURSO");
    }
    public String consultarPrimerProyecto(ArrayList<Proyecto> datos) {
        return datos.isEmpty() ? noEncontrado(datos) : datos.get(0).toString();
    }
    public String consultarUltimoProyecto(ArrayList<Proyecto> datos) {
        return datos.isEmpty() ? noEncontrado(datos) : datos.get(datos.size() - 1).toString();
    }
    public String consultarProyectoDadoCodigo(ArrayList<Proyecto> datos, String codigoProyecto) {
        Proyecto proyecto = buscarProyecto(datos, codigoProyecto);
        return proyecto == null ? noEncontrado(datos) : proyecto.toString();
    }
    public String consultarGerenteProyectoDadoCodigo(ArrayList<Proyecto> datos, String codigoProyecto) {
        Proyecto proyecto = buscarProyecto(datos, codigoProyecto);
        return proyecto == null ? noEncontrado(datos) : "GERENTE DE " + proyecto.getTitulo() + "\n" + proyecto.getSuGerente();
    }
    public String consultarEquipoProyectoDadoCodigo(ArrayList<Proyecto> datos, String codigoProyecto) {
        Proyecto proyecto = buscarProyecto(datos, codigoProyecto);
        if (proyecto == null) return noEncontrado(datos);
        String cadena = "EQUIPO DE " + proyecto.getTitulo() + "\n";
        if (proyecto.getSuEquipo().isEmpty()) return cadena + "Sin integrantes registrados.";
        for (Empleado integrante : proyecto.getSuEquipo()) cadena += "\n" + integrante + "\n";
        return cadena;
    }
    public String consultarProyectosDadoNumIDEGerente(ArrayList<Proyecto> datos, String numIDEGerente) {
        ArrayList<Proyecto> resultado = new ArrayList<Proyecto>();
        for (Proyecto proyecto : datos) if (proyecto.getSuGerente().getNumeroIdentificacion().equals(numIDEGerente)) resultado.add(proyecto);
        return listar(resultado, "PROYECTOS DEL GERENTE " + numIDEGerente);
    }
    public String consultarProyectosDadoNumIDECliente(ArrayList<Proyecto> datos, String numIDECliente) {
        ArrayList<Proyecto> resultado = new ArrayList<Proyecto>();
        for (Proyecto proyecto : datos) if (proyecto.getSuCliente().getNumeroIdentificacion().equals(numIDECliente)) resultado.add(proyecto);
        return listar(resultado, "PROYECTOS DEL CLIENTE " + numIDECliente);
    }
    public String consultarProyectosDadoTipoCliente(ArrayList<Proyecto> datos, String tipoCliente) {
        if (!"Empresa".equals(tipoCliente) && !"PersonaNatural".equals(tipoCliente)) throw new IllegalArgumentException("Tipo de cliente no válido.");
        ArrayList<Proyecto> resultado = new ArrayList<Proyecto>();
        for (Proyecto proyecto : datos) {
            if ((tipoCliente.equals("Empresa") && proyecto.getSuCliente() instanceof Empresa)
                    || (tipoCliente.equals("PersonaNatural") && proyecto.getSuCliente() instanceof PersonaNatural)) resultado.add(proyecto);
        }
        return listar(resultado, "PROYECTOS POR TIPO: " + tipoCliente);
    }
    public String registrarIntegranteProyectoDadoCodigo(ArrayList<Proyecto> datos, String codigoProyecto) {
        Proyecto proyecto = buscarProyecto(datos, codigoProyecto);
        if (proyecto == null) return noEncontrado(datos);
        proyecto.agregarIntegrante(capturarEmpleado("Nuevo integrante"));
        cambiosPendientes = true;
        return "Integrante registrado.\n" + consultarEquipoProyectoDadoCodigo(datos, codigoProyecto);
    }
    protected ArrayList<Empleado> capturarEquipo(ArrayList<Empleado> actuales) {
        int cantidad;
        try { cantidad = Integer.parseInt(solicitar("Cantidad de integrantes a agregar (mayor que cero)")); }
        catch (NumberFormatException ex) { throw new IllegalArgumentException("La cantidad debe ser un entero."); }
        if (cantidad <= 0) throw new IllegalArgumentException("La cantidad debe ser mayor que cero.");
        ArrayList<Empleado> equipo = new ArrayList<Empleado>(actuales);
        for (int i = 0; i < cantidad; i++) equipo.add(capturarEmpleado("Integrante " + (i + 1) + " de " + cantidad));
        return equipo;
    }
    public String registrarEquipoProyectoDadoCodigo(ArrayList<Proyecto> datos, String codigoProyecto) {
        Proyecto proyecto = buscarProyecto(datos, codigoProyecto);
        if (proyecto == null) return noEncontrado(datos);
        // Conserva los integrantes anteriores; valida el conjunto antes de actualizar.
        proyecto.setSuEquipo(capturarEquipo(proyecto.getSuEquipo()));
        cambiosPendientes = true;
        return "Equipo registrado.\n" + consultarEquipoProyectoDadoCodigo(datos, codigoProyecto);
    }
    public String eliminarIntegranteProyectoDadoCodigo(ArrayList<Proyecto> datos, String codigoProyecto) {
        Proyecto proyecto = buscarProyecto(datos, codigoProyecto);
        if (proyecto == null) return noEncontrado(datos);
        String id = solicitar("Identificación del integrante que desea retirar");
        if (!confirmar("¿Retirar al integrante " + id + " del proyecto " + codigoProyecto + "?")) return "Retiro cancelado.";
        Empleado eliminado = proyecto.eliminarIntegrante(id);
        if (eliminado == null) return "Integrante no encontrado en el equipo.";
        cambiosPendientes = true;
        return "Integrante retirado:\n" + eliminado;
    }
    /** Alias de la nota del PDF, que usa 'retirar' en vez de 'eliminar'. */
    public void retirarIntegranteProyectoDadoCodigo(ArrayList<Proyecto> datos, String codigoProyecto) {
        informar(eliminarIntegranteProyectoDadoCodigo(datos, codigoProyecto));
    }
    public String actualizarEstadoProyectoDadoCodigo(ArrayList<Proyecto> datos, String codigoProyecto) {
        Proyecto proyecto = buscarProyecto(datos, codigoProyecto);
        if (proyecto == null) return noEncontrado(datos);
        String estado = elegir("Nuevo estado", new String[]{"INICIADO", "EN CURSO", "CANCELADO", "FINALIZADO", "SUSPENDIDO", "APLAZADO"});
        proyecto.setEstado(estado);
        cambiosPendientes = true;
        return "Estado actualizado.\n\n" + proyecto;
    }
    private ArrayList<Cliente> extraerClientes(ArrayList<Proyecto> datos) {
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        for (Proyecto proyecto : datos) agregarClienteSinRepetir(clientes, proyecto.getSuCliente());
        return clientes;
    }
    private void agregarClienteSinRepetir(ArrayList<Cliente> datos, Cliente nuevo) {
        for (Cliente cliente : datos) if (cliente.getNumeroIdentificacion().equals(nuevo.getNumeroIdentificacion())) return;
        datos.add(nuevo);
    }
    public void generarArchivoDeObjetosProyectos(ArrayList<Proyecto> datos) {
        ArchivoObjetos.escribir(carpetaDatos.resolve("datosProyectos"), datos);
    }
    public ArrayList<Proyecto> leerArchivoDeObjetosProyectos() {
        ArrayList<Proyecto> leidos = ArchivoObjetos.leer(carpetaDatos.resolve("datosProyectos"), Proyecto.class);
        java.util.HashSet<String> codigos = new java.util.HashSet<String>();
        for (Proyecto proyecto : leidos) {
            if (!codigos.add(proyecto.getCodigo())) throw new IllegalStateException("El archivo contiene códigos de proyecto repetidos.");
        }
        return leidos;
    }
    public void generarArchivoDeObjetosClientes(ArrayList<Proyecto> datos) {
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        for (Proyecto proyecto : datos) clientes.add(proyecto.getSuCliente());
        ArchivoObjetos.escribir(carpetaDatos.resolve("datosClientes"), clientes);
    }
    public ArrayList<Cliente> leerArchivoDeObjetosClientes() {
        // La nota del PDF menciona datosProyectos al abrir; se usa datosClientes,
        // coherente con el nombre del método y los objetos que se van a leer.
        ArrayList<Cliente> leidos = ArchivoObjetos.leer(carpetaDatos.resolve("datosClientes"), Cliente.class);
        ArrayList<Cliente> unicos = new ArrayList<Cliente>();
        for (Cliente cliente : leidos) agregarClienteSinRepetir(unicos, cliente);
        return unicos;
    }
    protected static class Cancelacion extends RuntimeException {
        private static final long serialVersionUID = 1L;
        public Cancelacion() { super("Operación cancelada"); }
    }
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override public void run() {
                try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
                catch (Exception ex) { /* Se conserva el aspecto estándar de Swing. */ }
                new UsaProyecto().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAccion0;
    private javax.swing.JButton btnAccion1;
    private javax.swing.JButton btnAccion10;
    private javax.swing.JButton btnAccion11;
    private javax.swing.JButton btnAccion12;
    private javax.swing.JButton btnAccion13;
    private javax.swing.JButton btnAccion14;
    private javax.swing.JButton btnAccion15;
    private javax.swing.JButton btnAccion16;
    private javax.swing.JButton btnAccion17;
    private javax.swing.JButton btnAccion18;
    private javax.swing.JButton btnAccion19;
    private javax.swing.JButton btnAccion2;
    private javax.swing.JButton btnAccion3;
    private javax.swing.JButton btnAccion4;
    private javax.swing.JButton btnAccion5;
    private javax.swing.JButton btnAccion6;
    private javax.swing.JButton btnAccion7;
    private javax.swing.JButton btnAccion8;
    private javax.swing.JButton btnAccion9;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panelAcciones;
    private javax.swing.JScrollPane scrollResultado;
    private javax.swing.JTextArea txtResultado;
    // End of variables declaration//GEN-END:variables
}
