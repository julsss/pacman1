package monde;

public enum BonusEnum {
	RAPIDE, SERIALKILLER, IMMORTEL, RIEN;
		
		public static BonusEnum bonusenum (int i) {
			return BonusEnum.values()[i];
		}
}
