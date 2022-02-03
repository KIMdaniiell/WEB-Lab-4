package itmo.web.dao.beans;

import itmo.web.dao.entities.DotEntity;
import itmo.web.dao.entities.UserEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class DotTableBean {

    @PersistenceContext(unitName = "NewExamplePU")
    private EntityManager entityManager;

    public void loadDot(DotEntity dot) {
        entityManager.persist(dot);
        entityManager.flush();
    }
    public List getDots(UserEntity user){
        List l = entityManager.createQuery("SELECT d FROM DotEntity d WHERE d.user LIKE :name ORDER BY d.id DESC")
                .setParameter("name", user.getUsername())
                .getResultList();
        return l;
    }
    public void resetDots(UserEntity user) {
        List l =  entityManager.createQuery("SELECT d FROM DotEntity d WHERE d.user LIKE :name")
                .setParameter("name", user.getUsername())
                .getResultList();
        for (Object o : l) {
            entityManager.remove(o);
        }
    }
}
