<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="style/style.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>

<body>

  
<div id="wrapper">

 <%@ include file="header.jsp" %>
  <%@ include file="menuHomeFuncionario.jsp" %>
  
<div id="pageAdmin">
<div style="color:red">${errorMessage}</div>
<c:remove var="errorMessage" scope="session" /> 

<%@ include file="novaBicla.jsp" %>
 <c:if test="${not empty biclas}">
	<table  id="tabela1" align="center" >

	<tr>
    <th>Designação</th>
  	<th>Preço</th>
	 <th>Categoria</th>
  	 <th>Estado</th>	 
  	 <th>Nova</th>
     <th>Imagem</th>

 	</tr> 
 	<tr>


	<c:forEach items="${biclas}" var="element">
  <tr>
	
    <FORM action="http:BikesEditAction" method="POST" enctype="multipart/form-data">
   
     <input type="hidden" name="id" value="${element.matricula}"/>
     <input type="hidden" name="fotoAntiga" value="${element.foto}"/>
     <td><INPUT id="nomeId" type="text" name="nome" value="${element.nome}"/></td>
	

     <td> <INPUT id="precoId" type="text" name="preco" value="${element.preco}"/>&euro;</td>
    	
          <td> 
  <select name="categoria">
      <option selected="selected" value="${element.cat.id}">${element.cat.tipo}</option>
      <c:forEach items="${categorias}" var="element2">
        <option  value="${element2.id}">${element2.tipo}</option>
    </c:forEach>
    </select>
    </td>
	   <td>   <select name="estado">
    		<option selected="selected" value="${element.estado.id}">${element.estado.estado}</option>
    		<c:forEach items="${estados}" var="element3">
    			<option  value="${element3.id}">${element3.estado}</option>
			</c:forEach>
    	</select></td>
     <td> <select name="nova">
    <option selected="selected" value="${element.nova}">
    <c:if test="${element.nova == 1}">Sim</c:if>
    <c:if test="${element.nova == 0}">Não</c:if>
    </option>
    <option value="${element.trocarEstado()}">
    <c:if test="${element.trocarEstado() == 1}">Sim</c:if>
    <c:if test="${element.trocarEstado() == 0}">Não</c:if>
    </option> 
    </select></td>
    
       <td> <input type="file" name="imagem" title="lalalalal"style="width: 100px;" onchange="this.style.width = '100%';"/></td>
     <td> <INPUT type="submit" value="Editar"/></td>
    </FORM>
   <td> <FORM action="http:DeletBikeAction" method="POST">
   		<input type="hidden" name="id" value="${element.matricula}"/>
    	<INPUT type="submit" value="Apagar"/>
    </FORM>
    </td>	
	</tr>
</c:forEach>
</tr>
</table>
</c:if>

</div>
</div>
<%@ include file="footer.jsp"%>
</body>
</html>