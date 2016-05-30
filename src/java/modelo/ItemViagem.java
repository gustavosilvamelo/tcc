package modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/*
    @author 
    Gustavo Silva Melo 
    Ciência da Computação - UNIFOR MG
 */

@Entity
@Table(name = "item_viagem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItemViagem.findAll", query = "SELECT i FROM ItemViagem i"),
    @NamedQuery(name = "ItemViagem.findByIdItemViagem", query = "SELECT i FROM ItemViagem i WHERE i.idItemViagem = :idItemViagem"),
    @NamedQuery(name = "ItemViagem.findByQuantidade", query = "SELECT i FROM ItemViagem i WHERE i.quantidade = :quantidade"),
    @NamedQuery(name = "ItemViagem.findByValorUnitario", query = "SELECT i FROM ItemViagem i WHERE i.valorUnitario = :valorUnitario"),
    @NamedQuery(name = "ItemViagem.findByValorTotal", query = "SELECT i FROM ItemViagem i WHERE i.valorTotal = :valorTotal"),
    @NamedQuery(name = "ItemViagem.findByRepasse", query = "SELECT i FROM ItemViagem i WHERE i.repasse = :repasse"),
    @NamedQuery(name = "ItemViagem.findByValorCompra", query = "SELECT i FROM ItemViagem i WHERE i.valorCompra = :valorCompra"),
    @NamedQuery(name = "ItemViagem.findByValorVenda", query = "SELECT i FROM ItemViagem i WHERE i.valorVenda = :valorVenda"),
    @NamedQuery(name = "ItemViagem.findByTipoFrete", query = "SELECT i FROM ItemViagem i WHERE i.tipoFrete = :tipoFrete"),
    @NamedQuery(name = "ItemViagem.findByDistancia", query = "SELECT i FROM ItemViagem i WHERE i.distancia = :distancia"),
    @NamedQuery(name = "ItemViagem.findByKmInicial", query = "SELECT i FROM ItemViagem i WHERE i.kmInicial = :kmInicial"),
    @NamedQuery(name = "ItemViagem.findByKmFinal", query = "SELECT i FROM ItemViagem i WHERE i.kmFinal = :kmFinal")})
public class ItemViagem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_item_viagem")
    private Integer idItemViagem;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "quantidade")
    private Float quantidade;
    @Column(name = "valor_unitario")
    private Float valorUnitario;
    @Column(name = "valor_total")
    private Float valorTotal;
    @Column(name = "repasse")
    private String repasse;
    @Column(name = "valor_compra")
    private Float valorCompra;
    @Column(name = "valor_venda")
    private Float valorVenda;
    @Column(name = "tipo_frete")
    private String tipoFrete;
    @Column(name = "distancia")
    private Integer distancia;
    @Column(name = "km_inicial")
    private Integer kmInicial;
    @Column(name = "km_final")
    private Integer kmFinal;
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    @ManyToOne
    private Cliente idCliente;
    @JoinColumn(name = "id_local_destino", referencedColumnName = "id")
    @ManyToOne
    private Local idLocalDestino;
    @JoinColumn(name = "id_produto", referencedColumnName = "id")
    @ManyToOne
    private Produto idProduto;
    @JoinColumn(name = "id_viagem", referencedColumnName = "id")
    @ManyToOne
    private Viagem idViagem;

    public ItemViagem() {
    }

    public ItemViagem(Integer idItemViagem) {
        this.idItemViagem = idItemViagem;
    }

    public Integer getIdItemViagem() {
        return idItemViagem;
    }

    public void setIdItemViagem(Integer idItemViagem) {
        this.idItemViagem = idItemViagem;
    }

    public Float getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Float quantidade) {
        this.quantidade = quantidade;
    }

    public Float getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(Float valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getRepasse() {
        return repasse;
    }

    public void setRepasse(String repasse) {
        this.repasse = repasse;
    }

    public Float getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(Float valorCompra) {
        this.valorCompra = valorCompra;
    }

    public Float getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(Float valorVenda) {
        this.valorVenda = valorVenda;
    }

    public String getTipoFrete() {
        return tipoFrete;
    }

    public void setTipoFrete(String tipoFrete) {
        this.tipoFrete = tipoFrete;
    }

    public Integer getDistancia() {
        return distancia;
    }

    public void setDistancia(Integer distancia) {
        this.distancia = distancia;
    }

    public Integer getKmInicial() {
        return kmInicial;
    }

    public void setKmInicial(Integer kmInicial) {
        this.kmInicial = kmInicial;
    }

    public Integer getKmFinal() {
        return kmFinal;
    }

    public void setKmFinal(Integer kmFinal) {
        this.kmFinal = kmFinal;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    public Local getIdLocalDestino() {
        return idLocalDestino;
    }

    public void setIdLocalDestino(Local idLocalDestino) {
        this.idLocalDestino = idLocalDestino;
    }

    public Produto getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Produto idProduto) {
        this.idProduto = idProduto;
    }

    public Viagem getIdViagem() {
        return idViagem;
    }

    public void setIdViagem(Viagem idViagem) {
        this.idViagem = idViagem;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idItemViagem != null ? idItemViagem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemViagem)) {
            return false;
        }
        ItemViagem other = (ItemViagem) object;
        if ((this.idItemViagem == null && other.idItemViagem != null) || (this.idItemViagem != null && !this.idItemViagem.equals(other.idItemViagem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.ItemViagem[ idItemViagem=" + idItemViagem + " ]";
    }

}
