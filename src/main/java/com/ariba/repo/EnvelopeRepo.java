package com.ariba.repo;

import com.ariba.model.Envelope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Frank Lloyd Teh
 */
@Repository("envelopeRepo")
public interface EnvelopeRepo extends JpaRepository<Envelope, Long> {

    Envelope findByName(String name);

}
