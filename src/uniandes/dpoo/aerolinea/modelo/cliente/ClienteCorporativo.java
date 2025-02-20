package uniandes.dpoo.aerolinea.modelo.cliente;

import org.json.JSONObject;


/**
 * Esta clase se usa para representar a los clientes de la aerolínea que son empresas
 */
public class ClienteCorporativo extends Cliente
{
    public static final String CORPORATIVO = "Corporativo";
    public static final int GRANDE = 1;
    public static final int MEDIANA = 2;
    public static final int PEQUENA = 3;

    private String nombreEmpresa;
    private int tamanoEmpresa;

    public ClienteCorporativo(String nombreEmpresa, int tamano) {
        super(); // Call to superclass constructor
        this.nombreEmpresa = nombreEmpresa;
        this.tamanoEmpresa = tamano;
    }

    @Override
    public String getTipoCliente() {
        return CORPORATIVO;
    }

    @Override
    public String getIdentificador() {
        return nombreEmpresa;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public int getTamanoEmpresa() {
        return tamanoEmpresa;
    }

    public static ClienteCorporativo cargarDesdeJSON(JSONObject cliente) {
        String nombreEmpresa = cliente.getString("nombreEmpresa");
        int tamano = cliente.getInt("tamanoEmpresa");
        return new ClienteCorporativo(nombreEmpresa, tamano);
    }

    public JSONObject salvarEnJSON() {
        JSONObject json = new JSONObject();
        json.put("nombreEmpresa", nombreEmpresa);
        json.put("tamanoEmpresa", tamanoEmpresa);
        json.put("tipo", CORPORATIVO);
        return json;
    }
}