/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rocket.session;

import com.rocket.entities.News;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Andreu
 */
@Stateless
public class NewsFacade extends AbstractFacade<News> {

    @PersistenceContext(unitName = "RocketLeagueNewsEE-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NewsFacade() {
        super(News.class);
    }

    public List<News> findLatestNews(int n) {
        return em.createNamedQuery("News.findLatestNews").setMaxResults(n).getResultList();
    }

    public List<News> findPreviousNews(int id, int n) {
        return em.createNamedQuery("News.findPreviousNews").setParameter("id", id).setMaxResults(n).getResultList();
    }

    public boolean slugExists(String slug) {
        return em.createNamedQuery("News.findBySlug").setParameter("slug", slug).getResultList().size() > 0;
    }

    public News findNewBySlug(String slug) {
        return (News) em.createNamedQuery("News.findBySlug").setParameter("slug", slug).getSingleResult();
    }
}
