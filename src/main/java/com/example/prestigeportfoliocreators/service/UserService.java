package com.example.prestigeportfoliocreators.service;

import com.example.prestigeportfoliocreators.models.User;
import com.example.prestigeportfoliocreators.repository.UserRepository;
import com.example.prestigeportfoliocreators.util.Hash;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private static String activeProfile;


    @Value("${spring.profiles.active}")
    public void setActiveProfile(String activeProfile) {
        this.activeProfile = activeProfile;
    }

    public User getDummyUser(HttpServletRequest request){
        String hashedAddress = getUserAddress(request);
        User user = new User();
        user.setAddress(hashedAddress);
        return user;
    }

    public User getUser(HttpServletRequest request){
        String hashedAddress = getUserAddress(request);
        Optional<User> optional = userRepository.findUserByAddress(hashedAddress);
        if (optional.isPresent()){
            return optional.get();
        }else {
            User user  = new User();
            user.setAddress(hashedAddress);
            return user;
        }
    }



    public static String getUserAddress(HttpServletRequest request) {
        if (activeProfile == null) {
            return Hash.SHA256(request.getRemoteAddr()+" "+request.getLocalAddr());
        }
        switch (activeProfile) {
            case "production":
                return Hash.SHA256(request.getHeader("CF-Connecting-IP"));
            case "dev":
            case "default":
            default:
                return Hash.SHA256(request.getRemoteAddr()+" "+request.getLocalAddr());
        }
    }
}
