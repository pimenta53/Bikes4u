package beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

public class Reserva implements Serializable {

  private Long idReserva = null;
  private Date dataInicio = null;
  private Date dataFim = null;
  private BigDecimal preco = null;
  private Long idRegistoA = null;
  private String nomeCategoria = null;
  private Integer quantidade = null;
  
  public Reserva(){
    
  }
  
  public Long getIdReserva() {return idReserva;}

  public void setIdReserva(Long idReserva) {this.idReserva = idReserva;}

  public Date getDataInicio() {return dataInicio;}

  public void setDataInicio(Date dataInicio) {this.dataInicio = dataInicio;}
  
  public Date getDataFim() {return dataFim;}

  public void setDataFim(Date dataFim) {this.dataFim = dataFim;}
  
  public BigDecimal getPreco() {return preco;}

  public void setPreco(BigDecimal preco) {this.preco = preco;}
  
  public Long getIdRegistoA() {return idRegistoA;}

  public void setIdRegistoA(Long idRegistoA) {this.idRegistoA = idRegistoA;}

  public String getNomeCategoria() {return nomeCategoria;}

  public void setNomeCategoria(String nomeCategoria) {this.nomeCategoria = nomeCategoria;}

  public Integer getQuantidade() {return quantidade;}

  public void setQuantidade(Integer quantidade) {this.quantidade = quantidade;}
}
