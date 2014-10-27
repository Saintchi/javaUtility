package huawei.test5;

import java.util.Scanner;

public class Main {

	public static void main(String args[]) {
		Scanner cin = new Scanner(System.in);
		int str =0;
		str = cin.nextInt();

		// int int2 = -123400560;
		Main test = new Main();
		System.out.println(test.getConvertInt(str));
	}

	/**
	 * @param 输入为一个int型整数
	 *            ，按照从右向左的阅读顺序，返回一个打印的数字中不含重复数字的新的整数。
	 *            整数中间和最后的数字0，不输出。如果是负数，转换后继续是负数。
	 * @return
	 */
	public static long getConvertInt(long input) {
		boolean isNegative = false;
		if (input == 0) {
			return 0;
		}
		if (input > 0 && input <= Integer.MAX_VALUE) {
			isNegative = false; // 正数
		} else if (input < 0 && input >= Integer.MIN_VALUE) {
			isNegative = true; // 负数
		}else{
			return 0;
		}
		input = Math.abs(input); // 取绝对值
		String str = String.valueOf(input).replaceAll("$+0", "");
		char[] chars = str.toCharArray();
		StringBuffer buffer = new StringBuffer();
		if (isNegative) { // 如果是负数，前面添加一个负号
			buffer.append("-");
		}
		for (int i = chars.length - 1; i >= 0; i--) {
			if (str.lastIndexOf(chars[i]) == i) {
				buffer.append(chars[i]);
			}
		}

		return Long.valueOf(buffer.toString());
	}

}
