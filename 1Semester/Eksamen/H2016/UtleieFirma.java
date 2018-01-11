class UtleieFirma {
	private String navn;
	private Bil[] biler;
	
	public UtleieFirma(String navn, int antallBiler) {
		this.navn = navn;
		this.bilder = new Bil[antallBilder];
	}
	public boolean regBil(Bil x) {
		for(Bil i : biler) if(i == x) return false;
		int temp = -1;
		for(int i = 0; i < biler.length; i++) {
			if(biler[i] == null) {
				temp = i;
				break;
			}
		}
		if(temp != -1) {
			biler[temp] = x;
			return true;
		}
		return false;  
	}
	public Bil[] getBiler() {
		boolean sortert = false;
		while(!sortert) {
			sortert = true;
			for(int i = 0; i < biler.length - 1; i++) {
				if(biler[i].getRegnr() > biler[i+1].getRegnr()) {
					swap(biler, i, i+1);
					sortert = false;
				}
			}
		}
		return biler;
	}
	private void swap(Bil[] i, x, y) {
		Bil temp = i[x];
		i[x] = i[y];
		i[y] = temp;
	}
}