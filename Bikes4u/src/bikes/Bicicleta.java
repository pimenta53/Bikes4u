package bikes;

import java.io.Serializable;
import java.math.BigDecimal;

import bikes.categorias.CategoriaB;
import bikes.estado.EstadoB;

public class Bicicleta implements Serializable {

	private String matricula = null;
	private String nome = null;
	private BigDecimal preco = null;
	private String foto = null;
	private Integer nova = null;
	private CategoriaB cat = null;
	private EstadoB estado = null;

	public Bicicleta(){
	  
	}
	
	public EstadoB getEstado() {
		return estado;
	}

	public void setEstado(EstadoB estado) {
		this.estado = estado;
	}

	public CategoriaB getCat() {
		return cat;
	}

	public void setCat(CategoriaB cat) {
		this.cat = cat;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getNova() {
		return nova;
	}
	public void setNova(Integer nova) {
		this.nova = nova;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	public Integer trocarEstado(){
		if(nova == 1) return 0;
		else return 1;
	}
}
