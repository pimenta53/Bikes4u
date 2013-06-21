<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
</head>
<body>

<div id="wrapper">

 <%@ include file="header.jsp" %>
<div id="page">
	
<div><h3>Preencha os seguintes campos e usufrua da nossa loja </h3> </div>

<div style="color:red">${errorMessage}</div>
<c:remove var="errorMessage" scope="session" /> 
<p/>  
<div id="formCenter">
<form method="POST" action="http:RegistoCliente" name="registo">
    <table>
      <tr> 
        <td>Nome de Utilizador:<br/> 
      <input type="text" name="nomeCliente" size="40" maxlength="30" />  <br/>   <br/> </td>
      </tr>
      <tr>  
        <td>Email:<br/> 
         <input type="text" name="email" size="40" maxlength="30" />   <br/>   <br/> </td>
        </tr>
      <tr>
        <td>Palavra-passe:<br/> 
         <input type="password" name="password" size="40" maxlength="30" />  <br/>  <br/>  </td>
  		</tr>
      <tr>
        <td>Nome:<br/> 
         <input type="text" name="nome" size="40" maxlength="50" />  <br/>   <br/> </td>
      </tr>
      <tr>
        <td>Cartão de Crédito: <br/>
        <input type="text" name="cartaoCredito" size="40" maxlength="30" />  <br/>  </td>
           </tr>
    </table>
    <p>
	<input type="button" value="Cancelar" onClick="history.go(-1);return true;"/>
	<input type="submit" value="Registar"/>
    </p>
</form>

</div>
<br/><br/>
</div>
<br class="clearfix" />
</div>
<%@ include file="footer.jsp"%>


</body>
</html>