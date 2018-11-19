/**
 * 
 */
package com.ferreirarubens.authserver.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ferreirarubens.authserver.model.User;

/**
 * @author rubens.ferreira
 *
 */
public interface IUserRepository extends JpaRepository<User, String> {
	
    User findByLogin(String login);

}
