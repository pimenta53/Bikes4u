package bikes;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import bikes.categorias.CategoriaB;
import bikes.estado.EstadoB;

/**
 * Servlet implementation class NovaBikeAction
 */
@WebServlet("/NovaBikeAction")
public class NovaBikeAction extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	BicicletaDAO bikeDAO = new BicicletaDAO();   
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String page = "RedirectAdicionarBicicleta"; // por omiss√£o
		int count = 1, nova = 0;
		//imagem
	    String strPath = new File(getServletContext().getRealPath("/")).getParentFile().getParentFile().
	        getParentFile().getParentFile().getParentFile().getParentFile().getPath();
	    //Form fields
		String imagem = null, nome = null, idCat = null;
		BigDecimal preco = null;
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (!isMultipart) System.out.println("File Not Uploaded!");
		else {
	      FileItemFactory factory = new DiskFileItemFactory();
	      ServletFileUpload upload = new ServletFileUpload(factory);
	      List items = null;
	      try {
	        items = upload.parseRequest(request);
	        System.out.println("items: " + items);
	      } catch (FileUploadException e) {e.printStackTrace();}
	      Iterator itr = items.iterator();
	      while (itr.hasNext()) {
	        FileItem item = (FileItem) itr.next();
	        if (item.isFormField()){
	          //Verifica o nome do campo do formulario, e consoante esse nome, vai buscar o valor introduzido e atribui à respectiva
	          //variavel
	          String name = item.getFieldName();
	          if (name.equals("nome")) nome = item.getString();
	          else if (name.equals("preco")){
	            try {preco = new BigDecimal(item.getString());  
	            } catch (NumberFormatException ignored) { preco = new BigDecimal(0);}
	          }
	          else if (name.equals("categoria")) idCat = item.getString();
	          else if (name.equals("nova")) nova = Integer.parseInt(item.getString());
	        } else {
              if(nome == null || nome.equals("") || preco.compareTo(new BigDecimal(0)) == 0){
                request.getSession().setAttribute("errorMessage", "A operação não foi bem sucedida!");
                response.sendRedirect(page);
                return;
              }
	          try {
	            //Caso chegue à parte em que é para ir buscar o ficheiro, e neste formulario ainda nao existiu nenhum outro campo
	            //entao significa que o pedido veio do repBase
	            imagem = item.getName();
	            if(imagem.isEmpty()) break;

	            String reg = "[.*]";
	            String replacingtext = "";
	            Pattern pattern = Pattern.compile(reg);
	            Matcher matcher = pattern.matcher(imagem);
	            StringBuffer buffer = new StringBuffer();

	            while (matcher.find()) matcher.appendReplacement(buffer, replacingtext);

	            int IndexOf = imagem.lastIndexOf(".");
	            String domainName = imagem.substring(IndexOf);
	            String nomeF = imagem.substring(0, IndexOf);

	            System.out.println("Domain Name: " + domainName);
	            System.out.println("File Name: " + nomeF);
	            System.out.println("Final File: " + imagem);

	            new File(strPath + "/Bikes4u/WebContent/images/bicicletas").mkdir();
	            
	            //Caso existam ficheiros com o mesmo nome!!
	            File folder = new File(strPath + "/Bikes4u/WebContent/images/bicicletas");
	            String[] fileNames = folder.list();
	            for (int i = 0; i< fileNames.length; i++)
	              if (fileNames[i].contains(nomeF) && fileNames[i].contains(domainName)) count++;

	            if (count > 1) imagem = nomeF + count + domainName;

	            //Grava o ficheiro com o nome dado ou com o novo nome
	            File savedFile = new File(strPath + "/Bikes4u/WebContent/images/bicicletas" + "/" + imagem);
	            item.write(savedFile);

	          } catch (Exception e) {e.printStackTrace();}
	        }
	      }
	    }
		
		EstadoB estado = new EstadoB();
		estado.setId("4");
		CategoriaB cat = new CategoriaB();
		cat.setId(idCat);
		Bicicleta bicla = new Bicicleta();
		bicla.setNome(nome);
		bicla.setPreco(preco);
		if (!imagem.isEmpty()) bicla.setFoto(imagem);
		bicla.setNova(nova);
		bicla.setCat(cat);
		bicla.setEstado(estado);
		System.out.println("nome:" + nome + " preco:" + preco + " idCat:" + idCat + "imagem:" + imagem);
		bikeDAO.add(bicla);
		
		response.sendRedirect(page); // crucial
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
