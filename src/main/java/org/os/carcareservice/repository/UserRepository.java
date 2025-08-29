package org.os.carcareservice.repository;


import org.os.carcareservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> , JpaSpecificationExecutor<User> {
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String newEmail);
   Optional<User> findById(Long id);


    default List<User> findByRole(Role role){
        return findAll().stream().filter(user -> user.getRole() == role).toList();
    }
}
