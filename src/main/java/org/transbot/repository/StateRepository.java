package org.transbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.transbot.model.State;

/**
 * Created by Erwin on 15-Mar-17.
 */
public interface StateRepository extends JpaRepository<State,String> {

    State findByUserId(String userId);
}
