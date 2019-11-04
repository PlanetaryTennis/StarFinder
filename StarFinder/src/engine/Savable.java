package engine;

public interface Savable {

	public int loadString(String load);

	public String saveString();

	public int getClassIndex();

	public String getID();
}
