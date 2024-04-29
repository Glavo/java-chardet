package org.glavo.chardet;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BOMTest {

	private static final String TEST_STRING = "========================================================================";

	@Test
	public void testUTF8() throws IOException {
		assertEquals(TEST_STRING, getFirstLine("utf8.txt"));
	}

	@Test
	public void testUTF8N() throws IOException {
		assertEquals(TEST_STRING, getFirstLine("utf8n.txt"));
	}

	@Test
	public void testUTF16LE() throws IOException {
		assertEquals(TEST_STRING, getFirstLine("utf16le.txt"));
	}

	private String getFirstLine(String testFileName) throws IOException {
		String fileName = "src/test/resources/" + testFileName;
		try (BufferedReader reader = ReaderFactory.createBufferedReader(Paths.get(fileName))) {
			// return first line
			return reader.readLine();
		}
	}
}
