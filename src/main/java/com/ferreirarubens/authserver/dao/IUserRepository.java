/**
 * 
 */
package com.ferreirarubens.authserver.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ferreirarubens.authserver.model.User;

/**
 * @auhtor Ferreira Rubens <rubensdefrancaferreira@gmail.com>
 *
 */
public interface IUserRepository extends JpaRepository<User, String> {
	
    User findByLogin(String login);

}
