package reservas;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Login;
import bikes.Bicicleta;
import bikes.BicicletaDAO;
import bikes.categorias.CategoriaB;
import bikes.estado.EstadoB;

/**
 * Servlet implementation class NovaReservaAction
 */
@WebServlet("/NovaReservaAction")
public class NovaReservaAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReservaDAO resDAO = new ReservaDAO();
	public static double miliToDias = 0.0000000115740741;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		String forwardPage = "/alugadaCheck.jsp"; // por omissão

		HttpSession session = request.getSession();
		Login l = (Login) session.getAttribute("cliente");
		String idCliente = l.getId().toString();
		Date dataInicio = (Date) request.getSession().getAttribute("dataI");
		Date dataFim = (Date) request.getSession().getAttribute("dataF");
		//request.getSession().removeAttribute("dataI");
		//request.getSession().removeAttribute("dataF");
		float preco=0;
		int total=0;
		int disp=-1;
		String idCat = request.getParameter("idCat");

		try {
			preco = Float.parseFloat(request.getParameter("preco"));
			total =Integer.parseInt(request.getParameter("quantidade"));
			disp = Integer.parseInt(request.getParameter("disp"));
		} catch (NullPointerException ignored) {
			preco = 0;
			total=0;
			disp= -1;
			System.out.println("tou a levar o SACO");
		}

		System.out.println("total ->"+total+" e disp -> " + disp +" preco "+ preco);

		if(total==0 || total>disp){
			String	menssage="quantidades invalidas";
		request.setAttribute("erro", menssage);
		forwardPage = "/error.jsp";
		}

		else {
		BigDecimal precoTotal = new BigDecimal((total*preco*((dataFim.getTime()-dataInicio.getTime())*miliToDias)));
		CategoriaB cat = new CategoriaB();
		cat.setId(idCat);
		ReservaAluguer reserva = new ReservaAluguer();
		reserva.setDataInicio(new java.sql.Date(dataInicio.getTime()));
		reserva.setDataFim(new java.sql.Date(dataFim.getTime()));
		reserva.setTotal(precoTotal);
		reserva.setQuantidade(total);
		reserva.setIdCliente(idCliente);
		reserva.setCat(cat);
			System.out.println("VOU RESERVAR!");
			resDAO.addRes(reserva);
			resDAO.addResCat(reserva);
			request.setAttribute("valor", precoTotal.setScale(2, BigDecimal.ROUND_HALF_UP));
		}
		request.getRequestDispatcher(forwardPage).forward(request, response); // crucial

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
