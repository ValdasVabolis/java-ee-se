package lt.vu.persistence;

import lt.vu.entities.Dealer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class DealersDAO {

    @Inject
    private EntityManager em;

    public List<Dealer> loadAll() {
        return em.createNamedQuery("Dealer.findAll", Dealer.class).getResultList();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(Dealer dealer){
        this.em.persist(dealer);
    }

    public Dealer findOne(Integer id) {
        return em.find(Dealer.class, id);
    }

    public Dealer findByName(String dealerName) {
        return em.createNamedQuery("Dealer.findByName", Dealer.class)
                .setParameter("dealerName", dealerName)
                .getSingleResult();
    }
}
