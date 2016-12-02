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
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@Table(name="answer")
public class Answerpojo {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int ansid;
	private int reqid;
	private int qid;
	private String answer;
	
	public int getAnsid() {
		return ansid;
	}
	public void setAnsid(int ansid) {
		this.ansid = ansid;
	}
	public int getReqid() {
		return reqid;
	}
	public void setReqid(int reqid) {
		this.reqid = reqid;
	}
	public int getQid() {
		return qid;
	}
	public void setQid(int qid) {
		this.qid = qid;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	

}
