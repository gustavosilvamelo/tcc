package controle;

import dao.UnidadeDao;
import dao.ProdutoDao;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Produto;
import modelo.Unidade;
import util.UtilMensagens;

/*
    @author 
    Gustavo Silva Melo 
    Ciência da Computação - UNIFOR MG
 */

@ManagedBean (name = "produtoMB")
@SessionScoped
public class ProdutoMB implements Serializable{
    
    private List<Produto> produtos;
    private Produto produto;
    private ProdutoDao produtoDao;
    
    private Unidade unidade;
    private UnidadeDao unidadeDao;
    
    private String descricao;
    private Float preco_atual;
    private int id_Unidade;

    public ProdutoMB() {
        produtoDao = new ProdutoDao();
        produtos = produtoDao.listar();
    }
    
    public String novo(){
        produto = new Produto();
        return "novo?faces-redirect=true";
    }

    public String gravar(){
        defineObjeto();
        if(produtoDao.gravar(produto)){
            produtos = produtoDao.listar();
            limpaVariaveis();
            UtilMensagens.mensagemSucesso("Cadastrado com sucesso!");
            return "lista?faces-redirect=true";
        }else{
            UtilMensagens.mensagemErro("Falha ao cadastrar!");
            return null;
        }
       
    }
    
    public String alteracao(Produto obj){
        produto = obj;
        descricao = obj.getDescricao();
        preco_atual = obj.getPrecoAtual();
        id_Unidade = obj.getIdUnidade().getId();
        return "altera?faces-redirect=true";
    }
    
    public String alterar(){
        defineObjeto();
        if(produtoDao.alterar(produto)){
            produtos = produtoDao.listar();
            limpaVariaveis();
            UtilMensagens.mensagemSucesso("Registro alterado!");
            return "lista?faces-redirect=true";
        }else{
            UtilMensagens.mensagemErro("Falha na alteração!");
            return null;
        }
    }
    
    public String excluir(Produto obj){
        if(produtoDao.excluir(obj)){
            produtos = produtoDao.listar();
            limpaVariaveis();
            UtilMensagens.mensagemSucesso("Registro excluído!");
            return "lista?faces-redirect=true";
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
    
    public void defineObjeto(){
        produto.setDescricao(descricao);
        produto.setPrecoAtual(preco_atual);
        
        unidadeDao = new UnidadeDao();
        unidade = unidadeDao.consultar(id_Unidade);
        produto.setIdUnidade(unidade);
        
    }
    
    public void limpaVariaveis(){
        descricao = null;
        preco_atual = null;        
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public ProdutoDao getProdutoDao() {
        return produtoDao;
    }

    public void setProdutoDao(ProdutoDao produtoDao) {
        this.produtoDao = produtoDao;
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

    public Float getPreco_atual() {
        return preco_atual;
    }

    public void setPreco_atual(Float preco_atual) {
        this.preco_atual = preco_atual;
    }

    public int getId_Unidade() {
        return id_Unidade;
    }

    public void setId_Unidade(int id_Unidade) {
        this.id_Unidade = id_Unidade;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.id_Unidade;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProdutoMB other = (ProdutoMB) obj;
        if (this.id_Unidade != other.id_Unidade) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ProdutoMB{" + "id_Unidade=" + id_Unidade + '}';
    }
    
    
}
