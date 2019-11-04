package engine;

import javax.swing.JSlider;

public class InnerSlider extends JSlider {

	/**
	 * 
	 */
	private static final long serialVersionUID = 666587561053803713L;
	int innerMin, innerMax;

	public InnerSlider(int min, int max, int innerMin, int innerMax) {
		super(min, max);
		this.innerMin = innerMin;
		this.innerMax = innerMax;
		addChangeListener((e) -> {
			if (getValue() < innerMin) {
				setValue(innerMin);
				getMouseListeners()[0].mouseReleased(null);
			} else if (getValue() > innerMax) {
				setValue(innerMax);
				getMouseListeners()[0].mouseReleased(null);
			}
		});
	}

	public int getInnerMin() {
		return innerMin;
	}

	public void setInnerMin(int innerMin) {
		this.innerMin = innerMin;
	}

	public int getInnerMax() {
		return innerMax;
	}

	public void setInnerMax(int innerMax) {
		this.innerMax = innerMax;
	}
}
