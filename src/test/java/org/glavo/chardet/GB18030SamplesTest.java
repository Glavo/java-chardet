package org.glavo.chardet;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GB18030SamplesTest {

	@Test
	public void testGB18030Sample() throws IOException {
		assertEquals(DetectedCharset.GB18030, getFileEncoding("gb2312-sample.txt"));
	}

	@Test
	public void testGBKSample() throws IOException {
		assertEquals(DetectedCharset.GB18030, getFileEncoding("gbk-sample.txt"));
	}

	private DetectedCharset getFileEncoding(String testFileName) throws IOException {
		String fileName = "src/test/resources/" + testFileName;
		return UniversalDetector.detectCharset(Paths.get(fileName));
	}
}
