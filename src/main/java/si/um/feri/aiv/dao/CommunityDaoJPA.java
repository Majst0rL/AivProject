package si.um.feri.aiv.dao;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import si.um.feri.aiv.vao.Community;
import si.um.feri.aiv.vao.MSE;

import java.util.List;

@Stateless
public class CommunityDaoJPA implements CommunityDao{
    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Community community) {
        if (community.getId() == null) {
            em.persist(community);
        } else {
            em.merge(community);
        }
    }

    @Override
    public Community find(String communityName) {
        return em.createQuery("SELECT c FROM Community c WHERE c.communityName = :communityName", Community.class)
                .setParameter("communityName", communityName)
                .getResultStream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public void delete(String communityName) {
        Community community = find(communityName);
        if (community != null) {
            em.remove(community);
        }
    }

    @Override
    public void update(Community updatedCommunity) {
        // Z JPA, merge() bo sam poskrbel za update, če entiteta že obstaja
        em.merge(updatedCommunity);
    }

    @Override
    public List<Community> getAll() {
        return em.createQuery("SELECT c FROM Community c", Community.class)
                .getResultList();
    }

    public MSE findMSEByEmail(String mseEmail) {
        return em.createQuery("SELECT m FROM MSE m WHERE m.email = :email", MSE.class)
                .setParameter("email", mseEmail)
                .getResultStream()
                .findFirst()
                .orElse(null);
    }

    public List<MSE> getAllMSEs() {
        return em.createQuery("SELECT m FROM MSE m", MSE.class)
                .getResultList();
    }
}
