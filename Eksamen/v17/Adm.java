class Adm extends Person {
	private float KONSTANT = 1.4;

	public Adm(float timesLonn) {
		super(timesLonn);
	}

	public float beregnKostnad() {
		return super.timesLonn * KONSTANT;
	}
}