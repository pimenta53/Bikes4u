package beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

public class Manutencao implements Serializable {

  private Long idManutencao = null;
  private String descricaoAvaria = null;
  private Date dataInicio = null;
  private Date dataFim = null;
  private BigDecimal novoPreco = null;
  private String designacaoBicicleta = null;
  private Long idBicicleta = null;
  
  public Manutencao(){
    
  }
  
  public Long getIdManutencao() {return idManutencao;}

  public void setIdManutencao(Long idManutencao) {this.idManutencao = idManutencao;}

  public String getDescricaoAvaria() {return descricaoAvaria;}

  public void setDescricaoAvaria(String descricaoAvaria) {this.descricaoAvaria = descricaoAvaria;}
  
  public Date getDataInicio() {return dataInicio;}

  public void setDataInicio(Date dataInicio) {this.dataInicio = dataInicio;}
  
  public Date getDataFim() {return dataFim;}

  public void setDataFim(Date dataFim) {this.dataFim = dataFim;}
  
  public BigDecimal getPreco() {return novoPreco;}

  public void setPreco(BigDecimal preco) {this.novoPreco = preco;}

  public String getDesignacaoBicicleta() {return designacaoBicicleta;}

  public void setDesignacaoBicicleta(String designacaoBicicleta) {this.designacaoBicicleta = designacaoBicicleta;}

  public Long getIdBicicleta() {return idBicicleta;}

  public void setIdBicicleta(Long idBicicleta) {this.idBicicleta = idBicicleta;}
}
