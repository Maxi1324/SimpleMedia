package com.MaxiFischer.SocialMediaAPI.Controller;

import com.MaxiFischer.SocialMediaAPI.JwtUtil;
import com.MaxiFischer.SocialMediaAPI.jpa.*;
import com.MaxiFischer.SocialMediaAPI.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileWriter;
import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping(value = "/User")
public class UserController {
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

    @CrossOrigin
    @RequestMapping(value = "/like")
    public Message like(@RequestParam(name = "state") boolean state, @RequestParam(name = "beitrag") String beitrag, Principal principal) {
        Optional<Beitrag> beitrag1 = beiRepo.findByUeberschrift(beitrag);
        if (beitrag1.isEmpty()) {
            return new Message("Fehler weil dieser Beitrag nicht existent ist");
        }
        if (repo.findByNutzername(principal.getName()).isEmpty()) return new Message("dieser Nutzer existiert nicht");
        User user = repo.findByNutzername(principal.getName()).get();

        System.out.println(state);
        jpaQuerries.like(state, beitrag1.get().getId(), user.getId());
        return new Message("erfolgreich gehandelt");
    }


    @CrossOrigin
    @RequestMapping(value = "/postBeitrag", method = RequestMethod.POST)
    public ResponseEntity<?> PostBeitrag(@RequestBody NewBeitragRequest beireq, Principal principal) throws Exception {
        File ordner = new File(beitrageOrdner + "\\" + principal.getName());
        ordner.mkdir();
        File datei = new File(ordner.toString() + "\\" + beireq.getUeberschrift() + ".txt");
        try (FileWriter writer = new FileWriter(datei.toString())) {
            writer.write(beireq.getImg());
        } catch (Exception e) {
            return ResponseEntity.ok(new Message(e.getMessage()));
        }

        User user = repo.findByNutzername(principal.getName()).get();
        jpaQuerries.insertBeitrag(beireq.getUeberschrift(), beireq.getText(), datei.toString(), user.getId().intValue());
        return ResponseEntity.ok(new Message("hat funktiobiert"));
    }


    @CrossOrigin
    @RequestMapping(value = "/hasLiked")
    public Message hasLiked(@RequestParam(name = "beitrag") String beitrag, Principal principal) {
        Optional<Beitrag> beitrag1 = beiRepo.findByUeberschrift(beitrag);
        if (beitrag1.isEmpty()) {
            return new Message("Fehler weil dieser Beitrag nicht existent ist");
        }
        if (repo.findByNutzername(principal.getName()).isEmpty()) return new Message("dieser Nutzer existiert nicht");
        User user = repo.findByNutzername(principal.getName()).get();

        return new Message(""+jpaQuerries.hasLiked(user.getId(), beitrag1.get().getId()));
    }
}
