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

@ManagedBean (name = "emissaoRelatorioCliente")
@RequestScoped
public class EmissaoRelatorioCliente implements Serializable{

 
    public EmissaoRelatorioCliente() {
    }

    public void emitirRelatorio(){
        UtilRelatorio.gerarRelatoriosTela("Clientes", "Clientes", null);
    }
    
}
