package org.logan.java.string;

/**
 * desc: String intern() <br/>
 * -
 * 在 JDK 1.7 之后(包括1.7)，字符串常量池已经从方法区移到了堆中。<br/>
 * -
 * intern() 的定义：
 * 如果当前字符串内容存在于字符串常量池，存在的条件是使用 equal() 为 true，也就是内容一样，那直接返回此字符串在常量池的引用；
 * 如果之前不在字符串常量池中，那么在常量池创建一个引用，并且指向堆中已存在的字符串，然后返回常量池中的引用地址。
 * -
 * 根据以上描述，在 JDK 1.7 之后，字符串常量池不一定就是存字符串对象的，还有可能存储的是一个指向堆中地址的引用。
 * -
 * 引用地址：
 * https://my.oschina.net/u/4519772/blog/4255335
 * -
 * time: 2020/6/19 6:06 下午 <br/>
 * author: Logan <br/>
 * since V 1.0 <br/>
 */

class StringInternTest {

	public static void main(String[] args) {

		String s1 = "古时的风筝";
		String s2 = "古时的风筝";
		String a = "古时的";
		String s3 = new String(a + "风筝");
		String s4 = new String(a + "风筝");

		// 1, s1 == s2 返回 true，因为都是字面量声明，全都指向字符串常量池中同一字符串。
		System.out.println("s1 == s2：" + (s1 == s2));

		// 2, s2 == s3 返回 false，因为 new String() 是在堆中新建对象，所以和常量池的常量不相同。
		System.out.println("s2 == s3：" + (s2 == s3));

		// 3, s3 == s4 返回 false，都是在堆中新建对象，所以是两个对象，肯定不相同。
		System.out.println("s3 == s4：" + (s3 == s4));

		// 4, s2 == s3 返回 false，前面虽然调用了 intern() ，但是没有接收返回值，所以不起作用。
		s3.intern();
		System.out.println("s2 == s3：" + (s2 == s3));

		// 5, s2 == s3 返回 true，前面调用了 intern() ，并且返回给了 s3 ，此时 s2、s3 都直接指向常量池的同一个字符串。
		s3 = s3.intern();
		System.out.println("s2 == s3：" + (s2 == s3));

		// 6, s3 == s4 返回 true，和 s3 相同，都指向了常量池同一个字符串。
		s4 = s4.intern();
		System.out.println("s3 == s4：" + (s3 == s4));
	}

}