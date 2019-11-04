package astronomy;

//DIECA(30,'D',"Dieca"),NOVIA(27,'N',"Novia"),
//YOTTA(24,'Y',"Yotta"),ZETTA(21,'Z',"Zetta"),
//EXA(18,'E',"Exa"),PETA(15,'P',"Peta"),
//TERA(12,'T',"Tera"),GIGA(9,'G',"Giga"),
//MEGA(6,'M',"Mega"),KILO(3,'k',"Kilo"),
//BASE(0,' ',""),
//MILLI(-3,'m',"Milli"),MICRO(-6,'μ',"Micro"),
//NANO(-9,'n',"Nano"),PICO(-12,'p',"Pico"),
//FEMTO(-15,'f',"Femto"),ATTO(-18,'a',"Atto"),
//ZEPTO(-21,'a',"Zepto"),YOCTO(-24,'y',"Yocto"),
//NOVITO(-27,'v',"Novito"),DIETO(-30,'d',"Dieto");

import java.io.Serializable;

import engine.Savable;

public interface AstroObject extends Savable, Serializable {

	final static double LUNE = 7347000000000000000000d;
	final static double EARTH = 5972000000000000000000000d;
	final static double JOVIAN = 1898000000000000000000000000d;
	final static double SOL = 1989000000000000000000000000000d;

	final static double LUNERADI = 1737000;
	final static double EARTHRADI = 6371000;
	final static double JOVIANRADI = 71490000;
	static final double SOLRADI = 695500;
	final static double LIGHTSECOND = 299800000;
	final static double AU = 149600000000d;
	final static double LIGHTYEAR = 9461000000000000d;
	final static double SOLMASSBH = 0.00000424;

	final static double LIGHTSPEED = 299800000;

	final static double GRAVITYEARTH = 9.806;

	static final double BAR = 1200;
	static final double TERRIAN = 5510;

	final static double HOUR = 3600;
	final static double DAY = 8640;
	final static double MONTH = 2628000;
	final static double YEAR = 31540000d;
	final static double CENTURY = 3154000000d;

	static final double SUNLIGHT = 2250000000000000000000d;

	static final double SOLTEMP = 5778;
	static final double CORETEMP = 100000;
	static final double BACKGROUNDRADIATION = 5;

	final static double G = 0.00000000066742;
	final static double O = 0.000000056704;
}
