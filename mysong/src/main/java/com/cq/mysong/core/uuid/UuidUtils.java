package com.cq.mysong.core.uuid;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

import com.cq.mysong.util.BaseUtil;

public class UuidUtils {
	private static final char[] hexDigits = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	private static final String[] JAVA_KEYWORDS = { "assert", "abstract",
			"continue", "for", "new", "switch", "boolean", "default", "goto",
			"null", "synchronized", "break", "do", "if", "package", "this",
			"byte", "double", "implements", "private", "threadsafe", "byvalue",
			"else", "import", "protected", "throw", "case", "extends",
			"instanceof", "public", "transient", "catch", "false", "int",
			"return", "true", "char", "final", "interface", "short", "try",
			"class", "finally", "long", "static", "void", "const", "float",
			"native", "super", "while", "volatile", "strictfp" };

	private static String VOWELS = "AEIOU";

	private static String FRONTV = "EIY";

	private static String VARSON = "CSPTG";
	private static final int SEPERATOR_DEFAULT_SIZE = 9;

	public static String[] split(String line, int seperator) {
		if (line == null)
			return null;
		line = line.trim();
		if (line.length() == 0) {
			return null;
		}
		if (line.indexOf(seperator) < 0) {
			return new String[] { line };
		}
		List v = new ArrayList();
		int i = 0;
		int j;
		while ((j = line.indexOf(seperator, i)) >= 0) {
			v.add(line.substring(i, j).trim());
			i = j + 1;
		}
		v.add(line.substring(i).trim());
		return (String[]) v.toArray(new String[v.size()]);
	}

	public static String[] split(String line, String seperator) {
		if (line == null)
			return null;
		line = line.trim();
		if (line.length() == 0) {
			return new String[] { "" };
		}
		if (line.indexOf(seperator) < 0) {
			return new String[] { line };
		}

		List v = new ArrayList();
		int i = 0;
		int j;
		while ((j = line.indexOf(seperator, i)) >= 0) {
			v.add(line.substring(i, j).trim());
			i = j + seperator.length();
		}
		v.add(line.substring(i).trim());
		return (String[]) v.toArray(new String[v.size()]);
	}

	public static String fixNumber(long n) {
		return fixNumber(n, 8);
	}

	public static String fixNumber(long n, int len) {
		char[] b = new char[len];
		for (int i = 0; i < len; i++)
			b[i] = '0';
		return new DecimalFormat(new String(b)).format(n);
	}

	public static String fixNumber(int n) {
		return fixNumber(n, 4);
	}

	public static String fixNumber(int n, int len) {
		return fixNumber(n, len);
	}

	public static final boolean isEmpty(String s) {
		return (s == null) || (s.trim().length() == 0);
	}

	public static boolean equals(String s1, String s2) {
		if (isEmpty(s1))
			return isEmpty(s2);
		return s1.equals(s2);
	}

	public static boolean equalsIgnoreCase(String s1, String s2) {
		if (isEmpty(s1))
			return isEmpty(s2);
		return s1.equalsIgnoreCase(s2);
	}

