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
public class ParserSLR1Referencia {

	static String pila[] = new String[10000];
	static int tope = -1;
	static String a, LEXEMA, RENGLON, tipoNumero, Entrada, S, e;
	static int Posicion = 0;
	static String nt[] = { "LISTA_PP", "LISTA", "LISTA_P", "ELEM" };
	static String t[] = { "+", "-", "*", "/", "num", "id", "(", ")", "eof" };
	static int lpd[] = { 1, 2, 2, 2, 2, 2, 0, 1, 1, 3 };
	static String pi[] = { "LISTA_PP", "LISTA", "LISTA_P", "LISTA_P", "LISTA_P", "LISTA_P", "LISTA_P", "ELEM", "ELEM",
			"ELEM" };
	static int m[][] = new int[17][13];

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
				return (i + 9);
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
		for (int i = 0; i <= 16; i++) {
			for (int j = 0; j <= 12; j++) {
				m[i][j] = 0;
			}
		}
		m[0][4] = 3;
		m[0][5] = 4;
		m[0][6] = 5;
		m[0][10] = 1;
		m[0][12] = 2;
		m[1][8] = 3000;
		m[2][0] = 7;
		m[2][1] = 8;
		m[2][2] = 9;
		m[2][3] = 10;
		m[2][7] = -6;
		m[2][8] = -6;
		m[2][11] = 6;
		m[3][0] = -7;
		m[3][1] = -7;
		m[3][2] = -7;
		m[3][3] = -7;
		m[3][7] = -7;
		m[3][8] = -7;
		m[4][0] = -8;
		m[4][1] = -8;
		m[4][2] = -8;
		m[4][3] = -8;
		m[4][7] = -8;
		m[4][8] = -8;
		m[5][4] = 3;
		m[5][5] = 4;
		m[5][6] = 5;
		m[5][10] = 11;
		m[5][12] = 2;
		m[6][7] = -1;
		m[6][8] = -1;
		m[7][4] = 3;
		m[7][5] = 4;
		m[7][6] = 5;
		m[7][10] = 12;
		m[7][12] = 2;
		m[8][4] = 3;
		m[8][5] = 4;
		m[8][6] = 5;
		m[8][10] = 13;
		m[8][12] = 2;
		m[9][4] = 3;
		m[9][5] = 4;
		m[9][6] = 5;
		m[9][10] = 14;
		m[9][12] = 2;
		m[10][4] = 3;
		m[10][5] = 4;
		m[10][6] = 5;
		m[10][10] = 15;
		m[10][12] = 2;
		m[11][7] = 16;
		m[12][7] = -2;
		m[12][8] = -2;
		m[13][7] = -3;
		m[13][8] = -3;
		m[14][7] = -4;
		m[14][8] = -4;
		m[15][7] = -5;
		m[15][8] = -5;
		m[16][0] = -9;
		m[16][1] = -9;
		m[16][2] = -9;
		m[16][3] = -9;
		m[16][7] = -9;
		m[16][8] = -9;
		try {
			Entrada = argumento[0] + ".LA1";
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
			pausa();
			S = pila[tope];
			System.out.print("[" + S + "] con [" + a + "(" + LEXEMA + ")] ");
			if (m[Integer.parseInt(S)][terminal(a)] == 3000) {
				System.out.println("Revision sintactica exitosa :)");
				System.exit(0);
			} else {
				if (m[Integer.parseInt(S)][terminal(a)] > 0) {
					System.out.println("Shift (" + m[Integer.parseInt(S)][terminal(a)] + ")");
					pausa();
					push(a);
					push(m[Integer.parseInt(S)][terminal(a)] + "");
					lee_token(xArchivo(Entrada));
				} else {
					if (m[Integer.parseInt(S)][terminal(a)] < 0) {
						System.out.println("Reduce (" + m[Integer.parseInt(S)][terminal(a)] + ")");
						pausa();
						for (int i = 1; i <= lpd[m[Integer.parseInt(S)][terminal(a)] * (-1)] * 2; i++) {
							pop();
						}
						e = pila[tope];
						push(pi[m[Integer.parseInt(S)][terminal(a)] * (-1)]);
						if (m[Integer.parseInt(e)][no_terminal(pi[m[Integer.parseInt(S)][terminal(a)] * (-1)])] == 0) {
							rut_error();
						} else {
							push(m[Integer.parseInt(e)][no_terminal(pi[m[Integer.parseInt(S)][terminal(a)] * (-1)])]
									+ "");
						}
					} else {
						rut_error();
					}
				}
			}

		} while (true);
		// System.out.println("Termino el parse SL(1)");

	}
}
