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
import javax.persistence.EmbeddedId;
import javax.persistence.Temporal;

@Entity

@Table(name="permission")
public class Permissionpojo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int permid;
	private int qid;
	private int reqid;
    private String permissionvalue;
	public int getPermid() {
		return permid;
	}
	public void setPermid(int permid) {
		this.permid = permid;
	}
	public int getQid() {
		return qid;
	}
	public void setQid(int qid) {
		this.qid = qid;
	}
	public int getReqid() {
		return reqid;
	}
	public void setReqid(int reqid) {
		this.reqid = reqid;
	}
	public String getPermissionvalue() {
		return permissionvalue;
	}
	public void setPermissionvalue(String permissionvalue) {
		this.permissionvalue = permissionvalue;
	}
	
	
}
