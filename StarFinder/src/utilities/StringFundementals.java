package utilities;

public class StringFundementals {
	public static final String ENDLINE = "\n";
	public static final String MARK = "#@!@#";
	
	public static String[] breakByLine(String in) {
		int lines = 1;
		for(int i = 0;i < in.length();i++) {
			char c = in.charAt(i);
			if(c == '\n') {
				lines++;
			}
		}
		String[] out = new String[lines];
		int tracker = 0;
		out[0] = "";
		for(int i = 0;i < in.length();i++) {
			char c = in.charAt(i);
			if(c == '\n') {
				tracker++;
				out[tracker] = "";
			}else {
				out[tracker] += c;
			}
		}
		return out;
	}
}
