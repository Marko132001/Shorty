package hr.assecosee.shorty;

public class Shorty {
	
	private String url;
	private int redirectType = 302;
	
	public Shorty() {
		
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getRedirectType() {
		return redirectType;
	}

	public void setRedirectType(int redirectType) {
		this.redirectType = redirectType;
	}

}
