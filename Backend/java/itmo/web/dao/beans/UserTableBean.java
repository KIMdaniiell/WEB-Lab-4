package itmo.web.dao.beans;

import itmo.web.dao.entities.UserEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class UserTableBean {
    @PersistenceContext(unitName = "NewExamplePU")
    private EntityManager entityManager;

    public boolean register(UserEntity user) {
        if (!lookUp(user)){
            try {
                entityManager.persist(user);
                entityManager.flush();
                return true;
            } catch (Exception e){
                System.out.println(e.getMessage());
                return false;
            }
        }
        return false;
    }
    public boolean lookUp(UserEntity user){
        List l = entityManager
                .createQuery("SELECT u FROM UserEntity u WHERE u.username LIKE :name")
                .setParameter("name", user.getUsername())
                .getResultList();
        return !l.isEmpty();
    }
    public boolean login(UserEntity user){
        List l = entityManager
                .createQuery("SELECT u FROM UserEntity u WHERE u.username LIKE :name AND u.password LIKE :pas")
                .setParameter("name", user.getUsername())
                .setParameter("pas", user.getPassword())
                .getResultList();
        return !l.isEmpty();
    }

}
