import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RegistroMovimientosInsumos {
    private static RegistroMovimientosInsumos instancia;

    private final List<MovimientoInsumo> movimientos;

    private RegistroMovimientosInsumos() {
        this.movimientos = new ArrayList<>();
    }

    public static RegistroMovimientosInsumos getInstancia() {
        if (instancia == null) {
            instancia = new RegistroMovimientosInsumos();
        }
        return instancia;
    }

    public int calcularSaldo(String codigoInsumo) {
        if (codigoInsumo == null) {
            return 0;
        }
        String codigo = codigoInsumo.trim().toUpperCase();
        int saldo = 0;
        for (MovimientoInsumo m : movimientos) {
            if (m.getCodigoInsumo().equals(codigo)) {
                if (m.getTipo() == TipoMovimiento.ENTRADA) {
                    saldo += m.getCantidad();
                } else {
                    saldo -= m.getCantidad();
                }
            }
        }
        return saldo;
    }

    public void registrarMovimiento(String codigoInsumo, TipoMovimiento tipo, int cantidad, String sector, LocalDate fecha) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser positiva.");
        }
        String codigoNormalizado = codigoInsumo == null ? "" : codigoInsumo.trim().toUpperCase();
        if (tipo == TipoMovimiento.SALIDA) {
            int saldoActual = calcularSaldo(codigoNormalizado);
            if (cantidad > saldoActual) {
                throw new IllegalArgumentException("Saldo insuficiente para " + codigoNormalizado
                        + ". Disponible: " + saldoActual + ", solicitado: " + cantidad);
            }
        }
        MovimientoInsumo nuevo = new MovimientoInsumo(codigoInsumo, tipo, cantidad, sector, fecha);
        movimientos.add(nuevo);
    }

    public List<MovimientoInsumo> obtenerHistorialCompleto() {
        return Collections.unmodifiableList(new ArrayList<>(movimientos));
    }

    public List<MovimientoInsumo> filtrarPorInsumo(String codigoInsumo) {
        if (codigoInsumo == null) {
            return Collections.emptyList();
        }
        String codigo = codigoInsumo.trim().toUpperCase();
        List<MovimientoInsumo> resultado = new ArrayList<>();
        for (MovimientoInsumo m : movimientos) {
            if (m.getCodigoInsumo().equals(codigo)) {
                resultado.add(m);
            }
        }
        return Collections.unmodifiableList(resultado);
    }

    public List<MovimientoInsumo> filtrarPorSector(String sector) {
        if (sector == null || sector.trim().isEmpty()) {
            return Collections.emptyList();
        }
        String buscado = sector.trim().toLowerCase();
        List<MovimientoInsumo> resultado = new ArrayList<>();
        for (MovimientoInsumo m : movimientos) {
            if (m.getSector().toLowerCase().equals(buscado)) {
                resultado.add(m);
            }
        }
        return Collections.unmodifiableList(resultado);
    }
}