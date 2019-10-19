package engine;

public interface Savable {
	
	public int id;
	
	public void loadString(String load);
	public String saveString();
	public int getClassIndex();
}
