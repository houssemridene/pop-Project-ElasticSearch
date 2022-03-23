package talan.Elastic.entities;






import org.springframework.data.annotation.Id;


import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;


import talan.Elastic.helper.Indices;


@Document(indexName=Indices.USER_INDEX)
@Setting(settingPath="static/es-settings.json")
public class User {
	
	
		@Id
		@Field(type =FieldType.Keyword)
		private int id ;

		@Field(type =FieldType.Keyword)
		private String name;
		
		@Field(type =FieldType.Keyword)
		private String lastName ;
		
		@Field(type =FieldType.Keyword)
		private String adress ;
		
		
		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getAdress() {
			return adress;
		}

		public void setAdress(String adress) {
			this.adress = adress;
		}
		
}
