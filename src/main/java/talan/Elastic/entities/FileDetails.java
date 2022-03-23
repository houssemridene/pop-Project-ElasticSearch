package talan.Elastic.entities;

public class FileDetails {
	
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public String getContent_type() {
		return content_type;
	}
	public void setContent_type(String content_type) {
		this.content_type = content_type;
	}
	public String getIndexing_date() {
		return indexing_date;
	}
	public void setIndexing_date(String indexing_date) {
		this.indexing_date = indexing_date;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	private String extension;
	private String content_type ;
	private String indexing_date;
	private String filename;

}
