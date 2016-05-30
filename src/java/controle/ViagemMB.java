package controle;

import dao.ClienteDao;
import dao.ItemViagemDao;
import dao.LocalDao;
import dao.MotoristaDao;
import dao.ProdutoDao;
import dao.VeiculoDao;
import dao.ViagemDao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Cliente;
import modelo.ItemViagem;
import modelo.Viagem;
import modelo.Local;
import modelo.Veiculo;
import modelo.Motorista;
import modelo.Produto;
import util.UtilMensagens;

/*
    @author 
    Gustavo Silva Melo 
    Ciência da Computação - UNIFOR MG
 */

@ManagedBean(name = "viagemMB")
@SessionScoped
public class ViagemMB implements Serializable{
    
    private List<Viagem> viagens;
    private Viagem viagem;
    private ViagemDao viagemDao;
    
    private Local local;
    private LocalDao localDao;
    
    private Veiculo veiculo;
    private VeiculoDao veiculoDao;
    
    private Motorista motorista;
    private MotoristaDao motoristaDao;
        
    private int km_inicial;
    private int km_final;
    private Date data_inicial;
    private Date data_final;
    private int distancia;
    private Date data_financeiro;
    private String financeiro;
    
    private int id_local_origem;
    private int id_local_destino;
    private int id_veiculo;
    private int id_motorista;
 
    private int id_produto;
    private String descricao_produto;
    private float quantidade;
    private float valor_unitario;
    private float valor_total;
    private int id_cliente;
    private String nome_cliente;
    private int id_local_destino_item;
    private String nome_destino;
    private String repasse;
    private float valor_compra;
    private float valor_venda;
    private String tipo_frete;
    private float total_viagem;
    
    
    /* VÁRIAVEIS PARA TROCA DE TIPO */
    private Boolean com_repasse;
    private Boolean sem_repasse;
    private Boolean peso;
    private Boolean km;
    
    /* VARIAVEIS USADAS PARA ITEM DE VIAGEM*/
    private List<ItemViagem> itensViagem = new ArrayList();
    private ItemViagem itemViagem;    
    ProdutoDao produtoDao = new ProdutoDao();
    ClienteDao clienteDao = new ClienteDao();
    LocalDao locDao = new LocalDao();
    
    Produto prod;    
    Cliente cliente;
    Local loc;

    public ViagemMB() {
        viagemDao = new ViagemDao();
        viagens = viagemDao.listar();
        tipo_frete = "km";
        repasse = "nao";
    }
    
    public void adicionarLista(){
        defineObjeto();
        total_viagem = valor_total + total_viagem;
        itensViagem.add(itemViagem);            
        limpaVariaveisItens();        
    }
    
    
    
    
    public void defineObjeto(){
        itemViagem = new ItemViagem();
        //ProdutoDao produtoDao = new ProdutoDao();
        //Produto prod = produtoDao.consultar(id_produto);
        prod = produtoDao.consultar(id_produto);
        itemViagem.setIdProduto(prod);
        itemViagem.setQuantidade(quantidade);
        itemViagem.setValorUnitario(valor_unitario);
        itemViagem.setValorTotal(valor_total);
        //ClienteDao clienteDao = new ClienteDao();
        //Cliente cliente = clienteDao.consultar(id_cliente);
        cliente = clienteDao.consultar(id_cliente);
        itemViagem.setIdCliente(cliente);
        //LocalDao locDao = new LocalDao();
        //Local loc = locDao.consultar(id_local_destino);
        loc = locDao.consultar(id_local_destino);
        itemViagem.setIdLocalDestino(loc);
        itemViagem.setRepasse(repasse);
        itemViagem.setValorCompra(valor_compra);
        itemViagem.setValorVenda(valor_venda);
        itemViagem.setKmInicial(km_inicial);
        itemViagem.setKmFinal(km_final);
        
    }
    
    public String novo(){
        viagem = new Viagem();
        return "novo?faces-redirect=true";
    }
    
