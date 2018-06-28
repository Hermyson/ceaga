/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ceaga.Dao;

import br.com.ceaga.entidadeBase.EntidadeBase;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author hermy
 */
public class DAOGenerico<T extends EntidadeBase> {

    public EntityManager getEM() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("SistemaWebCeagaJsfPU");
        return factory.createEntityManager();
    }

    public T salvar(T t) throws Exception {
        EntityManager em = getEM();
        try {
            em.getTransaction().begin();
            if (t.getId() == null) {
                em.persist(t);
            } else {
                if (!em.contains(t)) {
                    if (em.find(t.getClass(), t.getId()) != null) {
                    } else {
                        throw new Exception("Erro ao atualizar!");
                    }
                }
                t = em.merge(t);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return t;

    }

    public void remover(Class<T> classe, Integer id) {
        EntityManager em = getEM();
        T t = em.find(classe, id);
        try {
            em.getTransaction().begin();
            em.remove(t);
            em.getTransaction().commit();
        } finally {
            em.close();

        }
    }

    public T consutarPorId(Class<T> classe, Integer id) {
        EntityManager em = getEM();
        T t = null;
        try {
            t = em.find(classe, id);
        } finally {
            em.close();
        }
        return t;
    }
    public List<T> consultarTodos(){
        EntityManager em = getEM();
        List<T> t;
        try{
            Query q = em.createNamedQuery("T.ConsultarTodos");
            t = q.getResultList();
        }catch(Exception e){
            t = new ArrayList();
            
        }finally{
            em.close();
        }
        return t;
    }
    public List<T> consultarProximo(){
        EntityManager em = getEM();
        List<T> t;
        try{
            Query q = em.createNamedQuery("T.consultarProximo");
            t = q.getResultList();
        }catch(Exception e){
            t = new ArrayList();
            
        }finally{
            em.close();
        }
        return t;
        
    }

}
