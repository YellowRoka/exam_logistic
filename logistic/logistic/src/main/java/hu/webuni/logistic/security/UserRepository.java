package hu.webuni.logistic.security;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<LogisticUser, String> {

}
