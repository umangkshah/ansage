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
import javax.persistence.Column;
import javax.persistence.Temporal;

	@Entity
	@Table(name="login")

				
public class Loginpojo {
		@Id
		@GeneratedValue(strategy = GenerationType.TABLE)
        private int profileid;
		@Column(unique=true)
		private String email;
		private String password;
		private Date logindate;
		
		
		public Date getLogindate() {
			return logindate;
		}
		public void setLogindate(Date logindate) {
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
