
<div id="formInput" align="center">
<div id="titulopagina" align="center">Adicionar Bicicleta</div>
<br/>
<FORM action="http:NovaBikeAction" method="POST" enctype="multipart/form-data">
<table id="tabela" align="center">

<tr>
<td>Descrição:</td>
<td><INPUT id="nomeId" type="text" name="nome"/></td>
</tr>
<tr>
<td>Preço:</td>
<td><INPUT id="precoId" type="text" name="preco"/></td>
</tr>

<tr>
<td>Categoria: </td>
<td>
<select name="categoria">
  <c:forEach items="${categorias}" var="element">
    <option selected="selected" value="${element.id}">${element.tipo}</option>
  </c:forEach>
</select>
</td>
</tr>

<tr>
<td>Nova: </td>
<td>
<select name="nova">
    <option value="1">Sim</option>
    <option value="0">Não</option>
</select>
</td>
</tr>

<tr>
  <td>Imagem: </td>
  <td> <input type="file" name="imagem" style="width: 100px;" onchange="this.style.width = '100%';"></td>
</tr>

<tr>
<td><INPUT type="submit" value="Send"/></td>
</tr> </div>
</table>
</FORM>
</div>