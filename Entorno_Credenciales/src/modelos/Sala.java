package modelos;

import bbdd.BD_Conector;

/**
 * 
 * @author Sandra
 *
 */
public class Sala {
	
	private String nSala;
	private String idEmple;
	private String idJefe;
	private String tipo;
	private String dificultad;
	private int nPersonas;
	private int precio;
	
	public Sala(String nSala, String idEmple, String idJefe, String tipo, String dificultad, int nPersonas,
			int precio) {
		super();
		this.nSala = nSala;
		this.idEmple = idEmple;
		this.idJefe = idJefe;
		this.tipo = tipo;
		this.dificultad = dificultad;
		this.nPersonas = nPersonas;
		this.precio = precio;
	}

	public Sala(String nSala, int precio) {
		this.nSala = nSala;
		this.precio = precio;
	}

	public String getNsala() {
		return nSala;
	}
	
	public String getIdEmple() {
		return idEmple;
	}
	
	public String getIdJefe() {
		return idJefe;
	}
	
	public String getTipo() {
		return tipo;
	}

	public String getDificultad() {
		return dificultad;
	}
	
	public int getNPersonas() {
		return nPersonas;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio=precio;
	}
	public String toStringMio() {
		return "Sala [nSala=" + nSala + ", idEmple=" + idEmple + ", idJefe=" + idJefe + ", tipo=" + tipo
				+ ", dificultad=" + dificultad + ", nPersonas=" + nPersonas + ", precio=" + precio + "]";
	}
	
	
	@Override
	public String toString() {
		return  nSala ;
	}

	public static String calcularNumeroSala(String codigo, String tabla, String cod) {
		int numSalas;
		BD_Conector bc= new BD_Conector ();
		numSalas=bc.consultaNumeroSecuencial(codigo, tabla, cod);
		numSalas++;
		return "SA"+numSalas;
	}

}
