package beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

public class Bicicleta implements Serializable {

  private Long idBicicleta = null;
  private String designacao = null;
  private Integer nova = null;
  private BigDecimal precoVenda = null;
  private String nomeCategoria = null;
  private BigDecimal precoAluguer = null;
  private Long idEstado = null;
  private String nomeEstado = null;
  private Date ultimaManutencao = null;
  private String imagem = null;
  
  public Bicicleta(){
    
  }
  
  public Long getIdBicicleta() {return idBicicleta;}
  
  public void setIdBicicleta(Long idBicicleta) {this.idBicicleta = idBicicleta;}
  
  public String getDesignacao() {return designacao;}
  
  public void setDesignacao(String designacao) {this.designacao = designacao;}
  
  public Integer getNova() {return nova;}
  
  public void setNova(Integer nova) {this.nova = nova;}
  
  public BigDecimal getPrecoVenda() {return precoVenda;}
  
  public void setPrecoVenda(BigDecimal precoVenda) {this.precoVenda = precoVenda;}
  
  public String getNomeCategoria() {return nomeCategoria;}
  
  public void setNomeCategoria(String nomeCategoria) {
    this.nomeCategoria = nomeCategoria;}

  public BigDecimal getPrecoAluguer() {return precoAluguer;}
  
  public void setPrecoAluguer(BigDecimal precoAluguer) {this.precoAluguer = precoAluguer;}
  
  public String getNomeEstado() {return nomeEstado;}
  
  public void setNomeEstado(String nomeEstado) {this.nomeEstado = nomeEstado;}

  public Date getUltimaManutencao() {return ultimaManutencao;}

  public void setUltimaManutencao(Date ultimaManutencao) {this.ultimaManutencao = ultimaManutencao;}
  
  public String getImagem() {return imagem;}
  
  public void setImagem(String imagem) {this.imagem = imagem;}

  public Long getIdEstado() {return idEstado;}

  public void setIdEstado(Long idEstado) {this.idEstado = idEstado;}
}
