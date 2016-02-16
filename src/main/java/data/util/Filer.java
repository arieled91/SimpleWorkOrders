package main.java.data.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Filer {

	public static List<String> read(String path) throws IOException {
		List<String> list = new ArrayList<>();

		File file = new File(path);

		if (!file.exists()) {
			return null;
		}

		Scanner s = new Scanner(file);

		while (s.hasNext()) {
			list.add(s.nextLine());
		}

		s.close();

		return list;

	}

	public static void write(String line, String path) throws IOException {

		FileWriter file = new FileWriter(path, true);
		file.write(line + "\n");
		file.close();
	}

	public static void update(String path, List<String> list) throws IOException {

		FileWriter delete = new FileWriter(path);
		delete.write("");
		delete.close();

		for (String line : list) {
			write(line, path);
		}

	}

	public static List<String> getFiles(String path) {
		List<String> list = new ArrayList<>();
		File folder = new File(path);
		if (folder.exists()) {
			File[] files = folder.listFiles();
			for (File file : files) {
				list.add(file.getName());
			}

		}
		if (!list.isEmpty())
			return list;
		else {
			System.err.println("Folder " + path + " is empty");
			return null;
		}

	}

}
