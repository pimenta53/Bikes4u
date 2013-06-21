package beans;

import java.io.Serializable;

public class Cliente implements Serializable {

  private String idCliente = null;
  private String nomeCliente = null;
  private String password = null;
  private String nome = null;
  private String email = null;
  private String cartaoCredito = null;
  
  public Cliente(){
    
  }

  public String getNomeCliente() {return nomeCliente;}

  public void setNomeCliente(String nomeCliente) {this.nomeCliente = nomeCliente;}
  
  public String getNome() {return nome;}

  public void setNome(String nome) {this.nome = nome;}
  
  public String getPassword() {return password;}

  public void setPassword(String password) {this.password = password;}
  
  public String getEmail() {return email;}

  public void setEmail(String email) {this.email = email;}

  public String getCartaoCredito(){ return cartaoCredito;}
  
  public void setCartaoCredito(String cartaoCredito) {this.cartaoCredito = cartaoCredito;}
  
  public String getIdCliente() {return idCliente;}

  public void setIdCliente(String idCliente) {this.idCliente = idCliente;}
}
