package dao;

import java.util.List;
import modelo.Viagem;
import org.hibernate.Session;
import util.HibernateUtil;

public class ViagemDao {

    public List<Viagem> listar(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try{
            String query = "from Viagem order by id";
            List<Viagem> viagens = session.createQuery(query).list();
            session.getTransaction().commit();
            return viagens;
        }catch(Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
            return null;
        }
    }
    
    public Viagem consultar(Integer codigo){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try{
            String query = "from Viagem where id = " + codigo;
            Viagem viagem = (Viagem)session.createQuery(query).uniqueResult();
            session.getTransaction().commit();
            return viagem;
        }catch(Exception e){
            session.getTransaction().rollback();
            return null;
        }
    } 
    
    public Integer gravar(Viagem obj){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try{
            session.save(obj);
            session.getTransaction().commit();
        
        }catch(Exception e){
            session.getTransaction().rollback();
          
        }
        
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try{
            String sql = "SELECT MAX (id) FROM viagem";
            Integer id = (Integer)(session.createSQLQuery(sql).uniqueResult());
            session.getTransaction().commit();
            return id;
        }catch(Exception e){
            session.getTransaction().rollback();
            return null;
        }
    }
    
    public boolean alterar(Viagem obj){
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
    
    public boolean excluir(Viagem obj){
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
