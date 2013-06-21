package reservas;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bikes.BicicletaDAO;
import bikes.categorias.CategoriaBDAO;
import bikes.categorias.CategoriaEmprestimo;

/**
 * Servlet implementation class ReservasAction
 */
@WebServlet("/ReservasAction")
public class ReservasAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	String formato = "yyyy-MM-dd";	
	String forward ="/alugarBike.jsp";
	
	if ( request.getParameter("dataInicio").isEmpty() || request.getParameter("dataFim").isEmpty() ){
		String	menssage="Dados mal inseridos";
		request.setAttribute("erro", menssage);
		forward = "/error.jsp";
	}
	else{
		CategoriaBDAO catB = new CategoriaBDAO();
		BicicletaDAO bikeDAO = new BicicletaDAO();
		List<CategoriaEmprestimo> cats = catB.findAll();
		java.util.Date dataInicio = null;
		try {
				dataInicio = new SimpleDateFormat(formato).parse(request.getParameter("dataInicio"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			java.util.Date dataFim = null;
			try {
				dataFim = new SimpleDateFormat(formato).parse(request.getParameter("dataFim"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int vendias=0;
			int alugadas=0;
			int totalCat=0;
			for(CategoriaEmprestimo cat : cats){
				vendias=bikeDAO.nBikesVendidas(cat.getId());
				totalCat=bikeDAO.nBikesT(cat.getId());
				alugadas=bikeDAO.nBikesR(cat.getId(), new java.sql.Date(dataInicio.getTime()), new java.sql.Date(dataFim.getTime()));
				cat.setDisponibilidade(totalCat-(vendias+alugadas));
			}
			request.setAttribute("categorias", cats);
			request.getSession().setAttribute("dataI",dataInicio);
			request.getSession().setAttribute("dataF",dataFim);
	}
		
	request.getRequestDispatcher(forward).forward(request, response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}



}
