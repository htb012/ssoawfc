/**
 * 
 */
package jp.co.pegatron.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Hashtable;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import com.ssoserver.utils.FormatDate;
import com.ssoserver.utils.GetPropMessage;

/**
 * 文件上传工具，将上传的文件保存在指定的文件夹内。
 * 
 * @author HTB
 * 
 */
public class FileSvc {
	public static Logger logger;
	public static FileSvc uploadSvc;
	private static String fileSafePath;
	private static int maxFileSize;
	static {
		fileSafePath = GetPropMessage.getMessage("fileSafePath");
		maxFileSize = new Integer(GetPropMessage.getMessage("maxFileSize"))
				.intValue();
		logger = Logger.getLogger(FileSvc.class);
	}

	/**
	 * 
	 */
	private FileSvc() {
		// TODO Auto-generated constructor stub
	}

	public static String safeFiles(ActionForm form) throws Exception {
		StringBuffer fileNameStr = new StringBuffer();
		Hashtable filetable = form.getMultipartRequestHandler()
				.getFileElements();
		int readSize = 0;
		byte[] write = new byte[1024];
		for (Enumeration e = filetable.keys(); e.hasMoreElements();) {
			String key = (String) e.nextElement();
			String datetimeKey = "";
			FormFile file = (FormFile) filetable.get(key);
			if (file.getFileSize() <= maxFileSize
					&& !"".equals(file.getFileName())) {
				logger.debug("safe a file name is:" + file.getFileName()
						+ " to：" + fileSafePath);
				datetimeKey = FormatDate.parseNowDateToString();
				FileOutputStream fileStream = new FileOutputStream(fileSafePath
						+ datetimeKey + file.getFileName());
				InputStream is = file.getInputStream();
				while ((readSize = is.read(write, 0, 1024)) != -1) {
					fileStream.write(write, 0, readSize);
				}
				fileStream.close();
				fileNameStr.append(datetimeKey + file.getFileName() + ";");
			} else {
				logger.info("The file(" + file.getFileName() + ")size is: "
						+ file.getFileSize() + " cant not saved");
			}
		}
		return fileNameStr.toString();
	}

	/**
	 * 在更新的时候删除用户指定删除的若干个文件。保存文件的字符串。
	 * 
	 * @param files
	 *            原有的文件名字符串
	 * @param delFileStr
	 *            要删除的文件名字符串
	 * @return 新的文件名字符串
	 */
	public static String updateFile(String files, String delFileStr) {
		if (delFileStr != null && !"".equals(delFileStr)) {
			File file = null;
			String[] delFileArray = delFileStr.split(";");
			for (int i = 0; i < delFileArray.length; i++) {
				delFileArray[i] = delFileArray[i].replaceAll("uploadfile/", "");
				files = files.replaceAll(delFileArray[i] + ";", "");
				deleteSingleFile(fileSafePath + delFileArray[i]);
			}
		}
		return files;
	}

	/**
	 * 从文件名字符串中删除所有的文件
	 * 
	 * @param filesStr
	 */
	public static void deleteFile(String filesStr) {
		if (filesStr != null && !"".equals(filesStr)) {
			String[] fileNames = filesStr.split(";");
			for (int i = 0; i < fileNames.length; i++) {
				deleteSingleFile(fileNames[i]);
			}
		}
	}

	/**
	 * 删除单个文件
	 * 
	 * @param fileStr
	 * @return
	 */
	public static boolean deleteSingleFile(String fileStr) {
		File file = new File(fileStr);
		return file.delete();
	}
}
