/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ceaga.teste;

import br.com.ceaga.Dao.DAOGenerico;
import br.com.ceaga.modelo.Cliente;
import br.com.ceaga.modelo.Produto;
import br.com.ceaga.modelo.Venda;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;



/**
 *
 * @author hermy
 */
public class Teste {

    public static void main(String[] args) {
        EntityManagerFactory emf = 
                Persistence.createEntityManagerFactory("SistemaWebCeagaJsfPU");
        EntityManager em = emf.createEntityManager();
        
        Cliente c = new Cliente();
        Produto p = new Produto();
        Venda v = new Venda();

        DAOGenerico<Cliente> dc = new DAOGenerico<>();
        DAOGenerico<Produto> dp = new DAOGenerico<>();
        DAOGenerico<Venda> dv = new DAOGenerico<>();
        
        //c.setNome("Hermyson");
        
        EntityTransaction tx = em .getTransaction();
        tx.begin();
        em.persist(c);
        tx.commit();
        
        
        System.out.println("Entidades salva com sucesso!");
        em.close();
        emf.close();
    }
}
