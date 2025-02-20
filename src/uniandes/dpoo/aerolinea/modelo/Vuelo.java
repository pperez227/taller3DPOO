package uniandes.dpoo.aerolinea.modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import uniandes.dpoo.aerolinea.tiquetes.Tiquete;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.tarifas.CalculadoraTarifas;
import uniandes.dpoo.aerolinea.exceptions.VueloSobrevendidoException;

public class Vuelo {
    private Ruta ruta;
    private String fecha;
    private Avion avion;
    private List<Tiquete> tiquetes;

    public Vuelo(Ruta ruta, String fecha, Avion avion) {
        this.ruta = ruta;
        this.fecha = fecha;
        this.avion = avion;
        this.tiquetes = new ArrayList<>();
    }

    public Ruta getRuta() {
        return ruta;
    }

    public String getFecha() {
        return fecha;
    }

    public Avion getAvion() {
        return avion;
    }

    public Collection<Tiquete> getTiquetes() {
        return tiquetes;
    }

    public int venderTiquetes(Cliente cliente, CalculadoraTarifas calculadora, int cantidad) throws VueloSobrevendidoException {
        int capacidadDisponible = avion.getCapacidad() - tiquetes.size();
        if (cantidad > capacidadDisponible) {
            throw new VueloSobrevendidoException("No hay suficiente espacio en el vuelo para todos los pasajeros");
        }
        int total = 0;
        for (int i = 0; i < cantidad; i++) {
            int tarifa = calculadora.calculaTarifa(this, cliente);
            Tiquete tiquete = new Tiquete("T" + (tiquetes.size() + 1), this, cliente, tarifa);
            tiquetes.add(tiquete);
            cliente.agregarTiquete(tiquete);
            total += tarifa;
        }
        return total;
    }

    public void realizarVuelo() {
        for (Tiquete tiquete : tiquetes) {
            tiquete.marcarComoUsado();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Vuelo vuelo = (Vuelo) obj;
        return (fecha != null ? fecha.equals(vuelo.fecha) : vuelo.fecha == null) &&
               (ruta != null ? ruta.equals(vuelo.ruta) : vuelo.ruta == null) &&
               (avion != null ? avion.equals(vuelo.avion) : vuelo.avion == null);
    }
}
