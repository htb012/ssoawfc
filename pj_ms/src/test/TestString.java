/**
 * 
 */
package test;

/**
 * @author HTB
 *
 */
public class TestString {

	/**
	 * 
	 */
	public TestString() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "uploadfile/2010031719401310920071227-78_1.gif";
		System.err.println(str.replaceAll("uploadfile/", ""));
		System.err.println(str);
		
		System.err.println("".split(";").length);
	}

}
