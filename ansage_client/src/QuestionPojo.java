public class QuestionPojo{
	
	public String question;
	public int qid;
	public int oid;
	public String descr;
	public String oname;
	public int nbids;
	
	void setQuestion(String x){
		question = x;
	}
	
	void setQid(int x){
		qid = x;
	}
	
	void setOid(int x){
		oid = x;
	}

	void setDescr(String x){
		descr = x;
	}
	
	void setOname(String x){
		oname = x;
	}
	
	void setNbids(int x){
		nbids = x;
	}
	

	public String getQuestion(){
		return question;
	}
	
	public int getQid(){
		return qid;
	}
	
	public int getOid(){
		return oid;
	}
	
	public String getDescr(){
		return descr;
	}
	
	public String getOname(){
		return oname;
	}
	
	public int getNbids(){
		return nbids;
	}
	
}