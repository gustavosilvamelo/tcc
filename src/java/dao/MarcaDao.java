package dao;

import java.util.List;
import modelo.Marca;
import org.hibernate.Session;
import util.HibernateUtil;

public class MarcaDao {

    public List<Marca> listar(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try{
            String query = "from Marca order by id";
            List<Marca> marcas = session.createQuery(query).list();
            session.getTransaction().commit();
            return marcas;
        }catch(Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
            return null;
        }
    }
    
    public Marca consultar(Integer codigo){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try{
            String query = "from Marca where id = " + codigo;
            Marca marca = (Marca)session.createQuery(query).uniqueResult();
            session.getTransaction().commit();
            return marca;
        }catch(Exception e){
            session.getTransaction().rollback();
            return null;
        }
    } 
    
    public boolean gravar(Marca obj){
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
    
    public boolean alterar(Marca obj){
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
    
    public boolean excluir(Marca obj){
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