    public String gravar(){                
        defineObjetoItem();
        Integer idViagem = viagemDao.gravar(viagem);
        Viagem v = viagemDao.consultar(idViagem);
        Iterator it = itensViagem.iterator();
        
        ItemViagemDao itemViagemDao = new ItemViagemDao();
        while(it.hasNext()){
            ItemViagem item = (ItemViagem) it.next();
            item.setIdViagem(v);
            itemViagemDao.gravar(item);
        }
        
            viagens = viagemDao.listar();
            limpaVariaveis();
            limpaVariaveisItens();
            itensViagem = null;
            UtilMensagens.mensagemSucesso("Cadastrado com sucesso!");
            return "lista?faces-redirect=true";
        
    }
    
    public String alteracao(Viagem obj){
        viagem = obj;
        id_local_origem = obj.getIdLocalOrigem().getId();
        id_veiculo = obj.getIdVeiculo().getId();
        id_motorista = obj.getIdMotorista().getId();
        data_inicial = obj.getDataInicial();
        data_final = obj.getDataFinal();
        financeiro = obj.getFinanceiro();
        data_financeiro = obj.getDataFinanceiro();
        
        ItemViagemDao itemViagemDao = new ItemViagemDao();
        itensViagem = itemViagemDao.consultarItens(obj.getId());
        
        
        return "altera?faces-redirect=true";
    }

    public String alterar(){
        defineObjetoItem();
        if(viagemDao.alterar(viagem)){
            ItemViagemDao itemViagemDao = new ItemViagemDao();
            itemViagemDao.excluir(viagem.getId());
            
            Iterator it = itensViagem.iterator();
        
        
        while(it.hasNext()){
            ItemViagem item = (ItemViagem) it.next();
            item.setIdViagem(viagem);
            itemViagemDao.gravar(item);
        }
            
            viagens = viagemDao.listar();
            limpaVariaveis();
            UtilMensagens.mensagemSucesso("Registro alterado!");
            return "lista?faces-redirect=true";
        }else{
            UtilMensagens.mensagemErro("Falha na alteração!");
            return null;
        }
    }
    
