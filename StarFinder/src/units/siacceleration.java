package units;

public class siacceleration extends siunit{
	public final static String NAME = "meters per second per second";
	public final static String SYMBOL = "m/s/s";

	private sci value;
	private SI power;

	public siacceleration(double v,SI p){
		power = p;
		value = new sci(v, power.getBase());
	}

	public siacceleration(double d) {
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

	public boolean greaterThan(siacceleration comp) {
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

	public boolean lessThan(siacceleration comp) {
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

	public boolean equalTo(siacceleration comp) {
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

	public boolean lessOrEqual(siacceleration comp) {
		return !greaterThan(comp);
	}

	public boolean greaterOrEqual(siacceleration comp) {
		return !lessThan(comp);
	}

	public siacceleration plus(siacceleration addend) {
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
		return new siacceleration(num,value);
	}

	public siacceleration minus(siacceleration subtrahend) {
		return this.plus(subtrahend.scale(-1));
	}

	public siacceleration scale(double s) {
		if(s == 0 || this.equalTo(new siacceleration(0))) {
			return new siacceleration(0, SI.BASE);
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
		return new siacceleration(num,value);
	}

	public String toString() {
		int power = this.getValue().getMyPow() - this.power.getBase();
		double value = this.value.getMyNum() * Math.pow(sci.base, power);
		value = sci.round(value, 3);
		return value + " " + this.power.getName() + NAME;
	}
}

