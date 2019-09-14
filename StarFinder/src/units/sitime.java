package units;

import astronomy.AstroObject;

public class sitime  extends siunit{
	public final static String NAME = "second";
	public final static char SYMBOL = 's';
	
	private sci value;
	private SI power;
	
	public sitime(double v,SI p){
		power = p;
		value = new sci(v, power.getBase());
	}

	public sitime(double d) {
		if(d == 0) {
			value = sci.ZERO;
			power = SI.BASE;
			return;
		}
		double num = d;
		int pow = 0;
		while(Math.abs(num) >= sci.base) {
			num = num/sci.base;
			pow++;
		}
		while(Math.abs(num) <= 1) {
			num = num*sci.base;
			pow--;
		}
		value = new sci(num,pow);
		int i = pow % 3;
		if(i < 0) {
			i = (3 + i)%3;
		}
		while(i != 0){
			num = num * 10;
			pow--;
			i--;
		}
		power = SI.valuate(pow);
	}

	public sci getValue() {
		return value;
	}

	public void setValue(sci value) {
		this.value = value;
	}

	public SI getPower() {
		return power;
	}

	public void setPower(SI power) {
		this.power = power;
	}
	
	public boolean greaterThan(sitime comp) {
		if(comp.getMyPow() > this.getMyPow()) {
			return false;
		}else if(comp.getMyPow() < this.getMyPow()) {
			return true;
		}else {
			if(comp.getMyNum() > this.getMyNum()) {
				return false;
			}else if(comp.getMyNum() < this.getMyNum()) {
				return true;
			}else {
				return false;
			}
		}
	}
	
	private int getMyPow() {
		return this.getValue().getMyPow();
	}

	private double getMyNum() {
		return this.getValue().getMyNum();
	}

	public boolean lessThan(sitime comp) {
		if(comp.getMyPow() > this.getMyPow()) {
			return true;
		}else if(comp.getMyPow() < this.getMyPow()) {
			return false;
		}else {
			if(comp.getMyNum() > this.getMyNum()) {
				return true;
			}else if(comp.getMyNum() < this.getMyNum()) {
				return false;
			}else {
				return false;
			}
		}
	}

	public boolean equalTo(sitime comp) {
		if(comp.getMyPow() > this.getMyPow()) {
			return false;
		}else if(comp.getMyPow() < this.getMyPow()) {
			return false;
		}else {
			if(comp.getMyNum() > this.getMyNum()) {
				return false;
			}else if(comp.getMyNum() < this.getMyNum()) {
				return false;
			}else {
				return true;
			}
		}
	}
	
	public boolean lessOrEqual(sitime comp) {
		return !greaterThan(comp);
	}
	
	public boolean greaterOrEqual(sitime comp) {
		return !lessThan(comp);
	}
	
	public sitime plus(sitime addend) {
		int pow = this.getMyPow();
		double num = 0;
		
		int diff = this.getValue().Diff(addend.getValue());
		
		num = this.getMyNum() + addend.getMyNum()/Math.pow(sci.base, diff);
		while(Math.abs(num) >= sci.base) {
			num = num/sci.base;
			pow++;
		}
		int i = pow % 3;
		while(i != 0){
			num = num * 10;
			pow--;
			i--;
		}
		SI value = SI.valuate(pow);
		return new sitime(num,value);
	}
	
	public sitime minus(sitime subtrahend) {
		return this.plus(subtrahend.scale(-1));
	}

	public sitime scale(double s) {
		if(s == 0||this.getMyNum() == 0) {
			return new sitime(0,SI.BASE);
		}
		int pow = this.getMyPow();
		double num = this.getMyNum() * s;
		while(Math.abs(num) >= sci.base) {
			num = num/sci.base;
			pow++;
		}
		while(Math.abs(num) < 1) {
			num = num*sci.base;
			pow--;
		}
		int i = pow % 3;
		while(i != 0){
			num = num * 10;
			pow--;
			i--;
		}
		SI value = SI.valuate(pow);		
		return new sitime(num,value);
	}
	
	public String timeOut() {
		String out = "";
		int value = 0;
		sitime calc = this.scale(1.0);
		if(calc.compair(AstroObject.YEAR)>=1) {
			value = (int) Math.floor(calc.compair(AstroObject.YEAR));
			out += value + "y:";
			calc = calc.minus(AstroObject.YEAR.scale(value));
		}
		if(calc.compair(AstroObject.DAY)>=1) {
			value = (int) Math.floor(calc.compair(AstroObject.DAY));
			out += value + "d:";
			calc = calc.minus(AstroObject.DAY.scale(value));
		}
		if(calc.compair(AstroObject.HOUR)>=1) {
			value = (int) Math.floor(calc.compair(AstroObject.HOUR));
			out += value + "h:";
			calc = calc.minus(AstroObject.HOUR.scale(value));
		}
		if(sci.convertToDouble(calc.value)>=60) {
			value = (int) Math.floor(sci.convertToDouble(calc.value)/60);
			out += value + "m:";
			value = (int)(sci.convertToDouble(calc.getValue()) - value*60);
		}
		out += value + "s";
		return out;
	}
	
	public String toString() {
		int power = this.getValue().getMyPow() - this.power.getBase();
		double value = this.value.getMyNum() * Math.pow(sci.base, power);
		value = sci.round(value,3);
		return value + " " + this.power.getName() + NAME;
	}
}

