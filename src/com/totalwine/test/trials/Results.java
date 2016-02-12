package com.totalwine.test.trials;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;

import com.totalwine.test.config.ConfigurationFunctions;


public class Results {
	
	@Test
	public void ArchiveResults() throws IOException {
		File file = new File(ConfigurationFunctions.RESULTSPATH+"UATTestResults.html");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
		String nameAppend = sdf.format(file.lastModified());
		File archivedFile =  new File(ConfigurationFunctions.RESULTSPATH+"UATTestResults_"+nameAppend+".html");
		FileUtils.copyFile(file, archivedFile);
		System.out.println("Previous results are stored as UATTestResults_"+nameAppend+".html");
    }
}
