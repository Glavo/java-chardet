package org.glavo.chardet;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Bug20LatinDetectedAsMaccyrillicTest {

	private static final String TEST_STRING = "ÄÜÖßäöü,Name1ÄÜÖßäöü,Name2ÄÜÖßäöü,Name3ÄÜÖßäöü,StreetÄÜÖßäöü,MÄÜÖßäöü,DE,80080,München,ContactÄÜÖßäöü,+49(0)ÄÜÖßäöü,ÄÜÖßäöü@gls-itservices.com,CommentÄÜÖßäöü,+49,(0)98,765,432,BlÄÜÖßäöü";

	@Test
	@Disabled("Bug not fixed yet")
	public void testFile() throws IOException {
		Path testFile = Paths.get("src/test/resources/bug20-example-latin.txt");
		DetectedCharset originalEncoding = UniversalDetector.detectCharset(testFile);
		assertEquals(DetectedCharset.WINDOWS_1252, originalEncoding);
	}

	@Test
	@Disabled("Bug not fixed yet")
	public void testLatin() {
		UniversalDetector detector = new UniversalDetector();
		detector.handleData(TEST_STRING.getBytes(Charset.forName("WINDOWS-1252")));
		detector.dataEnd();
		assertEquals(DetectedCharset.WINDOWS_1252, detector.getDetectedCharset());

	}

	@Test
	public void testUTF8() {
		UniversalDetector detector = new UniversalDetector();
		detector.handleData(TEST_STRING.getBytes(StandardCharsets.UTF_8));
		detector.dataEnd();
		assertEquals(DetectedCharset.UTF_8, detector.getDetectedCharset());
	}

}
