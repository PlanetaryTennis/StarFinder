package units;

import java.io.Serializable;

public class sci implements Serializable{
	
	public static final sci ZERO = new sci(0,0);
	
	public static final short base = 10;
	private double myNum;
	private int myPow;
	
	public sci(double n,int p){
		if(n == 0) {
			myPow = 0;
			myNum = 0;
		}else {
			myNum = n;
			myPow = p;
			while(Math.abs(myNum) >= base) {
				myNum = myNum/base;
				myPow++;
			}
			while(Math.abs(myNum) < 1) {
				myNum = myNum*base;
				myPow--;
			}
			myNum = round(myNum,3);
		}
	}
	
	public static double round(double n, int i) {
		double d = Math.pow(10, i);
		double k = n*d;
		double h = Math.round(k);
		double j = h/d;
		return j;
	}

	public double getMyNum() {
		return myNum;
	}

	public void setMyNum(double myNum) {
		this.myNum = myNum;
	}

	public int getMyPow() {
		return myPow;
	}

	public void setMyPow(int myPow) {
		this.myPow = myPow;
	}
	
	public boolean greaterThan(sci comp) {
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
	
	public boolean lessThan(sci comp) {
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

	public boolean equalTo(sci comp) {
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
	
	public boolean lessOrEqual(sci comp) {
		return !greaterThan(comp);
	}
	
	public boolean greaterOrEqual(sci comp) {
		return !lessThan(comp);
	}
	
	public sci plus(sci addend) {
		int pow = this.getMyPow();
		double num = 0;
		
		int diff = this.Diff(addend);
		
		num = this.getMyNum() + addend.getMyNum()/Math.pow(base, diff);
		while(Math.abs(num) >= base) {
			num = num/base;
			pow++;
		}
		
		return new sci(num,pow);
	}
	
	public sci multiply(sci factor) {
		int pow = this.getMyPow() + factor.getMyPow();
		double num = this.getMyNum() * factor.getMyNum();
		while(Math.abs(num) >= base) {
			num = num/base;
			pow++;
		}
		while(Math.abs(num) < 1) {
			num = num*base;
			pow--;
		}
		return new sci(num,pow);
	}
	
	public sci div(sci divisor) {
		return this.multiply(new sci(1/divisor.getMyNum(),-divisor.getMyPow()));
	}
	
	public sci minus(sci subtrahend) {
		return this.plus(subtrahend.scale(-1));
	}

	public sci scale(double s) {
		int pow = this.getMyPow();
		double num = this.getMyNum() * s;
		while(Math.abs(num) >= base) {
			num = num/base;
			pow++;
		}
		while(Math.abs(num) < 1) {
			num = num*base;
			pow--;
		}
		return new sci(num,pow);
	}
	
	public int Diff(sci devisor) {
		return this.getMyPow() - devisor.getMyPow();
	}
	
	public String toString() {
		return this.getMyNum()+"E"+this.getMyPow();
	}
	
	//static methods
	
	public static sci convertFromInt(int i) {
		int pow = 0;
		double num = i;
		while(Math.abs(num) >= base) {
			num = num/base;
			pow++;
		}
		return new sci(num,pow);
	}
	
	public static sci convertFromDouble(double d) {
		int pow = 0;
		double num = d;
		while(Math.abs(num) >= base) {
			num = num/base;
			pow++;
		}
		while(Math.abs(num) < 1) {
			num = num*base;
			pow--;
		}
		return new sci(num,pow);
	}
	
	public static long convertToLong(sci s) {
		return (long) (s.getMyNum()*Math.pow(base, s.getMyPow()));
	}
	
	public static Double convertToDouble(sci s) {
		return s.getMyNum()*Math.pow(base, s.getMyPow());
	}

	public Double makeDouble() {
		return sci.convertToDouble(this);
	}
}
