package monde;

public class PastillesBonus {
	
	private BonusEnum b;
	
    public PastillesBonus(BonusEnum bb) {
        this.b=bb;
    }

	public BonusEnum getB() {
		return b;
	}

	public void setB(BonusEnum b) {
		this.b = b;
	}
}
