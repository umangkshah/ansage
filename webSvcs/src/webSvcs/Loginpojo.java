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
	@Table(name="login")

				
public class Loginpojo {
		@Id
		private int profileid;
		@Column(unique=true)
		private String email;
		private String password;
		private String logindate;
		private String address;
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		
		public String getLogindate() {
			return logindate;
		}
		public void setLogindate(String logindate) {
			this.logindate = logindate;
		}
		public int getProfileid() {
			return profileid;
		}
		public void setProfileid(int profileid) {
			this.profileid = profileid;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		
		
		
		
		
		
		
		

}
