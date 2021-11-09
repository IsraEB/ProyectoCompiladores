/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author mari2
 */
public class ParserSLR1 {

	static String pila[] = new String[10000];
	static int tope = -1;
	static String a, LEXEMA, RENGLON, tipoNumero, Entrada, S, e;
	static int Posicion = 0;

	// Variables a ajustar
	static String nt[] = { "PROGP", "PROG", "VARS", "BLQ", "INST", "DUMP", "IF", "ASIG", "EXPAR", "OPREL", "E", "T",
			"F" };
	static String t[] = { "comienza", "termina", "entero", "id", "flotante", "muestra", "num", "si", "(", ")",
			"entonces", "otro", "finsi", "asig", "ig", "may", "men", "mai", "mei", "dif", "+", "-", "*", "/", "eof" };
	static int lpd[] = { 2, 8, 6, 6, 0, 4, 2, 2, 2, 2, 4, 18, 14, 6, 6, 2, 2, 2, 2, 2, 2, 6, 6, 2, 6, 6, 2, 2, 2, 6 };
	static String pi[] = { "PROGP", "PROG", "VARS", "VARS", "VARS", "BLQ", "BLQ", "INST", "INST", "INST", "DUMP", "IF",
			"IF", "ASIG", "EXPAR", "OPREL", "OPREL", "OPREL", "OPREL", "OPREL", "OPREL", "E", "E", "E", "T", "T", "T",
			"F", "F", "F" };
	static int comienzoNoTerminalesEnLaTabla = 25;
	static int m[][] = {
			{ -4, 0, 3, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3000, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0, 0 },
			{ 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0 },
			{ 0, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0 },
			{ 0, 0, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0 },
			{ 0, 0, 0, 14, 0, 15, 0, 13, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 9, 12, 10, 11,
					0, 0, 0, 0, 0 },
			{ -4, 0, 3, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 16, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0 },
			{ -4, 0, 3, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 17, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0 },
			{ 0, 18, 0, 14, 0, 15, 0, 13, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 19, 12, 10, 11,
					0, 0, 0, 0, 0 },
			{ 0, -6, 0, -6, 0, -6, 0, -6, 0, 0, 0, -6, -6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0, 0, 0 },
			{ 0, -7, 0, -7, 0, -7, 0, -7, 0, 0, 0, -7, -7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0, 0, 0 },
			{ 0, -8, 0, -8, 0, -8, 0, -8, 0, 0, 0, -8, -8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0, 0, 0 },
			{ 0, -9, 0, -9, 0, -9, 0, -9, 0, 0, 0, -9, -9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 21, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 22, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0 },
			{ -2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0 },
			{ -3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0 },
			{ 0, -5, 0, -5, 0, -5, 0, -5, 0, 0, 0, -5, -5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0, 0, 0 },
			{ 0, 0, 0, 28, 0, 0, 27, 0, 29, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 23,
					0, 24, 25, 26 },
			{ 0, 0, 0, 28, 0, 0, 27, 0, 29, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 30, 25, 26 },
			{ 0, -10, 0, -10, 0, -10, 0, -10, 0, 0, 0, -10, -10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 31, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 33, 34, 35, 36, 37, 38, 39, 40, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 32, 0, 0, 0 },
			{ 0, -23, 0, -23, 0, -23, 0, -23, 0, -23, 0, -23, -23, 0, -23, -23, -23, -23, -23, -23, -23, -23, 41, 42, 0,
					0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, -26, 0, -26, 0, -26, 0, -26, 0, -26, 0, -26, -26, 0, -26, -26, -26, -26, -26, -26, -26, -26, -26, -26,
					0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, -27, 0, -27, 0, -27, 0, -27, 0, -27, 0, -27, -27, 0, -27, -27, -27, -27, -27, -27, -27, -27, -27, -27,
					0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, -28, 0, -28, 0, -28, 0, -28, 0, -28, 0, -28, -28, 0, -28, -28, -28, -28, -28, -28, -28, -28, -28, -28,
					0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 28, 0, 0, 27, 0, 29, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 43, 25, 26 },
			{ 0, -13, 0, -13, 0, -13, 0, -13, 0, 0, 0, -13, -13, 0, 0, 0, 0, 0, 0, 0, 39, 40, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 44, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0 },
			{ 0, 0, 0, 28, 0, 0, 27, 0, 29, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 45, 25, 26 },
			{ 0, 0, 0, -15, 0, 0, -15, 0, -15, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0, 0, 0 },
			{ 0, 0, 0, -16, 0, 0, -16, 0, -16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0, 0, 0 },
			{ 0, 0, 0, -17, 0, 0, -17, 0, -17, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0, 0, 0 },
			{ 0, 0, 0, -18, 0, 0, -18, 0, -18, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0, 0, 0 },
			{ 0, 0, 0, -19, 0, 0, -19, 0, -19, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0, 0, 0 },
			{ 0, 0, 0, -20, 0, 0, -20, 0, -20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0, 0, 0 },
			{ 0, 0, 0, 28, 0, 0, 27, 0, 29, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 46, 26 },
			{ 0, 0, 0, 28, 0, 0, 27, 0, 29, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 47, 26 },
			{ 0, 0, 0, 28, 0, 0, 27, 0, 29, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0, 48 },
			{ 0, 0, 0, 28, 0, 0, 27, 0, 29, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0, 49 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 50, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 39, 40, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0, 0 },
			{ 0, 0, 0, 14, 0, 15, 0, 13, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 51, 9, 12, 10, 11,
					0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, -14, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 39, 40, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0, 0 },
			{ 0, -21, 0, -21, 0, -21, 0, -21, 0, -21, 0, -21, -21, 0, -21, -21, -21, -21, -21, -21, -21, -21, 41, 42, 0,
					0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, -22, 0, -22, 0, -22, 0, -22, 0, -22, 0, -22, -22, 0, -22, -22, -22, -22, -22, -22, -22, -22, 41, 42, 0,
					0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, -24, 0, -24, 0, -24, 0, -24, 0, -24, 0, -24, -24, 0, -24, -24, -24, -24, -24, -24, -24, -24, -24, -24,
					0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, -25, 0, -25, 0, -25, 0, -25, 0, -25, 0, -25, -25, 0, -25, -25, -25, -25, -25, -25, -25, -25, -25, -25,
					0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, -29, 0, -29, 0, -29, 0, -29, 0, -29, 0, -29, -29, 0, -29, -29, -29, -29, -29, -29, -29, -29, -29, -29,
					0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 14, 0, 15, 0, 13, 0, 0, 0, 52, 53, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 19, 12, 10,
					11, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 14, 0, 15, 0, 13, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 54, 9, 12, 10, 11,
					0, 0, 0, 0, 0 },
			{ 0, -12, 0, -12, 0, -12, 0, -12, 0, 0, 0, -12, -12, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 14, 0, 15, 0, 13, 0, 0, 0, 0, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 19, 12, 10, 11,
					0, 0, 0, 0, 0 },
			{ 0, -11, 0, -11, 0, -11, 0, -11, 0, 0, 0, -11, -11, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0, 0, 0, 0, 0 } };

