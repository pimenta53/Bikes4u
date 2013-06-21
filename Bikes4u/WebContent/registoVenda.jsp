<%@ page language="java" contentType="text/html; charset=UTF-8" session="true"
    pageEncoding="UTF-8"%>
<html>
<head>


</head>
<body>
<div id="wrapper">


 <%@ include file="header.jsp" %>

	<div id="pageAdmin">
	

<div style="color:red">${errorMessage}</div>
<c:remove var="errorMessage" scope="session" />



<p/>  
<div id="form">
<form method="POST" action="http:RegistoVenda" name="registoV">

<div id="tituloPagina">Confirme o cliente que pretende efetuar a compra</div>
    <table id="tabela">
      <tr>
        <td>Identificador da Bicicleta: </td>
        <td> ${dadosVenda.idBicicleta}</td>
        <input type="hidden" name="idBicicleta" value="${dadosVenda.idBicicleta}"/>
      </tr>
      <tr>
        <td>Modelo da Bicicleta: </td>
        <td> ${dadosVenda.designacaoBicicleta}</td>
      </tr>
      <tr>
        <td>Categoria: </td>
        <td> ${dadosVenda.nomeCategoria}</td>
      </tr>
      <tr>
        <td>Preço: </td>
        <td> ${dadosVenda.preco} &euro;</td>
      </tr>
      <tr>
        <td>Nova: </td>
        <td> 
        <c:if test="${dadosVenda.nova == 0}">Não</c:if>
        <c:if test="${dadosVenda.nova == 1}">Sim</c:if>
        </td>
      </tr>
      <tr>
        <td>Funcionário: </td>
        <td> ${dadosVenda.funcionario}</td>
      </tr>
      <tr>
      <td>Cliente: </td> 
     <td>
     <select name="cliente">
       <option> </option>
       <c:forEach var="cli" items="${clientes}" varStatus="index">
        <option value="${cli.idCliente}">${cli.nomeCliente}</option>
      </c:forEach>
    </select>
    </td>
    </tr>
  
	<tr> <td><input type="button" value="Cancelar" onClick="history.go(-1);return true;"/>
	<input type="submit" value="Confirmar"/></td>
	
	</tr>

    </table>
   
</form>
</div>
</div>
</div>	
	<%@ include file="footer.jsp"%>

</body>

</html>