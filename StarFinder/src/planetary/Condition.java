package planetary;

import java.io.Serializable;
import java.util.Random;
import java.util.UUID;

import astronomy.AstroObject;
import astronomy.planetary.Habitable;
import astronomy.planetary.HabitableMoon;
import engine.Savable;
import utilities.StringFundementals;

public class Condition  implements Serializable, Savable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6385732161636494008L;
	private int GravityIndex;
	private int TempitureIndex;
	private int VarianceIndex;
	private int AtmosphericIndex;
	private int WaterIndex;
	private Atmosphere AirIndex;
	private boolean Dextros;

	public static Atmosphere[] LIST = new Atmosphere[] {Atmosphere.AMMONIA,Atmosphere.METHANE,Atmosphere.AMMONIA,Atmosphere.NIGTORGEN,Atmosphere.NIGTORGEN,Atmosphere.NIGTORGEN,Atmosphere.NIGTORGEN,Atmosphere.NIGTORGEN};
	public static Random ran = new Random(System.currentTimeMillis());

	public Condition(String load) {
		this.loadString(load);
	}
	
	public Condition(Habitable world) {
		Dextros = ran.nextBoolean();
		AirIndex = LIST[ran.nextInt(LIST.length)];
		GravityIndex = parseGravity(world.getMyGravity());
		TempitureIndex = parseTempiture(world.getMyTemps()[1]+(world.getMyTemps()[0])*(0.5));
		VarianceIndex = parseVar(world.getMyTemps()[2],world.getMyTemps()[5]);
		AtmosphericIndex = parseAtmosphere(world.getMyAtmosphere());
		WaterIndex = parseWater(world.getMyWater());
		myID = UUID.randomUUID().toString()+".Surface";
	}

	public Condition(HabitableMoon world) {
		Dextros = ran.nextBoolean();
		AirIndex = LIST[ran.nextInt(LIST.length)];
		GravityIndex = parseGravity(world.getMyGravity());
		TempitureIndex = parseTempiture(world.getMyTemps()[0]);
		VarianceIndex = parseVar(world.getMyTemps()[2],world.getMyTemps()[5]);
		AtmosphericIndex = parseAtmosphere(world.getMyAtmosphere());
		WaterIndex = parseWater(world.getMyWater());
		myID = UUID.randomUUID().toString()+".Surface";
	}

	private int parseWater(double w) {
		if(w < 0.1) {
			return 0;
		}else if(w < 0.2) {
			return 1;
		}else if(w < 0.3) {
			return 2;
		}else if(w < 0.4) {
			return 3;
		}else if(w < 0.5) {
			return 4;
		}else if(w < 0.55) {
			return 5;
		}else if(w < 0.6) {
			return 6;
		}else if(w < 0.65) {
			return 7;
		}else if(w < 0.7) {
			return 8;
		}else if(w < 0.8) {
			return 9;
		}else {
			return 10;
		}
	}

	private int parseAtmosphere(double g) {
		if(AstroObject.BAR*(0.1) >= (g)) {
			return 0;
		}else if(AstroObject.BAR*(0.25) >= (g)) {
			return 1;
		}else if(AstroObject.BAR*(0.5) >= (g)) {
			return 2;
		}else if(AstroObject.BAR*(0.75) >= (g)) {
			return 3;
		}else if(AstroObject.BAR >= (g)) {
			return 4;
		}else if(AstroObject.BAR*(1.5) >= (g)) {
			return 5;
		}else if(AstroObject.BAR*(2.0) >= (g)) {
			return 6;
		}else if(AstroObject.BAR*(3.0) >= (g)) {
			return 7;
		}else if(AstroObject.BAR*(4.0) >= (g)) {
			return 8;
		}else if(AstroObject.BAR*(5.0) >= (g)) {
			return 9;
		}else {
			return 10;
		}
	}

	private int parseVar(double d1, double d2) {
		int TempO = parseTempiture(d1);
		int TempT = parseTempiture(d2);
		return TempO-TempT;
	}

	private int parseTempiture(double t) {
		double d = t;
		if(240.0>=d) {
			return 0;
		}else if(260>=d) {
			return 1;
		}else if(270>=d) {
			return 2;
		}else if(280>=d) {
			return 3;
		}else if(290>=d) {
			return 4;
		}else if(300>=d) {
			return 5;
		}else if(310>=d) {
			return 6;
		}else if(320>=d) {
			return 7;
		}else if(330>=d) {
			return 8;
		}else if(340>=d) {
			return 9;
		}else {
			return 10;
		}
	}

	private int parseGravity(double g) {
		if(AstroObject.GRAVITYEARTH*(0.1) >= (g)) {
			return 0;
		}else if(AstroObject.GRAVITYEARTH*(0.25) >= (g)) {
			return 1;
		}else if(AstroObject.GRAVITYEARTH*(0.5) >= (g)) {
			return 2;
		}else if(AstroObject.GRAVITYEARTH*(0.75) >= (g)) {
			return 3;
		}else if(AstroObject.GRAVITYEARTH >= (g)) {
			return 4;
		}else if(AstroObject.GRAVITYEARTH*(1.5) >= (g)) {
			return 5;
		}else if(AstroObject.GRAVITYEARTH*(2.0) >= (g)) {
			return 6;
		}else if(AstroObject.GRAVITYEARTH*(3.0) >= (g)) {
			return 7;
		}else if(AstroObject.GRAVITYEARTH*(4.0) >= (g)) {
			return 8;
		}else if(AstroObject.GRAVITYEARTH*(5.0) >= (g)) {
			return 9;
		}else {
			return 10;
		}
	}

	public int getGravityIndex() {
		return GravityIndex;
	}

	public void setGravityIndex(int gravityIndex) {
		GravityIndex = gravityIndex;
	}

	public int getTempitureIndex() {
		return TempitureIndex;
	}

	public void setTempitureIndex(int tempitureIndex) {
		TempitureIndex = tempitureIndex;
	}

	public int getVarianceIndex() {
		return VarianceIndex;
	}

	public void setVarianceIndex(int varianceIndex) {
		VarianceIndex = varianceIndex;
	}

	public int getAtmosphericIndex() {
		return AtmosphericIndex;
	}

	public void setAtmosphericIndex(int atmosphericIndex) {
		AtmosphericIndex = atmosphericIndex;
	}

	public int getWaterIndex() {
		return WaterIndex;
	}

	public void setWaterIndex(int waterIndex) {
		WaterIndex = waterIndex;
	}

	public Atmosphere getAirIndex() {
		return AirIndex;
	}

	public void setAirIndex(Atmosphere airIndex) {
		AirIndex = airIndex;
	}

	public boolean isDextros() {
		return Dextros;
	}

	public void setDextros(boolean dextros) {
		Dextros = dextros;
	}

	@Override
	public int loadString(String load) {
		String[] in = StringFundementals.breakByLine(load);
		myID = in[0];
		int i = 2;
		WaterIndex = Integer.parseInt(in[i++]);
		VarianceIndex = Integer.parseInt(in[i++]);
		TempitureIndex = Integer.parseInt(in[i++]);
		GravityIndex = Integer.parseInt(in[i++]);
		AtmosphericIndex = Integer.parseInt(in[i++]);
		AirIndex = Atmosphere.valueOf(in[i++]);
		return i;
	}

	@Override
	public String saveString() {
		String out = "";
		out += this.getID() + "\n";
		out += this.getClassIndex() + "\n";
		out += this.getWaterIndex() + "\n";
		out += this.getVarianceIndex() + "\n";
		out += this.getTempitureIndex() + "\n";
		out += this.getGravityIndex() + "\n";
		out += this.getAtmosphericIndex() + "\n";
		out += this.getAirIndex().name() + "\n";
		return out;
	}


	public static final int CLASSINDEX = 119013;
	
	@Override
	public int getClassIndex() {
		return CLASSINDEX;
	}
	
	String myID;

	@Override
	public String getID() {
		return myID;
	}
}