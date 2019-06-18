/**
 * 
 */
package com.ferreirarubens.authserver.data.serializer;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.ferreirarubens.authserver.model.ProfilePermission;

/**
 * @auhtor Ferreira Rubens <rubensdefrancaferreira@gmail.com>
 *
 */
public class ProfilePermissionListSerializer extends StdSerializer<List<ProfilePermission>> {

	private static final long serialVersionUID = 1L;

	public ProfilePermissionListSerializer() {
		this(null);
	}

	protected ProfilePermissionListSerializer(Class<List<ProfilePermission>> t) {
		super(t);
	}

	@Override
	public void serialize(List<ProfilePermission> profilePermissions, JsonGenerator generator,
			SerializerProvider provider) throws IOException {
		if (Objects.nonNull(profilePermissions)) {
			profilePermissions.forEach(this::removeFiles);
		}
		generator.writeObject(profilePermissions);
	}

	private void removeFiles(ProfilePermission profilePermission) {
		profilePermission.setProfile(null);
	}

}
