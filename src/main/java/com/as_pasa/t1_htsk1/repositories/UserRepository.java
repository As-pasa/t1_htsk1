package com.as_pasa.t1_htsk1.repositories;

import com.as_pasa.t1_htsk1.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
