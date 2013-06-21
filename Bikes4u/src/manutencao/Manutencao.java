package manutencao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;


public class Manutencao implements Serializable {
	private String idManutencao = null;
	private String descricaoAvaria = null;
	private Date dataInicio = null;
	private Date dataFim = null;
	private BigDecimal precoPosManutencao = null;
	private String idBicicleta = null;
	private String idFuncionario = null;
	private int terminado = 0;

	public Manutencao(){
	  
	}
	
	public String getIdManutencao() {
		return idManutencao;
	}

	public void setIdManutencao(String idManutencao) {
		this.idManutencao = idManutencao;
	}

	public String getDescricaoAvaria() {
		return descricaoAvaria;
	}

	public void setDescricaoAvaria(String descricaoAvaria) {
		this.descricaoAvaria = descricaoAvaria;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio2) {
		this.dataInicio = dataInicio2;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public BigDecimal getPrecoPosManutencao() {
		return precoPosManutencao;
	}

	public void setPrecoPosManutencao(BigDecimal precoPosManutencao) {
		this.precoPosManutencao = precoPosManutencao;
	}

	public String getIdBicicleta() {
		return idBicicleta;
	}

	public void setIdBicicleta(String idBicicleta) {
		this.idBicicleta = idBicicleta;
	}

	public String getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(String idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public int getTerminado() {
		return terminado;
	}

	public void setTerminado(int terminado) {
		this.terminado = terminado;
	}
}
