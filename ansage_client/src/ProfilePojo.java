public class ProfilePojo{
	
	public String name;
	public int proid;
	public String bio;
	public String skills;
	public String tagline;
	public String coins;
	
	void setName(String x){
		name = x;
	}
	
	void setProid(int x){
		proid = x;
	}

	void setBio(String x){
		bio = x;
	}
	
	void setSkills(String x){
		skills = x;
	}
	
	void setTagline(String x){
		tagline = x;
	}
	
	void setCoins(String x){
		coins = x;
	}
	
	public String getName(){
		return name;
	}
	
	public int getProid(){
		return proid;
	}
	
	public String getBio(){
		return bio;
	}
	
	public String getSkills(){
		return skills;
	}
	
	public String getTagline(){
		return tagline;
	}
	
	public String getCoins(){
		return coins;
	}
}