	public static void push(String x) {
		if (tope >= 9999) {
			System.out.println("Error pila llena");
			System.exit(4);
		}
		if (!x.equals("epsilon")) {
			pila[++tope] = x;
		}
	}

	public static String pop() {
		if (tope < 0) {
			System.out.println("Error pila vacia");
			System.exit(4);
		}
		return (pila[tope--]);
	}

	public static void print_pila() {
		System.out.print("PILA ==> ");
		for (int i = 0; i <= tope; i++) {
			System.out.print(pila[i] + " ");
		}
		System.out.println("");
	}

	public static void lee_token(File xFile) {
		try {
			FileReader fr = new FileReader(xFile);
			BufferedReader br = new BufferedReader(fr);

			br.skip(Posicion);

			String linea;

			linea = br.readLine();
			Posicion = Posicion + linea.length() + 2;
			a = linea;

			linea = br.readLine();
			Posicion = Posicion + linea.length() + 2;
			LEXEMA = linea;

			linea = br.readLine();
			Posicion = Posicion + linea.length() + 2;
			RENGLON = linea;

			linea = br.readLine();
			Posicion = Posicion + linea.length() + 2;
			tipoNumero = linea;

			fr.close();
		} catch (IOException e) {
			System.out.println("Error");
		}
	}

	public static File xArchivo(String xName) {
		File xFile = new File(xName);
		return xFile;
	}

	public static String pausa() {
		BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
		String nada = null;
		try {
			nada = entrada.readLine();
			return (nada);
		} catch (Exception e) {
			System.err.println(e);
		}
		return ("");
	}

	public static void rut_error() {
		System.out
				.println("\nERROR: Error sintactico en el renglon " + RENGLON + ", token [" + a + "(" + LEXEMA + ")]");
		System.exit(4);
	}

	public static int no_terminal(String x) {
		for (int i = 0; i < nt.length; i++) {
			if (nt[i].equals(x)) {
				return (i + comienzoNoTerminalesEnLaTabla);
			}
		}
		return (-1);

	}

	public static int terminal(String x) {
		for (int i = 0; i < t.length; i++) {
			if (t[i].equals(x)) {
				return (i);
			}
		}
		return (-1);
	}

	public static void main(String argumento[]) {
		try {
			Entrada = argumento[0] + ".FT1";
		} catch (Exception e) {
			System.out.println("\7Error en el archivo de entrada");
			System.exit(4);
		}
		if (!xArchivo(Entrada).exists()) {
			System.out.println("\7El archivo [" + Entrada + "] no existe");
			System.exit(4);
		}
		push("0");

		lee_token(xArchivo(Entrada));
		do {
			print_pila();
			// pausa();
			S = pila[tope];
			System.out.print("[" + S + "] con [" + a + "(" + LEXEMA + ")] ");
			if (m[Integer.parseInt(S)][terminal(a)] == 3000) {
				System.out.println("Revision sintactica exitosa :)");
				System.exit(0);
			} else if (m[Integer.parseInt(S)][terminal(a)] > 0) {
				System.out.println("Shift (" + m[Integer.parseInt(S)][terminal(a)] + ")");
				// pausa();
				push(a);
				push(m[Integer.parseInt(S)][terminal(a)] + "");
				lee_token(xArchivo(Entrada));
			} else if (m[Integer.parseInt(S)][terminal(a)] < 0) {
				System.out.println("Reduce (" + m[Integer.parseInt(S)][terminal(a)] + ")");
				// pausa();
				for (int i = 1; i <= lpd[m[Integer.parseInt(S)][terminal(a)] * (-1)]; i++) {
					pop();
				}
				e = pila[tope];
				push(pi[m[Integer.parseInt(S)][terminal(a)] * (-1)]);
				if (m[Integer.parseInt(e)][no_terminal(pi[m[Integer.parseInt(S)][terminal(a)] * (-1)])] == 0) {
					rut_error();
				} else {
					push(m[Integer.parseInt(e)][no_terminal(pi[m[Integer.parseInt(S)][terminal(a)] * (-1)])] + "");
				}
			} else {
				rut_error();
			}

			System.out.println();

		} while (true);
		// System.out.println("Termino el parse SL(1)");

	}
}
