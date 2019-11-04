package utilities;

public class Functions {

	public static int ClassSwitch(Class<?> key, Class<?>[] range) {
		for(int i = 0;i < range.length;i++) {
			if(key==range[i]) return i;
		}
		return -1;
	}

}
