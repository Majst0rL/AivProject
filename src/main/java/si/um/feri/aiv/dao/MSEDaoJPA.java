package si.um.feri.aiv.dao;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import si.um.feri.aiv.vao.MSE;
import java.util.List;

@Stateless
public class MSEDaoJPA implements MSEDAO{
    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(MSE mse) {
        if (mse.getId() == null) {
            em.persist(mse);
        } else {
            em.merge(mse);
        }
    }

    @Override
    public MSE find(String email) {
        List<MSE> result = em.createQuery("SELECT m FROM MSE m WHERE m.email = :email", MSE.class)
                .setParameter("email", email)
                .getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public void delete(String email) {
        MSE mse = find(email);
        if (mse != null) {
            em.remove(mse);
        }
    }

    @Override
    public void update(MSE updatedMSE) {
        MSE existingMSE = find(updatedMSE.getEmail());
        if (existingMSE != null) {
            existingMSE.setXcoordinates(updatedMSE.getXcoordinates());
            existingMSE.setYcoordinates(updatedMSE.getYcoordinates());
            existingMSE.setName(updatedMSE.getName());
            existingMSE.setSurname(updatedMSE.getSurname());
            existingMSE.setCapacity(updatedMSE.getCapacity());
            em.merge(existingMSE);
        }
    }

    @Override
    public List<MSE> getAll() {
        return em.createQuery("SELECT m FROM MSE m", MSE.class).getResultList();
    }
}
