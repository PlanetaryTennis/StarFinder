package units;

import java.io.Serializable;

public abstract class siunit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2734254474653521041L;

	public abstract sci getValue();
	
	public double compair(siunit in) {
		return sci.round(this.getValue().makeDouble()/in.getValue().makeDouble(), 4);
	}
	
	public siunit scale(double factor) {
		return null;
	}
}
