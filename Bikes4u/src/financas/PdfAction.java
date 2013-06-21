package financas;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import beans.RegistoTransaccao;

@SuppressWarnings("serial")
@WebServlet(urlPatterns={"/GerarPdf"})
public class PdfAction extends HttpServlet{
  

  public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, java.io.IOException{

    Integer mes =  Integer.parseInt(req.getParameter("mes"));
    Integer ano = Integer.parseInt(req.getParameter("ano"));
    BigDecimal sumA = new BigDecimal(0), sumV = new BigDecimal(0);
    
    FinancasDAO dao = new FinancasDAO();
    List<RegistoTransaccao> rtsAluguers = dao.getAllAluguersMesAno(mes, ano);
    List<RegistoTransaccao> rtsVendas = dao.getAllVendasMesAno(mes, ano);
    for(RegistoTransaccao rt : rtsAluguers) sumA = sumA.add(rt.getPreco());
    for(RegistoTransaccao rt : rtsVendas) sumV = sumV.add(rt.getPreco());
    
    //Creating the pdf document
    Document document = new Document(PageSize.LETTER.rotate(), 50, 50, 50, 50);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    try {
      @SuppressWarnings("unused")
      PdfWriter writer = PdfWriter.getInstance(document, baos);
      document.open();
      
      for(int i = 0; i < 8; i ++) document.add(new Paragraph(" "));

      Paragraph p;
      if(mes < 10) p = new Paragraph("Relat—rio Contabil’stico do Bikes4u em 0" + mes + "/" + ano, FontFactory.getFont(FontFactory.HELVETICA, 35, Font.BOLD));
      else p = new Paragraph("Relat—rio Contabil’stico do Bikes4u em " + mes + "/" + ano, FontFactory.getFont(FontFactory.HELVETICA, 35, Font.BOLD));
      p.setAlignment(Element.ALIGN_CENTER);
      document.add(p);
      document.newPage();
      //Alugueres
      p = new Paragraph("Alugueres", FontFactory.getFont(FontFactory.HELVETICA, 25, Font.BOLD));
      p.setSpacingAfter(25);
      document.add(p);
      //Create the table with the lending transactions
      document.add(createTable(rtsAluguers, 1));
      p = new Paragraph("Receitas: " + sumA + " Û", FontFactory.getFont(FontFactory.HELVETICA, 15));
      document.add(p);
      document.newPage();
      //Sales
      p = new Paragraph("Vendas", FontFactory.getFont(FontFactory.HELVETICA, 25, Font.BOLD));
      p.setSpacingAfter(25);
      document.add(p);
      //Create the table with the lending transactions
      document.add(createTable(rtsVendas, 0));
      p = new Paragraph("Receitas: " + sumV + " Û", FontFactory.getFont(FontFactory.HELVETICA, 15));
      p.setSpacingAfter(40);
      document.add(p);
      
      p = new Paragraph("Lucro Total: " + sumV.add(sumA) + " Û", FontFactory.getFont(FontFactory.HELVETICA, 20, Font.BOLD));
      p.setSpacingAfter(40);
      document.add(p);
      
      p = new Paragraph("O Respons‡vel,", FontFactory.getFont(FontFactory.HELVETICA, 20));
      p.setAlignment(Element.ALIGN_RIGHT);
      p.setSpacingAfter(20);
      document.add(p);
      
      p = new Paragraph("___________________", FontFactory.getFont(FontFactory.HELVETICA, 20));
      p.setAlignment(Element.ALIGN_RIGHT);
      document.add(p);
      
      document.close();
      // setting some response headers
      res.setHeader("Expires", "0");
      res.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
      res.setHeader("Pragma", "public");
      // setting the content type
      res.setContentType("application/pdf");
      // the contentlength
      res.setContentLength(baos.size());
      // write ByteArrayOutputStream to the ServletOutputStream
      OutputStream os = res.getOutputStream();
      baos.writeTo(os);
      os.flush();
      os.close();
    } catch (DocumentException e) {e.printStackTrace();}
  }
  
