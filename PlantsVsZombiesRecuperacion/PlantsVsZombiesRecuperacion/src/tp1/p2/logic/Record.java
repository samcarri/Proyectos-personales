package tp1.p2.logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import tp1.p2.control.exceptions.GameException;
import tp1.p2.control.exceptions.RecordException;
import tp1.p2.view.Messages;
import tp1.p2.control.Level;

public class Record {

	public Record() {
	}

	public void initializeRecord() throws GameException, IOException {// SE ENCARGA DE CREAR EL ARCHIVO NUEVO EN CASO DE
																		// NO EXISTIR
		File archivo = new File("Record.txt");
		if (!archivo.exists()) {
			archivo.createNewFile();
		}
		try (Scanner s = new Scanner(new BufferedReader(new FileReader("Record.txt")))) {
			if (!s.hasNext()) {
				try (PrintWriter out1 = new PrintWriter("Record.txt")) {
					out1.println("EASY:0");
					out1.println("HARD:0");
					out1.println("INSANE:0");
				} catch (FileNotFoundException e) {
					throw new RecordException(Messages.RECORD_READ_ERROR);
				}
			}
		}

	}

	public void writeRecord(Level level, int points) throws GameException {
		String mod[] = new String[3];
		int i = 0;
		try (Scanner s = new Scanner(new BufferedReader(new FileReader("Record.txt")))) {
			while (s.hasNext()) {
				String[] parts = s.next().split(":");

				mod[i] = (parts[0] + ":" + parts[1]);

				if (level.toString().equals(parts[0])) {
					if (Integer.valueOf(parts[1]) < points) {
						mod[i] = (level + ":" + points);
					}

				}
				i++;
			}
		}

		catch (FileNotFoundException e) {
			throw new RecordException(Messages.RECORD_WRITE_ERROR);
		}

		try (PrintWriter out = new PrintWriter("Record.txt")) {
			for (int j = 0; j < 3; j++)

				out.println(mod[j]);

		} catch (FileNotFoundException fnfe) {
			throw new RecordException(Messages.RECORD_WRITE_ERROR);
		}

	}

	public void showRecord(Level level) throws RecordException {

		try (Scanner s = new Scanner(new BufferedReader(new FileReader("Record.txt")))) {
			while (s.hasNext()) {
				String[] parts = s.next().split(":");
				if (level.toString().equals(parts[0])) {
					System.out.println(parts[0] + " record is " + parts[1]);
				}
			}
		} catch (FileNotFoundException e) {
			throw new RecordException(Messages.RECORD_READ_ERROR);
		}
	}
}
