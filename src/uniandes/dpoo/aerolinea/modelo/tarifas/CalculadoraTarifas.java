package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.Aeropuerto;
import uniandes.dpoo.aerolinea.modelo.Ruta;

public abstract class CalculadoraTarifas {
    public static final double IMPUESTO = 0.19;

    public abstract int calcularCostoBase(Vuelo vuelo, Cliente cliente);

    public abstract double calcularPorcentajeDescuento(Cliente cliente);

    public int calculaTarifa(Vuelo vuelo, Cliente cliente) {
        int costoBase = calcularCostoBase(vuelo, cliente);
        double descuento = calcularPorcentajeDescuento(cliente);
        int costoConDescuento = (int) (costoBase * (1 - descuento));
        int valorImpuestos = calcularValorImpuestos(costoConDescuento);
        return costoConDescuento + valorImpuestos;
    }

    public int calcularValorImpuestos(int costoBase) {
        return (int) (costoBase * IMPUESTO);
    }

    public int calcularDistanciaVuelo(Ruta ruta) {
        return Aeropuerto.calcularDistancia(ruta.getOrigen(), ruta.getDestino());
    }
}