  public static PdfPTable createTable(List<RegistoTransaccao> rts, int alug) {
    // a table with twelve columns
    PdfPTable table;
    int szH = 0, szF = 0;
    if (alug == 1) {
      table = new PdfPTable(new float[] { 1, 3, 3, 3, 2, 2, 3, 3, 3, 3, 3, 2});
      szH = 11; szF = 9;
    }
    else {
      table = new PdfPTable(new float[] { 1, 3, 3, 3, 2, 2, 3, 3, 3, 2});
      szH = 13; szF = 11;
    }
    table.setHorizontalAlignment(Element.ALIGN_CENTER);
    table.setWidthPercentage(100f);
    table.getDefaultCell().setBackgroundColor(BaseColor.LIGHT_GRAY);
    table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
    
    for (int i = 0; i < 2; i++) {
      table.addCell(new Phrase("#", FontFactory.getFont(FontFactory.HELVETICA, szH, Font.BOLD)));
      table.addCell(new Phrase("Id da Transac‹o", FontFactory.getFont(FontFactory.HELVETICA, szH, Font.BOLD)));
      table.addCell(new Phrase("Data da Transac‹o", FontFactory.getFont(FontFactory.HELVETICA, szH, Font.BOLD)));
      table.addCell(new Phrase("Id da Bicicleta", FontFactory.getFont(FontFactory.HELVETICA, szH, Font.BOLD)));
      table.addCell(new Phrase("Modelo", FontFactory.getFont(FontFactory.HELVETICA, szH, Font.BOLD)));
      table.addCell(new Phrase("Nova", FontFactory.getFont(FontFactory.HELVETICA, szH, Font.BOLD)));
      table.addCell(new Phrase("Categoria", FontFactory.getFont(FontFactory.HELVETICA, szH, Font.BOLD)));
      if (alug == 1) table.addCell(new Phrase("Data In’cio", FontFactory.getFont(FontFactory.HELVETICA, szH, Font.BOLD)));
      if (alug == 1) table.addCell(new Phrase("Data Final", FontFactory.getFont(FontFactory.HELVETICA, szH, Font.BOLD)));
      table.addCell(new Phrase("Cliente", FontFactory.getFont(FontFactory.HELVETICA, szH, Font.BOLD)));
      table.addCell(new Phrase("Funcion‡rio", FontFactory.getFont(FontFactory.HELVETICA, szH, Font.BOLD)));
      table.addCell(new Phrase("Preo", FontFactory.getFont(FontFactory.HELVETICA, szH, Font.BOLD)));
    }
    table.getDefaultCell().setBackgroundColor(null);
    table.setHeaderRows(2);
    table.setFooterRows(1);
    Integer count = 1;
    for(RegistoTransaccao rt : rts){
      table.addCell(new Phrase("" + count, FontFactory.getFont(FontFactory.HELVETICA, szF))); count++;
      table.addCell(new Phrase("" + rt.getIdTransaccao(), FontFactory.getFont(FontFactory.HELVETICA, szF)));
      table.addCell(new Phrase("" + rt.getDataTransaccao(), FontFactory.getFont(FontFactory.HELVETICA, szF)));
      table.addCell(new Phrase("" + rt.getIdBicicleta(), FontFactory.getFont(FontFactory.HELVETICA, szF)));
      table.addCell(new Phrase("" + rt.getDesignacaoBicicleta(), FontFactory.getFont(FontFactory.HELVETICA, szF)));
      if(rt.getNova() == 1) table.addCell(new Phrase("Sim", FontFactory.getFont(FontFactory.HELVETICA, szF)));
      else table.addCell(new Phrase("N‹o", FontFactory.getFont(FontFactory.HELVETICA, szF)));
      table.addCell(new Phrase("" + rt.getNomeCategoria(), FontFactory.getFont(FontFactory.HELVETICA, szF)));
      if (alug == 1) table.addCell(new Phrase("" + rt.getDataInicioAluguer(), FontFactory.getFont(FontFactory.HELVETICA, szF)));
      if (alug == 1) table.addCell(new Phrase("" + rt.getDataFimAluguer(), FontFactory.getFont(FontFactory.HELVETICA, szF)));
      if(rt.getNomeCliente() != null) table.addCell(new Phrase("" + rt.getNomeCliente(), FontFactory.getFont(FontFactory.HELVETICA, szF)));
      else table.addCell(new Phrase("-", FontFactory.getFont(FontFactory.HELVETICA, szF)));
      table.addCell(new Phrase("" + rt.getNomeFuncionario(), FontFactory.getFont(FontFactory.HELVETICA, szF)));
      table.addCell(new Phrase("" + rt.getPreco() + " Û", FontFactory.getFont(FontFactory.HELVETICA, szF)));
    }
    table.setSpacingAfter(35);
    return table;
}

  public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, java.io.IOException{
    doGet(req, res);
  }
}
