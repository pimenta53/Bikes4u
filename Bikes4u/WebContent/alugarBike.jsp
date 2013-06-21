<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>

<body>
<div id="wrapper">
 <%@ include file="header.jsp" %>
<div id="page">
 
<h3>Escolha o Número de Bicicletas para cada Categoria que Pretende Alugar </h3>

<c:forEach items="${categorias}" var="element">
    <FORM action="http:NovaReservaAction" method="POST">
		<div id="externa">
    <input type="hidden" name="idCat" value="${element.id}"/>
    <div id="tipo">${element.tipo}</div>

    <div id="precoPequeno">Preço Aluguer:  </div><div id="precoGrande">  ${element.preco} &euro;</div>
    <INPUT id="precoId" type="hidden" name="preco" value="${element.preco}"/>
    <div id="quantidade">Quantidade Disponivel:  ${element.disponibilidade}</div>
    <INPUT id="dispId" type="hidden" name="disp" value="${element.disponibilidade}"/>
    <div align="center">
    <INPUT id="quantidadeId" type="text" name="quantidade" value="0"/>
   <br/>
    <INPUT type="submit" value="Reservar"/><br></div></div>
	</FORM>    
</c:forEach>
</div>
<br class="clearfix" />
</div>
<%@ include file="footer.jsp"%>

</body>
</html>