package beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

public class RegistoTransaccao implements Serializable {

  private Long idTransaccao = null; //usada tambem para as vendas 
  private Date dataTransaccao = null; //usada tambem para as vendas 
  private BigDecimal preco = null; //usada tambem para as vendas 
  private Long idBicicleta = null; //usadata tambem para as vendas
  private String designacaoBicicleta = null; //usada tambem para as vendas
  private String nomeCategoria = null; //usada tambem para as vendas
  private Integer nova = null; //usada tambem para as vendas
  private String nomeCliente = null; //usada tambem para as vendas 
  private String nomeFuncionario = null; //usada tambem para as vendas 
  private Date dataInicioAluguer = null; 
  private Date dataFimAluguer = null;
  
  public RegistoTransaccao(){
    
  }
  
  public Long getIdTransaccao() {return idTransaccao;}  
  public void setIdTransaccao(Long idTransaccao) {this.idTransaccao = idTransaccao;}  
  public Date getDataTransaccao() {return dataTransaccao;}  
  public void setDataTransaccao(Date dataTransaccao) {this.dataTransaccao = dataTransaccao;}
  public BigDecimal getPreco() {return preco;}  
  public void setPreco(BigDecimal preco) {this.preco = preco;}
  public Long getIdBicicleta() {return idBicicleta;}  
  public void setIdBicicleta(Long idBicicleta) {this.idBicicleta = idBicicleta;}  
  public String getDesignacaoBicicleta() {return designacaoBicicleta;} 
  public void setDesignacaoBicicleta(String designacaoBicicleta) {this.designacaoBicicleta = designacaoBicicleta;}
  public String getNomeCategoria() {return nomeCategoria;}
  public void setNomeCategoria(String nomeCategoria) {this.nomeCategoria = nomeCategoria;}
  public Integer getNova() {return nova;}
  public void setNova(Integer nova) {this.nova = nova;}
  public String getNomeCliente() {return nomeCliente;}
  public void setNomeCliente(String nomeCliente) {this.nomeCliente = nomeCliente;}
  public String getNomeFuncionario() {return nomeFuncionario;}
  public void setNomeFuncionario(String nomeFuncionario) {this.nomeFuncionario = nomeFuncionario;}
  public Date getDataInicioAluguer() {return dataInicioAluguer;}  
  public void setDataInicioAluguer(Date dataInicioAluguer) {this.dataInicioAluguer = dataInicioAluguer;}  
  public Date getDataFimAluguer() {return dataFimAluguer;} 
  public void setDataFimAluguer(Date dataFimAluguer) {this.dataFimAluguer = dataFimAluguer;}
}
