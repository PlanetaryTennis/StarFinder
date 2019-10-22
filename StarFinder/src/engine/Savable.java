package engine;

public interface Savable {
	
	public void loadString(String load);
	public String saveString();
	public int getClassIndex();
	public String getID();
}
 