package com.pasier.goldroom.machineexam;

public class CharTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String str = "hELLO1";
		String str2 = "hELLO";
		CharTest test = new CharTest();
		System.out.println(test.getStringJudge(str));
		System.out.println(test.getCharSensitive(str));

	}

	/**
	 * 使用Character判断字母大小写
	 * 
	 * @param str
	 * @return
	 */
	public int getStringJudge(String str) {
		int ret = 1; // 默认通过
		if (null != str && str.length() > 0 && str.length() <= 256) { // 长度判断
			char[] charArray = str.toCharArray();
			if (Character.isLowerCase(charArray[0])) { // 首字母判断是否大写
				for (int i = 1; i < str.length(); i++) { // 从第二个字母开始
					if (!Character.isUpperCase(charArray[i])) {
						// 不满足条件
						ret = 0;
						break;
					}
				}
			} else {
				ret = 0;
			}

		} else {
			ret = 0;
		}

		return ret;
	}

	public int getCharSensitive(String str) {
		int ret = 0;
		if (null != str && str.length() > 0 && str.length() <= 256) { // 长度判断
			String regex = "^[a-z][A-Z]+$"; // 正则表达式，判断首字母小写，非首字母大写
			if (str.matches(regex)) {
				ret = 1;
			}
		}
		return ret;
	}

}
