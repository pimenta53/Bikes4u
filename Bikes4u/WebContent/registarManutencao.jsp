<%@ page language="java" contentType="text/html; charset=UTF-8"
	session="true" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

</head>
<body>
<div id="wrapper">

 <%@ include file="header.jsp" %>
<div id="pageAdmin">



	<div id="form">
	<div style="color: red">${errorMessage}</div>
	<br/>
	
	<div id="titulopagina">Insira uma Nova Manutenção</div>

  <c:remove var="errorMessage" scope="session" />
	<p />

	<form method="POST" action="http:RegistarManutencao" name="registoM">
		<table>
			<tr>
				<td>Descrição da Avaria:</td>
				<td><textarea  name="descricaoAvaria" 
					maxlength="50"></textarea></td>
			</tr>
			<tr>
				<td>Data Final:</td>
				<td><input type="date" name="dataFim" /></td>
			</tr>
			<tr>
				<td>Preco após a Manutenção<br/> (ex: 20.50):</td>
				<td><input type="text" name="precoPosManutencao" size="10"
					maxlength="5" />€</td>
			</tr>
		</table>
		<br/>
		
		<div  align="center" >
      <input type="hidden" name="idBicicleta" value="${idBic}"/>
      <input type="button" value="Cancelar" onClick="history.go(-1);return true;" /> 
      <input type="submit" value="Registar Manutenção" />
    </div>
		



	</form>
	</div>
	</div>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>