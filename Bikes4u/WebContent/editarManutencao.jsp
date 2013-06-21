<%@ page language="java" contentType="text/html; charset=UTF-8"
	session="true" pageEncoding="UTF-8"%>
<html>
<head>


</head>
<body>

  
<div id="wrapper">

 <%@ include file="header.jsp" %>
<div id="pageAdmin">


	<h3>Preencha o formulário com os dados da Manutenção</h3>
	<div id="form">
	<div style="color: red">${errorMessage}</div>
	<p />
	<form method="POST" action="http:RegistarEditarManutencao"
		name="registoM">
		<table>
			<tr>
				<td>Matricula:</td>
				<td>${manutencao.idBicicleta}</td>
			<tr>
				<td>Descrição da Avaria:</td>
				<td><input type="text" name="descricaoAvaria" size="50"
					value="${manutencao.getDescricaoAvaria()}" maxlength="50" /></td>
			</tr>
			<tr>
				<td>Data Final:</td>
				<td><input type="date" name="dataFim"
					value="${manutencao.getDataFim()}" /></td>
			</tr>
			<tr>
				<td>Preco após a Manutenção (ex: 20.50):</td>
				<td><input type="text" name="precoPosManutencao" size="5"
					maxlength="5" value="${manutencao.getPrecoPosManutencao()}" />€</td>
			</tr>
		</table>
		<input type="hidden" value='${manutencao.idBicicleta}'
			name="idBicicleta"> <input type="hidden"
			value='${manutencao.idManutencao}' name="idManutencao">
		<p>
			<input type="button" value="Cancelar"
				onClick="history.go(-1);return true;" /> <input type="submit"
				value="Editar Manutenção" />
		</p>
	</form>
	</div><br class="clearfix" />
</div>
<%@ include file="footer.jsp"%>

</body>
</body>
</html>