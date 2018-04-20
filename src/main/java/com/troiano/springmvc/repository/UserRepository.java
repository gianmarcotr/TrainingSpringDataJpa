package com.troiano.springmvc.repository;

import com.troiano.springmvc.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findByFirstnameContainingOrLastnameContainingOrCountryContaining(String str, String str2, String str3);

    /*
    @Modifying
    @Query ("DELETE FROM user_skill u WHERE u.idU= ?1")
    void deleteUserSkill(int id);*/

    /*
    @Modifying
    @Query("UPDATE User u SET u = :user WHERE u.id = :idU")
    int updateUser(@Param("user") User user, @Param("idU") int idU);*/
}

