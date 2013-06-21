
<div id="formInput" align="center">
<div id="titulopagina" align="center">Adicionar Bicicleta</div>
<br/>
	<table id="tabela">
		<FORM action="http:NovaBikeAction" method="POST">

		<tr>
		<td>Descrição:</td>
<td><INPUT id="nomeId" type="text" name="nome"/></td></tr>
<tr>
<td>
Preço:</td>
<td><INPUT id="precoId" type="text" name="preco"/></td></tr>
<tr>
<select name="categoria">
<c:forEach items="${categorias}" var="element">
    <option selected="selected" value="${element.id}">${element.tipo}</option>
</c:forEach>
</select></tr>
<tr>

<INPUT type="submit" value="Send"/></tr> </div>
</FORM></table></div>