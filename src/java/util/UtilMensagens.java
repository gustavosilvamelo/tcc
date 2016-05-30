package util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class UtilMensagens {
    
    public static void mensagemSucesso(String mensagem){
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,mensagem,"");
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        context.addMessage(null, msg);
    }
    
    public static void mensagemErro(String mensagem){
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,mensagem,"");
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        context.addMessage(null, msg);
    }
    
}
