package shipfight;

public enum Tech {
	G("Gravity Manipulation"),
	D("Dark Matter Tech"),
	A("Antimatter Tech"),
	M("Matter Coding"),
	F("Fusion Tech"),
	Q("Quantum Manipulation"),
	T("Matter Transmition"),
	S("Super-Materials"),
	P("Psi-tech"),
	X("Energy Transformation"),
	C("Computer Tech "),
	N("-"),
	Z("Requires A,D,F, and G");
	
	public String name;
	
	Tech(String name){
		this.name=name;
	}
	
	@Override
	public String toString() {
		if(this == N)
			return "-";
		else
			return super.toString();
		}
	

}
