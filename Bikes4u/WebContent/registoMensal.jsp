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
	<div id="titulopagina" align="center"> Registo Mensal</div>
	<br/>
<div id="form">



<form action="http:RegistoMensal" method="GET">
 
<p>Escolha uma data: <p/>
    Mês: <select name="mes">
      <c:forEach var="pos" items="${mes}" varStatus="index">
        <option value="${pos}">${pos}</option>
      </c:forEach>
    </select>
  
     Ano: <select name="ano">
       <c:forEach var="pos" items="${ano}" varStatus="index">
        <option value="${pos}">${pos}</option>
      </c:forEach>
    </select>


  <input type="submit" value='Ok'> 
   <p/>
</form>
</div>
<div id="titulo" align="center">
<b>${message}</b>
<c:if test="${empty aluguersMes && empty vendasMes}">
  <b >Não existe informação sobre a data escolhida!</b>
</c:if>
</div>
<c:if test="${not empty aluguersMes}">
<div id="titulo" align="center">
<h4 align="center">Alugueres em ${mesEscolhido}/${anoEscolhido}</h4></div>
  <table id="tabela1">
    <tr>
      <th align="center">Matrícula</th>
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
    <c:forEach var="bic" items="${aluguersMes}" varStatus="index">
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

<c:if test="${not empty vendasMes}">
<div id="titulo" align="center">
<h4 align="center">Vendas em ${mesEscolhido}/${anoEscolhido}</h4></div>
  <table id="tabela1" align="center">
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
    <c:forEach var="bic" items="${vendasMes}" varStatus="index">
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
<br/>
<div align="center">
  <c:if test="${not empty aluguersMes || not empty vendasMes}">
    <form method="get" action="http:GerarPdf">
      <input type="hidden" value="${mesEscolhido}" name="mes">
      <input type="hidden" value="${anoEscolhido}" name="ano">
      <input type="submit" value="Gerar PDF">
    </form>  
  </c:if>
</div>
</div>

</div>

<%@ include file="footer.jsp"%>
</body>
</html>