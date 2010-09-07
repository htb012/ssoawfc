package test;

import java.io.File;

public class TestFilePath {

	public TestFilePath() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file = new File("");
		System.err.println(file.getAbsolutePath());
	}

}
