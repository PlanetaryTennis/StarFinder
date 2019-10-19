package units;

public class sitemperature  extends siunit{
	public final static String NAME = "kelvin";
	public final static char SYMBOL = 'K';
	
	private sci value;
	private SI power;
	
	public sitemperature(double v,SI p){
		power = p;
		value = new sci(v, power.getBase());
	}

	public sitemperature(double d) {
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
	
	public boolean greaterThan(sitemperature comp) {
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

	public boolean lessThan(sitemperature comp) {
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

	public boolean equalTo(sitemperature comp) {
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
	
	public boolean lessOrEqual(sitemperature comp) {
		return !greaterThan(comp);
	}
	
	public boolean greaterOrEqual(sitemperature comp) {
		return !lessThan(comp);
	}
	
	public sitemperature plus(sitemperature addend) {
		int pow = this.getMyPow();
		double num = 0;
		
		int diff = this.getValue().Diff(addend.getValue());
		
		num = this.getMyNum() + addend.getMyNum()/Math.pow(sci.base, diff);
		while(Math.abs(num) >= sci.base) {
			num = num/sci.base;
			pow++;
		}
		int i = pow % 3;
		if(i < 0) {
			i = (3 + i)%3;
		}
		while(i != 0){
			num = num * 10;
			pow--;
			i--;
		}
		SI value = SI.valuate(pow);
		return new sitemperature(num,value);
	}
	
	public sitemperature minus(sitemperature subtrahend) {
		return this.plus(subtrahend.scale(-1));
	}

	public sitemperature scale(double s) {
		if(s == 0||this.getMyNum() == 0) {
			return new sitemperature(0,SI.BASE);
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
		if(i < 0) {
			i = (3 + i)%3;
		}
		while(i != 0){
			num = num * 10;
			pow--;
			i--;
		}
		SI value = SI.valuate(pow);		
		return new sitemperature(num,value);
	}
	
	public String toString() {
		int power = this.getValue().getMyPow() - this.power.getBase();
		double value = this.value.getMyNum() * Math.pow(sci.base, power);
		value = sci.round(value,3);
		return value + " " + this.power.getName() + NAME;
	}
}

