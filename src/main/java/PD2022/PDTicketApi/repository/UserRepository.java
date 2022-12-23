package PD2022.PDTicketApi.repository;

import PD2022.PDTicketApi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserByUsername(String username);

    boolean existsUserByUsernameOrNome(String username, String name);

    User deleteUserById(Integer id);
}
