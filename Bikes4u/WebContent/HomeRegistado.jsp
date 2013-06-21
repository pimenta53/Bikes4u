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
<div id="text">
<h4>Bem-vindo/a ${cliente.nome}!</h4>
</div>
<c:if test="${not empty cliente}">
  <div>
  <c:if test="${not empty Reservas}">
    <div id="form">
   <div id="titulopaginaMedio">Reservas feitas para os próximos 7 dias:</div>
    <c:forEach var="res" items="${Reservas}" varStatus="index">
      <b>Número da reserva: </b>${res.idReserva}<p/>
      De ${res.dataInicio} até ${res.dataFim}
      <p/>
    </c:forEach>
    </div>
    <p/>
  </c:if>
  <div id="titulopaginaMedio">
Deseja comprar uma bicicleta? Não perca estes preços:</div>
    <br/>
    <c:forEach var="bic" items="${BicicletasVenda}" varStatus="index">
    <div id="formUser">
    <div id="imagem">
    <c:if test="${empty bic.imagem}">
      <img width="140px" height="95px" src="images/bicicletas/desc.jpeg"/>
    </c:if>
    <c:if test="${not empty bic.imagem}">
      <img width="140px" height="95px" src="images/bicicletas/${bic.imagem}"/>
    </c:if>
    </div>
    <div id="right">
   <div id="nomeUser">${bic.designacao}
      <c:if test="${bic.nova == 0}">(Usada)</c:if>
      <c:if test="${bic.nova == 1}">(Nova)</c:if>  </div>
      <div id="tipoUser">${bic.nomeCategoria}</div> <br/>
      <table>
       <tr id="preco" >Preço Venda: </tr><tr id="precoUser" >${bic.precoVenda} &euro;</tr></table>
      </div></div>
    </c:forEach>
  </div>
</c:if>


</div>
<br class="clearfix" />
</div>
<%@ include file="footer.jsp"%>

</body>
</html>