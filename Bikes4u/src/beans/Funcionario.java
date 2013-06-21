package beans;

import java.io.Serializable;

public class Funcionario implements Serializable {

  private Long idFuncionario = null;
  private String nomeFuncionario = null;
  private String password = null;
  private String nome = null;
  private String email = null;
  
  public Funcionario(){
    
  }

  public String getNomeFuncionario() {return nomeFuncionario;}

  public void setNomeFuncionario(String nomeFuncionario) {this.nomeFuncionario = nomeFuncionario;}
  
  public String getNome() {return nome;}

  public void setNome(String nome) {this.nome = nome;}
  
  public String getPassword() {return password;}

  public void setPassword(String password) {this.password = password;}
  
  public String getEmail() {return email;}

  public void setEmail(String email) {this.email = email;}
  
  public Long getIdFuncionario() {return idFuncionario;}

  public void setIdFuncionario(long idFuncionario) {this.idFuncionario = idFuncionario;}
}
