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
<p/>
<c:if test="${empty reservasPassadas && empty reservasFuturas && empty reservasAtuais}">
 <div align="center" > <b>Nenhuma reserva foi efetuada!</b></div>
</c:if>

<div style="color:red">${errorMessage}</div>
<c:remove var="errorMessage" scope="session" /> 
<p/>  

<c:if test="${not empty reservasPassadas}">
<div align="center" id="titulo"><h4>Reservas Passadas</h4></div>
  <table   id="tabela1"  align="center" >
    <tr>
     
      <th align="center">Id da Reserva</th>
      <th align="center">Data Inicial</th>
      <th align="center">Data Final</th>
      <th align="center">Preço</th>
      <th align="center">Categoria</th>
      <th align="center">Quantidade</th>
      <th align="center">Registada</th>
    </tr>
    <c:forEach var="rp" items="${reservasPassadas}" varStatus="index">
      <tr>
        
        <td align="center">${rp.idReserva}</td>
        <td align="center">${rp.dataInicio}</td>
        <td align="center">${rp.dataFim}</td>
        <td align="center">${rp.preco} &euro;</td>
        <td align="center">${rp.nomeCategoria}</td>
        <td align="center">${rp.quantidade}</td>
        <td align="center">
          <c:if test="${rp.idRegistoA != 0}">Sim</c:if>
          <c:if test="${rp.idRegistoA == 0}">Não</c:if>
        </td>
      </tr>
    </c:forEach>
  </table>  
</c:if>
<p/>
<p/>
<c:if test="${not empty reservasAtuais}">
<h2>Reservas Atuais</h2>
  <table  id="tabela1"  align="center" >
    <tr>
    
      <th align="center">Id da Reserva</th>
      <th align="center">Data Inicial</th>
      <th align="center">Data Final</th>
      <th align="center">Preço</th>
      <th align="center">Categoria</th>
      <th align="center">Quantidade</th>
      <th align="center">Registada</th>
    </tr>
    <c:forEach var="rp" items="${reservasAtuais}" varStatus="index">
      <tr>
      
        <td align="center">${rp.idReserva}</td>
        <td align="center">${rp.dataInicio}</td>
        <td align="center">${rp.dataFim}</td>
        <td align="center">${rp.preco} &euro;</td>
        <td align="center">${rp.nomeCategoria}</td>
        <td align="center">${rp.quantidade}</td>
        <td align="center">
          <c:if test="${rp.idRegistoA != 0}">Sim</c:if>
          <c:if test="${rp.idRegistoA == 0}">Não</c:if>
        </td>
      </tr>
    </c:forEach>
  </table>  
</c:if>
<p/>
<p/>
<c:if test="${not empty reservasFuturas}">
<div id="titulo" align="center"><h4>Reservas Futuras</h4></div>
  <table  id="tabela1"  align="center" >
    <tr>
    
      <th align="center">Id da Reserva</th>
      <th align="center">Data Inicial</th>
      <th align="center">Data Final</th>
      <th align="center">Preço</th>
      <th align="center">Categoria</th>
      <th align="center">Quantidade</th>
      <th align="center">Registada</th>
    </tr>
    <c:forEach var="rp" items="${reservasFuturas}" varStatus="index">
      <tr>
       
        <td align="center">${rp.idReserva}</td>
        <td align="center">${rp.dataInicio}</td>
        <td align="center">${rp.dataFim}</td>
        <td align="center">${rp.preco} &euro;</td>
        <td align="center">${rp.nomeCategoria}</td>
        <td align="center">${rp.quantidade}</td>
        <td align="center">
          <c:if test="${rp.idRegistoA != 0}">Sim</c:if>
          <c:if test="${rp.idRegistoA == 0}">Não</c:if>
        </td>
        <td>
        <form method="POST" action="http:CancelarReserva">
          <input type="hidden" value="${rp.idReserva}" name="idReserva">
          <input type="submit" value="Cancelar">
        </form>
      </td>
      </tr>
    </c:forEach>
  </table>  
</c:if>
</div></div>
<%@ include file="footer.jsp"%>

</body>
</html>