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
  
<form method="GET" action= 'http:PesquisarLista' name='pesquisaPublicaBicicleta'>
  <table>
    <tr>
      <td><input type="text" name="textoPesquisa"></td>
      <td><input type="submit" value="Pesquisar"></td>
    </tr>
  </table>
</form>
<p/>
<c:if test="${empty listaBic}">
  <b>Não foi encontrada nenhuma bicicleta!</b>
</c:if>
<c:if test="${not empty listaBic}">

    <c:forEach var="bic" items="${listaBic}" varStatus="index">
    
    <div id="formUser">
    <div id="left">
    <div id="imagem">

    <c:if test="${empty bic.imagem}">
      <img width="140px" height="95px" src="images/bicicletas/desc.jpeg"/>
    </c:if>
    <c:if test="${not empty bic.imagem}">
      <img width="140px" height="95px" src="images/bicicletas/${bic.imagem}"/>
    </c:if>
    
    </div> 
    <div align="center"> 
    <c:if test="${bic.idEstado != 4 }">Indisponível</c:if>
    <c:if test="${bic.idEstado == 4 }">Disponível</c:if>
    </div>
    </div>
    <div id="right">
   <div id="nomeUser">${bic.designacao}
      <c:if test="${bic.nova == 0}">(Usada)</c:if>
      <c:if test="${bic.nova == 1}">(Nova)</c:if>  </div>
      <div id="tipoUser"  align="center">${bic.nomeCategoria}</div> <br/>
      <table align="center">
       <tr align="center">
       <td id="preco" >Preço Venda: </td> <td>${bic.precoVenda} &euro;</td></tr>
        <tr align="center"><td id="preco" >Preço Aluguer: </td> <td>${bic.precoVenda} &euro;</td></tr>
     
      
         </table>
      </div></div>

    </c:forEach>
</c:if>

      </div>

<br class="clearfix" />
</div>
<%@ include file="footer.jsp"%>

</body>
</html>