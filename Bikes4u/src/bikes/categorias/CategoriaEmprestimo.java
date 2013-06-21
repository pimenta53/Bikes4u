package bikes.categorias;

import java.io.Serializable;
import java.math.BigDecimal;


public class CategoriaEmprestimo extends CategoriaB implements Serializable{

	private BigDecimal preco;
	private int disponibilidade=0;
	
	public CategoriaEmprestimo(){
	}
	
	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public int getDisponibilidade() {
		return disponibilidade;
	}

	public void setDisponibilidade(int disponibilidade) {
		this.disponibilidade = disponibilidade;
	}
}
