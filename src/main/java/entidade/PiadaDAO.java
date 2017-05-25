package entidade;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;


import java.util.List;

/**
 * Created by rafael on 06/04/17.
 */
public class PiadaDAO {


    public Piada salvar(Piada p){
        Transaction tx = null;
        try{
            Session sessao = HibernateUtil.getSessionFactory().openSession();

            tx = sessao.beginTransaction();

            sessao.save(p);
            sessao.flush();
            tx.commit();



            sessao.close();
        } catch (Exception e) {
            e.printStackTrace();
            if(tx != null) {
                tx.rollback();
            }
        }

        return p;
    }

    public Piada buscar(int id){
        return (Piada) HibernateUtil.getSessionFactory().openSession().get(Piada.class, id);
    }

    public List<Piada> consultar(String palavra, boolean safe){

        Session sessao = HibernateUtil.getSessionFactory().openSession();

        Criteria criteria = sessao.createCriteria(Piada.class);
        criteria.add(Restrictions.like("texto", "%" + palavra + "%"));
        if(safe) {
            criteria.add(Restrictions.eq("adulta", false));
        }

        return criteria.list();
    }
    
    
    
    public void deletar(int id){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sessao.beginTransaction();
        sessao.delete(sessao.get(Piada.class, id));
        tx.commit();
    }
    
    public Piada atualizar(int id, Piada nova){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Piada antiga = (Piada) sessao.get(Piada.class, id);
        
        antiga.setNota(nova.getNota());
        antiga.setAdulta(nova.isAdulta());
        
        if(nova.getNome() != null && !nova.getNome().equals("")){
            antiga.setNome(nova.getNome());
        }
        if(nova.getTexto() != null && !nova.getTexto().equals("")){
            antiga.setTexto(nova.getTexto());
        }
        
        sessao.save(antiga);
        sessao.flush();
        
        return antiga;
    }
    
    








}
