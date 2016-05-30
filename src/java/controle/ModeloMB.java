package controle;

import dao.ModeloDao;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Modelo;
import util.UtilMensagens;

/*
    @author 
    Gustavo Silva Melo 
    Ciência da Computação - UNIFOR MG
 */

@ManagedBean(name="modeloMB")
@SessionScoped
public class ModeloMB implements Serializable{

    private List<Modelo> modelos;
    private Modelo modelo;
    private ModeloDao modeloDao;
    
    private String nome;
    
    public ModeloMB() {
        modeloDao = new ModeloDao();
        modelos = modeloDao.listar();
    }
    
    public String novo(){
        modelo = new Modelo();
        return "novo?faces-redirect=true";
    }
    
    public String gravar(){
        defineObjeto();
        if(modeloDao.gravar(modelo)){
            modelos = modeloDao.listar();
            limpaVariaveis();
            UtilMensagens.mensagemSucesso("Cadastrado com sucesso!");
            return "lista?faces-redirect=true";
        }else{
            UtilMensagens.mensagemErro("Falha ao cadastrar");
            return null;
        }
    }
    
    public String alteracao(Modelo obj){
        modelo = obj;
        nome = obj.getNome();
        return "altera?faces-redirect=true";
    }
    
    public String alterar(){
        defineObjeto();
        if(modeloDao.alterar(modelo)){
            modelos = modeloDao.listar();
            limpaVariaveis();
            UtilMensagens.mensagemSucesso("Registro alterado!");
            return "lista?faces-redirect=true";
        }else{
            UtilMensagens.mensagemErro("Falha na alteração!");
            return null;
        }
    }
    
    public String excluir(Modelo obj){
        if(modeloDao.excluir(obj)){
            modelos = modeloDao.listar();
            UtilMensagens.mensagemSucesso("Registro excluído!");
            return "lista?faces-redirect-true";
        }else{
            UtilMensagens.mensagemErro("Erro na exclusão!");
            return null;
        }
    }
    
    public String voltaIndex(){
        limpaVariaveis();
        return "principal";
    }
    
    public String voltar(){
        limpaVariaveis();
        return "lista?faces-redirect=true";
    }
    
    public void limpaVariaveis(){
        nome = null;
    }
    
    public void defineObjeto(){
        modelo.setNome(nome);
    }

    public List<Modelo> getModelos() {
        return modelos;
    }

    public void setModelos(List<Modelo> modelos) {
        this.modelos = modelos;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public ModeloDao getModeloDao() {
        return modeloDao;
    }

    public void setModeloDao(ModeloDao modeloDao) {
        this.modeloDao = modeloDao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
