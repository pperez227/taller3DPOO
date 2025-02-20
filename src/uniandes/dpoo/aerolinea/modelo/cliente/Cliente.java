package uniandes.dpoo.aerolinea.modelo.cliente;

import java.util.ArrayList;
import java.util.List;

import uniandes.dpoo.aerolinea.tiquetes.Tiquete;
import uniandes.dpoo.aerolinea.modelo.Vuelo;

public abstract class Cliente {
    protected List<Tiquete> tiquetes;

    public Cliente() {
        this.tiquetes = new ArrayList<>();
    }

    public abstract String getTipoCliente();

    public abstract String getIdentificador();

    public void agregarTiquete(Tiquete tiquete) {
        this.tiquetes.add(tiquete);
    }

    public int calculaValorTotalTiquetes() {
        int total = 0;
        for (Tiquete tiquete : tiquetes) {
            if (!tiquete.esUsado()) {
                total += tiquete.getTarifa();
            }
        }
        return total;
    }

    public void usarTiquetes(Vuelo vuelo) {
        for (Tiquete tiquete : tiquetes) {
            if (tiquete.getVuelo().equals(vuelo)) {
                tiquete.marcarComoUsado();
            }
        }
    }
}
