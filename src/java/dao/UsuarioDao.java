package dao;

import java.util.List;
import modelo.Usuario;
import org.hibernate.Session;
import util.HibernateUtil;

public class UsuarioDao {

    public void inicializarSistema(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.getTransaction().commit();
    }
    
    public List<Usuario> listar(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try{
            String query = "from Usuario order by id";
            List<Usuario> usuarios = session.createQuery(query).list();
            session.getTransaction().commit();
            return usuarios;
        }catch(Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
            return null;
        }
    }
    
    public Usuario consultar(Integer codigo){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try{
            String query = "from Usuario where id = " + codigo;
            Usuario usuario = (Usuario)session.createQuery(query).uniqueResult();
            session.getTransaction().commit();
            return usuario;
        }catch(Exception e){
            session.getTransaction().rollback();
            return null;
        }
    } 
    
    public Usuario consultarLogin(String login){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try{
            String query = "from Usuario where login = '" + login + "'";
            Usuario usuario = (Usuario)session.createQuery(query).uniqueResult();
            session.getTransaction().commit();
            return usuario;
        }catch(Exception e){
            session.getTransaction().rollback();
            return null;
        }
    }
    
    public boolean gravar(Usuario obj){
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
    
    public boolean alterar(Usuario obj){
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
    
    public boolean excluir(Usuario obj){
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
