package webSvcs;


	
	import javax.persistence.Entity;
	import java.util.*;
	import javax.persistence.Id;
	import javax.persistence.Table;
	import javax.persistence.TemporalType;
	import javax.persistence.Column;
	import javax.persistence.Temporal;

	@Entity
	@Table(name="REGISTRATION")
	public class hibernatepojo{
	    
		@Id
		
	     private int profileid;
	    
		private String password;
	    
		private String country;
		
		@Column(unique=true)
		private String email;
		
		private String name;
	   
	    @Temporal(TemporalType.DATE)
	    private Date birthdate;
	    
	    
		public Date getBirthdate() {
			return birthdate;
		}

		public void setBirthdate(Date birthdate) {
			this.birthdate = birthdate;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public int getProfileid() {
			return profileid;
		}

		public void setProfileid(int profileid) {
			this.profileid = profileid;
		}

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
		
	
	
	

}
