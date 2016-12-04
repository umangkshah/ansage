public class QuestionPojo{
	
	public String question;
	public int qid;
	public int oid;
	public String descr;
	public String oname;
	public int nbids;
	public String category;
	public int ocoins;
	
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
	
	void setOcoins(int x){
		ocoins = x;
	}

	void setCategory(String x){
		category = x;
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
	
	public int getOcoins(){
		return ocoins;
	}
	
	public String getCategory(){
		return category;
	}
}