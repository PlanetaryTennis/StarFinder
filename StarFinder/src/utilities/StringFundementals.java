package utilities;

import java.util.Vector;

public class StringFundementals {
	public static final String ENDLINE = "\n";

	public static Vector<String> unnestString(char nestStart, char nestEnd, String Prosses) {
		Vector<String> out = new Vector<String>();
		int i = 0;
		String load = "";
		String sub = "";
		for (int k = 0; k < Prosses.length(); k++) {
			if (i == 0) {
				if (Prosses.charAt(k) == nestStart) {
					i = 1;
					k++;
					sub = "";
				} else {
					load += Prosses.charAt(k);
				}
			} else {
				if (Prosses.charAt(k) == nestStart) {
					i++;
				}
				if (Prosses.charAt(k) == nestEnd && i == 1) {
					i = 0;
					k++;
					out.add(sub);
				} else {
					sub += Prosses.charAt(k);
					if (Prosses.charAt(k) == nestEnd) {
						i--;
					}
				}
			}
		}
		out.add(0, load);
		return out;
	}

	public static String[] breakByLine(String in) {
		int lines = 1;
		for (int i = 0; i < in.length(); i++) {
			char c = in.charAt(i);
			if (c == '\n') {
				lines++;
			}
		}
		String[] out = new String[lines];
		int tracker = 0;
		out[0] = "";
		for (int i = 0; i < in.length(); i++) {
			char c = in.charAt(i);
			if (c == '\n') {
				tracker++;
				out[tracker] = "";
			} else {
				out[tracker] += c;
			}
		}
		return out;
	}
}
