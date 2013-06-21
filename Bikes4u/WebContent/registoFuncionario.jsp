<%@ page language="java" contentType="text/html; charset=UTF-8" session="true"
    pageEncoding="UTF-8"%>
<html>
<head>

</head>
<body>

<div id="wrapper">

 <%@ include file="header.jsp" %>
  <%@ include file="menuHomeFuncionario.jsp" %>
<div id="pageAdmin">

  <div id="form">
<div style="color:red">${errorMessage}</div>
<c:remove var="errorMessage" scope="session" /> 
<p/>  	<div id="titulopagina" align="center" >

Adicionar Funcionário</div>
<br/>
<form method="POST" action="http:RegistoFuncionario" name="registoF">
    <table>
      <tr>
        <td>Nome de Login: </td>
        <td> <input type="text" name="nomeFuncionario" size="40" maxlength="30" /></td>
      </tr>
      <tr>
        <td>Email: </td>
        <td> <input type="text" name="email" size="40" maxlength="30" /> </td>
      </tr>
      <tr>
        <td>Palavra-passe: </td>
        <td> <input type="password" name="password" size="40" maxlength="30" /></td>
      </tr>
      <tr>
        <td>Nome: </td>
        <td> <input type="text" name="nome" size="40" maxlength="50" /></td>
      </tr>
    </table>

    	<div  align="center" >
	<input type="button" value="Cancelar" onClick="history.go(-1);return true;"/>
	<input type="submit" value="Registar"/>
    </div>
</form>
</div>
</div>
<br class="clearfix" />
</div>
<%@ include file="footer.jsp"%>

</body>
</html>