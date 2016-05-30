package dao;

import java.util.List;
import modelo.Unidade;
import org.hibernate.Session;
import util.HibernateUtil;

public class UnidadeDao {

    public List<Unidade> listar(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try{
            String query = "from Unidade order by id";
            List<Unidade> unidades = session.createQuery(query).list();
            session.getTransaction().commit();
            return unidades;
        }catch(Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
            return null;
        }
    }
    
    public Unidade consultar(Integer codigo){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try{
            String query = "from Unidade where id = " + codigo;
            Unidade unidade = (Unidade)session.createQuery(query).uniqueResult();
            session.getTransaction().commit();
            return unidade;
        }catch(Exception e){
            session.getTransaction().rollback();
            return null;
        }
    } 
    
    public boolean gravar(Unidade obj){
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
    
    public boolean alterar(Unidade obj){
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
    
    public boolean excluir(Unidade obj){
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
