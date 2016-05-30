package controle;

import dao.MarcaDao;
import dao.ModeloDao;
import dao.VeiculoDao;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Veiculo;
import modelo.Marca;
import modelo.Modelo;
import util.UtilMensagens;

/*
    @author 
    Gustavo Silva Melo 
    Ciência da Computação - UNIFOR MG
 */

@ManagedBean (name = "veiculoMB")
@SessionScoped
public class VeiculoMB implements Serializable{
    
    private List<Veiculo> veiculos;
    private Veiculo veiculo;
    private VeiculoDao veiculoDao;
    
    private Marca marca;
    private MarcaDao marcaDao;
    
    private Modelo modelo;
    private ModeloDao modeloDao;
    
    private String placa;
    private int hodometro;
    private Float tara;
    private Float lotacao;
    private String rntrc;
    private String chassi;
    private String renavam;
    private String observacao;    
    private int id_modelo;
    private int id_marca;

    public VeiculoMB() {
        veiculoDao = new VeiculoDao();
        veiculos = veiculoDao.listar();
    }
    
    public String novo(){
        veiculo = new Veiculo();
        return "novo?faces-redirect=true";
    }
    
    public String gravar(){
        defineObjeto();
        if(veiculoDao.gravar(veiculo)){
            veiculos = veiculoDao.listar();
            limpaVariaveis();
            UtilMensagens.mensagemSucesso("Cadastrado com sucesso!");
            return "lista?faces-redirect=true";
        }else{
            UtilMensagens.mensagemErro("Falha ao cadastrar!");
            return null;
        }
    }
    
    public String alteracao(Veiculo obj){
        veiculo = obj;
        placa = obj.getPlaca();
        hodometro = obj.getHodometro();
        tara = obj.getTara();
        lotacao = obj.getLotacao();
        rntrc = obj.getRntrc();
        chassi = obj.getChassi();
        renavam = obj.getRenavam();
        observacao = obj.getRenavam();
        observacao = obj.getObservacao();
        id_marca = obj.getIdMarca().getId();
        id_modelo = obj.getIdModelo().getId();
        return "altera?faces-redirect=true";
    }
        public String alterar(){
        defineObjeto();
        if(veiculoDao.alterar(veiculo)){
            veiculos = veiculoDao.listar();
            limpaVariaveis();
            UtilMensagens.mensagemSucesso("Registro alterado!");
            return "lista?faces-redirect=true";
        }else{
            UtilMensagens.mensagemErro("Falha na alteração!");
            return null;
        }
    }

    
    public String excluir(Veiculo obj){
        if(veiculoDao.excluir(obj)){
            veiculos = veiculoDao.listar();
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
        veiculo.setPlaca(placa);
        veiculo.setHodometro(hodometro);
        veiculo.setTara(tara);
        veiculo.setLotacao(lotacao);
        veiculo.setRntrc(rntrc);
        veiculo.setChassi(chassi);
        veiculo.setRenavam(renavam);
        veiculo.setObservacao(observacao);
        
        marcaDao = new MarcaDao();
        marca = marcaDao.consultar(id_marca);
        veiculo.setIdMarca(marca);
        
        modeloDao = new ModeloDao();
        modelo = modeloDao.consultar(id_modelo);
        veiculo.setIdModelo(modelo);
    }

    public void limpaVariaveis(){
        placa = null;
        hodometro = 0;
        tara = null;
        lotacao = null;
        rntrc = null;
        chassi = null;
        renavam = null;
        observacao = null;
    }

    public List<Veiculo> getVeiculos() {
        return veiculos;
    }

    public void setVeiculos(List<Veiculo> veiculos) {
        this.veiculos = veiculos;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public VeiculoDao getVeiculoDao() {
        return veiculoDao;
    }

    public void setVeiculoDao(VeiculoDao veiculoDao) {
        this.veiculoDao = veiculoDao;
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

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getHodometro() {
        return hodometro;
    }

    public void setHodometro(int hodometro) {
        this.hodometro = hodometro;
    }

    public Float getTara() {
        return tara;
    }

    public void setTara(Float tara) {
        this.tara = tara;
    }

    public Float getLotacao() {
        return lotacao;
    }

    public void setLotacao(Float lotacao) {
        this.lotacao = lotacao;
    }

    public String getRntrc() {
        return rntrc;
    }

    public void setRntrc(String rntrc) {
        this.rntrc = rntrc;
    }

    public String getChassi() {
        return chassi;
    }

    public void setChassi(String chassi) {
        this.chassi = chassi;
    }

    public String getRenavam() {
        return renavam;
    }

    public void setRenavam(String renavam) {
        this.renavam = renavam;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public int getId_modelo() {
        return id_modelo;
    }

    public void setId_modelo(int id_modelo) {
        this.id_modelo = id_modelo;
    }

    public int getId_marca() {
        return id_marca;
    }

    public void setId_marca(int id_marca) {
        this.id_marca = id_marca;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.id_modelo;
        hash = 17 * hash + this.id_marca;
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
        final VeiculoMB other = (VeiculoMB) obj;
        if (this.id_modelo != other.id_modelo) {
            return false;
        }
        if (this.id_marca != other.id_marca) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "VeiculoMB{" + "id_modelo=" + id_modelo + ", id_marca=" + id_marca + '}';
    }
    
}
