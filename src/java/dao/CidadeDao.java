package dao;

import java.util.List;
import modelo.Cidade;
import org.hibernate.Session;
import util.HibernateUtil;

public class CidadeDao {

    public List<Cidade> listar(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try{
            String query = "from Cidade order by id";
            List<Cidade> cidades = session.createQuery(query).list();
            session.getTransaction().commit();
            return cidades;
        }catch(Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
            return null;
        }
    }
    
    public Cidade consultar(Integer codigo){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try{
            String query = "from Cidade where id = " + codigo;
            Cidade cidade = (Cidade)session.createQuery(query).uniqueResult();
            session.getTransaction().commit();
            return cidade;
        }catch(Exception e){
            session.getTransaction().rollback();
            return null;
        }
    } 
    
    public boolean gravar(Cidade obj){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try{
            session.save(obj);
            session.getTransaction().commit();
            return true;
        }catch(Exception e){
            session.getTransaction().rollback();
            return false;
        }
    }
    
    public boolean alterar(Cidade obj){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try{
            session.update(obj);
            session.getTransaction().commit();
            return true;
        }catch(Exception e){
            session.getTransaction().rollback();
            return false;
        }
    }
    
    public boolean excluir(Cidade obj){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try{
            session.delete(obj);
            session.getTransaction().commit();
            return true;
        }catch(Exception e){
            session.getTransaction().rollback();
            return false;
        }
    }
    
}