    public String excluir(Viagem obj){
        if(viagemDao.excluir(obj)){
            viagens = viagemDao.listar();
            limpaVariaveis();
            return "lista?faces-redirect=true";
        }else{
            UtilMensagens.mensagemErro("Erro na excluxão!");
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

    
    public void defineObjetoItem(){
        viagem.setDataInicial(data_inicial);
        viagem.setDataFinal(data_final);
        viagem.setTotalViagem(total_viagem);
        viagem.setFinanceiro(financeiro);
        viagem.setDataFinanceiro(data_financeiro);
        
        localDao = new LocalDao();
        local = localDao.consultar(id_local_origem);
        viagem.setIdLocalOrigem(local);
        
        local = localDao.consultar(id_local_destino);
//        viagem.setIdLocalDestino(local);
        
        veiculoDao = new VeiculoDao();
        veiculo = veiculoDao.consultar(id_veiculo);
        viagem.setIdVeiculo(veiculo);
        
        motoristaDao = new MotoristaDao();
        motorista = motoristaDao.consultar(id_motorista);
        viagem.setIdMotorista(motorista);
        
    }
    
    public void limpaVariaveis(){
        
        id_local_origem = 0;
        id_veiculo = 0;
        id_motorista = 0;
        data_inicial = null;
        data_final = null;
        financeiro = null;
        data_financeiro = null;
        
        /*
        km_inicial = 0;
        km_final = 0;
        distancia = 0;
        data_inicial = null;
        data_final = null;
        distancia = 0;
        id_produto = 0;
        descricao_produto = null;
        quantidade = 0;
        valor_unitario = 0;
        valor_total = 0;
        id_cliente = 0;
        nome_cliente = null;
        id_local_destino_item = 0;
        nome_destino = null;
        repasse = null;
        valor_compra = 0;
        valor_venda = 0;
        prod = null;
        cliente = null;
        loc = null;     
        */
    }
    
    public void limpaVariaveisItens(){
        id_cliente = 0;
        id_produto = 0;
        id_local_destino = 0;
        km_inicial = 0;
        km_final = 0;
        distancia = 0;
        repasse = "nao";
        valor_compra = 0;
        valor_venda = 0;
        valor_unitario = 0;
        tipo_frete = "km";
        quantidade = 0;
        valor_unitario = 0;
        valor_total = 0;        
    }
    
    public void removeLista(ItemViagem item){
        itensViagem.remove(item);
    }
    
    public void trocaTipoRepasse(){
        if(repasse.equals("sim")){
            com_repasse = true;
            sem_repasse = false;
        }else{
            com_repasse = false;
            sem_repasse = true;
        }
        valor_compra = 0;
        valor_venda = 0;
        //valor_unitario = 0;     
        quantidade = 0;
        valor_total = 0;
    }

    public void trocaTipoFrete(){
        if(tipo_frete.equals("km")){
            peso = false;
            km = true;
        }else{
            peso = true;
            km = false;
        }
        
    }
        
    public void calculaDistancia(){
        distancia = km_final-km_inicial;        
    }
    
    
    public void calculoTotal(){
        System.out.println("metodo calculo total");
            if(tipo_frete.equals("km")){
                System.out.println("metodo tipo frete km");
                calculoFreteKM();
            }else{
                System.out.println("metodo tipo frete peso");
                calculoFretePeso();
            }                
    }
    
    public void calculoFreteKM(){
        System.out.println("Distância: " + distancia);
        System.out.println("Unitário: " + valor_unitario);

        valor_total = distancia*valor_unitario;
        System.out.println("Total: " + valor_total);

    }
    
    public void calculoFretePeso(){
        valor_total = quantidade*valor_unitario;
    }
    
    public void calculoRepasse(){
        valor_total = valor_venda*valor_unitario;
    }
    
    public String voltarIndex(){
        limpaVariaveis();
        return "principal";
    }
    
    public List<Viagem> getViagens() {
        return viagens;
    }

    public void setViagens(List<Viagem> viagens) {
        this.viagens = viagens;
    }

    public Viagem getViagem() {
        return viagem;
    }

    public void setViagem(Viagem viagem) {
        this.viagem = viagem;
    }

    public ViagemDao getViagemDao() {
        return viagemDao;
    }

    public void setViagemDao(ViagemDao viagemDao) {
        this.viagemDao = viagemDao;
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

    public int getKm_inicial() {
        return km_inicial;
    }

    public void setKm_inicial(int km_inicial) {
        this.km_inicial = km_inicial;
    }

    public int getKm_final() {
        return km_final;
    }

    public void setKm_final(int km_final) {
        this.km_final = km_final;
    }

    public Date getData_inicial() {
        return data_inicial;
    }

    public void setData_inicial(Date data_inicial) {
        this.data_inicial = data_inicial;
    }

    public Date getData_final() {
        return data_final;
    }

    public void setData_final(Date data_final) {
        this.data_final = data_final;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public int getId_local_origem() {
        return id_local_origem;
    }

    public void setId_local_origem(int id_local_origem) {
        this.id_local_origem = id_local_origem;
    }

    public int getId_local_destino() {
        return id_local_destino;
    }

    public void setId_local_destino(int id_local_destino) {
        this.id_local_destino = id_local_destino;
    }

    public int getId_veiculo() {
        return id_veiculo;
    }

    public void setId_veiculo(int id_veiculo) {
        this.id_veiculo = id_veiculo;
    }

    public int getId_motorista() {
        return id_motorista;
    }

    public void setId_motorista(int id_motorista) {
        this.id_motorista = id_motorista;
    }

    public int getId_produto() {
        return id_produto;
    }

    public void setId_produto(int id_produto) {
        this.id_produto = id_produto;
    }

    public String getDescricao_produto() {
        return descricao_produto;
    }

    public void setDescricao_produto(String descricao_produto) {
        this.descricao_produto = descricao_produto;
    }

    public float getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(float quantidade) {
        this.quantidade = quantidade;
    }

    public float getValor_unitario() {
        return valor_unitario;
    }

    public void setValor_unitario(float valor_unitario) {
        this.valor_unitario = valor_unitario;
    }

    public float getValor_total() {
        return valor_total;
    }

    public void setValor_total(float valor_total) {
        this.valor_total = valor_total;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNome_cliente() {
        return nome_cliente;
    }

    public void setNome_cliente(String nome_cliente) {
        this.nome_cliente = nome_cliente;
    }

    public int getId_local_destino_item() {
        return id_local_destino_item;
    }

    public void setId_local_destino_item(int id_local_destino_item) {
        this.id_local_destino_item = id_local_destino_item;
    }

    public String getNome_destino() {
        return nome_destino;
    }

    public void setNome_destino(String nome_destino) {
        this.nome_destino = nome_destino;
    }

    public String getRepasse() {
        return repasse;
    }

    public void setRepasse(String repasse) {
        this.repasse = repasse;
    }

    public float getValor_compra() {
        return valor_compra;
    }

    public void setValor_compra(float valor_compra) {
        this.valor_compra = valor_compra;
    }

    public float getValor_venda() {
        return valor_venda;
    }

    public void setValor_venda(float valor_venda) {
        this.valor_venda = valor_venda;
    }

    public List<ItemViagem> getItensViagem() {
        return itensViagem;
    }

    public void setItensViagem(List<ItemViagem> itensViagem) {
        this.itensViagem = itensViagem;
    }

    public ItemViagem getItemViagem() {
        return itemViagem;
    }

    public void setItemViagem(ItemViagem itemViagem) {
        this.itemViagem = itemViagem;
    }

    public Boolean getCom_repasse() {
        return com_repasse;
    }

    public void setCom_repasse(Boolean com_repasse) {
        this.com_repasse = com_repasse;
    }

    public Boolean getSem_repasse() {
        return sem_repasse;
    }

    public void setSem_repasse(Boolean sem_repasse) {
        this.sem_repasse = sem_repasse;
    }

    public ProdutoDao getProdutoDao() {
        return produtoDao;
    }

    public void setProdutoDao(ProdutoDao produtoDao) {
        this.produtoDao = produtoDao;
    }

    public ClienteDao getClienteDao() {
        return clienteDao;
    }

    public void setClienteDao(ClienteDao clienteDao) {
        this.clienteDao = clienteDao;
    }

    public LocalDao getLocDao() {
        return locDao;
    }

    public void setLocDao(LocalDao locDao) {
        this.locDao = locDao;
    }

    public Produto getProd() {
        return prod;
    }

    public void setProd(Produto prod) {
        this.prod = prod;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Local getLoc() {
        return loc;
    }

    public void setLoc(Local loc) {
        this.loc = loc;
    }

    public String getTipo_frete() {
        return tipo_frete;
    }

    public float getTotal_viagem() {
        return total_viagem;
    }

    public void setTotal_viagem(float total_viagem) {
        this.total_viagem = total_viagem;
    }

    public void setTipo_frete(String tipo_frete) {
        this.tipo_frete = tipo_frete;
    }

    public Boolean getPeso() {
        return peso;
    }

    public void setPeso(Boolean peso) {
        this.peso = peso;
    }

    public Boolean getKm() {
        return km;
    }

    public void setKm(Boolean km) {
        this.km = km;
    }

    public Date getData_financeiro() {
        return data_financeiro;
    }

    public void setData_financeiro(Date data_financeiro) {
        this.data_financeiro = data_financeiro;
    }

    public String getFinanceiro() {
        return financeiro;
    }

    public void setFinanceiro(String financeiro) {
        this.financeiro = financeiro;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.id_local_origem;
        hash = 89 * hash + this.id_local_destino;
        hash = 89 * hash + this.id_motorista;
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
        final ViagemMB other = (ViagemMB) obj;
        if (this.id_local_origem != other.id_local_origem) {
            return false;
        }
        if (this.id_local_destino != other.id_local_destino) {
            return false;
        }
        if (this.id_motorista != other.id_motorista) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ViagemMB{" + "id_local_origem=" + id_local_origem + ", id_local_destino=" + id_local_destino + ", id_motorista=" + id_motorista + '}';
    }

    
    
}
