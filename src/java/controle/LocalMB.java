package controle;

import dao.CidadeDao;
import dao.ClienteDao;
import dao.LocalDao;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Local;
import modelo.Cliente;
import modelo.Cidade;
import util.UtilMensagens;

/*
    @author 
    Gustavo Silva Melo 
    Ciência da Computação - UNIFOR MG
 */

@ManagedBean (name = "localMB")
@SessionScoped
public class LocalMB implements Serializable{
    
    private List<Local> locais;
    private Local local;
    private LocalDao localDao;
    
    private Cliente cliente;
    private ClienteDao clienteDao;
    
    private Cidade cidade;
    private CidadeDao cidadeDao;
    
    private String nome;
    private String endereco;
    private String numero;
    private String bairro;
    private String complemento;    
    
    private int id_cliente;
    private int id_cidade;        
    
    public LocalMB() {
        localDao = new LocalDao();
        locais = localDao.listar();
    }

    public String gravar(){
        defineObjeto();
        if(localDao.gravar(local)){
            locais = localDao.listar();
            limpaVariaveis();
            UtilMensagens.mensagemSucesso("Cadastrado com sucesso!");
            return "lista?faces-redirect=true";
        }else{
            UtilMensagens.mensagemErro("Falha ao cadastrar!");
            return null;
        }
    }
    
    public String novo(){
        local = new Local();
        return "novo?faces-redirect=true";
    }
    
    public void defineObjeto(){
        local.setNome(nome);
        local.setEndereco(endereco);
        local.setNumero(numero);
        local.setBairro(bairro);
        local.setComplemento(complemento);
        
        clienteDao = new ClienteDao();
        cliente = clienteDao.consultar(id_cliente);
        local.setIdCliente(cliente);
        
        cidadeDao = new CidadeDao();
        cidade = cidadeDao.consultar(id_cidade);
        local.setIdCidade(cidade);
    }
    
    public String alteracao(Local obj){
        local = obj;
        nome = obj.getNome();
        endereco = obj.getEndereco();
        numero = obj.getNumero();
        bairro = obj.getBairro();
        complemento = obj.getComplemento();
        id_cliente = obj.getIdCliente().getId();
        id_cidade = obj.getIdCidade().getId();
        return "altera?faces-redirect=true";
    }
    
    public String alterar(){
        defineObjeto();
        if(localDao.alterar(local)){
            locais = localDao.listar();
            limpaVariaveis();
            UtilMensagens.mensagemSucesso("Registro alterado!");
            return "lista?faces-redirect=true";
        }else{
            UtilMensagens.mensagemErro("Falha na alteração!");
            return null;
        }
    }
    
    public String excluir(Local obj){
        if(localDao.excluir(local)){
            locais = localDao.listar();
            limpaVariaveis();
            UtilMensagens.mensagemSucesso("Registro excluído!");
            return "lista?faces-redirect=true";
        }else{
            UtilMensagens.mensagemErro("Falha na exclusão!");
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
        endereco = null;
        numero = null;
        bairro = null;
        complemento = null;
    }

    public List<Local> getLocais() {
        return locais;
    }

    public void setLocais(List<Local> locais) {
        this.locais = locais;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public LocalDao getLocalDao() {
        return localDao;
    }

    public void setLocalDao(LocalDao localDao) {
        this.localDao = localDao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ClienteDao getClienteDao() {
        return clienteDao;
    }

    public void setClienteDao(ClienteDao clienteDao) {
        this.clienteDao = clienteDao;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public CidadeDao getCidadeDao() {
        return cidadeDao;
    }

    public void setCidadeDao(CidadeDao cidadeDao) {
        this.cidadeDao = cidadeDao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_cidade() {
        return id_cidade;
    }

    public void setId_cidade(int id_cidade) {
        this.id_cidade = id_cidade;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.id_cliente;
        hash = 29 * hash + this.id_cidade;
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
        final LocalMB other = (LocalMB) obj;
        if (this.id_cliente != other.id_cliente) {
            return false;
        }
        if (this.id_cidade != other.id_cidade) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "LocalMB{" + "id_cliente=" + id_cliente + ", id_cidade=" + id_cidade + '}';
    }
       
}
