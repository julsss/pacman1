package monde;

public enum SensEnum {
	GAUCHE,DROITE,HAUT,BAS;
	
	public static SensEnum sensenum (int i) {
		return SensEnum.values()[i];
	}
}
