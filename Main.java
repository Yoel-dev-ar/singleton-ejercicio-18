import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        RegistroMovimientosInsumos registro = RegistroMovimientosInsumos.getInstancia();

        // Saldo inicial mediante movimiento de ENTRADA
        registro.registrarMovimiento("INS-GUANTES", TipoMovimiento.ENTRADA, 100, "Depósito", LocalDate.now());
        registro.registrarMovimiento("INS-JERINGAS", TipoMovimiento.ENTRADA, 50, "Depósito", LocalDate.now());

        // Salidas desde Consultorios y Laboratorio
        registro.registrarMovimiento("INS-GUANTES", TipoMovimiento.SALIDA, 25, "Consultorios", LocalDate.now());
        registro.registrarMovimiento("INS-JERINGAS", TipoMovimiento.SALIDA, 15, "Laboratorio", LocalDate.now());
        registro.registrarMovimiento("INS-GUANTES", TipoMovimiento.ENTRADA, 40, "Depósito", LocalDate.now());

        // Intento de salida mayor al saldo disponible
        try {
            registro.registrarMovimiento("INS-JERINGAS", TipoMovimiento.SALIDA, 80, "Laboratorio", LocalDate.now());
        } catch (IllegalArgumentException e) {
            System.out.println("Salida rechazada correctamente: " + e.getMessage());
        }

        System.out.println("\nSaldo INS-GUANTES: " + registro.calcularSaldo("INS-GUANTES"));
        System.out.println("Saldo INS-JERINGAS: " + registro.calcularSaldo("INS-JERINGAS"));

        System.out.println("\n--- Historial completo ---");
        for (MovimientoInsumo m : registro.obtenerHistorialCompleto()) {
            System.out.println(m);
        }

        System.out.println("\n--- Filtrado por insumo INS-GUANTES ---");
        for (MovimientoInsumo m : registro.filtrarPorInsumo("INS-GUANTES")) {
            System.out.println(m);
        }

        System.out.println("\n--- Filtrado por sector Laboratorio ---");
        for (MovimientoInsumo m : registro.filtrarPorSector("Laboratorio")) {
            System.out.println(m);
        }
    }
}