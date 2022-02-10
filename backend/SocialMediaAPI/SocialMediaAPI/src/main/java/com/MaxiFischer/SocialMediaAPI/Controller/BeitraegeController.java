package com.MaxiFischer.SocialMediaAPI.Controller;

import com.MaxiFischer.SocialMediaAPI.JwtUtil;
import com.MaxiFischer.SocialMediaAPI.jpa.*;
import com.MaxiFischer.SocialMediaAPI.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.security.Principal;
import java.sql.Array;
import java.sql.Date;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping(value = "/Beitraege")
public class BeitraegeController {

    @Autowired
    private AuthenticationManager authmanager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    JpaQuerries jpaQuerries;

    @Autowired
    private UserRepository repo;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private AuthentificationRequestRepository authrepo;

    @Value("${beitrageOrdner}")
    private String beitrageOrdner;

    @Value("${ProfilPICs}")
    private String profilPICs;

    @Value("${defaultProfilbild}")
    private String defaultProfilbild;

    @Autowired
    private BeitragRepo beiRepo;

    @Autowired
    private LikedRepo likedRepo;

    private int maxPro = 10;

    @CrossOrigin
    @RequestMapping(value = "/User")
    public GetUser User(@RequestParam(name = "User") String user, @RequestParam(name = "Page") Integer page) {
        if (repo.findByNutzername(user).isEmpty()) return null;
        ArrayList<GetBeitrag> beitraege = jpaQuerries.getUsersBeitraege(user);

        User userOB = repo.findByNutzername(user).get();
        GetUser getUser = new GetUser(new Beitraege(beitraege), new GetUserOB(userOB));
        return getUser;
    }

    @CrossOrigin
    @RequestMapping(value = "/getUserProfilbild")
    public GetUserOB profilbild(@RequestParam(name = "User") String user) {
        Optional<User> user1 = repo.findByNutzername(user);
        if (user1.isEmpty()) return null;

        return new GetUserOB(user1.get());
    }

    @CrossOrigin
    @RequestMapping(value = "/Trends")
    public Beitraege trends() {
        return jpaQuerries.trends();
    }

    @CrossOrigin
    @RequestMapping(value = "/Entdecke")
    public Beitraege Beitraege(Principal principal) throws Exception {
       int uid = -1;
        if (principal != null) {
            Optional<User> user = repo.findByNutzername(principal.getName());
            if (user.isEmpty()) {
                throw new Exception("Fehler im System");

            }
            uid = user.get().getId();
        }
        List<Object> beitragroh = jpaQuerries.entdecke(uid);
        List<Beitrag> beitrag = toBeitragArr(beitragroh);
        beitrag = beitrag.subList(0, (maxPro >= beitrag.size()) ? beitrag.size() : maxPro);
        ArrayList<Beitrag> b = new ArrayList<>();
        beitrag.forEach((Beitrag bei) -> {
            b.add(bei);
        });
        //if(uid != -1)addShown(b, uid);

         return new Beitraege(b, repo);
    }

    public static void useImageUser(GetUserOB user) {
        String img = null;
        if (user.getProfilBild() == null) {
            new Exception("www").printStackTrace();
            return;
        }

        try (BufferedReader reader1 = new BufferedReader(new FileReader(user.getProfilBild()))) {
            img = (reader1.readLine());
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
        user.setProfilBild(img);
    }

    public static void useBeitragImage(GetBeitrag beitrag) {
        String img = null;
        if (beitrag.getImg() == null) {
            new Exception("www").printStackTrace();
            return;
        }

        try (BufferedReader reader1 = new BufferedReader(new FileReader(beitrag.getImg()))) {
            img = (reader1.readLine());
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
        beitrag.setImg(img);
    }

    private void addShown(ArrayList<Beitrag> beitraege, int user) {
        beitraege.forEach((Beitrag beitrag) -> {
            jpaQuerries.addShown(user, beitrag.getId());
        });
    }

    private ArrayList<Beitrag> toBeitragArr(List<Object> ob) {
        ArrayList<Beitrag> beitrag = new ArrayList<>();
        ob.forEach((Object o) -> {
            beitrag.add((Beitrag) o);
        });
        return beitrag;
    }
}




























