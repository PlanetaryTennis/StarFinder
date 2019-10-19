package units;

public class sivolume  extends siunit{
	public final static String NAME = "liter";
	public final static char SYMBOL = 'l';
	
	private sci value;
	private SI power;
	
	public sivolume(double v,SI p){
		power = p;
		value = new sci(v, power.getBase());
	}

	public sivolume(double d) {
		if(d == 0) {
				value = sci.ZERO;
				power = SI.BASE;
		}else {
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

	public boolean greaterThan(sivolume comp) {
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

	public boolean lessThan(sivolume comp) {
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

	public boolean equalTo(sivolume comp) {
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
	
	public boolean lessOrEqual(sivolume comp) {
		return !greaterThan(comp);
	}
	
	public boolean greaterOrEqual(sivolume comp) {
		return !lessThan(comp);
	}
	
	public sivolume plus(sivolume addend) {
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
		return new sivolume(num,value);
	}
	
	public sivolume minus(sivolume subtrahend) {
		return this.plus(subtrahend.scale(-1));
	}

	public sivolume scale(double s) {
		if(s == 0||this.getMyNum() == 0) {
			return new sivolume(0,SI.BASE);
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
		return new sivolume(num,value);
	}
	
	public String toString() {
		int power = this.getValue().getMyPow() - this.power.getBase();
		double value = this.value.getMyNum() * Math.pow(sci.base, power);
		
		return value + " " + this.power.getName() + NAME;
	}
}
