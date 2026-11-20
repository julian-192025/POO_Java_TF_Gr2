package Modelo;


final class Validacion {
    private Validacion() { }
    static String texto(String valor, String campo) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(campo + " es obligatorio.");
        }
        return valor.trim();
    }
    static <T> T requerido(T valor, String campo) {
        if (valor == null) throw new IllegalArgumentException(campo + " es obligatorio.");
        return valor;
    }
}
