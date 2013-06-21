<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="beans.Login"%>
<link rel="stylesheet" type="text/css" href="style/style.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <% Login c = (Login) session.getAttribute("cliente");
     Login f = (Login) session.getAttribute("funcionario");%>

<div id="header">
  <div id="logo">
  <%if (c == null && f == null) {%>
    <h1><a href="${pageContext.request.contextPath}"><img src="images/bikes4u.png"/></a></h1>
  <% } else if (f != null && c == null) { %>
    <h1><a href="${pageContext.request.contextPath}/BicicletasArmazem"><img src="images/bikes4u.png"/></a></h1>
  <% } else if (c != null && f == null){ %>
    <h1><a href="${pageContext.request.contextPath}/Home"><img src="images/bikes4u.png"/></a></h1>
  <%} %>
  </div>
  <div id="slogan">
      
  </div>
</div>


<div>
  <%if (c == null && f == null) {%>
  <div class="menu">
    <a class="horizontalbutton" href="${pageContext.request.contextPath}/ListaBicicletas">Pesquisa</a>
    <a class="horizontalbutton" href="${pageContext.request.contextPath}/home.jsp">P�gina Inicial</a>
  </div>
  <% } else if (c != null && f == null){ %>
  <div class="menu">
    <a class="horizontalbutton" href="${pageContext.request.contextPath}/Logout">Logout</a>
    <a class="horizontalbutton" href="${pageContext.request.contextPath}/MinhasReservas">Minhas Reservas</a>
    <a class="horizontalbutton" href="${pageContext.request.contextPath}/NovoAluguer">Reservar Bicicleta</a>
    <a class="horizontalbutton" href="${pageContext.request.contextPath}/ListaBicicletas">Pesquisa</a>
   <a class="horizontalbutton" href="${pageContext.request.contextPath}/Home">P�gina Inicial</a>
        
    
  </div>

  <% } else if (f != null && c == null) { %>
  <div class="menu">


	
	<ul id="navbar"> 
	  <li><a class="horizontalbutton" href="${pageContext.request.contextPath}/BicicletasArmazem">Bicicletas em Loja</a><li>
	  <li> <a class="horizontalbutton"  href="${pageContext.request.contextPath}/ListaReservas"> Reservas</a></li>
	<li>  <a class="horizontalbutton" href="${pageContext.request.contextPath}/ListaManutencoes"> Manuten��o </a></li>
    <li> <a class="horizontalbutton"  href="${pageContext.request.contextPath}/RegistoDiario"> Relat�rios</a></li>
   		
    <li><a class="horizontalbutton" href="${pageContext.request.contextPath}/Logout" >Logout</a></li>
     	</ul>
	
		</div>
    <a class="horizontalbutton" href="${pageContext.request.contextPath}/BicicletasArmazem"> Bicicletas no Armaz�m </a>
    <a class="horizontalbutton" href="${pageContext.request.contextPath}/Home">P�gina Inicial</a>
 
  
  <%} %>
   </div>
