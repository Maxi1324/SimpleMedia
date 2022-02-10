package com.MaxiFischer.SocialMediaAPI.jpa;

import com.MaxiFischer.SocialMediaAPI.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JpaQuerries {

    @Autowired
    EntityManager entityManager;

    @Autowired
    UserRepository userRepository;

    @Transactional
    public void insertUser(User user) {
        // Query hallo = entityManager.createNativeQuery("INSERT INTO u_user (u_nutzername, u_passwort, u_mail, u_g_geschlecht, u_r_rolle, u_erstanmeldung, u_letztanmeldung) VALUES ('halo' , 1234,'testmail',0,0,null,null)");
        entityManager.createNativeQuery("INSERT INTO u_user (u_nutzername, u_passwort, u_mail, u_g_geschlecht, u_r_rolle, u_erstanmeldung, u_letztanmeldung,u_profilBild) VALUES (?,?,?,?,?,?,?,?)")
                .setParameter(1, user.getNutzername())
                .setParameter(2, user.getPasswort())
                .setParameter(3, user.getMail())
                .setParameter(4, user.getGeschlecht())
                .setParameter(5, user.getRolle())
                .setParameter(6, user.getErstanmeldung())
                .setParameter(7, user.getLetztanmeldung())
                .setParameter(8, user.getProfilBild())
                .executeUpdate();
    }

    @Transactional
    public void setActivateUser(Integer userID, int val) {
        entityManager.createNativeQuery(" Update u_user Set u_aktiv = ?  where u_id = ?")
                .setParameter(1, val)
                .setParameter(2, userID)
                .executeUpdate();
    }


    @Transactional
    public void insertAuthRequest(AuthentificationRequest auth) {
        entityManager.createNativeQuery("INSERT INTO a_authentificationRequest (a_key,a_u_id) VALUES(?,?)")
                .setParameter(1, auth.getKey())
                .setParameter(2, auth.getUserID())
                .executeUpdate();
    }

    @Transactional
    public void deleteAuthRequest(int id) {
        entityManager.createNativeQuery("Delete from a_authentificationRequest where a_id = ?")
                .setParameter(1, id)
                .executeUpdate();
    }

    @Transactional
    public void insertBeitrag(String ueberschrift, String text, String img, Integer userID) {
        entityManager.createNativeQuery("insert into b_beitrag(b_ueberschrift, b_img, b_text, b_erstellungsdatum,b_u_id, b_likes)Values(?,?,?,CURDATE(),?,0)")
                .setParameter(1, ueberschrift)
                .setParameter(2, img)
                .setParameter(3, text)
                .setParameter(4, userID)
                .executeUpdate();
    }

    @Transactional
    public int getBeitragCount() {
        return ((Long) entityManager.createQuery("select count(b_id) from Beitrag").getResultList().get(0)).intValue();
    }


    @Transactional
    public ArrayList<GetBeitrag> getUsersBeitraege(String username) {
        Query query = entityManager.createNativeQuery("select * from b_beitrag inner join u_user on b_beitrag.b_u_id = u_user.u_id where u_nutzername like ?"
                , Beitrag.class);
        User user = userRepository.findByNutzername(username).get();
        query.setParameter(1, username);
        ArrayList<GetBeitrag> beitraege = new ArrayList<>();
        for (Object beitrag1 : query.getResultList()) {
            Beitrag beitrag = (Beitrag) beitrag1;
            String img = null;
            try (BufferedReader reader1 = new BufferedReader(new FileReader(beitrag.getImg()))) {
                img = (reader1.readLine());
            } catch (FileNotFoundException e) {

            } catch (IOException e) {

            }
            beitraege.add(new GetBeitrag(beitrag.getUeberschrift(), beitrag.getText(), img, beitrag.getErstellungsdatum(), beitrag.getLikes(), new GetUserOB(user)));
        }
        return beitraege;
    }

    @Transactional
    public void like(boolean state, Integer beitrag, Integer userId) {
        boolean hasLiked = hasLiked(userId, beitrag);
        if (state) {
            if (!hasLiked) {
                entityManager.createNativeQuery("insert into l_liked(l_u_id,l_b_id) values(?,?)")
                        .setParameter(1, userId)
                        .setParameter(2, beitrag)
                        .executeUpdate();
            }
        } else {
            if (hasLiked) {
                System.out.println("hier");
                entityManager.createNativeQuery("Delete from l_liked where (l_u_id = ?) and (l_b_id = ?)")
                        .setParameter(1, userId)
                        .setParameter(2, beitrag)
                        .executeUpdate();
            }
        }
        entityManager.createNativeQuery("Update b_beitrag Set b_likes =  ( select count(*) from l_liked where (l_b_id = ?)) where (b_id = ?)")
                .setParameter(1, beitrag)
                .setParameter(2, beitrag)
                .executeUpdate();
    }


    @Transactional
    public boolean hasLiked(Integer user, Integer beitragId) {
        Query query = entityManager.createNativeQuery("select * from l_liked where (l_u_id = ?) and (l_b_id = ?)")
                .setParameter(1, user)
                .setParameter(2, beitragId);
        if (query.getResultList().size() == 1) return true;
        else return false;
    }

    @Transactional
    public Beitraege trends() {
        Query query = entityManager.createNativeQuery("select * from b_beitrag Order by b_likes desc", Beitrag.class);
        ArrayList<GetBeitrag> beitraege = new ArrayList<>();
        for (Object beitrag : query.getResultList()) {
            Beitrag bei = (Beitrag) beitrag;
            User user = userRepository.findById(bei.getUserId()).get();
            GetUserOB getuser = new GetUserOB(user);

            String img = null;
            try (BufferedReader reader1 = new BufferedReader(new FileReader(bei.getImg()))) {
                img = (reader1.readLine());
            } catch (FileNotFoundException e) {

            } catch (IOException e) {

            }

            beitraege.add(new GetBeitrag(bei.getUeberschrift(), bei.getText(), img, bei.getErstellungsdatum(), bei.getLikes(), getuser));
        }
        return new Beitraege(beitraege);
    }

    @Transactional
    public void addShown(int user, int beitragId) {

        System.out.println(user + ";" + beitragId);
        ;
        Query query = entityManager.createNativeQuery("insert into shownBeitraege(s_u_user,s_b_id) values(?,?)")
                .setParameter(1, user)
                .setParameter(2, beitragId);
        query.executeUpdate();

    }

    @Transactional
    public List<Object> entdecke(int u_user) {
        List<Object> result = null;
        if (u_user != -1) {
            result = entityManager.createNativeQuery("select * from b_beitrag as b2 Where not ((select count(*) from shownbeitraege where shownbeitraege.s_u_user = ? and shownbeitraege.s_b_id =  b2.b_id))> 0 Order by rand()", Beitrag.class)
                    .setParameter(1, u_user)
                    .getResultList();
        } else {
            result = entityManager.createNativeQuery("select * from b_beitrag as b2 Order by rand()", Beitrag.class)
                    .getResultList();
        }
        return result;
    }

}
