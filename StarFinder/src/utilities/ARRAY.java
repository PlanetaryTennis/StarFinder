package utilities;

import units.sidistance;

public class ARRAY {

	public static sidistance[] SORT(sidistance[] o) {
		sidistance[] p = new sidistance[o.length];
		sidistance c;
		sidistance t;
		for(int i = 0;i < p.length;i++) {
			c = o[i];
			for(int k = i;k < o.length;k++) {
				if(c.greaterThan(o[k])) {
					t = c;
					c = o[k];
					o[k] = t;
				}
			}
			p[i] = c;
		}
		return p;		
	}

	public static int[] SORT(int[] o) {
		int [] p = new int[o.length];
		int c;
		int t;
		for(int i = 0;i < p.length;i++) {
			c = o[i];
			for(int k = i;k < o.length;k++) {
				if(c > o[k]) {
					t = c;
					c = o[k];
					o[k] = t;
				}
			}
			p[i] = c;
		}
		return p;	
	}

	public static String print(int[][] in,String firstdim,String seconddim) {
		String out = "";
		for(int i = 0;i < in.length;i++) {
			out += print(false, in[i],firstdim);
			if(i != in.length-1)out += seconddim;
		}
		return out;
	}

	public static String print(boolean show,int[] in, String dim) {
		String out = "";
		for(int i = 0;i < in.length;i++) {
			if(show)out+=i+":";
			out += 0+in[i];
			if(i != in.length-1)out += dim;
		}
		return out;
	}

}
