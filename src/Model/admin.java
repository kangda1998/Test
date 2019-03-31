package Model;

public class admin {
	private String adId;
	private String adPassWord;

	
	public void setAdPassWord(String adPassWord) {
		this.adPassWord = adPassWord;
	}

	public admin(String adId ,String adPassWord){
		this.adId = adId ;
		this.adPassWord = adPassWord;
	}
	public void setAdId(String adId) {
		this.adId = adId;
	}
	public String getAdId() {
		return adId;
	}
	public String getAdPassWord() {
		return adPassWord;
	}
}
