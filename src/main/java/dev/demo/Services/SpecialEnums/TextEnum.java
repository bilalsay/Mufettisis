package dev.demo.Services.SpecialEnums;

public enum TextEnum {
	FAAL("Faal"),
	TERK("Terk"),
	TAM("Tam"),
	SINIRLI("Sınırlı"),
	TAMAM("Tamamlandı"),
	DEVAM("Devam");

	private String label;
		
	TextEnum(String label) {
		this.label = label;
	}
		
	public String toString() {
		return this.label;
	}
}
