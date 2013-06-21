package reservas;

import java.math.BigDecimal;
import bikes.categorias.CategoriaB;

public class ReservaAluguer extends Reserva{
	private String idReservaAluguer = null;
	private CategoriaB cat = null;
	private int quantidade = 0;
	private BigDecimal total = null;

	public ReservaAluguer(){

	}
	
	public CategoriaB getCat() {
		return cat;
	}

	public void setCat(CategoriaB cat) {
		this.cat = cat;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getIdReservaAluguer() {
		return idReservaAluguer;
	}

	public void setIdReservaAluguer(String idReservaAluguer) {
		this.idReservaAluguer = idReservaAluguer;
	}

	public BigDecimal getTotal() {
		return total.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}
}