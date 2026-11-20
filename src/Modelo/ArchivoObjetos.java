package Modelo;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;


public final class ArchivoObjetos {
    private ArchivoObjetos() { }

    public static void escribir(Path archivo, ArrayList<?> datos) {
        if (datos.isEmpty()) throw new IllegalArgumentException("No hay datos para guardar.");
        Path temporal = null;
        try {
            Path destino = archivo.toAbsolutePath();
            temporal = Files.createTempFile(destino.getParent(), "proyectos-", ".tmp");
            try (ObjectOutputStream salida = new ObjectOutputStream(Files.newOutputStream(temporal))) {
                for (Object dato : datos) salida.writeObject(dato);
            }
            // Solo sustituye el archivo anterior cuando terminó la escritura.
            Files.move(temporal, destino, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            throw new IllegalStateException("No se pudo guardar " + archivo + ": " + ex.getMessage(), ex);
        } finally {
            if (temporal != null) {
                try { Files.deleteIfExists(temporal); } catch (IOException ex) { /* No afecta al destino. */ }
            }
        }
    }

    public static <T> ArrayList<T> leer(Path archivo, Class<T> tipo) {
        ArrayList<T> datos = new ArrayList<T>();
        try (InputStream fuente = Files.newInputStream(archivo);
                BufferedInputStream entrada = new BufferedInputStream(fuente);
                ObjectInputStream objetos = new ObjectInputStream(entrada)) {
            while (true) {
                // Distingue el fin normal del archivo de un objeto incompleto.
                entrada.mark(1);
                if (entrada.read() == -1) break;
                entrada.reset();
                Object dato = objetos.readObject();
                if (!tipo.isInstance(dato)) throw new IOException("El archivo contiene un tipo de objeto incorrecto.");
                datos.add(tipo.cast(dato));
            }
            return datos;
        } catch (IOException | ClassNotFoundException ex) {
            throw new IllegalStateException("No se pudo leer " + archivo + ": " + ex.getMessage(), ex);
        }
    }
}
