package dev.demo.Services.SpecialEnums;

public enum ColorCategory {
	YELLOW(1, "yellow", "Sarı"),
	RED(2, "red", "Kırmızı"),
	BLUE(3, "blue", "Mavi"),
	PURPLE(4, "purple", "Mor"),
	ORANGE(5, "orange", "Turuncu"),
	GREEN(6, "green", "Yeşil");
	
	private String enLabel;
	private String trLabel;
	private int id;
	
	ColorCategory(int id, String enLabel, String trLabel) {
		this.enLabel = enLabel;
		this.trLabel = trLabel;
		this.id = id;
	}
		
	public String toString() {
		return this.trLabel;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getEnLabel() {
		return enLabel;
	}

	public void setEnLabel(String enLabel) {
		this.enLabel = enLabel;
	}

	public static ColorCategory findById(int id) {
		ColorCategory retCategory = null;
		for (ColorCategory c: values()) {
			if (c.getId() == id) {
				retCategory = c;
			}
		}
		return retCategory;
	}

}
