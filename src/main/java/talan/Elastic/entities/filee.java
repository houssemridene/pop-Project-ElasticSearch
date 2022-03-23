package talan.Elastic.entities;

import java.util.Objects;

public class filee {
	private boolean ok ;

	
	@Override
	public int hashCode() {
		return Objects.hash(filename, ok, url);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		filee other = (filee) obj;
		return Objects.equals(filename, other.filename) && ok == other.ok && Objects.equals(url, other.url);
	}
	public boolean isOk() {
		return ok;
	}
	public void setOk(boolean ok) {
		this.ok = ok;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	private String filename;
	private String url ;
		

}
