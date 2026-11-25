import java.time.LocalDate;

public class MovimientoInsumo {
    private final String codigoInsumo;
    private final TipoMovimiento tipo;
    private final int cantidad;
    private final String sector;
    private final LocalDate fecha;

    public MovimientoInsumo(String codigoInsumo, TipoMovimiento tipo, int cantidad, String sector, LocalDate fecha) {
        if (codigoInsumo == null || codigoInsumo.trim().isEmpty()) {
            throw new IllegalArgumentException("El código de insumo es obligatorio.");
        }
        if (tipo == null) {
            throw new IllegalArgumentException("El tipo de movimiento es obligatorio.");
        }
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad del movimiento debe ser positiva.");
        }
        if (sector == null || sector.trim().isEmpty()) {
            throw new IllegalArgumentException("El sector es obligatorio.");
        }
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha del movimiento es obligatoria.");
        }
        this.codigoInsumo = codigoInsumo.trim().toUpperCase();
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.sector = sector.trim();
        this.fecha = fecha;
    }

    public String getCodigoInsumo() {
        return codigoInsumo;
    }

    public TipoMovimiento getTipo() {
        return tipo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getSector() {
        return sector;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    @Override
    public String toString() {
        return "[" + fecha + "] " + tipo + " de " + cantidad + " u. | Insumo: " + codigoInsumo + " | Sector: " + sector;
    }
}