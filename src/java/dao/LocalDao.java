package dao;

import java.util.List;
import modelo.Local;
import org.hibernate.Session;
import util.HibernateUtil;

public class LocalDao {

    public List<Local> listar(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try{
            String query = "from Local order by id";
            List<Local> locals = session.createQuery(query).list();
            session.getTransaction().commit();
            return locals;
        }catch(Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
            return null;
        }
    }
    
    public Local consultar(Integer codigo){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try{
            String query = "from Local where id = " + codigo;
            Local local = (Local)session.createQuery(query).uniqueResult();
            session.getTransaction().commit();
            return local;
        }catch(Exception e){
            session.getTransaction().rollback();
            return null;
        }
    } 
    
    public boolean gravar(Local obj){
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
    
    public boolean alterar(Local obj){
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
    
    public boolean excluir(Local obj){
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
