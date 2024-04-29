package org.glavo.chardet;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TIS620BasicTest {

	@Test
	public void testTIS620() throws IOException {
		assertEquals(DetectedCharset.TIS620, getFileEncoding("tis620.txt"));
	}

	private DetectedCharset getFileEncoding(String testFileName) throws IOException {
		String fileName = "src/test/resources/" + testFileName;
		return UniversalDetector.detectCharset(Paths.get(fileName));
	}
}
