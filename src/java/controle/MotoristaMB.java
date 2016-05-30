package controle;

import dao.CidadeDao;
import dao.MotoristaDao;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Cidade;
import modelo.Motorista;
import util.UtilMensagens;

/*
    @author 
    Gustavo Silva Melo 
    Ciência da Computação - UNIFOR MG
 */

@ManagedBean(name="motoristaMB")
@SessionScoped
public class MotoristaMB implements Serializable{

    private List<Motorista> motoristas;
    private Motorista motorista;
    private MotoristaDao motoristaDao;
    
    private Cidade cidade;
    private CidadeDao cidadeDao;
    
    private String nome;
    private String cpf;
    private Date data_nascimento;
    private String endereco;
    private String bairro;
    private String numero;
    private String telefone;
    private String celular;
    private String email;
    private String cnh;
    private String categoria;
    private String observacao;
    private int idCidade;
    
    
    public MotoristaMB() {
        motoristaDao = new MotoristaDao();
        motoristas = motoristaDao.listar();
    }

    public String novo(){
        motorista = new Motorista();
        return "novo?faces-redirect=true";
    }
    
        public String gravar(){
        defineObjeto();
        if(motoristaDao.gravar(motorista)){
            motoristas = motoristaDao.listar();
            limpaVariaveis();
            UtilMensagens.mensagemSucesso("Cadastrado com sucesso!");
            return "lista?faces-redirect=true";
        }else{
            UtilMensagens.mensagemErro("Falha ao cadastrar!");
            return null;
        }
    }
    
    public String alteracao(Motorista obj){
        motorista = obj;
        nome = obj.getNome();
        cpf = obj.getCpf();
        data_nascimento = obj.getDataNascimento();
        endereco = obj.getEndereco();
        bairro = obj.getBairro();
        email = obj.getEmail();
        numero = obj.getNumero();
        telefone = obj.getTelefone();
        celular = obj.getCelular();
        cnh = obj.getCnh();
        categoria = obj.getCategoria();
        observacao = obj.getObservacao();        
        idCidade = obj.getIdCidade().getId();
        return "altera?faces-redirect=true";
    }
    
    public String alterar(){
        defineObjeto();
        if(motoristaDao.alterar(motorista)){
            motoristas = motoristaDao.listar();
            limpaVariaveis();
            UtilMensagens.mensagemSucesso("Registro alterado!");
            return "lista?faces-redirect=true";
        }else{
            UtilMensagens.mensagemErro("Falha na alteração!");
            return null;
        }
    }
    
    public String excluir(Motorista obj){
        if(motoristaDao.excluir(obj)){
            motoristas = motoristaDao.listar();
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
        motorista.setNome(nome);
        motorista.setCpf(cpf);
        motorista.setDataNascimento(data_nascimento);
        motorista.setEndereco(endereco);
        motorista.setBairro(bairro);
        motorista.setNumero(numero);
        motorista.setTelefone(telefone);
        motorista.setCelular(celular);
        motorista.setEmail(email);
        motorista.setCnh(cnh);
        motorista.setCategoria(categoria);
        motorista.setObservacao(observacao);
        
        cidadeDao = new CidadeDao();
        cidade = cidadeDao.consultar(idCidade);
        motorista.setIdCidade(cidade);
    }
    
    public void limpaVariaveis(){
        nome = null;
        cpf = null;
        data_nascimento = null;
        endereco = null;
        bairro = null;
        numero = null;
        telefone = null;
        celular = null;
        email = null;
        cnh = null;
        categoria = null;
        observacao = null;        
    }

    public List<Motorista> getMotoristas() {
        return motoristas;
    }

    public void setMotoristas(List<Motorista> motoristas) {
        this.motoristas = motoristas;
    }

    public Motorista getMotorista() {
        return motorista;
    }

    public void setMotorista(Motorista motorista) {
        this.motorista = motorista;
    }

    public MotoristaDao getMotoristaDao() {
        return motoristaDao;
    }

    public void setMotoristaDao(MotoristaDao motoristaDao) {
        this.motoristaDao = motoristaDao;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(Date data_nascimento) {
        this.data_nascimento = data_nascimento;
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

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public int getIdCidade() {
        return idCidade;
    }

    public void setIdCidade(int idCidade) {
        this.idCidade = idCidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.idCidade;
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
        final MotoristaMB other = (MotoristaMB) obj;
        if (this.idCidade != other.idCidade) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MotoristaMB{" + "idCidade=" + idCidade + '}';
    }
  
}
