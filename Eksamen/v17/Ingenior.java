class Ingenior extends Person {

	public Ingenior(float timesLonn) {
		super(timesLonn);
	}

	public float beregnKostnad() {
		int ansiennitet = Date.now().getYear() - super.ansettelsesAar.getYear();
		
		if(ansiennitet < 3) {
			return super.timesLonn * 1.6;
		} else if(ansiennitet < 6) {
			return super.timesLonn * 1.8;
		} else {
			return super.timesLonn * 2.1;
		}
	}
}