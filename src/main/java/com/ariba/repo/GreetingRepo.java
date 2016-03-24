package com.ariba.repo;

import com.ariba.model.Greeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Frank Lloyd Teh
 */
@Repository("greetingRepo")
public interface GreetingRepo extends JpaRepository<Greeting, Long> {
}
