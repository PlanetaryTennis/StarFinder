package planetary;

import java.util.Random;

import astronomy.AstroObject;
import astronomy.Habitable;
import astronomy.HabitableMoon;
import astronomy.Terrestrial;
import units.sci;
import units.siacceleration;
import units.sidensity;
import units.sitemperature;

public class Condition {

	private int GravityIndex;
	private int TempitureIndex;
	private int VarianceIndex;
	private int AtmosphericIndex;
	private int WaterIndex;
	private Atmosphere AirIndex;
	private boolean Dextros;

	public static Atmosphere[] LIST = new Atmosphere[] {Atmosphere.AMMONIA,Atmosphere.METHANE,Atmosphere.AMMONIA,Atmosphere.NIGTORGEN,Atmosphere.NIGTORGEN,Atmosphere.NIGTORGEN,Atmosphere.NIGTORGEN,Atmosphere.NIGTORGEN};
	public static Random ran = new Random(System.currentTimeMillis());

	public Condition(Habitable world) {
		Dextros = ran.nextBoolean();
		AirIndex = LIST[ran.nextInt(LIST.length)];
		GravityIndex = parseGravity(world.getMyGravity());
		TempitureIndex = parseTempiture(world.getMyTemps()[1].plus(world.getMyTemps()[0]).scale(0.5));
		VarianceIndex = parseVar(world.getMyTemps()[2],world.getMyTemps()[5]);
		AtmosphericIndex = parseAtmosphere(world.getMyAtmosphere());
		WaterIndex = parseWater(world.getMyWater());
	}

	public Condition(HabitableMoon world) {
		Dextros = ran.nextBoolean();
		AirIndex = LIST[ran.nextInt(LIST.length)];
		GravityIndex = parseGravity(world.getMyGravity());
		TempitureIndex = parseTempiture(world.getMyTemps()[0]);
		VarianceIndex = parseVar(world.getMyTemps()[2],world.getMyTemps()[5]);
		AtmosphericIndex = parseAtmosphere(world.getMyAtmosphere());
		WaterIndex = parseWater(world.getMyWater());
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

	private int parseAtmosphere(sidensity g) {
		if(AstroObject.BAR.scale(0.1).greaterOrEqual(g)) {
			return 0;
		}else if(AstroObject.BAR.scale(0.25).greaterOrEqual(g)) {
			return 1;
		}else if(AstroObject.BAR.scale(0.5).greaterOrEqual(g)) {
			return 2;
		}else if(AstroObject.BAR.scale(0.75).greaterOrEqual(g)) {
			return 3;
		}else if(AstroObject.BAR.greaterOrEqual(g)) {
			return 4;
		}else if(AstroObject.BAR.scale(1.5).greaterOrEqual(g)) {
			return 5;
		}else if(AstroObject.BAR.scale(2.0).greaterOrEqual(g)) {
			return 6;
		}else if(AstroObject.BAR.scale(3.0).greaterOrEqual(g)) {
			return 7;
		}else if(AstroObject.BAR.scale(4.0).greaterOrEqual(g)) {
			return 8;
		}else if(AstroObject.BAR.scale(5.0).greaterOrEqual(g)) {
			return 9;
		}else {
			return 10;
		}
	}

	private int parseVar(sitemperature sitemperature, sitemperature sitemperature2) {
		int TempO = parseTempiture(sitemperature);
		int TempT = parseTempiture(sitemperature2);
		return TempO-TempT;
	}

	private int parseTempiture(sitemperature t) {
		double d = sci.convertToDouble(t.getValue());
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

	private int parseGravity(siacceleration g) {
		if(AstroObject.GRAVITYEARTH.scale(0.1).greaterOrEqual(g)) {
			return 0;
		}else if(AstroObject.GRAVITYEARTH.scale(0.25).greaterOrEqual(g)) {
			return 1;
		}else if(AstroObject.GRAVITYEARTH.scale(0.5).greaterOrEqual(g)) {
			return 2;
		}else if(AstroObject.GRAVITYEARTH.scale(0.75).greaterOrEqual(g)) {
			return 3;
		}else if(AstroObject.GRAVITYEARTH.greaterOrEqual(g)) {
			return 4;
		}else if(AstroObject.GRAVITYEARTH.scale(1.5).greaterOrEqual(g)) {
			return 5;
		}else if(AstroObject.GRAVITYEARTH.scale(2.0).greaterOrEqual(g)) {
			return 6;
		}else if(AstroObject.GRAVITYEARTH.scale(3.0).greaterOrEqual(g)) {
			return 7;
		}else if(AstroObject.GRAVITYEARTH.scale(4.0).greaterOrEqual(g)) {
			return 8;
		}else if(AstroObject.GRAVITYEARTH.scale(5.0).greaterOrEqual(g)) {
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
}