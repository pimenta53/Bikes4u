package bikes.categorias;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bikes.Bicicleta;
import bikes.BicicletaDAO;
import bikes.estado.EstadoB;

/**
 * Servlet implementation class EditCatAction
 */
@WebServlet("/EditCatAction")
public class EditCatAction extends HttpServlet {

	private static final long serialVersionUID = 1L;
	CategoriaBDAO catDAO = new CategoriaBDAO();   
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BigDecimal preco ;
		String page = "RedirectAdicionarCategoria"; // por omiss√£o
		String tipo = request.getParameter("tipo");
		try{
		preco = new BigDecimal(request.getParameter("preco"));
		}
		catch (NumberFormatException ignored) {
			preco = new BigDecimal(0);
		}
		String id = request.getParameter("id");
		if(tipo==null || tipo.equals("") || preco.compareTo(new BigDecimal(0)) == 0){
          request.getSession().setAttribute("errorMessage", "A operação não foi bem sucedida!");
		}
		else {
		CategoriaEmprestimo cat = new CategoriaEmprestimo();
		cat.setId(id);
		cat.setTipo(tipo);
		cat.setPreco(preco);
		catDAO.update(cat);
		}
		response.sendRedirect(page); // crucial
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
