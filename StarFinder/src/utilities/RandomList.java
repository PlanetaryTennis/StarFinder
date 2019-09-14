package utilities;

import java.util.Random;
import java.util.Vector;

public class RandomList {

	private Vector<Object> myList = new Vector<Object>();
	private Vector<Integer> myWeights = new Vector<Integer>();
	private int myTotal = 0;
	
	public static final Random random = new Random(System.currentTimeMillis());
	
	public void add(int i,Object s) {
		myTotal += i;
		myWeights.add(i);
		myList.add(s);
	}
	
	public Object get(){
		int r = random.nextInt(myTotal);
		for(int i = 0;i < myList.size();i++) {
			if(r-myWeights.elementAt(i)<=0) return myList.elementAt(i);
			r -= myWeights.elementAt(i);
		}
		return null;
	}
	
	public static RandomList zif(Object[] o) {
		RandomList out = new RandomList();
		int weight = o.length*30;
		for(int i = 0;i < o.length;i++) {
			out.add(weight/((i+1)), o[i]);
		}
		return out;
	}
	
}
