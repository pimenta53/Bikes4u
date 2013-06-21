package reservas;

import java.sql.Date;

public class Reserva {

	private String idCliente = null;
	private Date dataInicio = null;
	private Date dataFim = null;
	
	public Reserva(){
	}
	
	public Date getDataInicio() {
		return dataInicio;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

}