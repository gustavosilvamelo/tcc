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
@Table(name = "motorista")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Motorista.findAll", query = "SELECT m FROM Motorista m"),
    @NamedQuery(name = "Motorista.findById", query = "SELECT m FROM Motorista m WHERE m.id = :id"),
    @NamedQuery(name = "Motorista.findByNome", query = "SELECT m FROM Motorista m WHERE m.nome = :nome"),
    @NamedQuery(name = "Motorista.findByCpf", query = "SELECT m FROM Motorista m WHERE m.cpf = :cpf"),
    @NamedQuery(name = "Motorista.findByDataNascimento", query = "SELECT m FROM Motorista m WHERE m.dataNascimento = :dataNascimento"),
    @NamedQuery(name = "Motorista.findByEndereco", query = "SELECT m FROM Motorista m WHERE m.endereco = :endereco"),
    @NamedQuery(name = "Motorista.findByNumero", query = "SELECT m FROM Motorista m WHERE m.numero = :numero"),
    @NamedQuery(name = "Motorista.findByTelefone", query = "SELECT m FROM Motorista m WHERE m.telefone = :telefone"),
    @NamedQuery(name = "Motorista.findByCelular", query = "SELECT m FROM Motorista m WHERE m.celular = :celular"),
    @NamedQuery(name = "Motorista.findByCnh", query = "SELECT m FROM Motorista m WHERE m.cnh = :cnh"),
    @NamedQuery(name = "Motorista.findByCategoria", query = "SELECT m FROM Motorista m WHERE m.categoria = :categoria"),
    @NamedQuery(name = "Motorista.findByObservacao", query = "SELECT m FROM Motorista m WHERE m.observacao = :observacao"),
    @NamedQuery(name = "Motorista.findByBairro", query = "SELECT m FROM Motorista m WHERE m.bairro = :bairro"),
    @NamedQuery(name = "Motorista.findByEmail", query = "SELECT m FROM Motorista m WHERE m.email = :email")})
public class Motorista implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "cpf")
    private String cpf;
    @Column(name = "data_nascimento")
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;
    @Column(name = "endereco")
    private String endereco;
    @Column(name = "numero")
    private String numero;
    @Column(name = "telefone")
    private String telefone;
    @Column(name = "celular")
    private String celular;
    @Column(name = "cnh")
    private String cnh;
    @Column(name = "categoria")
    private String categoria;
    @Column(name = "observacao")
    private String observacao;
    @Column(name = "bairro")
    private String bairro;
    @Column(name = "email")
    private String email;
    @OneToMany(mappedBy = "idMotorista")
    private Collection<Viagem> viagemCollection;
    @JoinColumn(name = "id_cidade", referencedColumnName = "id")
    @ManyToOne
    private Cidade idCidade;

    public Motorista() {
    }

    public Motorista(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
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

    @XmlTransient
    public Collection<Viagem> getViagemCollection() {
        return viagemCollection;
    }

    public void setViagemCollection(Collection<Viagem> viagemCollection) {
        this.viagemCollection = viagemCollection;
    }

    public Cidade getIdCidade() {
        return idCidade;
    }

    public void setIdCidade(Cidade idCidade) {
        this.idCidade = idCidade;
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
        if (!(object instanceof Motorista)) {
            return false;
        }
        Motorista other = (Motorista) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Motorista[ id=" + id + " ]";
    }

}
