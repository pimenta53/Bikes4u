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




<c:if test="${empty listaManutencoesFim && empty listaManutencoes && empty listaRegistoManutencoes}">
  <b>Não existem manutenções no sistema!</b>
</c:if>
	<c:if test="${not empty listaManutencoesFim}">
		<div id="titulopagina" align="center">Bicicletas que terminaram a Manutenção</div>
		<br/>
		<table  id="tabela1" >
			<tr>
				<th align="center">Matricula</th>
				<th align="center">Designação</th>
				<th align="center">Categoria</th>

				<th align="center">Inicio</th>
				<th align="center">Fim</th>
				<th align="center">Preço</th>
				<th align="center">Funcionário</th>
			</tr>
			<c:forEach var="m" items="${listaManutencoesFim}" varStatus="index">
				<tr>

					<td align="center"><c:if test="${not empty m.idBicicleta}">${m.idBicicleta}</c:if></td>
					<td align="center"><c:if test="${not empty m.designacao}">${m.designacao}</c:if></td>
					<td align="center"><c:if test="${not empty m.nomeCategoria}">${m.nomeCategoria}</c:if></td>
					<td align="center"><c:if test="${not empty m.dataInicio}">${m.dataInicio}</c:if></td>
					<td align="center"><c:if test="${not empty m.dataFim}">${m.dataFim}</c:if></td>
					<td align="center"><c:if
							test="${not empty m.precoPosManutencao}">${m.precoPosManutencao}</c:if></td>
					<td align="center"><c:if test="${not empty m.nomeFuncionario}">${m.nomeFuncionario}</c:if></td>
					<td>
						<form method="POST" action="http:RetirarManutencao"
							name="retirarM">
							<input type="hidden" value='${m.idBicicleta}' name="idBicicleta">
							<input type="hidden" value='${m.idManutencao}' name="idManutencao">
							<input type="hidden" value='${m.precoPosManutencao}' name="precoPosManutencao">
							<input type="submit" value='Colocar Disponivel'>
						</form>
					</td>
					<td>
						<form method="POST" action="http:EditarManutencao"
							name="retirarM">
							<input type="hidden" value='${m.idManutencao}' name="idManutencao">
              <input type="hidden" value='${m.descricaoAvaria}' name="descricaoAvaria">
							<input type="hidden" value='${m.dataInicio}' name="dataInicio">
							<input type="hidden" value='${m.dataFim}' name="dataFim">
							<input type="hidden" value='${m.precoPosManutencao}' name="precoPosManutencao">
							<input type="hidden" value='${m.idBicicleta}' name="idBicicleta">
							<input type="submit" value='Editar'>
						</form>
					</td>
					<td>
						<form method="POST" action="http:RemoverManutencao"
							name="retirarM">
							<input type="hidden" value='${m.idManutencao}' name="idManutencao">
							<input type="hidden" value='${m.idBicicleta}' name="idBicicleta">
							<input type="hidden" value='${m.terminado}' name="terminado">
							<input type="submit" value='Eliminar'>
						</form>
					</td>
			</c:forEach>
		</table>
	</c:if>



<br/>
	<c:if test="${not empty listaManutencoes}">
		<div id="titulopagina" align="center">Bicicletas em Manutenção</div>
			<br/>
		<table  id="tabela1" >
			<tr>

				<th align="center">Matricula</th>
				<th align="center">Designação</th>
				<th align="center">Categoria</th>

				<th align="center">Inicio</th>
				<th align="center">Fim</th>
				<th align="center">Preço</th>
				<th align="center">Funcionário</th>
			</tr>
			<c:forEach var="m" items="${listaManutencoes}" varStatus="index">
				<tr>

					<td align="center"><c:if test="${not empty m.idBicicleta}">${m.idBicicleta}</c:if></td>
					<td align="center"><c:if test="${not empty m.designacao}">${m.designacao}</c:if></td>
					<td align="center"><c:if test="${not empty m.nomeCategoria}">${m.nomeCategoria}</c:if></td>

					<td align="center"><c:if test="${not empty m.dataInicio}">${m.dataInicio}</c:if></td>
					<td align="center"><c:if test="${not empty m.dataFim}">${m.dataFim}</c:if></td>
					<td align="center"><c:if
							test="${not empty m.precoPosManutencao}">${m.precoPosManutencao}</c:if></td>
					<td align="center"><c:if test="${not empty m.nomeFuncionario}">${m.nomeFuncionario}</c:if></td>
					<td>
						<form method="POST" action="http:RetirarManutencao"
							name="retirarM">
							<input type="hidden" value='${m.idBicicleta}' name="idBicicleta">
							<input type="hidden" value='${m.idManutencao}' name="idManutencao">
							<input type="hidden" value='${m.precoPosManutencao}' name="precoPosManutencao">
							<input type="submit" value='Colocar Disponivel'>
						</form>
					</td>
					<td>
						<form method="POST" action="http:EditarManutencao"
							name="retirarM">
							<input type="hidden" value='${m.idManutencao}' name="idManutencao">
              <input type="hidden" value='${m.descricaoAvaria}' name="descricaoAvaria">
							<input type="hidden" value='${m.dataInicio}' name="dataInicio">
							<input type="hidden" value='${m.dataFim}' name="dataFim">
							<input type="hidden" value='${m.precoPosManutencao}' name="precoPosManutencao">
							<input type="hidden" value='${m.idBicicleta}' name="idBicicleta">
							<input type="submit" value='Editar'>
						</form>
					</td>
					<td>
						<form method="POST" action="http:RemoverManutencao"
							name="retirarM">
							<input type="hidden" value='${m.idManutencao}' name="idManutencao">
							<input type="hidden" value='${m.idBicicleta}' name="idBicicleta">
							<input type="hidden" value='${m.terminado}' name="terminado">
							<input type="submit" value='Eliminar'>
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>

<br/>

	<c:if test="${not empty listaRegistoManutencoes}">
		<div id="titulopagina" align="center">Registo de Manutenções</div>
			<br/>
		<table  id="tabela1" >
			<tr>

				<th align="center">Matrícula</th>
				<th align="center">Designação</th>
				<th align="center">Categoria</th>

				<th align="center">Inicio</th>
				<th align="center">Fim</th>
				<th align="center">Preço</th>
				<th align="center">Funcionário</th>
			</tr>
			<c:forEach var="m" items="${listaRegistoManutencoes}" varStatus="index">
				<tr>

					<td align="center"><c:if test="${not empty m.idBicicleta}">${m.idBicicleta}</c:if></td>
					<td align="center"><c:if test="${not empty m.designacao}">${m.designacao}</c:if></td>
					<td align="center"><c:if test="${not empty m.nomeCategoria}">${m.nomeCategoria}</c:if></td>

					<td align="center"><c:if test="${not empty m.dataInicio}">${m.dataInicio}</c:if></td>
					<td align="center"><c:if test="${not empty m.dataFim}">${m.dataFim}</c:if></td>
					<td align="center"><c:if
							test="${not empty m.precoPosManutencao}">${m.precoPosManutencao}</c:if></td>
					<td align="center"><c:if test="${not empty m.nomeFuncionario}">${m.nomeFuncionario}</c:if></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</div>
</div>
	<%@ include file="footer.jsp"%>

</body>
</html>