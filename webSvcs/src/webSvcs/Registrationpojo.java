package webSvcs;


	
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.*;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Temporal;

	@Entity
	@Cacheable
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE,region="profilecache") 
	@Table(name="registration")
	
	public class Registrationpojo{
	    
		@Id
		@GeneratedValue(strategy = GenerationType.SEQUENCE)
        private int profileid;
		@Column(unique=true)
		private String email;
		
	    private String name;
		private int coins;
		private String tagline;
	    private String bioinfo;
	    private String skills;
	   
	  
	   

		public String getSkills() {
			return skills;
		}

		public void setSkills(String skills) {
			this.skills = skills;
		}

		public int getCoins() {
			return coins;
		}

		public void setCoins(int coins) {
			this.coins = coins;
		}
	    
	   public String getTagline() {
			return tagline;
		}

		public void setTagline(String tagline) {
			this.tagline = tagline;
		}

		public String getBioinfo() {
			return bioinfo;
		}

		public void setBioinfo(String bioinfo) {
			this.bioinfo = bioinfo;
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

}
