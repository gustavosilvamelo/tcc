package controle;

import dao.CidadeDao;
import dao.ClienteDao;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Cliente;
import modelo.Cidade;
import util.UtilMensagens;

/*
    @author 
    Gustavo Silva Melo 
    Ciência da Computação - UNIFOR MG
 */

@ManagedBean(name = "clienteMB")
@SessionScoped
public class ClienteMB implements Serializable{
    
    private List<Cliente> clientes;
    private Cliente cliente;
    private ClienteDao clienteDao;
    
    private Cidade cidade;
    private CidadeDao cidadeDao;
    
    private String cpf_cnpj;
    private String nome;
    private String endereco;
    private String numero;
    private String bairro;
    private String complenento;
    private String telefone;
    private String celular;
    private String email;
    private String tipo;
    private int id_cidade;
    
    private Boolean exibe_cpf;
    private Boolean exibe_cnpj;
    

    public ClienteMB() {
        clienteDao = new ClienteDao();
        clientes = clienteDao.listar();
        tipo = "cpf";
        exibe_cpf = true;
        exibe_cnpj = false;
    }
    
    public String novo(){
        cliente = new Cliente();
        return "novo?faces-redirect=true";
    }
    
    public String gravar(){
        defineObjeto();
        if(clienteDao.gravar(cliente)){
            clientes = clienteDao.listar();
            limpaVariaveis();
            UtilMensagens.mensagemSucesso("Cadastrado com sucesso!");
            return "lista?faces-redirect=true";
        }else{
            UtilMensagens.mensagemErro("Falha ao cadastrar!");
            return null;
        }
    }
    
    public String alteracao(Cliente obj){
        cliente = obj;
        cpf_cnpj = obj.getCpfCnpj();
        nome = obj.getNome();
        endereco = obj.getEndereco();
        numero = obj.getNumero();
        bairro = obj.getBairro();
        complenento = obj.getComplemento();
        telefone = obj.getTelefone();
        celular = obj.getCelular();
        email = obj.getEmail();
        tipo = obj.getTipo();
        id_cidade = obj.getIdCidade().getId();
        return "altera?faces-redirect = true";
    }
    
    public String alterar(){
        defineObjeto();
        if(clienteDao.alterar(cliente)){
            clientes = clienteDao.listar();
            limpaVariaveis();
            UtilMensagens.mensagemSucesso("Registro alterado!");
            return "lista?faces-redirect=true";
        }else{
            UtilMensagens.mensagemErro("Falha na alteração");
            return null;
        }
    }
    
    public String excluir(Cliente obj){
        if(clienteDao.excluir(obj)){
            clientes = clienteDao.listar();
            limpaVariaveis();
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

    
    public void defineObjeto(){
        cliente.setCpfCnpj(cpf_cnpj);
        cliente.setNome(nome);
        cliente.setEndereco(endereco);
        cliente.setNumero(numero);
        cliente.setBairro(bairro);
        cliente.setComplemento(complenento);
        cliente.setTelefone(telefone);
        cliente.setCelular(celular);
        cliente.setEmail(email);
        cliente.setTipo(tipo);
        
        cidadeDao = new CidadeDao();
        cidade = cidadeDao.consultar(id_cidade);
        cliente.setIdCidade(cidade);
    }

    public void limpaVariaveis(){
        cpf_cnpj = null;
        nome = null;
        endereco = null;
        numero = null;
        bairro = null;
        complenento = null; 
        telefone = null;
        celular = null;
        email = null;
        tipo = null;        
    }

    public void trocaTipo(){
        if(tipo.equals("cpf")){
            exibe_cpf = true;
            exibe_cnpj = false;
        }else{
            exibe_cnpj = true;
            exibe_cpf = false;
        }
        cpf_cnpj = null;
    }
    
    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
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

    public String getCpf_cnpj() {
        return cpf_cnpj;
    }

    public void setCpf_cnpj(String cpf_cnpj) {
        this.cpf_cnpj = cpf_cnpj;
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

    public String getComplenento() {
        return complenento;
    }

    public void setComplenento(String complenento) {
        this.complenento = complenento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getId_cidade() {
        return id_cidade;
    }

    public void setId_cidade(int id_cidade) {
        this.id_cidade = id_cidade;
    }

    public Boolean getExibe_cpf() {
        return exibe_cpf;
    }

    public void setExibe_cpf(Boolean exibe_cpf) {
        this.exibe_cpf = exibe_cpf;
    }

    public Boolean getExibe_cnpj() {
        return exibe_cnpj;
    }

    public void setExibe_cnpj(Boolean exibe_cnpj) {
        this.exibe_cnpj = exibe_cnpj;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id_cidade;
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
        final ClienteMB other = (ClienteMB) obj;
        if (this.id_cidade != other.id_cidade) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ClienteMB{" + "id_cidade=" + id_cidade + '}';
    }    
    
}
