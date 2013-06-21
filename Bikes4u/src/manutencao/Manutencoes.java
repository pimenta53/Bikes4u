package manutencao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;


public class Manutencoes implements Serializable {

	private long idManutencao = 0;
	private long idBicicleta = 0;
	private String designacao = null;
	private String nomeCategoria = null;
	private String descricaoAvaria = null;
	private Date dataInicio = null;
	private Date dataFim = null;
	private BigDecimal precoPosManutencao = null;
	private String nomeFuncionario = null;
	private int terminado = 0;

	public long getIdBicicleta() {
		return idBicicleta;
	}

	public void setIdBicicleta(long idBicicleta) {
		this.idBicicleta = idBicicleta;
	}

	public String getNomeFuncionario() {
		return nomeFuncionario;
	}

	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
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

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
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

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}

	public String getDesignacao() {
		return designacao;
	}

	public void setDesignacao(String designacao) {
		this.designacao = designacao;
	}

	public long getIdManutencao() {
		return idManutencao;
	}

	public void setIdManutencao(long idManutencao) {
		this.idManutencao = idManutencao;
	}

	public int getTerminado() {
		return terminado;
	}

	public void setTerminado(int terminado) {
		this.terminado = terminado;
	}
}
