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

import java.io.File;
import java.io.FileWriter;
import java.util.Optional;

@RestController
@RequestMapping(value = "/Account")
public class AccountController {

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
    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public ResponseEntity<?> createJwt(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authmanager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPasswort())
            );
        } catch (Exception e) {
            throw new Exception("falsches Passwort");
        }
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResponse(jwt));
    }

    @CrossOrigin
    @RequestMapping(value = "/registrieren", method = RequestMethod.POST)
    public ResponseEntity<?> regist(@RequestBody RegistrierenRequest regreq) throws Exception {
        Optional<User> user = repo.findByNutzername(regreq.getNutzername());
        Optional<User> userbyMail = repo.findByMail(regreq.getMail());
        if (!userbyMail.isEmpty()) return ResponseEntity.ok(new Message("mail ist schon existend"));
        if (user.isEmpty()) {
            File ordner = new File(profilPICs + "\\" + regreq.getNutzername());
            ordner.mkdir();
            File datei = new File(ordner.toString() + "\\" + regreq.getNutzername() + ".txt");
            if (regreq.getImg() == null) datei = new File(defaultProfilbild);
            else {
                try (FileWriter writer = new FileWriter(datei.toString())) {
                    writer.write(regreq.getImg());
                } catch (Exception e) {
                    return ResponseEntity.ok(new Message(e.getMessage()));
                }
            }

            jpaQuerries.insertUser(new User(regreq.getNutzername(), regreq.getPasswort(), regreq.getMail(), regreq.getGeschlecht(), 0, null, null, datei.toString()));
            int key = (int) ((float) Math.random() * (float) 2000000);
            jpaQuerries.insertAuthRequest(new AuthentificationRequest(null, key, repo.findByNutzername(regreq.getNutzername()).get().getId()));

            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setTo(regreq.getMail());

            msg.setSubject("Code für aktivierung");
            msg.setText("Sehr geehrter Nutzer,\n\n ihr Code für die Aktivierung lautet: " + key + " \n\nmit freundlichen grußen\nihr SImpleMediaTeam");

            javaMailSender.send(msg);
            return ResponseEntity.ok(new Message("Key erfolgreicht gesendet"));
        } else {
            if (user.get().getAktiv()) {
                return ResponseEntity.ok(new Message("Nutzer schon eksistent"));
            } else {
                return ResponseEntity.ok(new Message("key schon gesendet"));
            }
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/activate", method = RequestMethod.POST)
    public ResponseEntity<?> activate(@RequestBody ActivateRequest act) throws Exception {
        Optional<User> userOpt = repo.findByNutzername(act.getUserName());
        if (!userOpt.isEmpty()) {
            User user = userOpt.get();
            Optional<AuthentificationRequest> auth = authrepo.findByUserID(user.getId());
            if (auth.isEmpty()) throw new Exception("es wurde kein reqest gemacht für diesen Nutzer");
            if ((auth.get().getKey()).equals(act.getKey() + ""))
                throw new Exception("key ist nicht korrekt" + " " + act.getKey());
            jpaQuerries.setActivateUser(user.getId(), 1);
            jpaQuerries.deleteAuthRequest(auth.get().getId().intValue());
        } else {
            return ResponseEntity.ok(new Message("wack"));
        }
        return ResponseEntity.ok(new Message("WOW"));
    }

}
