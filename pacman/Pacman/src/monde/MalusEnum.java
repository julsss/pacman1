package monde;

public enum MalusEnum {
	NUIT, LENT, STOP, RIEN;
	
	public static MalusEnum malusenum (int i) {
		return MalusEnum.values()[i];
	}
}
