package com.gkhy.msmservice.utils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * get random number
 * 
 * @author leo
 *
 */
public class RandomUtil {

	private static final Random random = new Random();

	private static final DecimalFormat DECIMAL_FORMAT4 = new DecimalFormat("0000");

	private static final DecimalFormat DECIMAL_FORMAT6 = new DecimalFormat("000000");

	public static String getFourBitRandom() {
		return DECIMAL_FORMAT4.format(random.nextInt(10000));
	}

	public static String getSixBitRandom() {
		return DECIMAL_FORMAT6.format(random.nextInt(1000000));
	}

	/**
	 * Given an array, extract n data
	 * @param list
	 * @param n
	 * @return
	 */
	public static ArrayList getRandom(List list, int n) {

		Random random = new Random();

		HashMap<Object, Object> hashMap = new HashMap<Object, Object>();

		// Generate random numbers and store them in HashMap
		for (int i = 0; i < list.size(); i++) {

			int number = random.nextInt(100) + 1;

			hashMap.put(number, i);
		}

		// Import array from HashMap
		Object[] robjs = hashMap.values().toArray();

		ArrayList r = new ArrayList();

		// iterate through the array and print the data
		for (int i = 0; i < n; i++) {
			r.add(list.get((int) robjs[i]));
			System.out.print(list.get((int) robjs[i]) + "\t");
		}
		System.out.print("\n");
		return r;
	}
}
