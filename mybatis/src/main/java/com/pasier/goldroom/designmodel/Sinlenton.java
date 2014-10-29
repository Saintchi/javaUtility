package com.pasier.goldroom.designmodel;

import java.math.BigInteger;

public class Sinlenton {
	public static void main(String[] args) {
		System.out.println("1111111");

		// 加减乘除
		System.out.println("xxxxxxxxxx="
				+ BigInteger.TEN.add(new BigInteger("1")));
		int total = 200;
		
		int num = total / 500;
		int remain = total % 500;
		if (num > 0 && remain == 0) { // 大于1页，余数为0
			num--;
		}
		System.out.println("num= " + num);
	}

}
