package astronomy;

import engine.Savable;
import units.SI;
import units.sci;
import units.siacceleration;
import units.sibrightness;
import units.sidensity;
import units.sidistance;
import units.simass;
import units.sispeed;
import units.sitemperature;
import units.sitime;

public interface AstroObject extends Savable{
	
	final static simass LUNE = new simass(73.47,SI.ZETTA);
	final static simass EARTH = new simass(5.972,SI.YOTTA);
	final static simass JOVIAN = new simass(1.898,SI.NOVIA);
	final static simass SOL = new simass(1.989,SI.DIECA);
	
	final static sidistance LUNERADI = new sidistance(1.737,SI.MEGA);
	final static sidistance EARTHRADI = new sidistance(6.371,SI.MEGA);
	final static sidistance JOVIANRADI = new sidistance(71.49,SI.MEGA);
	static final sidistance SOLRADI = new sidistance(695.5,SI.MEGA);	
	final static sidistance LIGHTSECOND = new sidistance(299.8,SI.MEGA);
	final static sidistance AU = new sidistance(149.6,SI.GIGA);
	final static sidistance LIGHTYEAR = new sidistance(9.461,SI.PETA);
	final static sidistance SOLMASSBH = new sidistance(4.24,SI.MICRO);
	
	final static sispeed LIGHTSPEED = new sispeed(299.8,SI.MEGA);
	
	final static sci LIGHTSQUARE = new sci(8.9876,16);
	
	final static siacceleration GRAVITYEARTH = new siacceleration(9.806,SI.BASE);
	
	static final sidensity BAR = new sidensity(1.200,SI.KILO);
	static final sidensity TERRIAN = new sidensity(5.510,SI.KILO);
	
	final static sitime HOUR = new sitime(3.600,SI.KILO);
	final static sitime DAY = new sitime(86.40,SI.KILO);
	final static sitime MONTH = new sitime(2.628,SI.MEGA);
	final static sitime YEAR = new sitime(31.54,SI.MEGA);
	final static sitime CENTURY = new sitime(3.154,SI.GIGA);
	
	static final sibrightness SUNLIGHT = new sibrightness(2.250,SI.ZETTA);
	
	static final sitemperature SOLTEMP = new sitemperature(5.778,SI.KILO);	
	static final sitemperature CORETEMP = new sitemperature(100,SI.KILO);	
	static final sitemperature BACKGROUNDRADIATION = new sitemperature(5,SI.BASE);	
	
	final static sci G = new sci(6.6742,-11);
	final static sci O = new sci(5.6704,-8);
}
