package com.issareem.main;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 
 * @author Reem Issa
 * @class_name Main.java
 * @class_description This class get's a path for a text file and provides the
 *                    count of characters and words.
 * @create_date Aug 6, 2023
 * @last_Update Aug 6, 2023
 */
public class Main {

	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Usage: java WordCountTool <inputFilePath>");
			return;
		}
		String inputFilePath = args[0];
		Path path = Paths.get(inputFilePath);

		Long wordCount = 0L;
		Long letterCount = 0L;

		try (Stream<String> lines = Files.lines(path)) {

			Map<String, Long> wordCounts = lines.flatMap(line -> Stream.of(line.split("\\s+")))
					.collect(Collectors.groupingBy(String::toString, Collectors.counting()));

			for (String key : wordCounts.keySet()) {
				wordCount += wordCounts.get(key);
				for (char letter : key.toCharArray()) {
					letterCount += 1;
				}
			}
			System.out.println("Number of letters : \t " + letterCount);
			System.out.println("Number of words : \t " + wordCount);

		} catch (IOException e) {
			System.out.println("An error occurred while reading the file: " + e.getMessage());
		}
	}
}
