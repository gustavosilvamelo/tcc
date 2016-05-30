package dao;

import java.util.List;
import modelo.Produto;
import org.hibernate.Session;
import util.HibernateUtil;

public class ProdutoDao {

    public List<Produto> listar(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try{
            String query = "from Produto order by id";
            List<Produto> produtos = session.createQuery(query).list();
            session.getTransaction().commit();
            return produtos;
        }catch(Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
            return null;
        }
    }
    
    public Produto consultar(Integer codigo){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try{
            String query = "from Produto where id = " + codigo;
            Produto produto = (Produto)session.createQuery(query).uniqueResult();
            session.getTransaction().commit();
            return produto;
        }catch(Exception e){
            session.getTransaction().rollback();
            return null;
        }
    } 
    
    public boolean gravar(Produto obj){
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
    
    public boolean alterar(Produto obj){
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
    
    public boolean excluir(Produto obj){
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
