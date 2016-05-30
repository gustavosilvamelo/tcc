package modelo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/*
    @author 
    Gustavo Silva Melo 
    Ciência da Computação - UNIFOR MG
 */

@Entity
@Table(name = "viagem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Viagem.findAll", query = "SELECT v FROM Viagem v"),
    @NamedQuery(name = "Viagem.findById", query = "SELECT v FROM Viagem v WHERE v.id = :id"),
    @NamedQuery(name = "Viagem.findByDataInicial", query = "SELECT v FROM Viagem v WHERE v.dataInicial = :dataInicial"),
    @NamedQuery(name = "Viagem.findByDataFinal", query = "SELECT v FROM Viagem v WHERE v.dataFinal = :dataFinal"),
    @NamedQuery(name = "Viagem.findByTotalViagem", query = "SELECT v FROM Viagem v WHERE v.totalViagem = :totalViagem"),
    @NamedQuery(name = "Viagem.findByFinanceiro", query = "SELECT v FROM Viagem v WHERE v.financeiro = :financeiro"),
    @NamedQuery(name = "Viagem.findByDataFinanceiro", query = "SELECT v FROM Viagem v WHERE v.dataFinanceiro = :dataFinanceiro")})
public class Viagem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "data_inicial")
    @Temporal(TemporalType.DATE)
    private Date dataInicial;
    @Column(name = "data_final")
    @Temporal(TemporalType.DATE)
    private Date dataFinal;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "total_viagem")
    private Float totalViagem;
    @Column(name = "financeiro")
    private String financeiro;
    @Column(name = "data_financeiro")
    @Temporal(TemporalType.DATE)
    private Date dataFinanceiro;
    @JoinColumn(name = "id_local_origem", referencedColumnName = "id")
    @ManyToOne
    private Local idLocalOrigem;
    @JoinColumn(name = "id_motorista", referencedColumnName = "id")
    @ManyToOne
    private Motorista idMotorista;
    @JoinColumn(name = "id_veiculo", referencedColumnName = "id")
    @ManyToOne
    private Veiculo idVeiculo;
    @OneToMany(mappedBy = "idViagem")
    private Collection<ItemViagem> itemViagemCollection;

    public Viagem() {
    }

    public Viagem(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public Float getTotalViagem() {
        return totalViagem;
    }

    public void setTotalViagem(Float totalViagem) {
        this.totalViagem = totalViagem;
    }

    public String getFinanceiro() {
        return financeiro;
    }

    public void setFinanceiro(String financeiro) {
        this.financeiro = financeiro;
    }

    public Date getDataFinanceiro() {
        return dataFinanceiro;
    }

    public void setDataFinanceiro(Date dataFinanceiro) {
        this.dataFinanceiro = dataFinanceiro;
    }

    public Local getIdLocalOrigem() {
        return idLocalOrigem;
    }

    public void setIdLocalOrigem(Local idLocalOrigem) {
        this.idLocalOrigem = idLocalOrigem;
    }

    public Motorista getIdMotorista() {
        return idMotorista;
    }

    public void setIdMotorista(Motorista idMotorista) {
        this.idMotorista = idMotorista;
    }

    public Veiculo getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(Veiculo idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    @XmlTransient
    public Collection<ItemViagem> getItemViagemCollection() {
        return itemViagemCollection;
    }

    public void setItemViagemCollection(Collection<ItemViagem> itemViagemCollection) {
        this.itemViagemCollection = itemViagemCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Viagem)) {
            return false;
        }
        Viagem other = (Viagem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Viagem[ id=" + id + " ]";
    }

}
