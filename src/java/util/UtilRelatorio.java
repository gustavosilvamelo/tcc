package util;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/*
    @author 
    Gustavo Silva Melo 
    Ciência da Computação - UNIFOR MG
 */

public class UtilRelatorio {

    public static void gerarRelatoriosTela(String nomeRelatorio, String pastaRelatorio, HashMap parametros){
        try{
            String enderecoBd = "jdbc:postgresql://localhost:5432/TCC";
            Connection con = DriverManager.getConnection(enderecoBd, "postgres", "root");            
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ServletContext scontext = (ServletContext)facesContext.getExternalContext().getContext();
                        
            JasperPrint jp = JasperFillManager.fillReport(scontext.getRealPath("/WEB-INF/report/" + pastaRelatorio) + File.separator + 
                    nomeRelatorio + ".jasper", parametros, con);
            byte[] b = JasperExportManager.exportReportToPdf(jp);
            
            String realPath = scontext.getRealPath("/");
            
            File fileTeste = new File(realPath + "/report/");
            if (!fileTeste.exists())  {
                fileTeste.mkdir(); 
            }
            String caminho = realPath + "/report/" + nomeRelatorio + ".pdf";

            
            FileOutputStream fos = new FileOutputStream(caminho);
            fos.write(b);
            fos.close();

            //Gera o relatório na tela
            HttpServletResponse res = (HttpServletResponse)facesContext.getExternalContext().getResponse();            
            res.setContentType("application/pdf");
            res.setHeader("Content-disposition","inline);filename=" + nomeRelatorio + ".pdf");
            res.getOutputStream().write(b);
            facesContext.renderResponse();
            facesContext.responseComplete();
            
            fileTeste.delete();
            
            System.gc();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
