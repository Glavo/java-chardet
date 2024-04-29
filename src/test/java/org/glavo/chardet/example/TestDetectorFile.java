package org.glavo.chardet.example;

import org.glavo.chardet.DetectedCharset;
import org.glavo.chardet.UniversalDetector;

import java.nio.file.Path;
import java.nio.file.Paths;

public class TestDetectorFile {

	public static void main (String[] args) throws java.io.IOException {
		if (args.length != 1) {
			System.err.println("Usage: java TestDetectorFile FILENAME");
			System.exit(1);
		}
		Path file = Paths.get(args[0]);
		DetectedCharset encoding = UniversalDetector.detectCharset(file);
		if (encoding != null) {
			System.out.println("Detected encoding = " + encoding);
		} else {
			System.out.println("No encoding detected.");
		}
	}
}
