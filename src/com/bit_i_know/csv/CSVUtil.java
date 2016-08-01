package com.bit_i_know.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility to manipulate CSV files.
 * 
 * @author pradeep
 *
 */
public class CSVUtil {
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";
	private static final String FILE_HEADER = "Order Id,Order status,Order details,Shipping Value,Total";

	/**
	 * Create CSV file and add headers.
	 * 
	 * @param fileName
	 * @return
	 */
	public static void createCsvFile(String fileName) {
		try {
			FileWriter fileWriter = new FileWriter(fileName);
			// Add headers
			fileWriter.append(FILE_HEADER.toString());
			fileWriter.append(NEW_LINE_SEPARATOR);
			fileWriter.close();
			System.out.println("Header added successfully.");
		} catch (IOException e) {
			System.out.println("Exception occured  while creating a CSV file.");
			e.printStackTrace();
		}

	}

	/**
	 * Create Csv File If Not Exists
	 * 
	 * @param args
	 */
	public static void createCsvFileIfNotExists(String fileName) {
		File file = new File(fileName);
		if (file.exists()) {
			System.out.println("File already exists. ");
		} else {
			createCsvFile(fileName);
			System.out.println("File " + fileName + " created successfully.");
		}
	}

	/**
	 * Add header value to the csv file.
	 * 
	 * @param fileName
	 */
	public static void addHeader(String fileName) {
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(fileName);
			fileWriter.append(FILE_HEADER.toString());
			fileWriter.append(NEW_LINE_SEPARATOR);
			fileWriter.close();
			System.out.println("Header added successfully.");
		} catch (IOException e) {
			System.out.println("Exception occured  while adding the HEADER to a CSV file.");
			e.printStackTrace();
		}

	}

	/**
	 * Update the CSV file.
	 * 
	 * @param fileName
	 */
	public static void updadateCsvFileData(String fileName, List<String> data) {
		List<String> existingCsvData = readCsvFile(fileName);

		if (existingCsvData.size() == 0) {
			existingCsvData.add(FILE_HEADER);
		}
		FileWriter fileWriter = null;
		existingCsvData.addAll(data);
		try {
			fileWriter = new FileWriter(fileName);
			for (String string : existingCsvData) {
				fileWriter.append(string);
				fileWriter.append(NEW_LINE_SEPARATOR);
			}
			fileWriter.close();
			System.out.println("Order details added successfully.");
		} catch (IOException e) {
			System.out.println("Exception occured  while updating the CSV file.");
			e.printStackTrace();
		}

	}

	/**
	 * Read Csv data.
	 * 
	 * @param fileName
	 */
	public static List<String> readCsvFile(String fileName) {
		String line = "";
		BufferedReader br;
		List<String> existingCsvData = new ArrayList<>();
		try {
			br = new BufferedReader(new FileReader(fileName));
			while ((line = br.readLine()) != null) {
				existingCsvData.add(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return existingCsvData;
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String fileName = "C:\\pradeep.csv";
		createCsvFileIfNotExists(fileName);

		List<String> data = new ArrayList<>();
		StringBuilder data1 = new StringBuilder();
		data1.append("1").append(COMMA_DELIMITER).append("Shipped").append(COMMA_DELIMITER).append("Reebok Shoe")
				.append(COMMA_DELIMITER).append("$1").append(COMMA_DELIMITER).append("$25");

		data.add(data1.toString());
		updadateCsvFileData(fileName, data);
	}

}
