Ejercicio 8: Registro Compartido de Movimientos de Insumos (Patrón Singleton)
Temática
Gestión Hospitalaria — Trazabilidad y control de stock de insumos entre depósito, consultorios y laboratorio.

Consigna
Contexto del problema: Los sectores de depósito, consultorios y laboratorio registran por separado los movimientos de insumos. La administración necesita consultar un historial unificado para identificar entradas, salidas y sectores responsables.
Objetivo de aprendizaje: Centralizar información mediante Singleton y proteger la integridad de un registro compartido.
Requisitos funcionales:

Registrar movimientos con código de insumo, tipo de movimiento, cantidad, sector y fecha.

Admitir movimientos de entrada y salida.

Consultar el historial completo o filtrarlo por insumo y sector.

Calcular el saldo de cada insumo a partir de sus movimientos.
Restricciones técnicas:

Debe existir una única instancia del registro durante la ejecución.

Los tres sectores deben utilizar el mismo objeto.

Las cantidades deben ser positivas y las salidas no pueden superar el saldo disponible.

Los movimientos registrados no deben poder editarse ni eliminarse desde el código cliente.

El saldo inicial de cada insumo debe incorporarse mediante un movimiento de entrada.

Explicación lógica

Atributos: En RegistroMovimientosInsumos se declaran movimientos (private final List<MovimientoInsumo>) para guardar la secuencia inmutable de operaciones y instancia (private static RegistroMovimientosInsumos) para unificar los sectores.

Instancia única: El constructor private impide instanciar registros independientes y getInstancia() garantiza que depósito, consultorios y laboratorio trabajen sobre la misma colección.

Asignación y Acceso: En Main, el saldo inicial se carga como ENTRADA, las salidas se validan contra el saldo calculado para evitar stock negativo, los objetos MovimientoInsumo no exponen métodos de edición ni borrado y las consultas devuelven listas no modificables.

Estructura del ejercicio
TipoMovimiento.java: Enumeración que define los tipos ENTRADA y SALIDA.

MovimientoInsumo.java: Clase inmutable que modela cada movimiento con código, tipo, cantidad positiva, sector y fecha.

RegistroMovimientosInsumos.java: Singleton que centraliza los movimientos, calcula saldos, filtra por insumo o sector y bloquea salidas sin saldo suficiente.

Main.java: Simula operaciones en depósito, consultorios y laboratorio, valida rechazos de stock y muestra los filtros.
