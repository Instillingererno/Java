abstract class Person {
	private float timesLonn;
	private Date ansettelsesAar;

	public Person(float timesLonn) {
		this.timesLonn = timesLonn;
		this.ansettelsesAar = Date.now();
	}

	abstract float beregnKostnad();
}