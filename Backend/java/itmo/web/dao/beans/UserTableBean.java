package itmo.web.dao.beans;

import itmo.web.dao.entities.UserEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.security.MessageDigest;

@Stateless
public class UserTableBean {
    @PersistenceContext(unitName = "NewExamplePU")
    private EntityManager entityManager;

    public boolean register(UserEntity user) {
        confusePassword(user);
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
        confusePassword(user);
        List l = entityManager
                .createQuery("SELECT u FROM UserEntity u WHERE u.username LIKE :name AND u.password LIKE :pas")
                .setParameter("name", user.getUsername())
                .setParameter("pas", user.getPassword())
                .getResultList();
        return !l.isEmpty();
    }

    private void confusePassword (UserEntity user){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            String purePassword = user.getPassword();
            String salt = user.getUsername();
            String pepper = "#$EV#$407";
            byte[] hash = md.digest(
                            (pepper+purePassword+salt).getBytes(StandardCharsets.UTF_8));
            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : hash) {
                stringBuilder.append(String.format("%02X",b));
            }
            user.setPassword(stringBuilder.toString());
        } catch (java.security.NoSuchAlgorithmException e) {
            //ignored
        }
    }
}
