/**
 * 
 */
package jp.co.pegatron.common;

/**
 * @author HTB
 * 
 */
public class RepOrdPareseUtil {
	public static RepOrdPareseUtil repOrdPareseUtil;
	public static String[] confimStrs;
	public static String[] warrantyStrs;

	static {
		confimStrs = new String[] { "P1:パスワード確認", "P2:有償修理確認", "P3:症状再現せず",
				"P4:リカバリー確認", "P5:部品到着待ち", "P6:其他連絡事項", "P7:保証期間以外" };
		warrantyStrs = new String[] { "IW", "OOW", "本体IW/BAOOW", "CID",
				"OOW/CID" };
	}

	/**
	 * 
	 */
	private RepOrdPareseUtil() {

	}

	public static RepOrdPareseUtil getInstance() {
		if (repOrdPareseUtil == null) {
			repOrdPareseUtil = new RepOrdPareseUtil();
		}
		return repOrdPareseUtil;
	}

	public static String parseConfimStr(String confimStr) {
		if (confimStr != null && !"".equals(confimStr)) {
			StringBuffer sb = new StringBuffer();
			for (int i = confimStr.length() - 1; i >= 0; i--) {
				if ('1' == confimStr.charAt(i)) {
					sb.append(confimStrs[confimStr.length() - i - 1] + "<br/>");
				}
			}
			return sb.toString();
		}
		return "";
	}

	public static String parseWarrantyStr(String warrantyStr) {
		if (warrantyStr != null && !"".equals(warrantyStr)) {
			StringBuffer sb = new StringBuffer();
			for (int i = warrantyStr.length() - 1; i >= 0; i--) {
				if ('1' == warrantyStr.charAt(i)) {
					sb.append(warrantyStrs[warrantyStr.length() - i - 1]
							+ "<br/>");
				}
			}
			return sb.toString();
		}
		return "";
	}
}
