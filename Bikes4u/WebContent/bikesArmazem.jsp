<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Bicicletas no Armazém</title>
<script type="text/javascript">
    var background;
    
    function highlightRow(tableRow)
    {
      if (background == '#dcfac9')
      {
        tableRow.style.backgroundColor = '#FFF';
        background = '#FFF';
      }
      else {
        var rows = document.getElementsByName("row");
        for(var i = 0; i < rows.length; i++) {
          if (rows[i] != tableRow){
            rows[i].style.backgroundColor = '#FFF';
          }
        }
        tableRow.style.backgroundColor = '#dcfac9';
        background = '#dcfac9';
      }
    }
    
    function getData(id){
        document.getElementById("idBicicletaM").value=id;
        document.getElementById("idBicicletaV").value=id;
    }
  </script>
<style type="text/css">

thead.fixedHeader tr {
  position: relative
}

html>body thead.fixedHeader tr {
  display: block
}

html>body tbody.scrollContent {
  display: block;
  height: auto;
  overflow: auto;
  width: 100%
}

tbody.scrollContent tr {
  background: #FFF;
  border-bottom: none;
  border-left: none;
  border-right: 1px solid #CCC;
  border-top: 1px solid #DDD;
  padding: 2px 3px 3px 4px
}

html>body thead.fixedHeader th {
  width: 200px
}

html>body thead.fixedHeader th + th {
  width: 240px
}

html>body thead.fixedHeader th + th + th {
  width: 316px
}

html>body tbody.scrollContent td {
  width: 200px
}

html>body tbody.scrollContent td + td {
  width: 240px
}

html>body tbody.scrollContent td + td + td {
  width: 300px
}
</style>
</head>
<body>

<div id="wrapper">
 <%@ include file="header.jsp" %>
  <%@ include file="menuHomeFuncionario.jsp" %>
  
<div id="pageAdmin">

<c:if test="${empty bicicletasArma}">
  <b>Não existe nenhuma bicicleta disponível!</b>
</c:if>
<div style="color:red">${errorMessage}</div>
<c:remove var="errorMessage" scope="session" /> 

<c:if test="${not empty bicicletasArma}">
 <p float="left"> *Seleccione a bicicleta desejada</p>
  <table align="center" id="tabela" class="scrollTable" width="100%">
  <thead class="fixedHeader">
    <tr style="background-color:grey; color:white"> 
      <th align="center">Bicicleta</th>
      <th align="center">Designação</th>
      <th align="center">Categoria</th>
      <th align="center">Nova</th>
      <th align="center">Última Manutenção</th>
    </tr>
  </thead>
    
  <tbody class="scrollContent">
    <c:forEach var="bic" items="${bicicletasArma}">
      <tr name="row" onclick="highlightRow(this); getData(${bic.idBicicleta});">
       
        <td align="center">${bic.idBicicleta}</td>
        <td align="center">${bic.designacao}</td>
        <td align="center">${bic.nomeCategoria}</td>
        <td align="center">
          <c:if test="${not empty bic.nova}">
            <c:if test="${bic.nova == 1}">Sim</c:if>
            <c:if test="${bic.nova == 0}">Não</c:if>
          </c:if>
        </td>
        <td align="center">
          <c:if test="${not empty bic.ultimaManutencao}">${bic.ultimaManutencao}</c:if>
          <c:if test="${empty bic.ultimaManutencao}"> - </c:if>
        </td>
      </tr>
    </c:forEach>
    <div>
    <form method="GET" action= 'http:FormManutencao' name='enviarManutencao'>
      <table  align="center">
        <tr>
          <td><input id="idBicicletaM" type="hidden" value="" name="idBicicleta"></td>
          <td><input type="submit" value="Enviar para Manutenção"></td>
        </tr>
      </table>
    </form>
    <form method="GET" action= 'http:VenderBicicleta' name='venderBicicleta'>
    <table  align="center">
      <tr>
          <td><input id="idBicicletaV" type="hidden" value="" name="idBicicleta"></td>
        <td><input type="submit" value="Vender"></td>
      </tr>
    </table>
    </form>
  </div>
 
  </tbody>
  
  </table>  
 
  
</c:if>


</div>

</div>
<br class="clearfix" />
<%@ include file="footer.jsp"%>

</body>
</html>