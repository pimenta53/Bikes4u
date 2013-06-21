<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>

<div id="wrapper">
 <%@ include file="header.jsp" %>
  <%@ include file="menuHomeFuncionario.jsp" %>
	<div id="pageAdmin">
<div align="center">
	<c:if test="${empty listaReservas && empty listaReservasAConcluir && empty listaReservasPorConcluir}">
	<b>Não existem reservas!</b>
	</c:if>
</div>
	<c:if test="${not empty listaReservas}">

	<form method="GET" action='http:PesquisarListaReservas'
		name='pesquisarListaReservas'>
		<table>
			<tr>
				<td><input type="text" name="textoPesquisa"></td>
				<td><input type="submit" value="Pesquisar"></td>
			</tr>
		</table>
	</form>

	<div id="titulopagina" align="center">Lista de Reservas por Registar</div>
		<table id="tabela1" align="center">
			<tr>

				<th align="center">ID Reserva</th>
				<th align="center">ID Cliente</th>
				<th align="center">Inicio</th>
				<th align="center">Fim</th>
				<th align="center">Categoria</th>
				<th align="center">Quantidade</th>
				<th align="center">Preco Total</th>
			</tr>
			<c:forEach var="r" items="${listaReservas}" varStatus="index">
				<tr>

					<td align="center"><c:if
							test="${not empty r.idReservaAluguer}">${r.idReservaAluguer}</c:if></td>
					<td align="center"><c:if test="${not empty r.idCliente}">${r.idCliente}</c:if></td>
					<td align="center"><c:if test="${not empty r.dataInicio}">${r.dataInicio}</c:if></td>
					<td align="center"><c:if test="${not empty r.dataFim}">${r.dataFim}</c:if></td>
					<td align="center"><c:if test="${not empty r.cat.tipo}">${r.cat.tipo}</c:if></td>
					<td align="center"><c:if test="${not empty r.quantidade}">${r.quantidade}</c:if></td>
					<td align="center"><c:if test="${not empty r.total}">${r.total}</c:if></td>
					<td>
						<form method="POST" action="http:GestaoReservasAluguer"
							name="retirarM">
							<input type="hidden" value='${r.idReservaAluguer}'name="idReservaAluguer">
							<input type="hidden" value='${r.cat.id}' name="idCategoria">
							<input type="hidden" value='${r.cat.tipo}' name="nomeCategoria">
							<input type="hidden" value='${r.quantidade}' name="quantidade">
							<input type="hidden" value='${r.total}' name="preco">
							<input type="submit" value='Registar'>
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>



	<c:if test="${not empty listaReservasAConcluir}">
	<div id="titulopagina" align="center"> Reservas Diárias</div>
	<br>
		<table id="tabela1" align="center"> 
			<tr>

				<th align="center">ID Reserva</th>
				<th align="center">ID Cliente</th>
				<th align="center">Inicio</th>
				<th align="center">Fim</th>
				<th align="center">Categoria</th>
				<th align="center">Quantidade</th>
				<th align="center">Preco Total</th>
			</tr>
			<c:forEach var="r" items="${listaReservasAConcluir}" varStatus="index">
				<tr>

					<td align="center"><c:if
							test="${not empty r.idReservaAluguer}">${r.idReservaAluguer}</c:if></td>
					<td align="center"><c:if test="${not empty r.idCliente}">${r.idCliente}</c:if></td>
					<td align="center"><c:if test="${not empty r.dataInicio}">${r.dataInicio}</c:if></td>
					<td align="center"><c:if test="${not empty r.dataFim}">${r.dataFim}</c:if></td>
					<td align="center"><c:if test="${not empty r.cat.tipo}">${r.cat.tipo}</c:if></td>
					<td align="center"><c:if test="${not empty r.quantidade}">${r.quantidade}</c:if></td>
					<td align="center"><c:if test="${not empty r.total}">${r.total} &euro;</c:if></td>
					<td>
						<form method="POST" action="http:TerminarRegisto"
							name="retirarM">
							<input type="hidden" value='${r.idReservaAluguer}'name="idReservaAluguer">
							<input type="submit" value='Concluir'>
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>

	<br/>
	<br/>

	<c:if test="${not empty listaReservasPorConcluir}">
	<div id="titulopagina" align="center">Alugueres em Curso</div>
	<br/>
		<table id="tabela1">
			<tr>

				<th align="center">ID Reserva</th>
				<th align="center">ID Cliente</th>
				<th align="center">Inicio</th>
				<th align="center">Fim</th>
				<th align="center">Categoria</th>
				<th align="center">Quantidade</th>
				<th align="center">Preco Total</th>
			</tr>
			<c:forEach var="r" items="${listaReservasPorConcluir}" varStatus="index">
				<tr>

					<td align="center"><c:if
							test="${not empty r.idReservaAluguer}">${r.idReservaAluguer}</c:if></td>
					<td align="center"><c:if test="${not empty r.idCliente}">${r.idCliente}</c:if></td>
					<td align="center"><c:if test="${not empty r.dataInicio}">${r.dataInicio}</c:if></td>
					<td align="center"><c:if test="${not empty r.dataFim}">${r.dataFim}</c:if></td>
					<td align="center"><c:if test="${not empty r.cat.tipo}">${r.cat.tipo}</c:if></td>
					<td align="center"><c:if test="${not empty r.quantidade}">${r.quantidade}</c:if></td>
					<td align="center"><c:if test="${not empty r.total}">${r.total} &euro;</c:if></td>
					<td>
						<form method="POST" action="http:TerminarRegisto"
							name="retirarM">
							<input type="hidden" value='${r.idReservaAluguer}'name="idReservaAluguer">
							<input type="submit" value='Concluir'>
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
<br/>
	</div>

	</div>
<%@ include file="footer.jsp"%>
</body>
</html>