package br.com.listatarefas.model;

import org.json.JSONObject;

public class UsuarioFacebook {

	private Long id;
	// private String firstName;
	// private Integer timezone;
	private String email;
	// private Boolean verified;
	// private String middleName;
	// private String gender;
	// private String lastName;
	// private String link;
	// private String locale;
	private String name;
	// private String updatedTime;

	public UsuarioFacebook(JSONObject jsonUsuario) {

		// id = jsonUsuario.getLong("id");
		// firstName = jsonUsuario.getString("first_name");
		// timezone = jsonUsuario.getInt("timezone");
		// email = jsonUsuario.getString("email");
		// verified = jsonUsuario.getBoolean("verified");
		// middleName = jsonUsuario.getString("middle_name");
		// gender = jsonUsuario.getString("gender");
		// lastName = jsonUsuario.getString("last_name");
		// link = jsonUsuario.getString("link");
		// locale = jsonUsuario.getString("locale");
		// name = jsonUsuario.getString("name");
		// updatedTime = jsonUsuario.getString("updated_time");

	}

	// @Override
	// public String toString() {
	// return "UsuarioFacebook [id=" + id + ", firstName=" + firstName + ",
	// timezone=" + timezone + ", email=" + email
	// + ", verified=" + verified + ", middleName=" + middleName + ", gender=" +
	// gender + ", lastName="
	// + lastName + ", link=" + link + ", locale=" + locale + ", name=" + name +
	// ", updatedTime=" + updatedTime
	// + "]";
	// }

	// public String getLastName() {
	// return lastName;
	// }
	//
	// public String getFirstName() {
	// return firstName;
	// }

	public String getEmail() {
		return email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}