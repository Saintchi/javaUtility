package huawei;

import java.util.ArrayList;
import java.util.List;

public class HuaWeiTest02 {

	public static void main(String[] args) {
		// 输入：len=4 str="3,1,2,4" m=7
		// 输出：2,3,1,4
		System.out.println(getOutString(4, "3,1,2,4", 7));

	}

	public static String getOutString(int len, String str, int m) {
		String temp = "";
		String[] array = str.split(",");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < len; i++) {
			list.add(array[i]);
		}

		for (int i = 0; i < len; i++) {
			int p = (m - 1) % list.size();
			temp += list.get(p);
			m = Integer.valueOf(list.get(p));
			list.remove(p);
		}

		return temp;
	}

}
