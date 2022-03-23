package talan.Elastic.entities;

public class Document {
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		public Meta getMeta() {
			return meta;
		}
		public void setMeta(Meta meta) {
			this.meta = meta;
		}
		public FileDetails getFile() {
			return file;
		}
		public void setFile(FileDetails file) {
			this.file = file;
		}
		public Path getPath() {
			return path;
		}
		public void setPath(Path path) {
			this.path = path;
		}
		
		private String id; 
		private String content;
		private Meta meta;
		private FileDetails file;
		private Path path;
		
	
}

