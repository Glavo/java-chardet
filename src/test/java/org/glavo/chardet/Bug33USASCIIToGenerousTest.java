package org.glavo.chardet;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class Bug33USASCIIToGenerousTest {

	@Test
	@Disabled("Not sure")
	public void testUTF16() throws IOException {
		assertEquals(DetectedCharset.UTF_16BE, detect("ab".getBytes(StandardCharsets.UTF_16BE)));
		assertEquals(DetectedCharset.UTF_16LE, detect("ab".getBytes(StandardCharsets.UTF_16LE)));
	}

	@Test
	public void testZipHeader() throws IOException {
		byte[] zipHeader = new byte[] { 0x50, 0x4b, 0x03, 0x04, 0x14, 0x00, 0x02, 0x00 };
		assertNull(detect(zipHeader));
	}

	private DetectedCharset detect(byte[] data) throws IOException {
		return UniversalDetector.detectCharset(new ByteArrayInputStream(data));
	}
}
