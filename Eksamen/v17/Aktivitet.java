class Aktivitet {
	private int id;
	private List<Person> deltakere;
	private float estKostnad;
	private float timer;

	public Aktivitet(int id, List<Person> deltakere) {
		this.id = id;
		this.deltakere = deltakere;
		this.estKostnad = 0;
		this.timer = 0;
	}

	public regTimer(float timer) {
		this.timer += timer;
	}

	
}