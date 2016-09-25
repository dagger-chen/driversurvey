package com.phicomm.android.driversurvey;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import android.content.Context;
import android.os.Environment;

public class FileExportHelper {

	private Context context;
	private String SDPATH;
	private boolean hasSD = false;

	public FileExportHelper(Context context) {
		this.context = context;
		hasSD = Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED);
		SDPATH = Environment.getExternalStorageDirectory().getPath();
	}

	public File createSDFile(String fileName) throws IOException {
		File file = new File(SDPATH + "//" + fileName);
		if (!file.exists()) {
			file.createNewFile();
		}
		return file;
	}

	public boolean deleteSDFile(String fileName) {
		File file = new File(SDPATH + "//" + fileName);
		if (file == null || !file.exists() || file.isDirectory())
			return false;
		return file.delete();
	}

	public void writeSDFile(String str, String fileName) {
		try {
			FileWriter fw = new FileWriter(SDPATH + "//" + fileName,true);
			fw.write(str);
			fw.flush();
			fw.close();
		} catch (Exception e) {
		}
	}

	public String getSDPATH() {
		return SDPATH;
	}

	public void setSDPATH(String sDPATH) {
		SDPATH = sDPATH;
	}

	public boolean isHasSD() {
		return hasSD;
	}

	public void setHasSD(boolean hasSD) {
		this.hasSD = hasSD;
	}

	
	
	
	
}
