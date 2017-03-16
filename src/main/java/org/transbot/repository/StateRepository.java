package org.transbot.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.transbot.model.State;

/**
 * Created by Erwin on 15-Mar-17.
 */
public interface StateRepository extends CrudRepository<State,String> {

    State findByUserId(String userId);

    @Query("delete from State s where s.userId = :userId")
    void deleteByUserId(String userId);

}
