package controle;

import dao.UnidadeDao;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Unidade;
import util.UtilMensagens;

/*
    @author 
    Gustavo Silva Melo 
    Ciência da Computação - UNIFOR MG
 */

@ManagedBean(name="unidadeMB")
@SessionScoped
public class UnidadeMB implements Serializable{

    private List<Unidade> unidades;
    private Unidade unidade;
    private UnidadeDao unidadeDao;
    
    private String descricao;
    private String sigla;
    private int quantidade;
   
    public UnidadeMB() {
        unidadeDao = new UnidadeDao();
        unidades = unidadeDao.listar();
    }

    public String novo(){
        unidade = new Unidade();
        return "novo?faces-redirect=true";
    }
    
    public String gravar(){
        defineObjeto();
        if(unidadeDao.gravar(unidade)){
            unidades = unidadeDao.listar();
            limpaVariaveis();
            UtilMensagens.mensagemSucesso("Cadastrado com sucesso!");
            return "lista?faces-redirect-true";
        }else{
            UtilMensagens.mensagemErro("Falha ao cadastrar!");
            return null;
        }
    }        

    public String alteracao(Unidade obj){
        unidade = obj;
        descricao = obj.getDescricao();
        sigla = obj.getSigla();
        quantidade = obj.getQuantidade();
        return "altera?faces-redirect=true";
    }
    
    public String alterar(){
        defineObjeto();
        if(unidadeDao.alterar(unidade)){
            unidades = unidadeDao.listar();
            limpaVariaveis();
            UtilMensagens.mensagemSucesso("Registro alterado!");
            return "lista?faces-redirect=true";
        }else{
            UtilMensagens.mensagemErro("Falha na alteração!");
            return null;
        }
    }
    
    public String excluir(Unidade obj){        
        if(unidadeDao.excluir(obj)){
            unidades = unidadeDao.listar();
            UtilMensagens.mensagemSucesso("Registro excluído!");
            return "lista?faces-redirect=true";
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
        descricao = null;
        sigla = null;
        quantidade = 0;
    }
    
    public void defineObjeto(){
        unidade.setDescricao(descricao);
        unidade.setSigla(sigla);
        unidade.setQuantidade(quantidade);
    }

    public List<Unidade> getUnidades() {
        return unidades;
    }

    public void setUnidades(List<Unidade> unidades) {
        this.unidades = unidades;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    public UnidadeDao getUnidadeDao() {
        return unidadeDao;
    }

    public void setUnidadeDao(UnidadeDao unidadeDao) {
        this.unidadeDao = unidadeDao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
}

