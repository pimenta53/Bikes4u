<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

</head>
<body>
<div id="wrapper">

 <%@ include file="header.jsp" %>
 
 <%@ include file="menuRelatorio.jsp" %>
 
		<div id="pageAdmin">
	<div id="titulopagina" align="center">Registo Diário</div>

<c:if test="${empty aluguers && empty vendas}">
  <b>Ainda não foi efetuada nenhuma transacção!</b>
</c:if>
<c:if test="${not empty aluguers}">
<div id="titulo" align="center">
<h4>Alugueres</h4></div>
  <table id="tabela1">
    <tr>
    
     <th align="center">Matrícula </th>
      <th align="center">Data da Transacção</th>
    
      <th align="center">Modelo da Bicicleta</th>
      <th align="center">Nova</th>
      <th align="center">Categoria</th>
      <th align="center">Início Aluguer</th>
      <th align="center">Final Aluguer</th>
      <th align="center">Nome Cliente</th>
      <th align="center">Nome Funcionário</th>
      <th align="center">Preço</th>
    </tr>
    <c:forEach var="bic" items="${aluguers}" varStatus="index">
      <tr>
            <td align="center">${bic.idBicicleta}</td>
     
        <td align="center">${bic.dataTransaccao}</td>
  
        <td align="center">${bic.designacaoBicicleta}</td>
        <td align="center">
          <c:if test="${not empty bic.nova}">
            <c:if test="${bic.nova == 1}">Sim</c:if>
            <c:if test="${bic.nova == 0}">Não</c:if>
          </c:if>
        </td>
        <td align="center">${bic.nomeCategoria}</td>
        <td align="center">${bic.dataInicioAluguer}</td>
        <td align="center">${bic.dataFimAluguer}</td>
        <td align="center">${bic.nomeCliente}</td>
        <td align="center">${bic.nomeFuncionario}</td>
        <td align="center">${bic.preco} &euro;</td>
      </tr>
    </c:forEach>
  </table>  
</c:if>
<p/>
<p/>
<c:if test="${not empty vendas}">
<div id="titulo" align="center">
<h4>Vendas</h4></div>
  <table id="tabela1">
    <tr>
   
       <th align="center">Matrícula</th>
      <th align="center">Data da Transacção</th>
  
      <th align="center">Modelo da Bicicleta</th>
      <th align="center">Nova</th>
      <th align="center">Categoria</th>
      <th align="center">Cliente</th>
      <th align="center">Funcionário</th>
      <th align="center">Preço</th>
    </tr>
    <c:forEach var="bic" items="${vendas}" varStatus="index">
      <tr>
        <td align="center">${bic.idBicicleta}</td>
        
        <td align="center">${bic.dataTransaccao}</td>
     
        <td align="center">${bic.designacaoBicicleta}</td>
        <td align="center">
          <c:if test="${not empty bic.nova}">
            <c:if test="${bic.nova == 1}">Sim</c:if>
            <c:if test="${bic.nova == 0}">Não</c:if>
          </c:if>
        </td>
        <td align="center">${bic.nomeCategoria}</td>
        <td align="center">
          <c:if test="${not empty bic.nomeCliente}">${bic.nomeCliente}</c:if>
          <c:if test="${empty bic.nomeCliente}"> - </c:if>
        </td>
        <td align="center">${bic.nomeFuncionario}</td>
        <td align="center">${bic.preco} &euro;</td>
      </tr>
    </c:forEach>
  </table>  
</c:if>
</div>
</div>
<%@ include file="footer.jsp"%>


</body>
</html>