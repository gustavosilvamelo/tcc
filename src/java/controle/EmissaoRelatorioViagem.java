/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controle;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import util.UtilRelatorio;

/*
    @author 
    Gustavo Silva Melo 
    Ciência da Computação - UNIFOR MG
 */

@ManagedBean (name = "emissaoRelatorioViagem")
@RequestScoped
public class EmissaoRelatorioViagem implements Serializable{

    /*private Date dataInicial;
    private Date dataFinal;*/
    private String financeiro;

    public EmissaoRelatorioViagem() {
    }

    public void emitirRelatorio(){
        /*
        SimpleDateFormat sdf = new SimpleDateFormat("aaaa-MM-dd");
        String dataInicialStr = sdf.format(dataInicial);
        String dataFinalStr = sdf.format(dataFinal);
        */
        HashMap parametros = new HashMap();   
        parametros.put("financeiro",financeiro);
        /*
        parametros.put("dataInicial",dataInicial);
        parametros.put("dataFinal",dataFinal); */
        UtilRelatorio.gerarRelatoriosTela("Viagens", "Viagens", parametros);
    }

    public String getFinanceiro() {
        return financeiro;
    }

    public void setFinanceiro(String financeiro) {
        this.financeiro = financeiro;
    }
    
    
}