	public static String numStr4Java(String s, char c, char c1) {
		if (s == null)
			return null;
		StringBuffer stringbuffer = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c2 = s.charAt(i);
			if (c2 != c) {
				if (c2 == c1){
					stringbuffer.append('.');
				}else{
					stringbuffer.append(c2);
				}
			}
		}
		return stringbuffer.toString();
	}

	public static String takeCommasFromNumStr(String s) {
		if (s == null)
			return null;
		if (s.indexOf(',') == -1) {
			return s;
		}
		return numStr4Java(s, ',', '.');
	}

	public static String[] enumeration2StringArray(Enumeration enumeration,
			int i) {
		int j = 0;
		String[] as = new String[i];
		while (enumeration.hasMoreElements())
			as[(j++)] = ((String) enumeration.nextElement());
		return as;
	}

	public static byte[] chars2bytes(char[] ac) {
		byte[] abyte0 = new byte[ac.length * 2];
		int i = 0;
		for (int j = 0; j < ac.length; j++) {
			char c = ac[j];
			char c1 = ac[j];
			abyte0[(i++)] = ((byte) (c >> '\b'));
			abyte0[(i++)] = ((byte) c1);
		}

		return abyte0;
	}

	public static char[] bytes2chars(byte[] abyte0) throws Exception {
		if (abyte0.length % 2 != 0)
			throw new Exception("Cannot convert an odd number of bytes");
		char[] ac = new char[abyte0.length / 2];
		int i = 0;
		for (int j = 0; j < ac.length; j++) {
			byte byte0 = abyte0[(i++)];
			byte byte1 = abyte0[(i++)];
			ac[j] = ((char) (byte0 << 8 & 0xFF00 | byte1 & 0xFF));
		}

		return ac;
	}

	public static String padStringWidth(String s, int i) {
		StringBuffer stringbuffer = null;
		if (s != null) {
			stringbuffer = new StringBuffer(s);
			stringbuffer.setLength(i);
			int j = s.length();
			for (int l = j; l < i; l++)
				stringbuffer.setCharAt(l, ' ');
		} else {
			stringbuffer = new StringBuffer(i);
			stringbuffer.setLength(i);
			for (int k = 0; k < i; k++) {
				stringbuffer.setCharAt(k, ' ');
			}
		}
		return stringbuffer.toString();
	}

	public static String padStringWidth(int i, int j) {
		return padStringWidth(String.valueOf(i), j);
	}

	public static String padStringWidth(float f, int i) {
		return padStringWidth(String.valueOf(f), i);
	}

	public static String padStringWidth(long l, int i) {
		return padStringWidth(String.valueOf(l), i);
	}

	public static String padStringWidth(double d, int i) {
		return padStringWidth(String.valueOf(d), i);
	}

	public static String toHexString(long x, int chars) {
		char[] buf = new char[chars];
		int charPos = chars;
		while (true) {
			charPos--;
			if (charPos < 0)
				break;
			buf[charPos] = hexDigits[((int) (x & 0xF))];
			x >>>= 4;
		}
		return new String(buf);
	}

	public static String GBToUnicode(String str) {
		try {
			return new String(str.getBytes("8859_1"), "GB2312");
		} catch (UnsupportedEncodingException e) {
		}
		return null;
	}

	public static String unicodeToGB(String str) {
		if ((str == null) || (str.length() == 0)) {
			return str;
		}
		try {
			return new String(str.getBytes("GB2312"), "8859_1");
		} catch (UnsupportedEncodingException e) {
		}
		return null;
	}

	public static String cnulls(Object obj) {
		return cnulls(obj, "");
	}

	public static String cnulls(Object obj, String defaultValue) {
		if (obj == null) {
			return defaultValue;
		}
		return obj.toString();
	}

	public static String cnulls(String str, String defaultValue) {
		if (str == null) {
			return defaultValue;
		}
		return str;
	}

	public static String cnulls(String str) {
		if (str == null) {
			return "";
		}
		return str;
	}

	public static String formatCurrency(double cy) {
		return NumberFormat.getCurrencyInstance().format(cy);
	}

	public static String formatNumber(double d, int n) {
		String pattern = "#,##0";
		if (n > 0) {
			pattern = pattern + ".";
			for (int i = 0; i < n; i++)
				pattern = pattern + "0";
		}
		return new DecimalFormat(pattern).format(d);
	}

	public static int compareToIgnoreCase(String str1, String str2) {
		return str1.toLowerCase().compareTo(str2.toLowerCase());
	}

	public static boolean endsWithIgnoreCase(String str, String suffix) {
		return str.toLowerCase().endsWith(suffix.toLowerCase());
	}

	public static int indexOfIgnoreCase(String str, int find) {
		return str.toLowerCase().indexOf(Character.toLowerCase((char) find));
	}

	public static int indexOfIgnoreCase(String str, int find, int start) {
		return str.toLowerCase().indexOf(Character.toLowerCase((char) find),
				start);
	}

	public static int indexOfIgnoreCase(String str, String find) {
		return str.toLowerCase().indexOf(find.toLowerCase());
	}

	public static int indexOfIgnoreCase(String str, String find, int start) {
		return str.toLowerCase().indexOf(find.toLowerCase(), start);
	}

	public static int lastIndexOfIgnoreCase(String str, int find) {
		return str.toLowerCase()
				.lastIndexOf(Character.toLowerCase((char) find));
	}

	public static int lastIndexOfIgnoreCase(String str, int find, int start) {
		return str.toLowerCase().lastIndexOf(
				Character.toLowerCase((char) find), start);
	}

	public static int lastIndexOfIgnoreCase(String str, String find) {
		return str.toLowerCase().lastIndexOf(find.toLowerCase());
	}

	public static int lastIndexOfIgnoreCase(String str, String find, int start) {
		return str.toLowerCase().lastIndexOf(find.toLowerCase(), start);
	}

	public static int occurencesOf(String str, char ch) {
		char[] s = new char[str.length()];
		str.getChars(0, s.length, s, 0);
		int count = 0;

		for (int i = 0; i < s.length; i++) {
			if (s[i] == ch) {
				count++;
			}
		}

		return count;
	}

	public static int occurencesOfIgnoreCase(String str, char ch) {
		return occurencesOf(str.toLowerCase(), Character.toLowerCase(ch));
	}

	public static final String replace(String str, String oldChars,
			String newChars) {
		int len = newChars.length();
		int pos = str.indexOf(oldChars);
		int lastPos = pos;

		while (pos > -1) {
			String firstPart = str.substring(0, pos);
			String lastPart = str.substring(pos + oldChars.length(), str
					.length());
			str = firstPart + newChars + lastPart;
			lastPos = pos + len;
			pos = str.indexOf(oldChars, lastPos);
		}

		return str;
	}

	public static String replaceIgnoreCase(String str, String oldChars,
			String newChars) {
		String lowerStr = str.toLowerCase();
		int len = newChars.length();
		int pos = lowerStr.indexOf(oldChars);
		int lastPos = pos;

		while (pos > -1) {
			String firstPart = str.substring(0, pos);
			String lastPart = str.substring(pos + oldChars.length(), str
					.length());
			str = firstPart + newChars + lastPart;
			lastPos = pos + len;

			pos = str.indexOf(oldChars, lastPos);
		}

		return str;
	}

	public static boolean startsWithIgnoreCase(String str, String prefix) {
		return str.toLowerCase().startsWith(prefix.toLowerCase());
	}

	public static boolean startsWithIgnoreCase(String str, String prefix,
			int start) {
		return str.toLowerCase().startsWith(prefix.toLowerCase(), start);
	}

	public static String stackToString(Throwable e) {
		try {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			return "------\r\n" + sw.toString() + "------\r\n";
		} catch (Exception e2) {
		}
		return "(bad stack2string) " + e.getMessage();
	}

	public static String stackToString(Exception e) {
		try {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			return "------\r\n" + sw.toString() + "------\r\n";
		} catch (Exception e2) {
		}
		return "(bad stack2string) " + e.getMessage();
	}

	public static String byteArrayToString(byte[] ba) {
		int length = ba.length;
		char[] buf = new char[length * 2];
		int i = 0;
		for (int j = 0; i < length;) {
			int k = ba[(i++)];
			buf[(j++)] = HEX_DIGITS[(k >>> 4 & 0xF)];
			buf[(j++)] = HEX_DIGITS[(k & 0xF)];
		}
		return new String(buf);
	}

	public static byte[] hexFromString(String hex) {
		int len = hex.length();
		byte[] buf = new byte[(len + 1) / 2];

		int i = 0;
		int j = 0;
		if (len % 2 == 1) {
			buf[(j++)] = ((byte) fromDigit(hex.charAt(i++)));
		}
		while (i < len) {
			buf[(j++)] = ((byte) (fromDigit(hex.charAt(i++)) << 4 | fromDigit(hex
					.charAt(i++))));
		}

		return buf;
	}

	public static int fromDigit(char ch) {
		if ((ch >= '0') && (ch <= '9'))
			return ch - '0';
		if ((ch >= 'A') && (ch <= 'F'))
			return ch - 'A' + 10;
		if ((ch >= 'a') && (ch <= 'f')) {
			return ch - 'a' + 10;
		}
		throw new IllegalArgumentException("invalid hex digit '" + ch + "'");
	}

	public static String escapeXMLstr(String input) {
		if (input == null) {
			return null;
		}
		StringBuffer output = new StringBuffer("");
		int len = input.length();
		for (int i = 0; i < len; i++) {
			char ch = input.charAt(i);
			if (ch == '&')
				output.append("&amp;");
			else if (ch == '<')
				output.append("&lt;");
			else if (ch == '>')
				output.append("&gt;");
			else if (ch == '\'')
				output.append("&apos;");
			else if (ch == '"')
				output.append("&quot;");
			else {
				output.append(ch);
			}
		}
		return output.toString();
	}

	public static String escapeHTMLstr(String input) {
		if (input == null) {
			return null;
		}
		StringBuffer output = new StringBuffer("");
		int len = input.length();
		for (int i = 0; i < len; i++) {
			char ch = input.charAt(i);
			if (ch == '&')
				output.append("&amp;");
			else if (ch == '<')
				output.append("&lt;");
			else if (ch == '>')
				output.append("&gt;");
			else if (ch == '"')
				output.append("&quot;");
			else {
				output.append(ch);
			}
		}
		return output.toString();
	}

	public static String headCharUpperCase(String str) {
		if ((str == null) || ("".equals(str)))
			return str;
		String headChar = str.substring(0, 1).toUpperCase();
		if (str.length() == 1)
			return headChar;
		return headChar + str.substring(1, str.length());
	}

	public static String headCharLowerCase(String str) {
		if ((str == null) || ("".equals(str)))
			return str;
		String headChar = str.substring(0, 1).toLowerCase();
		if (str.length() == 1)
			return headChar;
		return headChar + str.substring(1, str.length());
	}

	public static String objToString(Object obj) {
		try {
			ByteArrayOutputStream byteOS = new ByteArrayOutputStream(2048);
			ObjectOutputStream objectOS = new ObjectOutputStream(byteOS);
			objectOS.writeObject(obj);
			objectOS.flush();
			return Base64Encoder.byteArrayToBase64(byteOS.toByteArray());
		} catch (Exception ex) {
		}
		return null;
	}

	public static Object strToObject(String str) {
		try {
			ObjectInputStream objectIN = new ObjectInputStream(
					new ByteArrayInputStream(Base64Encoder
							.base64ToByteArray(str)));

			return objectIN.readObject();
		} catch (Exception ex) {
		}
		return null;
	}

	public static String left(String str, int n) {
		return str.substring(str.length() - n);
	}

	public static String right(String str, int n) {
		return str.substring(str.length() - n, str.length());
	}

	public static boolean isJavaIdentifier(String s) {
		if (isEmpty(s)) {
			return false;
		}

		char[] chars = s.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if ((!Character.isJavaIdentifierPart(chars[i]))
					|| ((i == 0) && (!Character.isJavaIdentifierStart(chars[i])))) {
				return false;
			}
		}
		if (Arrays.asList(JAVA_KEYWORDS).contains(s)) {
			return false;
		}
		return true;
	}

	public static boolean isJavaClassName(String name) {
		if (isEmpty(name))
			return true;
		if ((name.startsWith(" ")) || (name.startsWith("."))
				|| (name.endsWith(" ")) || (name.endsWith("."))) {
			return false;
		}

		String[] pks = split(name, ".");
		for (int i = 0; i < pks.length; i++) {
			if (!isJavaIdentifier(pks[i])) {
				return false;
			}
		}

		return true;
	}

	public static boolean isKsqlIdentifierPart(String s) {
		if (!isEmpty(s)) {
			char[] chars = s.toCharArray();
			for (int i = 0; i < chars.length; i++) {
				if (!Character.isJavaIdentifierPart(chars[i]))
					return false;
			}
		}
		return true;
	}

	public static boolean isKsqlIdentifier(String s) {
		if (isEmpty(s)) {
			return false;
		}

		char[] chars = s.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if ((!Character.isJavaIdentifierPart(chars[i]))
					|| ((i == 0) && (!Character.isJavaIdentifierStart(chars[i])))
					|| ((i == 0) && (chars[i] == '$'))) {
				return false;
			}

		}

		return true;
	}

	public static boolean isFileName(String name) {
		if ((isEmpty(name)) || (name.indexOf('<') > -1)
				|| (name.indexOf('*') > -1) || (name.indexOf('/') > -1)
				|| (name.indexOf(':') > -1) || (name.indexOf('?') > -1)
				|| (name.indexOf('\\') > -1) || (name.indexOf('"') > -1)) {
			return false;
		}
		return true;
	}

	public static String arrayToString(Object[] objArray, String operator) {
		if ((objArray != null) && (objArray.length > 0)) {
			StringBuffer sb = new StringBuffer();

			for (int i = 0; i < objArray.length; i++) {
				sb.append(objArray[i] + operator);
			}

			if ((operator != null) && (operator.length() > 0)) {
				if (sb.indexOf(operator) == 0) {
					sb.delete(0, operator.length());
				} else if (sb.lastIndexOf(operator) == sb.length()
						- operator.length()) {
					sb.delete(sb.length() - operator.length(), sb.length());
				}
			}

			return sb.toString();
		}

		return null;
	}

	public static String[] stringArrayAppend(String[] array, String appendObj) {
		if (array == null) {
			array = new String[0];
		}

		String[] newArray = new String[array.length + 1];

		System.arraycopy(array, 0, newArray, 0, array.length);

		newArray[(newArray.length - 1)] = appendObj;

		return newArray;
	}

	public static String trim(String str) {
		return str != null ? str.trim() : null;
	}

	public static String addBracket(String str) {
		if (isEmpty(str)) {
			return str;
		}
		return "(" + str + ")";
	}

	public static String abbreviate(String txt, int len) {
		String result = "";

		String[] str = splitByUpperCaseChar(txt);
		if (str.length < len) {
			String abb = metaphone(str[0], len - str.length + 1);

			if (abb.length() == 0) {
				abb = str[0].substring(0, len - str.length + 1).toUpperCase();
			}

			abb = abb.substring(0, 1) + abb.substring(1).toLowerCase();
			result = result + abb;
			for (int i = 1; i < str.length; i++)
				result = result + str[i].charAt(0);
		} else {
			for (int i = 0; i < len; i++) {
				result = result + str[i].charAt(0);
			}
		}
		return result;
	}

	private static String metaphone(String txt, int maxCodeLen) {
		if ((txt == null) || (txt.length() == 0)) {
			return "";
		}

		if (maxCodeLen <= 0)
			return "";
		if (maxCodeLen > txt.length()) {
			return txt;
		}

		if (txt.length() == 1) {
			return txt.toUpperCase();
		}

		boolean hard = false;
		char[] inwd = txt.toUpperCase().toCharArray();

		StringBuffer local = new StringBuffer();
		StringBuffer code = new StringBuffer();

		switch (inwd[0]) {
		case 'G':
		case 'K':
		case 'P':
			if (inwd[1] == 'N')
				local.append(inwd, 1, inwd.length - 1);
			else {
				local.append(inwd);
			}
			break;
		case 'A':
			if (inwd[1] == 'E')
				local.append(inwd, 1, inwd.length - 1);
			else {
				local.append(inwd);
			}
			break;
		case 'W':
			if (inwd[1] == 'R') {
				local.append(inwd, 1, inwd.length - 1);
			} else if (inwd[1] == 'H') {
				local.append(inwd, 1, inwd.length - 1);
				local.setCharAt(0, 'W');
			} else {
				local.append(inwd);
			}
			break;
		case 'X':
			inwd[0] = 'S';
			local.append(inwd);
			break;
		default:
			local.append(inwd);
		}

		int wdsz = local.length();
		int n = 0;

		while ((code.length() < maxCodeLen) && (n < wdsz)) {
			char symb = local.charAt(n);

			if ((symb != 'C') && (isPreviousChar(local, n, symb))) {
				n++;
			} else {
				switch (symb) {
				case 'A':
				case 'E':
				case 'I':
				case 'O':
				case 'U':
					if (n == 0)
						code.append(symb);
					break;
				case 'B':
					if ((!isPreviousChar(local, n, 'M'))
							|| (!isLastChar(wdsz, n))) {
						code.append(symb);
					}
					break;
				case 'C':
					if ((!isPreviousChar(local, n, 'S'))
							|| (isLastChar(wdsz, n))
							|| (FRONTV.indexOf(local.charAt(n + 1)) < 0)) {
						if (regionMatch(local, n, "CIA")) {
							code.append('X');
						} else if ((!isLastChar(wdsz, n))
								&& (FRONTV.indexOf(local.charAt(n + 1)) >= 0)) {
							code.append('S');
						} else if ((isPreviousChar(local, n, 'S'))
								&& (isNextChar(local, n, 'H'))) {
							code.append('K');
						} else if (isNextChar(local, n, 'H')) {
							if ((n == 0) && (wdsz >= 3) && (isVowel(local, 2))) {
								code.append('K');
							} else
								code.append('X');
						} else
							code.append('C');
					}
					break;
				case 'D':
					if ((!isLastChar(wdsz, n + 1))
							&& (isNextChar(local, n, 'G'))
							&& (FRONTV.indexOf(local.charAt(n + 2)) >= 0)) {
						code.append('J');
						n += 2;
					} else {
						code.append('D');
					}
					break;
				case 'G':
					if ((!isLastChar(wdsz, n + 1))
							|| (!isNextChar(local, n, 'H'))) {
						if ((isLastChar(wdsz, n + 1))
								|| (!isNextChar(local, n, 'H'))
								|| (isVowel(local, n + 2))) {
							if ((n <= 0)
									|| ((!regionMatch(local, n, "GN")) && (!regionMatch(
											local, n, "GNED")))) {
								if (isPreviousChar(local, n, 'G'))
									hard = true;
								else {
									hard = false;
								}
								if ((!isLastChar(wdsz, n))
										&& (FRONTV.indexOf(local.charAt(n + 1)) >= 0)
										&& (!hard)) {
									code.append('J');
								} else
									code.append('G');
							}
						}
					}
					break;
				case 'H':
					if (!isLastChar(wdsz, n)) {
						if ((n <= 0)
								|| (VARSON.indexOf(local.charAt(n - 1)) < 0)) {
							if (isVowel(local, n + 1))
								code.append('H');
						}
					}
					break;
				case '0':
				case '1':
				case '2':
				case '3':
				case '4':
				case '5':
				case '6':
				case '7':
				case '8':
				case '9':
				case 'F':
				case 'J':
				case 'L':
				case 'M':
				case 'N':
				case 'R':
					code.append(symb);
					break;
				case 'K':
					if (n > 0) {
						if (!isPreviousChar(local, n, 'C'))
							code.append(symb);
					} else {
						code.append(symb);
					}
					break;
				case 'P':
					code.append(symb);
					break;
				case 'Q':
					code.append('Q');
					break;
				case 'S':
					if ((regionMatch(local, n, "SH"))
							|| (regionMatch(local, n, "SIO"))
							|| (regionMatch(local, n, "SIA"))) {
						code.append('X');
					} else
						code.append('S');

					break;
				case 'T':
					if ((regionMatch(local, n, "TIA"))
							|| (regionMatch(local, n, "TIO"))) {
						code.append('X');
					} else if (!regionMatch(local, n, "TCH")) {
						if (!regionMatch(local, n, "TH")) {
							code.append('T');
						}
					}
					break;
				case 'V':
					code.append('V');
					break;
				case 'W':
				case 'Y':
					if ((!isLastChar(wdsz, n)) && (isVowel(local, n + 1))) {
						code.append(symb);
					}
					break;
				case 'X':
					code.append('X');
					break;
				case 'Z':
					code.append('Z');
				case ':':
				case ';':
				case '<':
				case '=':
				case '>':
				case '?':
				case '@':
				}
				n++;
			}
			if (code.length() > maxCodeLen) {
				code.setLength(maxCodeLen);
			}
		}
		return code.toString();
	}

	private static String[] splitByUpperCaseChar(String line) {
		if (line == null)
			return null;
		line = line.trim();
		if (line.length() == 0) {
			return new String[] { "" };
		}
		List v = new ArrayList();
		int pos = 0;
		for (int i = 0; i < line.length(); i++) {
			char ch = line.charAt(i);

			if (((ch >= 'A') && (ch <= 'Z')) || ((ch >= '0') && (ch <= '9'))) {
				String str = line.substring(pos, i).trim();
				if (str.length() > 0)
					v.add(str);
				pos = i;
			}
		}
		v.add(line.substring(pos).trim());
		return (String[]) v.toArray(new String[v.size()]);
	}

	private static final boolean isVowel(StringBuffer string, int index) {
		return VOWELS.indexOf(string.charAt(index)) >= 0;
	}

	private static boolean isPreviousChar(StringBuffer string, int index, char c) {
		boolean matches = false;
		if ((index > 0) && (index < string.length())) {
			matches = string.charAt(index - 1) == c;
		}
		return matches;
	}

	private static boolean isNextChar(StringBuffer string, int index, char c) {
		boolean matches = false;
		if ((index >= 0) && (index < string.length() - 1)) {
			matches = string.charAt(index + 1) == c;
		}
		return matches;
	}

	private static boolean regionMatch(StringBuffer string, int index,
			String test) {
		boolean matches = false;
		if ((index >= 0) && (index + test.length() - 1 < string.length())) {
			String substring = string.substring(index, index + test.length());
			matches = substring.equals(test);
		}
		return matches;
	}

	private static final boolean isLastChar(int wdsz, int n) {
		return n + 1 == wdsz;
	}

	public static String[] fastSplit(String line, int seperator) {
		if ((line == null) || (line.length() == 0)) {
			return null;
		}
		line = line.trim();

		int[] pos = new int[9];
		int iTimes = calcSeperatorTimes(line, seperator, pos);
		String[] arr = new String[iTimes + 1];

		if (iTimes <= 0) {
			arr[0] = line;
		} else if (iTimes > 9) {
			int times = 0;
			int i = 0;
			int j;
			while ((j = line.indexOf(seperator, i)) >= 0) {
				arr[times] = line.substring(i, j).trim();
				i = j + 1;
				times++;
			}
			arr[times] = line.substring(i).trim();
		} else {
			arr[0] = line.substring(0, pos[0]).trim();
			for (int i = 0; i < iTimes - 1; i++) {
				arr[(i + 1)] = line.substring(pos[i] + 1, pos[(i + 1)]).trim();
			}
			arr[iTimes] = line.substring(pos[(iTimes - 1)] + 1).trim();
		}
		return arr;
	}

	private static int calcSeperatorTimes(String src, int seperator, int[] pos) {
		if ((src == null) || (src.equals("")))
			return 0;
		int times = 0;
		int i = 0;
		int j;
		while ((j = src.indexOf(seperator, i)) >= 0) {
			if (times < 9)
				pos[times] = j;
			i = j + 1;
			times++;
		}
		return times;
	}

	public static String[] fastSplit(String line, String seperator) {
		if ((line == null) || (line.length() == 0)) {
			return null;
		}
		line = line.trim();

		int[] pos = new int[9];
		int iTimes = calcSeperatorTimes(line, seperator, pos);
		String[] arr = new String[iTimes + 1];

		if (iTimes <= 0) {
			arr[0] = line;
		} else if (iTimes > 9) {
			int times = 0;
			int i = 0;
			int j;
			while ((j = line.indexOf(seperator, i)) >= 0) {
				arr[times] = line.substring(i, j).trim();
				i = j + 1;
				times++;
			}
			arr[times] = line.substring(i).trim();
		} else {
			arr[0] = line.substring(0, pos[0]).trim();
			for (int i = 0; i < iTimes - 1; i++) {
				arr[(i + 1)] = line.substring(pos[i] + 1, pos[(i + 1)]).trim();
			}
			arr[iTimes] = line.substring(pos[(iTimes - 1)] + 1).trim();
		}
		return arr;
	}

	private static int calcSeperatorTimes(String src, String seperator,
			int[] pos) {
		if ((src == null) || (src.equals("")))
			return 0;
		int times = 0;
		int i = 0;
		int j;
		while ((j = src.indexOf(seperator, i)) >= 0) {
			if (times < 9)
				pos[times] = j;
			i = j + 1;
			times++;
		}
		return times;
	}
	
	public static String reCodeUUID(String uuid){
		if(BaseUtil.isEmpty(uuid)) return uuid;
		return uuid.replaceAll(" ", "+");
	}
}
