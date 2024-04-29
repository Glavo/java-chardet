package org.glavo.chardet.example;

import org.glavo.chardet.ReaderFactory;

import java.nio.file.Paths;

public class TestCreateReaderFromFile {
	
	public static void main (String[] args) throws java.io.IOException {
		if (args.length != 1) {
			System.err.println("Usage: java TestCreateReaderFromFile FILENAME");
			System.exit(1);
		}

		java.io.Reader reader = null;
        //noinspection TryFinallyCanBeTryWithResources
        try {
			reader = ReaderFactory.createBufferedReader(Paths.get(args[0]));
			// Do whatever you want with the reader
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
	}

}
