package modelo;

import java.io.Serializable;
import java.util.Collection;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/*
    @author 
    Gustavo Silva Melo 
    Ciência da Computação - UNIFOR MG
 */

@Entity
@Table(name = "veiculo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Veiculo.findAll", query = "SELECT v FROM Veiculo v"),
    @NamedQuery(name = "Veiculo.findByPlaca", query = "SELECT v FROM Veiculo v WHERE v.placa = :placa"),
    @NamedQuery(name = "Veiculo.findByObservacao", query = "SELECT v FROM Veiculo v WHERE v.observacao = :observacao"),
    @NamedQuery(name = "Veiculo.findByHodometro", query = "SELECT v FROM Veiculo v WHERE v.hodometro = :hodometro"),
    @NamedQuery(name = "Veiculo.findByTara", query = "SELECT v FROM Veiculo v WHERE v.tara = :tara"),
    @NamedQuery(name = "Veiculo.findByLotacao", query = "SELECT v FROM Veiculo v WHERE v.lotacao = :lotacao"),
    @NamedQuery(name = "Veiculo.findByRntrc", query = "SELECT v FROM Veiculo v WHERE v.rntrc = :rntrc"),
    @NamedQuery(name = "Veiculo.findByChassi", query = "SELECT v FROM Veiculo v WHERE v.chassi = :chassi"),
    @NamedQuery(name = "Veiculo.findByRenavam", query = "SELECT v FROM Veiculo v WHERE v.renavam = :renavam"),
    @NamedQuery(name = "Veiculo.findById", query = "SELECT v FROM Veiculo v WHERE v.id = :id")})
public class Veiculo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "placa")
    private String placa;
    @Column(name = "observacao")
    private String observacao;
    @Column(name = "hodometro")
    private Integer hodometro;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "tara")
    private Float tara;
    @Column(name = "lotacao")
    private Float lotacao;
    @Column(name = "rntrc")
    private String rntrc;
    @Column(name = "chassi")
    private String chassi;
    @Column(name = "renavam")
    private String renavam;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "id_marca", referencedColumnName = "id")
    @ManyToOne
    private Marca idMarca;
    @JoinColumn(name = "id_modelo", referencedColumnName = "id")
    @ManyToOne
    private Modelo idModelo;
    @OneToMany(mappedBy = "idVeiculo")
    private Collection<Viagem> viagemCollection;

    public Veiculo() {
    }

    public Veiculo(Integer id) {
        this.id = id;
    }

    public Veiculo(Integer id, String placa) {
        this.id = id;
        this.placa = placa;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Integer getHodometro() {
        return hodometro;
    }

    public void setHodometro(Integer hodometro) {
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Marca getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Marca idMarca) {
        this.idMarca = idMarca;
    }

    public Modelo getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(Modelo idModelo) {
        this.idModelo = idModelo;
    }

    @XmlTransient
    public Collection<Viagem> getViagemCollection() {
        return viagemCollection;
    }

    public void setViagemCollection(Collection<Viagem> viagemCollection) {
        this.viagemCollection = viagemCollection;
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
        if (!(object instanceof Veiculo)) {
            return false;
        }
        Veiculo other = (Veiculo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Veiculo[ id=" + id + " ]";
    }

}
