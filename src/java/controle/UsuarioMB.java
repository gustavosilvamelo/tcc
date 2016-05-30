package controle;

import dao.UsuarioDao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Usuario;
import util.UtilMensagens;

/*
    @author 
    Gustavo Silva Melo 
    Ciência da Computação - UNIFOR MG
 */

@ManagedBean (name = "usuarioMB")
@SessionScoped
public class UsuarioMB implements Serializable{

    private List<Usuario> usuarios;
    private Usuario usuario;
    private UsuarioDao usuarioDao;
    
    private String login;
    private String senha;

    public UsuarioMB() {
        UsuarioDao usuarioDao = new UsuarioDao();
        usuarios = usuarioDao.listar();
    }
    
    public String novo(){
        usuario = new Usuario();
        return "novo?faces-redirect=true";
    }
    
    public void inicializarSistema(){
        UsuarioDao usuarioDao = new UsuarioDao();
        usuarioDao.inicializarSistema();
    }
    
    public String gravar(){
        defineObjeto();
        if(usuarioDao.gravar(usuario)){
            usuarios = usuarioDao.listar();
            limpaVariaveis();
            UtilMensagens.mensagemSucesso("Cadastrado com sucesso!");
            return "lista?faces-redirect=true";
        }else{
            UtilMensagens.mensagemErro("Falha ao cadastrar!");
            return null;
        }
    }
    
    public String alteracao(Usuario obj){
        usuario = obj;
        login = usuario.getLogin();
        senha = usuario.getSenha();
        return "altera?faces-redirect=true";
    }
    
    public String alterar(){
        defineObjeto();
        if(usuarioDao.alterar(usuario)){
            usuarios = usuarioDao.listar();
            limpaVariaveis();
            UtilMensagens.mensagemSucesso("Registro alterado!");
            return "lista?faces-redirect=true";
        }else{
            UtilMensagens.mensagemErro("Falha na alteração!");
            return null;
        }
    }
    
    public String excluir(Usuario obj){
        if(usuarioDao.excluir(obj)){
            usuarios = usuarioDao.listar();
            UtilMensagens.mensagemSucesso("Registro excluído!");
            return "lista?faces-redirect=true";
        }else{
            UtilMensagens.mensagemErro("Erro na exclusão!");
            return null;
        }
    }
    
    public String autenticacao(){
        if(login.equals("")){
            UtilMensagens.mensagemErro("Informe o login");
        }else{
            if(senha.equals("")){
                UtilMensagens.mensagemErro("Inforem a senha");
            }else{
                UsuarioDao usuarioDao = new UsuarioDao();
                Usuario usu = usuarioDao.consultarLogin(login);
                if(usu == null){
                    UtilMensagens.mensagemErro("Login inexistente");
                }else{
                    if(!usu.getSenha().equals(senha)){
                        UtilMensagens.mensagemErro("Senha incorreta");
                    }else{
                        return "/paginas/index.xhtml?faces-redirect=true";
                   }
                }
            }
        }
        return null;
    }
    
    public String voltarIndex(){
        limpaVariaveis();
        return "principal";
    } 
    
    public String voltar(){
        limpaVariaveis();
        return "lista?faces-redirect=true";
    }
    

    public void defineObjeto(){
        usuario.setLogin(login);
        usuario.setSenha(senha);
        
    }
    
    public void limpaVariaveis(){
        login = null;
        senha = null;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public UsuarioDao getUsuarioDao() {
        return usuarioDao;
    }

    public void setUsuarioDao(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    
}