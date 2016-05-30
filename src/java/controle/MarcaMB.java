package controle;

import dao.MarcaDao;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Marca;
import util.UtilMensagens;

/*
    @author 
    Gustavo Silva Melo 
    Ciência da Computação - UNIFOR MG
 */

@ManagedBean(name="marcaMB")
@SessionScoped
public class MarcaMB implements Serializable{

    private List<Marca> marcas;
    private Marca marca;
    private MarcaDao marcaDao;
    
    private String nome;
    
    public MarcaMB() {
        marcaDao = new MarcaDao();
        marcas = marcaDao.listar();
    }
    
    public String novo(){
        marca = new Marca();
        return "novo?faces-redirect=true";
    }
    
    public String gravar(){
        defineObjeto();
        if(marcaDao.gravar(marca)){
            marcas = marcaDao.listar();
            limpaVariaveis();
            UtilMensagens.mensagemSucesso("Cadastrado com sucesso!");
            return "lista?faces-redirect=true";
        }else{
            UtilMensagens.mensagemErro("Falha ao cadastrar!");
            return null;
        }
    }
    
    public String alteracao(Marca obj){
        marca = obj;
        nome = obj.getNome();
        return "altera?faces-redirect=true";
    }
    
    public String alterar(){
        defineObjeto();
        if(marcaDao.alterar(marca)){
            marcas = marcaDao.listar();
            limpaVariaveis();
            UtilMensagens.mensagemSucesso("Registro alterado!");
            return "lista?faces-redirect=true";
        }else{
            UtilMensagens.mensagemErro("Falha na alteração!");
            return null;
        }
    }
    
    public String excluir(Marca obj){
        if(marcaDao.excluir(obj)){
            marcas = marcaDao.listar();
            UtilMensagens.mensagemSucesso("Registro excluído!");
            return "lista?faces-redirect-true";
        }else{
            UtilMensagens.mensagemErro("Erro na exclusão!");
            return null;
        }
    }
    
    public String voltarIndex(){
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
        marca.setNome(nome);
    }

    public List<Marca> getMarcas() {
        return marcas;
    }

    public void setMarcas(List<Marca> marcas) {
        this.marcas = marcas;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public MarcaDao getMarcaDao() {
        return marcaDao;
    }

    public void setMarcaDao(MarcaDao marcaDao) {
        this.marcaDao = marcaDao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
