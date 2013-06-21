<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

</head>
<div id="wrapper">


 <%@ include file="header.jsp" %>


	<div id="page">



<h4>Choose the rental period:</h4>

	<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
	<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>
	<script>
		$(function() {
			$("#datepicker").datepicker({ minDate: 0 });
		});
	</script>
	
	<form method="GET" action="http:PesquisaAlugar">
	<p>
		Start date: <input type="text" id="datepicker"/> 
		End date: <input type="text" id="datepicker"/>
	</p>
	</form>

<h4>Choose your bike:</h4>
	
	<!-- lista de bicicletas que estão disponiveis nos dias escolhidos pelo utilizador  -->
	
	<input type="submit" value="Ok"/>
	
	<table>
	<!c:forEach var="bicicleta" items=${Bicicletas.bicicletas}>
	<tr>
		<td>${bicicleta.categoria}</td>
		<td>${bicicleta.descricao}</td>
		<td>${bicicleta.precoAlugar}</td>
		<!preco total a pagar tendo em conta os dias>
		<!talvez aqui um link para alugar a bicicleta>
	</tr>
	<!/c:forEach>
</table>
<br class="clearfix" />
</div>
<%@ include file="footer.jsp"%>	
</body>
</html>