package beans;

import java.io.Serializable;

public class Login implements Serializable {

  private String id = null;
  private String nomeLogin = null;
  private String password = null;
  private String nome = null;
  private String email = null;
  private String cartaoCredito = null;
  
  public Login(){
    
  }
  
  public String getId() {return id;}

  public void setId(String id) {this.id = id;}

  public String getNomeLogin() {return nomeLogin;}

  public void setNomeLogin(String nomeLogin) {this.nomeLogin = nomeLogin;}
  
  public String getNome() {return nome;}

  public void setNome(String nome) {this.nome = nome;}
  
  public String getPassword() {return password;}

  public void setPassword(String password) {this.password = password;}
  
  public String getEmail() {return email;}

  public void setEmail(String email) {this.email = email;}

  public String getCartaoCredito(){ return cartaoCredito;}
  
  public void setCartaoCredito(String cartaoCredito) {this.cartaoCredito = cartaoCredito;}
}
