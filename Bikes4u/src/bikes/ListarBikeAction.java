package bikes;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bikes.categorias.CategoriaBDAO;
import bikes.categorias.CategoriaEmprestimo;
import bikes.estado.EstadoB;
import bikes.estado.EstadoDAO;

/**
 * Servlet implementation class ListarBikeAction
 */
@WebServlet("/ListarBikeAction")
public class ListarBikeAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BicicletaDAO biclaB = new BicicletaDAO();
		List<Bicicleta> biclas = biclaB.findAll();
		CategoriaBDAO catB = new CategoriaBDAO();
		List<CategoriaEmprestimo> cats = catB.findAll();
		EstadoDAO estaB = new EstadoDAO();
		List<EstadoB> estados = estaB.findAll();
		request.setAttribute("estados", estados);
		request.setAttribute("categorias", cats);
		request.setAttribute("biclas", biclas);
		request.getRequestDispatcher("/listaBiclaStock.jsp").forward(request, response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	
}
