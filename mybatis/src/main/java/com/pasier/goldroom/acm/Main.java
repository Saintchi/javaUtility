package com.pasier.goldroom.acm;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
	public static void main(String args[]) {
		String mobile = "11110000001";
		String mobile2 = "	2000000";
		Main test = new Main();
		System.out.println(test.getCityNumResult(mobile2));
	}

	public static int getCityNumResult(String mobile) {
		int ret = 0;
		String temp = "";
		if(null!= mobile && !"".equals(mobile)&& mobile.length()>0){
			mobile = mobile.trim();
			temp = remove(mobile,' ');
			temp = temp.replaceAll("\\s*", "");
			temp = temp.replaceAll(" +","");
			temp = temp.replace(" ", "");
			Pattern p = Pattern.compile("(|\\d{3})[1-9]\\d{6}");
			Matcher m = p.matcher(temp);
			if (m.matches()) {
				ret = 1;
			} else {
				ret = 0;
			}
		}
		
		return ret;
	}

	public static String remove(String resource, char ch) {
		StringBuffer buffer = new StringBuffer();
		int position = 0;
		char currentChar;
		while (position < resource.length()) {
			currentChar = resource.charAt(position++);
			if (currentChar != ch) {
				buffer.append(currentChar);
			}
		}
		return buffer.toString();
	}
}
