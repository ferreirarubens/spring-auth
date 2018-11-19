package com.ferreirarubens.authserver.security.model.interfaces;

/**
 * @author rubens.ferreira
 *
 */
public interface Authenticated {
	String getLogin();

	String getPassword();

	void setPassword(String password);
}
