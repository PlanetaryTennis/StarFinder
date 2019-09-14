package units;

import java.io.Serializable;

public enum SI  implements Serializable{

	DIECA(30,'D',"Dieca"),NOVIA(27,'N',"Novia"),
	YOTTA(24,'Y',"Yotta"),ZETTA(21,'Z',"Zetta"),
	EXA(18,'E',"Exa"),PETA(15,'P',"Peta"),
	TERA(12,'T',"Tera"),GIGA(9,'G',"Giga"),
	MEGA(6,'M',"Mega"),KILO(3,'k',"Kilo"),
	BASE(0,' ',""),
	MILLI(-3,'m',"Milli"),MICRO(-6,'μ',"Micro"),
	NANO(-9,'n',"Nano"),PICO(-12,'p',"Pico"),
	FEMTO(-15,'f',"Femto"),ATTO(-18,'a',"Atto"),
	ZEPTO(-21,'a',"Zepto"),YOCTO(-24,'y',"Yocto"),
	NOVITO(-27,'v',"Novito"),DIETO(-30,'d',"Dieto");

//	private final SI contained[] = {DIETO,NOVITO,YOCTO,ZEPTO,ATTO,FEMTO,PICO,NANO,MICRO,MILLI,BASE,KILO,MEGA,GIGA,TERA,PETA,EXA,ZETTA,YOTTA,NOVIA,DIECA};
	private int base;
	private char symbol;
	private String name;
	SI(int i,char c,String n){
		base = i;
		symbol = c;
		name = n;
	}
	
	public char getSymbol() {
		return symbol;
	}

	public int getBase() {
		return base;
	}
	
	public String getName() {
		return name;
	}

	public String toString() {
		return getName();
	}
	
	public static SI valuate(int pow) {
		SI out;
		pow = Math.abs(pow / 3 - (SI.values().length - 1) / 2);
		out = SI.values()[pow];
		return out;
	}
}
