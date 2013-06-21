<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

</head>
<body>
<div id="wrapper">

 <%@ include file="header.jsp" %>
<div id="pageAdmin">

	<div id="titulopagina">Dados do Registo de Aluguer:</div>

	<p>ID RESERVA ALUGUER: ${idReservaAluguer}</p>
	<p>Categoria: ${nomeCategoria}</p>
	<p>Quantidade: ${quantidade}</p>

	<br>

	<c:if test="${not empty listaBicicletasReserva}">
		<table>
			<tr>
				<th title="Line Number">#</th>
				<th align="center">Matricula</th>
				<th align="center">Designação</th>
				<th align="center">Preço</th>
			</tr>
			<c:forEach var="l" items="${listaBicicletasReserva}"
				varStatus="index">
				<tr>
					<td title="Line Number">${index.count}</td>
					<td align="center"><c:if test="${not empty l.idBicicleta}">${l.idBicicleta}</c:if></td>
					<td align="center"><c:if test="${not empty l.designacao}">${l.designacao}</c:if></td>
					<td align="center"><c:if test="${not empty l.precoAluguer}">${l.precoAluguer}</c:if></td>
				</tr>
			</c:forEach>
			<tr>
				<td></td>
				<td></td>
				<td align="center"><b>TOTAL = </b></td>
				<td align="center"><b>${preco}</b></td>
			</tr>
		</table>
	</c:if>
	<form method="POST" action="http:RegistarReservasAluguer"
		name="regReservas">
		<input type="hidden" value='${idReservaAluguer}' name="idReservaAluguer">
		<input type="hidden" value='${idCategoria}' name="idCategoria">
		<input type="hidden" value='${quantidade}' name="quantidade">
		<input type="button"
			value="Cancelar" onClick="history.go(-1);return true;" /> <input
			type="submit" value="Confirmar Registo" />
	</form>
		</div>

	</div>
<%@ include file="footer.jsp"%>
</body>
</html>