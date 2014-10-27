package huawei.test4;

import java.util.Scanner;

public class Main {
	public static void main(String args[]) {
			Scanner cin = new Scanner(System.in);
			String str ="";
			str = cin.nextLine();

			// int int2 = -123400560;
			Main test = new Main();
			System.out.println(test.getDivisibleInt(str));
	}

	public String getDivisibleInt(String str) {
		if (null != str && !str.equals("") && str.length() > 0) {
			String[] array = str.split(",");
			int len = array.length;
			StringBuffer buffer = new StringBuffer();
			for (int i = 0; i < len; i++) {
				int pos = i;
				for (int j = 0; j < len; j++) {
					if (pos != j) { // 不是自身位置
						int indexA = 0;
						int indexB = 0;
						try {
							indexA = Integer.parseInt(array[i].trim());
							indexB = Integer.parseInt(array[j].trim());
						} catch (java.lang.NumberFormatException e) {
							return "";
						}
						int rem = indexA % indexB;
						if (rem == 0) { // 余数为0，可以被整除
							if (buffer.length() > 0) {
								buffer.append(" " + array[i]);
							} else {
								buffer.append(array[i]);
							}
							break;
						}
					}
				}
			}
			return buffer.toString();
		} else {
			return "";
		}
	}

}
