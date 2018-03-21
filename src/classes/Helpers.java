package classes;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import main.Main;

/**
 * Helpers for printing and writing to file.
 * @author Sherry Lau Geok Teng
 *
 */
public class Helpers {

	private static final String TOP_LEFT_JOINT = "┌";
	private static final String TOP_RIGHT_JOINT = "┐";
	private static final String BOTTOM_LEFT_JOINT = "└";
	private static final String BOTTOM_RIGHT_JOINT = "┘";
	private static final String TOP_JOINT = "┬";
	private static final String BOTTOM_JOINT = "┴";
	private static final String LEFT_JOINT = "├";
	private static final String JOINT = "┼";
	private static final String RIGHT_JOINT = "┤";
	private static final char HORIZONTAL_LINE = '─';
	private static final char PADDING = ' ';
	private static final String VERTICAL_LINE = "│";

	public static String aupMapToStr(HashMap<State, ActionUtilPair> map, int colSize, int rowSize) {

		String[][] actionArr = new String[colSize][rowSize];
		String[][] utilityArr = new String[colSize][rowSize];

		for (HashMap.Entry<State, ActionUtilPair> entry : map.entrySet()) {
			State s = entry.getKey();
			ActionUtilPair p = entry.getValue();
			actionArr[s.getCol()][s.getRow()] = Character.toString(p.getAct().symbol());
			utilityArr[s.getCol()][s.getRow()] = String.format("%.5f", p.getUtil());
		}

		String[] actionStr = arrayToStr(actionArr).split("\r\n");
		String[] utilityStr = arrayToStr(utilityArr).split("\r\n");

		String output = "";

		for (int i = 0; i < actionStr.length; i++) {
			output += actionStr[i] + " " + utilityStr[i] + "\r\n";
		}

		return output;
	}

	public static String arrayToStr(String[][] arr) {

		String output = "";
		int maxLength = getMaxWidth(arr);

		for (int i = 0; i < arr.length; i++) {
			if (i == 0)
				output += TOP_LEFT_JOINT + padLeft("", maxLength, HORIZONTAL_LINE);
			else if (i == arr.length - 1)
				output += TOP_JOINT + padLeft("", maxLength, HORIZONTAL_LINE) + TOP_RIGHT_JOINT + "\r\n";
			else
				output += TOP_JOINT + padLeft("", maxLength, HORIZONTAL_LINE);
		}

		for (int row = 0; row < arr[0].length; row++) {
			for (int col = 0; col < arr.length; col++) {
				String thisValue = padLeft(arr[col][row], maxLength, PADDING);

				if (col == arr.length - 1)
					output += VERTICAL_LINE + thisValue + VERTICAL_LINE + "\r\n";
				else
					output += VERTICAL_LINE + thisValue;
			}

			if (row != arr[0].length - 1) {
				for (int i = 0; i < arr.length; i++) {
					if (i == 0)
						output += LEFT_JOINT + padLeft("", maxLength, HORIZONTAL_LINE);
					else if (i == arr.length - 1)
						output += JOINT + padLeft("", maxLength, HORIZONTAL_LINE) + RIGHT_JOINT + "\r\n";
					else
						output += JOINT + padLeft("", maxLength, HORIZONTAL_LINE);
				}
			}
		}

		for (int i = 0; i < arr.length; i++) {
			if (i == 0)
				output += BOTTOM_LEFT_JOINT + padLeft("", maxLength, HORIZONTAL_LINE);
			else if (i == arr.length - 1)
				output += BOTTOM_JOINT + padLeft("", maxLength, HORIZONTAL_LINE) + BOTTOM_RIGHT_JOINT + "\r\n";
			else
				output += BOTTOM_JOINT + padLeft("", maxLength, HORIZONTAL_LINE);
		}

		return output;
	}

	private static int getMaxWidth(String[][] arr) {
		int maxLength = 0;

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				if (arr[i][j] != null && arr[i][j].length() > maxLength)
					maxLength = arr[i][j].length();
			}
		}
		return maxLength;
	}

	private static String padLeft(String s, int n, char padding) {
		if (s == null) {
			s = "";
			padding = '▒';
		}
		return padding + String.format("%1$" + n + "s", s).replace(' ', padding) + padding;
	}

	public static void writeToFile(String content) {
		try {
			BufferedWriter out = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(Main.FILE_NAME, true), StandardCharsets.UTF_8));
			out.write(content);
			out.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}
