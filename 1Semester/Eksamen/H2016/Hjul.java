class Hjul {
	private final String merke;
	private final String dimensjon;
	private final String dekkType;
	
	public Hjul(String merke, String dimensjon, String dekkType) {
		this.merke = merke;
		this.dimensjon = dimensjon;
		this.dekkType = dekkType;
	}
	public String getMerke() {
		return merke;
	}
	public String getDimensjon() {
		return dimensjon;
	}
	public String getDekkType() {
		return dekkType;
	}
	public String toString() {
		return "Merke: " + merke +
				"\nDimensjon: " + dimensjon +
				"\nDekk-type: " + dekkType;
	}

}