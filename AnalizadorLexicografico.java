
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class AnalizadorLexicografico {

	public static int filesize = 0;
	public static char[] linea;
	public static String hola;
	public static boolean fin_archivo = false;
	public static int a_a = 0;
	public static int a_i = 0;
	public static int c, ContRen = 1;
	public static int COMIENZO, ESTADO;
	public static String LEXEMA, MiToken;
	public static String Entrada = "", Salida = "";
	public static String tipoNumero = "chichito";
	public static ArrayList<String> palabrasReservadas = new ArrayList<>(Arrays.asList(new String[] { "entero",
			"flotante", "si", "entonces", "otro", "finsi", "muestra", "comienza", "termina" }));

	public static void rut_error() {
		System.out.println("\n\n Error: caracter [" + Character.toString((char) c) + "] en la linea " + ContRen
				+ " compilacion terminada\n");
		new File(Salida).delete();
		System.exit(4);
	}

	public static boolean es_letra(int X) {
		if (X >= 65 && X <= 90 || X >= 97 && X <= 122) {
			return (true);
		}
		return (false);
	}

	public static boolean es_digito(int X) {
		if (X >= 48 && X <= 57) {
			return (true);
		}
		return (false);
	}

	public static boolean es_delim(int X) {
		if (X == 13 || X == 10 || X == 9 || X == 32) {
			return (true);
		}
		return (false);
	}

	public static boolean es_any(int X) {
		if (!(X == '\r' || X == '\n' || X == 255)) {
			return true;
		}
		return false;
	}

	public static boolean es_any1(int X) {
		if (!(X == 255)) {
			return true;
		}
		return false;
	}

	public static boolean es_palabra_reservada(String X) {
		if (palabrasReservadas.contains(X)) {
			return true;
		}
		return false;
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

	public static int lee_car() {
		if (a_a <= filesize - 1) {
			if (linea[a_a] == 10) {
				ContRen++;
			}
			return linea[a_a++];
		} else {
			fin_archivo = true;
			return 255;
		}
	}

	public static char[] abreLeeCierra(String xName) {
		File xFile = new File(xName);
		char[] data;
		try {
			FileReader fin = new FileReader(xFile);
			filesize = (int) xFile.length();
			data = new char[filesize + 1];
			fin.read(data, 0, filesize);
			data[filesize] = ' ';
			filesize = filesize + 1;
			fin.close();
			return (data);
		} catch (FileNotFoundException exc) {
		} catch (IOException exc) {
		}
		return null;
	}

	public static String obten_lexema() {
		String xx = "";
		for (int i = a_i; i <= a_a - 1; i++) {
			xx = xx + linea[i];
		}
		return (xx);
	}

	public static File xArchivo(String xName) {
		File xFile = new File(xName);
		return xFile;
	}

	public static boolean creaEscribeArchivo(File xFile, String mensaje) {
		try {
			PrintWriter fileOut = new PrintWriter(new FileWriter(xFile, true));
			fileOut.println(mensaje);
			fileOut.close();
			return true;

		} catch (IOException ex) {
			return false;
		}
	}

	public static int diagrama() {
		a_a = a_i;
		switch (COMIENZO) {
		case 0:
			COMIENZO = 3;
			break;
		case 3:
			COMIENZO = 6;
			break;
		case 6:
			COMIENZO = 11;
			break;
		case 11:
			COMIENZO = 14;
			break;
		case 14:
			COMIENZO = 21;
			break;
		case 21:
			COMIENZO = 30;
			break;
		case 30:
			COMIENZO = 46;
			break;
		case 46:
			rut_error();
			break;

		}
		return (COMIENZO);
	}

	public static String TOKEN() {
		while (true) {
			switch (ESTADO) {
			case 0:
				c = lee_car();
				if (es_delim(c)) {
					ESTADO = 1;
				} else {
					ESTADO = diagrama();
				}
				break;
			case 1:
				c = lee_car();
				if (es_delim(c)) {
					ESTADO = 1;
				} else {
					ESTADO = 2;
				}
				break;
			case 2:
				a_a--;
				LEXEMA = obten_lexema();
				a_i = a_a;
				return ("nosirve");
			case 3:
				c = lee_car();
				if (es_letra(c)) {
					ESTADO = 4;
				} else {
					ESTADO = diagrama();
				}
				break;
			case 4:
				c = lee_car();
				if (es_letra(c) || es_digito(c)) {
					ESTADO = 4;
				} else {
					ESTADO = 5;
				}
				break;
			case 5:
				a_a--;
				LEXEMA = obten_lexema();
				a_i = a_a;
				if (es_palabra_reservada(LEXEMA)) {
					return (LEXEMA);
				}
				return ("id");

			case 6:
				c = lee_car();
				if (es_digito(c)) {
					ESTADO = 7;
				} else {
					ESTADO = diagrama();
				}
				break;
			case 7:
				c = lee_car();
				if (es_digito(c)) {
					ESTADO = 7;
				} else if (c == '.') {
					ESTADO = 8;
				} else {
					ESTADO = diagrama();
				}
				break;
			case 8:
				c = lee_car();
				if (es_digito(c)) {
					ESTADO = 9;
				} else {
					ESTADO = diagrama();
				}
				break;
			case 9:
				c = lee_car();
				if (es_digito(c)) {
					ESTADO = 9;
				} else {
					ESTADO = 10;
				}
				break;
			case 10:
				a_a--;
				LEXEMA = obten_lexema();
				a_i = a_a;
				tipoNumero = "flot";
				return ("num");

			case 11:
				c = lee_car();
				if (es_digito(c)) {
					ESTADO = 12;
				} else {
					ESTADO = diagrama();
				}
				break;
			case 12:
				c = lee_car();
				if (es_digito(c)) {
					ESTADO = 12;
				} else {
					ESTADO = 13;
				}
				break;
			case 13:
				a_a--;
				LEXEMA = obten_lexema();
				a_i = a_a;
				tipoNumero = "ent";
				return ("num");
			case 14:
				c = lee_car();
				if (c == '/') {
					ESTADO = 15;
				} else {
					ESTADO = diagrama();
				}
				break;
			case 15:
				c = lee_car();
				if (c == '/') {
					ESTADO = 16;
				} else if (c == '*') {
					ESTADO = 18;
				} else {
					ESTADO = diagrama();
				}
				break;
			case 16:
				c = lee_car();
				if (es_any(c)) {
					ESTADO = 16;
				} else {
					ESTADO = 17;
				}
				break;
			case 17:
				a_a--;
				LEXEMA = obten_lexema();
				a_i = a_a;
				return ("nosirve");
			case 18:
				c = lee_car();
				if (c == '*') {
					ESTADO = 19;
				} else if (es_any1(c)) {
					ESTADO = 18;
				} else {
					ESTADO = diagrama();
				}
				break;
			case 19:
				c = lee_car();
				if (c == '*') {
					ESTADO = 19;
				} else if (c == '/') {
					ESTADO = 20;
				} else if (es_any1(c)) {
					ESTADO = 18;
				} else {
					ESTADO = diagrama();
				}
				break;
			case 20:
				LEXEMA = obten_lexema();
				a_i = a_a;
				return ("nosirve");
			case 21:
				c = lee_car();
				if (c == '+') {
					ESTADO = 22;
				} else if (c == '-') {
					ESTADO = 23;
				} else if (c == '*') {
					ESTADO = 24;
				} else if (c == '/') {
					ESTADO = 25;
				} else if (c == '(') {
					ESTADO = 26;
				} else if (c == ')') {
					ESTADO = 27;
				} else if (c == ':') {
					ESTADO = 28;
				} else {
					ESTADO = diagrama();
				}
				break;
			case 22:
				LEXEMA = obten_lexema();
				a_i = a_a;
				return ("+");
			case 23:
				LEXEMA = obten_lexema();
				a_i = a_a;
				return ("-");
			case 24:
				LEXEMA = obten_lexema();
				a_i = a_a;
				return ("*");
			case 25:
				LEXEMA = obten_lexema();
				a_i = a_a;
				return ("/");
			case 26:
				LEXEMA = obten_lexema();
				a_i = a_a;
				return ("(");
			case 27:
				LEXEMA = obten_lexema();
				a_i = a_a;
				return (")");
			case 28:
				c = lee_car();
				if (c == '=') {
					ESTADO = 29;
				} else {
					ESTADO = diagrama();
				}
				break;
			case 29:
				LEXEMA = obten_lexema();
				a_i = a_a;
				return ("asig");

			case 30:
				c = lee_car();
				if (c == '=') {
					ESTADO = 31;
				} else if (c == '<') {
					ESTADO = 37;
				} else if (c == '>') {
					ESTADO = 41;
				} else if (c == '!') {
					ESTADO = 44;
				} else {
					ESTADO = diagrama();
				}
				break;

			case 31:
				c = lee_car();
				if (c == '|') {
					ESTADO = 32;
				} else if (c == '>') {
					ESTADO = 34;
				} else if (c == '<') {
					ESTADO = 35;
				} else {
					ESTADO = 36;
				}
				break;
			case 32:
				c = lee_car();
				if (c == '=') {
					ESTADO = 33;
				} else {
					ESTADO = diagrama();
				}
				break;
			case 33:
				LEXEMA = obten_lexema();
				a_i = a_a;
				return ("dif");
			case 34:
				LEXEMA = obten_lexema();
				a_i = a_a;
				return ("mai");
			case 35:
				LEXEMA = obten_lexema();
				a_i = a_a;
				return ("mei");
			case 36:
				a_a--;
				LEXEMA = obten_lexema();
				a_i = a_a;
				return ("ig");

			case 37:
				c = lee_car();
				if (c == '>') {
					ESTADO = 38;
				} else if (c == '=') {
					ESTADO = 39;
				} else {
					ESTADO = 40;
				}
				break;
			case 38:
				LEXEMA = obten_lexema();
				a_i = a_a;
				return ("dif");
			case 39:
				LEXEMA = obten_lexema();
				a_i = a_a;
				return ("mei");
			case 40:
				a_a--;
				LEXEMA = obten_lexema();
				a_i = a_a;
				return ("men");

			case 41:
				c = lee_car();
				if (c == '=') {
					ESTADO = 42;
				} else {
					ESTADO = 43;
				}
				break;
			case 42:
				LEXEMA = obten_lexema();
				a_i = a_a;
				return ("mai");
			case 43:
				a_a--;
				LEXEMA = obten_lexema();
				a_i = a_a;
				return ("may");

			case 44:
				c = lee_car();
				if (c == '=') {
					ESTADO = 45;
				} else {
					ESTADO = diagrama();
				}
				break;
			case 45:
				LEXEMA = obten_lexema();
				a_i = a_a;
				return ("dif");

			case 46:
				c = lee_car();
				if (c == 255) {
					ESTADO = 47;
				} else {
					ESTADO = diagrama();
				}
				break;
			case 47:
				LEXEMA = obten_lexema();
				a_i = a_a;
				return ("nosirve");
			}

		}

	}

	public static void main(String argumento[]) {

		try {
			Entrada = argumento[0] + ".FTE";
		} catch (Exception e) {
			System.out.println("Error en el archivo de entrada");
			System.exit(4);
		}
		if (!xArchivo(Entrada).exists()) {
			System.out.println("El archivo [" + Entrada + "] no existe");
			System.exit(4);
		}

		Salida = argumento[0] + ".FT1";

		new File(Salida).delete();

		linea = abreLeeCierra(Entrada);
		while (!fin_archivo) {
			ESTADO = 0;
			COMIENZO = 0;
			MiToken = TOKEN();

			if (!MiToken.equals("nosirve")) {
				creaEscribeArchivo(xArchivo(Salida), MiToken);
				creaEscribeArchivo(xArchivo(Salida), LEXEMA);
				creaEscribeArchivo(xArchivo(Salida), ContRen + "");
				creaEscribeArchivo(xArchivo(Salida), tipoNumero);
			}

		}
		creaEscribeArchivo(xArchivo(Salida), "eof");
		creaEscribeArchivo(xArchivo(Salida), "eof");
		creaEscribeArchivo(xArchivo(Salida), "" + (ContRen + 1));
		creaEscribeArchivo(xArchivo(Salida), tipoNumero);
		System.out.println("");

	}

}
