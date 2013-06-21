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

<div id="formAlugar">
	
	<div id="titulopagina" align ="center">Escolher bicicletas:</div>
	<br/>
	<div align="center" >
 	<FORM action="http:ReservasAction" method="POST">
	Data Inicial: <input type="date" name="dataInicio" /><br>
	Data Final:<input type="date" name="dataFim" /><br>
	<br/>
	<INPUT type="submit" value="Ver Disponibilidade" /><br>
	</FORM>
   </div>

</div>
</div><br class="clearfix" />
</div>
<%@ include file="footer.jsp"%>

</body>
</html>