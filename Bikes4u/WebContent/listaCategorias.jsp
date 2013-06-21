<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

</head>
<body>

<div id="wrapper">

 <%@ include file="header.jsp" %>
  <%@ include file="menuHomeFuncionario.jsp" %>
 
<div id="pageAdmin">

<div style="color:red">${errorMessage}</div>
<c:remove var="errorMessage" scope="session" /> 
 <%@ include file="novaCategoria.jsp" %> <br/>
<c:if test="${ not empty categorias}">
<table  id="tabela1" align="center" >

<tr>
    <th>Designação</th>
	<th>Preço</th>
	<th></th><th></th>
	<th></th></tr>
<c:forEach items="${categorias}" var="element">

<tr>
    
    <FORM action="http:EditCatAction" method="POST">
     <input type="hidden" name="id" value="${element.id}"/>
   <td>	<INPUT id="tipoId" type="text" name="tipo" value="${element.tipo}"/></td>
   <td>	<INPUT id="precoId" type="text" name="preco" value="${element.preco}"/> &euro;<td>
   <td>	<INPUT type="submit" value="Editar"/>
     </FORM></td>
 	
     
   <td> <FORM action="http:DeletCatAction" method="POST">
    <input type="hidden" name="id" value="${element.id}"/>
    <INPUT type="submit" value="apagar"/><td>
    </FORM>
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