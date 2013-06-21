<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


</head>
<body>

<div id="wrapper">

 <%@ include file="header.jsp" %>

	<div id="page">

		<div id="login">


<div style="color:red">${errorMessage}</div>
<c:remove var="errorMessage" scope="session" /> 



  <form method="POST" action="http:Login" name="autenticacao">
  <div id="log">Login</div>
    <table>
      <tr>
        <td>Email: </td>
        <td><input type="text" name="email" size="15" maxlength="30" /></td>
      </tr>
      <tr>
        <td>Palavra-passe: </td>
        <td><input type="password" name="pass" size="15" maxlength="30" /> 
        </td> 
       <td> <input type="submit" name="login" value="Iniciar Sessão">
      </td>  </tr>
     
     
    </table>
 
  
      <!-- input
        type="submit" name="requestPass"
        value="Esqueceste-te da tua palavra-passe?"-->
 
  </form>
<p/>Ainda não possui conta? <a href="registoCliente.jsp">Registe-se Aqui!</a>
</div>

</div>
<br class="clearfix" />
</div>
<%@ include file="footer.jsp"%>

</body>

</html>
