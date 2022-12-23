package PD2022.PDTicketApi.security;

import PD2022.PDTicketApi.constants.Constants;
import PD2022.PDTicketApi.model.User;
import PD2022.PDTicketApi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TicketUserDetails implements UserDetailsService {

    @Autowired
    UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if (user != null){
            GrantedAuthority grantedAuthority;
            if (user.getAdministrador() == 1){
                grantedAuthority = Constants.ADMIN;
            }else{
                grantedAuthority = Constants.USER;
            }
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                    List.of(grantedAuthority));
        }
        return null;
    }
}
