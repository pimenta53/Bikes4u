package beans;

import java.io.Serializable;
import java.math.BigDecimal;

public class Venda implements Serializable {

  private Long idBicicleta = null;
  private String designacaoBicicleta = null;
  private BigDecimal preco = null;
  private Integer nova = null;
  private String nomeCategoria = null;
  private String funcionario = null;
  
  public Venda(){
    
  }
  
  public Long getIdBicicleta() {return idBicicleta;}
  
  public void setIdBicicleta(Long idBicicleta) {this.idBicicleta = idBicicleta;}
  
  public String getDesignacaoBicicleta() {return designacaoBicicleta;}
  
  public void setDesignacaoBicicleta(String designacaoBicicleta) {this.designacaoBicicleta = designacaoBicicleta;}
  
  public BigDecimal getPreco() {return preco;}
  
  public void setPreco(BigDecimal preco) {this.preco = preco;}

  public Integer getNova() {return nova;}

  public void setNova(Integer nova) {this.nova = nova;}
  
  public String getNomeCategoria() {return nomeCategoria;}
  
  public void setNomeCategoria(String nomeCategoria) {this.nomeCategoria = nomeCategoria;}
  
  public String getFuncionario() {return funcionario;}
  
  public void setFuncionario(String funcionario) {this.funcionario = funcionario;}
}
