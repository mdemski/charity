package pl.coderslab.charity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.charity.model.AbstractEntity;
import pl.coderslab.charity.model.User;

public interface UserRepository <T extends AbstractEntity, L extends Number> extends JpaRepository<User, Long> {

    User save (User user);
}
