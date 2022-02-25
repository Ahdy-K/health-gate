package com.example.medecin_v1.entity;

import com.example.medecin_v1.entity.Document;
import com.example.medecin_v1.entity.MedicalFile;
import com.example.medecin_v1.entity.Specialities;
import lombok.Data;
import org.springframework.data.annotation.Transient;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;


@Entity
@Table(name = "user")
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@Column(name = "username", nullable = false, unique = true)
	@Email(message = "Please provide a valid e-mail")
	@NotEmpty(message = "Please provide an e-mail")
	private String email;
	
	@Column(name = "password")
	@Transient
	private String password;
	
	@Column(name = "first_name")
	@NotEmpty(message = "Please provide your first name")
	private String firstName;
	
	@Column(name = "last_name")
	@NotEmpty(message = "Please provide your last name")
	private String lastName;


	/* can be deleted*/
	@Column(name = "enabled")
	private boolean enabled;
	
	@Column(name = "confirmation_token")
	private String confirmationToken;

	@Column(name = "gender")
	private String gender;
	
	
	@Column(name = "authority")
	private String role;

	/* can be deleted*/
	@Column(name = "lastseen")
	@Transient
	private String lastseen;

	/* Diploma*/
	@OneToOne
	private Document diploma;

	/* justification Files And certifications */

	@OneToMany
	private List<Document> attachements;

	/*Medical File*/
	@OneToOne
	private MedicalFile medicalFile;

	/*Biography */
	@Column(name="Bio")
	private String bio;
	/* Speciality*/
	@Column(name="Specilaitiess")
	private Specialities specialities;

	@Column(name="IsVerified")
	private Boolean IsVerified;

/*	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public Specialities getSpecialities() {
		return specialities;
	}

	public void setSpecialities(Specialities specialities) {
		this.specialities = specialities;
	}

	public com.spring.bioMedical.entity.MedicalFile getMedicalFile() {
		return medicalFile;
	}

	public void setMedicalFile(com.spring.bioMedical.entity.MedicalFile medicalFile) {
		medicalFile = medicalFile;
	}

	public List<Document> getAttachements() {
		return attachements;
	}

	public void setAttachements(List<Document> attachements) {
		this.attachements = attachements;
	}

	public String getLastseen() {
		return lastseen;
	}

	public void setLastseen(String lastseen) {
		this.lastseen = lastseen;
	}
	
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	
	public String getConfirmationToken() {
		return confirmationToken;
	}

	public void setConfirmationToken(String confirmationToken) {
		this.confirmationToken = confirmationToken;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(boolean value) {
		this.enabled = value;
	}

	public File getDiploma() {
		return diploma;
	}

	public void setDiploma(File diploma) {
		this.diploma = diploma;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", enabled=" + enabled + ", confirmationToken=" + confirmationToken
				+ ", gender=" + gender + ", role=" + role + ", lastseen=" + lastseen + "]";
	}

	*/

}