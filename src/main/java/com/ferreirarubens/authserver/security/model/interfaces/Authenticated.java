package com.ferreirarubens.authserver.security.model.interfaces;

/**
 * @auhtor Ferreira Rubens <rubensdefrancaferreira@gmail.com>
 *
 */
public interface Authenticated {
	String getLogin();

	String getPassword();

	void setPassword(String password);
}
