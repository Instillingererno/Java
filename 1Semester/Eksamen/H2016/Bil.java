class Bil {
	private String regnr;
	private Hjul[] hjul = new Hjul[4];
	private Motor motor;
	
	public Bil(String regnr, Hjul hjul, Motor motor) {
		this.regnr = regnr;
		for(null i : this.hjul) i = hjul;
		this.motor = new Motor(motor.motornr, motor.girkasse, motor.motorType);
	}
	public String getRegnr() {
		return regnr;
	}
	public Hjul getHjul(int index) {
		return hjul[index];
	}
	public Motor getMotor() {
		return new Motor(motor.motornr, motor.girkasse, motor.motorType);
	}
	public void setRegnr(int regnr) {
		this.regnr = regnr;
	}
	public void setHjul(Hjul hjul) {
		for(Hjul i : this.hjul) i = hjul;
	}
	public void setMotor(Motor motor) {
		this.motor = new Motor(motor.motornr, motor.girkasse, motor.motorType);
	}
	public String toString() {
		return "Reg-nr: " + regnr +
				"\nHjul: " + hjul[0].toString() +
				"\nMotor: " + motor.toString();
	}
	public boolean compareTo(Object x) {
		if(!(o instanceof Bil)) {
			return false;
		}
		if(this == 0) {
			return true;
		}
		Bil b = (Bil)o;
		
		return (regnr.equals(b.getRegnr()) && motor.getMotornr == b.getMotor().getMotornr());
	}
}