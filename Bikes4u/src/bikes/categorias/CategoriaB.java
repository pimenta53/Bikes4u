package bikes.categorias;

import java.io.Serializable;

public class CategoriaB implements Serializable {

	private String id = null;
	private String tipo = null;
	
	public CategoriaB(){
		
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
