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
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE,region="ansagecache")
@Table(name="cart")

public class Transpojo {
	@Id
	private int bidid;
	private int qid;
	private int reqid;
	private int oid;
	private int qty;
	public int getBidid() {
		return bidid;
	}
	public void setBidid(int bidid) {
		this.bidid = bidid;
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
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	
	
	
	

}
