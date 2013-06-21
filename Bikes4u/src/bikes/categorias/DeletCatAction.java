package bikes.categorias;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeletCatAction
 */
@WebServlet("/DeletCatAction")
public class DeletCatAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CategoriaBDAO catDAO = new CategoriaBDAO();   
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String page = "RedirectAdicionarCategoria"; // por omissão
		String id = request.getParameter("id");
		catDAO.delete(id);
		
		response.sendRedirect(page); // crucial
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
