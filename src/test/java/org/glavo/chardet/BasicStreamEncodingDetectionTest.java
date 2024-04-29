package org.glavo.chardet;

import org.junit.jupiter.api.Test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BasicStreamEncodingDetectionTest {

	@Test
	public void testUTF8() throws IOException {
		assertEquals(DetectedCharset.UTF_8, getFileEncoding("utf8.txt"));
	}

	@Test
	public void testUTF8N() throws IOException {
		assertEquals(DetectedCharset.UTF_8, getFileEncoding("utf8n.txt"));
	}

	@Test
	public void testUTF16LE() throws IOException {
		assertEquals(DetectedCharset.UTF_16LE, getFileEncoding("utf16le.txt"));
	}

	@Test
	public void testShiftJis() throws IOException {
		assertEquals(DetectedCharset.SHIFT_JIS, getFileEncoding("shiftjis.txt"));
	}

	@Test
	public void testEUC() throws IOException {
		assertEquals(DetectedCharset.EUC_JP, getFileEncoding("euc.txt"));
	}

	@Test
	public void testISO2022JP() throws IOException {
		assertEquals(DetectedCharset.ISO_2022_JP, getFileEncoding("iso2022jp.txt"));
	}

	@Test
	public void testBIG5() throws IOException {
		assertEquals(DetectedCharset.BIG5, getFileEncoding("big5.txt"));
	}

	@Test
	public void testEUCTW() throws IOException {
		assertEquals(DetectedCharset.EUC_TW, getFileEncoding("euctw.txt"));
	}

	@Test
	public void testEUCKR() throws IOException {
		assertEquals(DetectedCharset.EUC_KR, getFileEncoding("euckr.txt"));
	}

	@Test
	public void testWindows1255() throws IOException {
		assertEquals(DetectedCharset.WINDOWS_1255, getFileEncoding("windows1255.txt"));
	}

	@Test
	public void testUTF8Emoji() throws IOException {
		assertEquals(DetectedCharset.UTF_8, getFileEncoding("utf8n-emoji.txt"));
	}

	private DetectedCharset getFileEncoding(String testFileName) throws IOException {
		File file = new File("src/test/resources/" + testFileName);

		try (EncodingDetectorInputStream edis = new EncodingDetectorInputStream(
				new BufferedInputStream(Files.newInputStream(file.toPath())));
				EncodingDetectorOutputStream edos = new EncodingDetectorOutputStream(
						NullOutputStream.NULL_OUTPUT_STREAM)) {
			byte[] buffer = new byte[1024];
			int read;
			while ((read = edis.read(buffer)) > 0) {
				edos.write(buffer, 0, read);
			}
			edis.close();
			edos.close();
			DetectedCharset encodingRead = edis.getDetectedCharset();
			DetectedCharset encodingWrite = edos.getDetectedCharset();
			assertNotNull(encodingRead);
			assertNotNull(encodingWrite);
			assertEquals(encodingRead, encodingWrite);
			return encodingRead;
		}
	}
}
