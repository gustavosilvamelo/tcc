package dao;

import java.util.List;
import modelo.ItemViagem;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateUtil;

public class ItemViagemDao {

    public List<ItemViagem> listar(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try{
            String query = "from ItemViagem order by id";
            List<ItemViagem> itensViagem = session.createQuery(query).list();
            session.getTransaction().commit();
            return itensViagem;
        }catch(Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
            return null;
        }
    }
    
    public ItemViagem consultar(Integer codigo){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try{
            String query = "from ItemViagem where id_item_viagem = " + codigo;
            ItemViagem itemViagem = (ItemViagem)session.createQuery(query).uniqueResult();
            session.getTransaction().commit();
            return itemViagem;
        }catch(Exception e){
            session.getTransaction().rollback();
            return null;
        }
    } 

    public List<ItemViagem> consultarItens(Integer id_viagem){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try{
            String query = "from ItemViagem where id_viagem = " + id_viagem;
            List<ItemViagem> listaItens = session.createQuery(query).list();
            session.getTransaction().commit();
            return listaItens;
        }catch(Exception e){
            session.getTransaction().rollback();
            return null;
        }
    } 

    
    public boolean gravar(ItemViagem obj){
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
    
    public boolean alterar(ItemViagem obj){
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
    
    public boolean excluir(int id_viagem){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try{
            Query query = session.createQuery("DELETE ItemViagem WHERE id_viagem = :idViagem");
            query.setParameter("idViagem", id_viagem);
            int result = query.executeUpdate();
            session.getTransaction().commit();
            return true;
        }catch(Exception e){
            session.getTransaction().rollback();
            return false;
        }
    }
    
}
