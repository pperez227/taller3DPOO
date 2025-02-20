package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.cliente.ClienteCorporativo;

public class CalculadoraTarifasTemporadaBaja extends CalculadoraTarifas {
    public static final int COSTO_POR_KM_NATURAL = 100;
    public static final int COSTO_POR_KM_CORPORATIVO = 80;
    public static final double DESCUENTO_PEQ = 0.05;
    public static final double DESCUENTO_MEDIANAS = 0.10;
    public static final double DESCUENTO_GRANDES = 0.15;

    @Override
    public int calcularCostoBase(Vuelo vuelo, Cliente cliente) {
        int distancia = calcularDistanciaVuelo(vuelo.getRuta());
        if (cliente instanceof ClienteCorporativo) {
            return distancia * COSTO_POR_KM_CORPORATIVO;
        } else {
            return distancia * COSTO_POR_KM_NATURAL;
        }
    }

    @Override
    public double calcularPorcentajeDescuento(Cliente cliente) {
        if (cliente instanceof ClienteCorporativo) {
            ClienteCorporativo corporativo = (ClienteCorporativo) cliente;
            switch (corporativo.getTamanoEmpresa()) {
                case ClienteCorporativo.GRANDE:
                    return DESCUENTO_GRANDES;
                case ClienteCorporativo.MEDIANA:
                    return DESCUENTO_MEDIANAS;
                case ClienteCorporativo.PEQUENA:
                    return DESCUENTO_PEQ;
                default:
                    return 0;
            }
        }
        return 0;
    }
}
