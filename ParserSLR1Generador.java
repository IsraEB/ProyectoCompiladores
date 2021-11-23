import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class ParserSLR1Generador {

	static String pila[] = new String[10000];
	static int tope = -1;
	static String a, LEXEMA, RENGLON, tipoNumero, Entrada, Salida, S, e;
	static int Posicion = 0;
	static String nt[] = new String[13];
	static String t[] = new String[25];
	static int lpd[] = new int[30];
	static String pi[] = new String[30];
	static int m[][] = new int[56][38];

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
				return (i + 25);
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
		t[0] = "comienza";
		t[1] = "termina";
		t[2] = "entero";
		t[3] = "id";
		t[4] = "flotante";
		t[5] = "muestra";
		t[6] = "num";
		t[7] = "si";
		t[8] = "(";
		t[9] = ")";
		t[10] = "entonces";
		t[11] = "otro";
		t[12] = "finsi";
		t[13] = "asig";
		t[14] = "ig";
		t[15] = "may";
		t[16] = "men";
		t[17] = "mai";
		t[18] = "mei";
		t[19] = "dif";
		t[20] = "+";
		t[21] = "-";
		t[22] = "*";
		t[23] = "/";
		t[24] = "eof";

		nt[0] = "PROGP";
		nt[1] = "PROG";
		nt[2] = "VARS";
		nt[3] = "BLQ";
		nt[4] = "INST";
		nt[5] = "DUMP";
		nt[6] = "IF";
		nt[7] = "ASIG";
		nt[8] = "EXPAR";
		nt[9] = "OPREL";
		nt[10] = "E";
		nt[11] = "T";
		nt[12] = "F";

		pi[0] = "PROGP";
		pi[1] = "PROG";
		pi[2] = "VARS";
		pi[3] = "VARS";
		pi[4] = "VARS";
		pi[5] = "BLQ";
		pi[6] = "BLQ";
		pi[7] = "INST";
		pi[8] = "INST";
		pi[9] = "INST";
		pi[10] = "DUMP";
		pi[11] = "IF";
		pi[12] = "IF";
		pi[13] = "ASIG";
		pi[14] = "EXPAR";
		pi[15] = "OPREL";
		pi[16] = "OPREL";
		pi[17] = "OPREL";
		pi[18] = "OPREL";
		pi[19] = "OPREL";
		pi[20] = "OPREL";
		pi[21] = "E";
		pi[22] = "E";
		pi[23] = "E";
		pi[24] = "T";
		pi[25] = "T";
		pi[26] = "T";
		pi[27] = "F";
		pi[28] = "F";
		pi[29] = "F";

		lpd[0] = 2;
		lpd[1] = 8;
		lpd[2] = 6;
		lpd[3] = 6;
		lpd[4] = 0;
		lpd[5] = 4;
		lpd[6] = 2;
		lpd[7] = 2;
		lpd[8] = 2;
		lpd[9] = 2;
		lpd[10] = 4;
		lpd[11] = 18;
		lpd[12] = 14;
		lpd[13] = 6;
		lpd[14] = 6;
		lpd[15] = 2;
		lpd[16] = 2;
		lpd[17] = 2;
		lpd[18] = 2;
		lpd[19] = 2;
		lpd[20] = 2;
		lpd[21] = 6;
		lpd[22] = 6;
		lpd[23] = 2;
		lpd[24] = 6;
		lpd[25] = 6;
		lpd[26] = 2;
		lpd[27] = 2;
		lpd[28] = 2;
		lpd[29] = 6;

		for (int i = 0; i <= 55; i++) {
			for (int j = 0; j <= 37; j++) {
				m[i][j] = 0;
			}
		}
		m[0][2] = 3;
		m[0][4] = 4;
		m[2][0] = 5;
		m[3][3] = 6;
		m[4][3] = 7;
		m[5][7] = 13;
		m[5][3] = 14;
		m[5][5] = 15;
		m[6][2] = 3;
		m[6][4] = 4;
		m[7][2] = 3;
		m[7][4] = 4;
		m[8][1] = 18;
		m[8][7] = 13;
		m[8][3] = 14;
		m[8][5] = 15;
		m[13][8] = 20;
		m[14][13] = 21;
		m[15][6] = 22;
		m[20][6] = 27;
		m[20][3] = 28;
		m[20][8] = 29;
		m[21][6] = 27;
		m[21][3] = 28;
		m[21][8] = 29;
		m[23][9] = 31;
		m[24][14] = 33;
		m[24][15] = 34;
		m[24][16] = 35;
		m[24][17] = 36;
		m[24][18] = 37;
		m[24][19] = 38;
		m[24][20] = 39;
		m[24][21] = 40;
		m[25][22] = 41;
		m[25][23] = 42;
		m[29][6] = 27;
		m[29][3] = 28;
		m[29][8] = 29;
		m[30][20] = 39;
		m[30][21] = 40;
		m[31][10] = 44;
		m[32][6] = 27;
		m[32][3] = 28;
		m[32][8] = 29;
		m[39][6] = 27;
		m[39][3] = 28;
		m[39][8] = 29;
		m[40][6] = 27;
		m[40][3] = 28;
		m[40][8] = 29;
		m[41][6] = 27;
		m[41][3] = 28;
		m[41][8] = 29;
		m[42][6] = 27;
		m[42][3] = 28;
		m[42][8] = 29;
		m[43][9] = 50;
		m[43][20] = 39;
		m[43][21] = 40;
		m[44][7] = 13;
		m[44][3] = 14;
		m[44][5] = 15;
		m[45][20] = 39;
		m[45][21] = 40;
		m[46][22] = 41;
		m[46][23] = 42;
		m[47][22] = 41;
		m[47][23] = 42;
		m[51][11] = 52;
		m[51][7] = 13;
		m[51][3] = 14;
		m[51][5] = 15;
		m[51][12] = 53;
		m[52][7] = 13;
		m[52][3] = 14;
		m[52][5] = 15;
		m[54][12] = 55;
		m[54][7] = 13;
		m[54][3] = 14;
		m[54][5] = 15;
		m[0][26] = 1;
		m[0][27] = 2;
		m[5][28] = 8;
		m[5][29] = 9;
		m[5][31] = 10;
		m[5][32] = 11;
		m[5][30] = 12;
		m[6][27] = 16;
		m[7][27] = 17;
		m[8][29] = 19;
		m[8][31] = 10;
		m[8][32] = 11;
		m[8][30] = 12;
		m[20][33] = 23;
		m[20][35] = 24;
		m[20][36] = 25;
		m[20][37] = 26;
		m[21][35] = 30;
		m[21][36] = 25;
		m[21][37] = 26;
		m[24][34] = 32;
		m[29][35] = 43;
		m[29][36] = 25;
		m[29][37] = 26;
		m[32][35] = 45;
		m[32][36] = 25;
		m[32][37] = 26;
		m[39][36] = 46;
		m[39][37] = 26;
		m[40][36] = 47;
		m[40][37] = 26;
		m[41][37] = 48;
		m[42][37] = 49;
		m[44][28] = 51;
		m[44][29] = 9;
		m[44][31] = 10;
		m[44][32] = 11;
		m[44][30] = 12;
		m[51][29] = 19;
		m[51][31] = 10;
		m[51][32] = 11;
		m[51][30] = 12;
		m[52][28] = 54;
		m[52][29] = 9;
		m[52][31] = 10;
		m[52][32] = 11;
		m[52][30] = 12;
		m[54][29] = 19;
		m[54][31] = 10;
		m[54][32] = 11;
		m[54][30] = 12;
		m[0][0] = -4;
		m[1][24] = 3000;
		m[6][0] = -4;
		m[7][0] = -4;
		m[9][1] = -6;
		m[9][7] = -6;
		m[9][3] = -6;
		m[9][5] = -6;
		m[9][11] = -6;
		m[9][12] = -6;
		m[10][1] = -7;
		m[10][7] = -7;
		m[10][3] = -7;
		m[10][5] = -7;
		m[10][11] = -7;
		m[10][12] = -7;
		m[11][1] = -8;
		m[11][7] = -8;
		m[11][3] = -8;
		m[11][5] = -8;
		m[11][11] = -8;
		m[11][12] = -8;
		m[12][1] = -9;
		m[12][7] = -9;
		m[12][3] = -9;
		m[12][5] = -9;
		m[12][11] = -9;
		m[12][12] = -9;
		m[16][0] = -2;
		m[17][0] = -3;
		m[18][24] = -1;
		m[19][1] = -5;
		m[19][7] = -5;
		m[19][3] = -5;
		m[19][5] = -5;
		m[19][11] = -5;
		m[19][12] = -5;
		m[22][1] = -10;
		m[22][7] = -10;
		m[22][3] = -10;
		m[22][5] = -10;
		m[22][11] = -10;
		m[22][12] = -10;
		m[25][14] = -23;
		m[25][15] = -23;
		m[25][16] = -23;
		m[25][17] = -23;
		m[25][18] = -23;
		m[25][19] = -23;
		m[25][20] = -23;
		m[25][21] = -23;
		m[25][9] = -23;
		m[25][1] = -23;
		m[25][7] = -23;
		m[25][3] = -23;
		m[25][5] = -23;
		m[25][11] = -23;
		m[25][12] = -23;
		m[26][22] = -26;
		m[26][23] = -26;
		m[26][14] = -26;
		m[26][15] = -26;
		m[26][16] = -26;
		m[26][17] = -26;
		m[26][18] = -26;
		m[26][19] = -26;
		m[26][20] = -26;
		m[26][21] = -26;
		m[26][9] = -26;
		m[26][1] = -26;
		m[26][7] = -26;
		m[26][3] = -26;
		m[26][5] = -26;
		m[26][11] = -26;
		m[26][12] = -26;
		m[27][22] = -27;
		m[27][23] = -27;
		m[27][14] = -27;
		m[27][15] = -27;
		m[27][16] = -27;
		m[27][17] = -27;
		m[27][18] = -27;
		m[27][19] = -27;
		m[27][20] = -27;
		m[27][21] = -27;
		m[27][9] = -27;
		m[27][1] = -27;
		m[27][7] = -27;
		m[27][3] = -27;
		m[27][5] = -27;
		m[27][11] = -27;
		m[27][12] = -27;
		m[28][22] = -28;
		m[28][23] = -28;
		m[28][14] = -28;
		m[28][15] = -28;
		m[28][16] = -28;
		m[28][17] = -28;
		m[28][18] = -28;
		m[28][19] = -28;
		m[28][20] = -28;
		m[28][21] = -28;
		m[28][9] = -28;
		m[28][1] = -28;
		m[28][7] = -28;
		m[28][3] = -28;
		m[28][5] = -28;
		m[28][11] = -28;
		m[28][12] = -28;
		m[30][1] = -13;
		m[30][7] = -13;
		m[30][3] = -13;
		m[30][5] = -13;
		m[30][11] = -13;
		m[30][12] = -13;
		m[33][6] = -15;
		m[33][3] = -15;
		m[33][8] = -15;
		m[34][6] = -16;
		m[34][3] = -16;
		m[34][8] = -16;
		m[35][6] = -17;
		m[35][3] = -17;
		m[35][8] = -17;
		m[36][6] = -18;
		m[36][3] = -18;
		m[36][8] = -18;
		m[37][6] = -19;
		m[37][3] = -19;
		m[37][8] = -19;
		m[38][6] = -20;
		m[38][3] = -20;
		m[38][8] = -20;
		m[45][9] = -14;
		m[46][14] = -21;
		m[46][15] = -21;
		m[46][16] = -21;
		m[46][17] = -21;
		m[46][18] = -21;
		m[46][19] = -21;
		m[46][20] = -21;
		m[46][21] = -21;
		m[46][9] = -21;
		m[46][1] = -21;
		m[46][7] = -21;
		m[46][3] = -21;
		m[46][5] = -21;
		m[46][11] = -21;
		m[46][12] = -21;
		m[47][14] = -22;
		m[47][15] = -22;
		m[47][16] = -22;
		m[47][17] = -22;
		m[47][18] = -22;
		m[47][19] = -22;
		m[47][20] = -22;
		m[47][21] = -22;
		m[47][9] = -22;
		m[47][1] = -22;
		m[47][7] = -22;
		m[47][3] = -22;
		m[47][5] = -22;
		m[47][11] = -22;
		m[47][12] = -22;
		m[48][22] = -24;
		m[48][23] = -24;
		m[48][14] = -24;
		m[48][15] = -24;
		m[48][16] = -24;
		m[48][17] = -24;
		m[48][18] = -24;
		m[48][19] = -24;
		m[48][20] = -24;
		m[48][21] = -24;
		m[48][9] = -24;
		m[48][1] = -24;
		m[48][7] = -24;
		m[48][3] = -24;
		m[48][5] = -24;
		m[48][11] = -24;
		m[48][12] = -24;
		m[49][22] = -25;
		m[49][23] = -25;
		m[49][14] = -25;
		m[49][15] = -25;
		m[49][16] = -25;
		m[49][17] = -25;
		m[49][18] = -25;
		m[49][19] = -25;
		m[49][20] = -25;
		m[49][21] = -25;
		m[49][9] = -25;
		m[49][1] = -25;
		m[49][7] = -25;
		m[49][3] = -25;
		m[49][5] = -25;
		m[49][11] = -25;
		m[49][12] = -25;
		m[50][22] = -29;
		m[50][23] = -29;
		m[50][14] = -29;
		m[50][15] = -29;
		m[50][16] = -29;
		m[50][17] = -29;
		m[50][18] = -29;
		m[50][19] = -29;
		m[50][20] = -29;
		m[50][21] = -29;
		m[50][9] = -29;
		m[50][1] = -29;
		m[50][7] = -29;
		m[50][3] = -29;
		m[50][5] = -29;
		m[50][11] = -29;
		m[50][12] = -29;
		m[53][1] = -12;
		m[53][7] = -12;
		m[53][3] = -12;
		m[53][5] = -12;
		m[53][11] = -12;
		m[53][12] = -12;
		m[55][1] = -11;
		m[55][7] = -11;
		m[55][3] = -11;
		m[55][5] = -11;
		m[55][11] = -11;
		m[55][12] = -11;

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
		Salida = argumento[0] + ".FT2";

		push("0");

		lee_token(xArchivo(Entrada));
		do {
			print_pila();

			S = pila[tope];
			System.out.print("[" + S + "] con [" + a + "(" + LEXEMA + ")] ");
			if (m[Integer.parseInt(S)][terminal(a)] == 3000) {
				System.out.println("Revision sintactica exitosa :)");
				System.exit(0);
			} else {
				if (m[Integer.parseInt(S)][terminal(a)] > 0) {
					System.out.println("Shift (" + m[Integer.parseInt(S)][terminal(a)] + ")");

					push(a);
					push(m[Integer.parseInt(S)][terminal(a)] + "");
					GC_SHIFT(m[Integer.parseInt(S)][terminal(a)]);
					lee_token(xArchivo(Entrada));
				} else {
					if (m[Integer.parseInt(S)][terminal(a)] < 0) {
						System.out.println("Reduce (" + m[Integer.parseInt(S)][terminal(a)] + ")");

						GC_REDUCE(m[Integer.parseInt(S)][terminal(a)]);

						for (int i = 1; i <= lpd[m[Integer.parseInt(S)][terminal(a)] * (-1)]; i++) {
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

			System.out.println();

		} while (true);

	}

	// Generación de código

	// Tabla de símbolos
	static int iSYM = -1;
	static String SymVAR[] = new String[1000];
	static String SymTIPO[] = new String[1000];

	// Variables para generar código en el shift
	static String TIPO = "";
	static String VAL = "";
	static String LVAR = "";

	// Variables para las reducciones
	static String DUMP_cod[] = new String[100];
	static int tDUMP_cod = -1;
	static String INST_cod[] = new String[100];
	static int tINST_cod = -1;
	static String BLQ_cod[] = new String[100];
	static int tBLQ_cod = -1;
	static String VARS_cod[] = new String[100];
	static int tVARS_cod = -1;
	static String PROG_cod[] = new String[100];
	static int tPROG_cod = -1;
	static String F_cod[] = new String[100];
	static int tF_cod = -1;
	static String F_res[] = new String[100];
	static int tF_res = -1;
	static String T_cod[] = new String[100];
	static int tT_cod = -1;
	static String T_res[] = new String[100];
	static int tT_res = -1;
	static String E_cod[] = new String[100];
	static int tE_cod = -1;
	static String E_res[] = new String[100];
	static int tE_res = -1;
	static String ASIG_cod[] = new String[100];
	static int tASIG_cod = -1;
	static String EXPAR_cod[] = new String[100];
	static int tEXPAR_cod = -1;
	static String EXPAR_res[] = new String[100];
	static int tEXPAR_res = -1;

	// Variables temporales
	static String Temp0 = ""; // Partir Lineas de Java
	static int ContVarTemp = 0;
	static String T0 = "";
	static String T1 = "";

	public static void GC_SHIFT(int X) {
		switch (X) {
		case 3:
			TIPO = "ent";
			break;
		case 4:
			TIPO = "flot";
			break;
		case 6:
		case 7:
			if (!(existe(LEXEMA))) {
				VAL = VAL + "\t\tdoblep\t\t" + LEXEMA + "\r\n";
				creaRenglonDeLaTabla(LEXEMA, TIPO);
			} else {
				System.out.println("Error semantico. Variable duplicada (" + RENGLON + ", " + LEXEMA + ")");
				System.exit(4);
			}
			break;
		case 22:
			if (tipoNumero.equals("ent")) {
				VAL = LEXEMA;
			} else {
				System.out.println("Error semantico. Instrucción muestra debe recibir un entero (" + RENGLON + ", "
						+ LEXEMA + ")");
				System.exit(4);
			}
			break;
		case 14:
			if (existe(LEXEMA)) {
				LVAR = LEXEMA;
			} else {
				System.out.println(
						"Error semantico. Estas usando una variable no declarada (" + RENGLON + ", " + LEXEMA + ")");
				System.exit(4);
			}
			break;
		case 27:
			VAL = LEXEMA;
			break;
		case 28:
			VAL = LEXEMA;
			break;
		}
	}

	public static void GC_REDUCE(int X) {
		switch (X) {
		case -1:
			PROG_cod[++tPROG_cod] = VARS_cod[tVARS_cod--] + BLQ_cod[tBLQ_cod--];
			System.out.println("\nCodigo generado:\n");
			System.out.println(PROG_cod[tPROG_cod]);
			break;
		case -4:
			VARS_cod[++tVARS_cod] = VAL;
			break;
		case -5:
			Temp0 = BLQ_cod[tBLQ_cod--] + INST_cod[tINST_cod--];
			BLQ_cod[++tBLQ_cod] = Temp0;
			break;
		case -6:
			BLQ_cod[++tBLQ_cod] = INST_cod[tINST_cod--];
			break;
		case -8:
			INST_cod[++tINST_cod] = ASIG_cod[tASIG_cod--];
			break;
		case -9:
			INST_cod[++tINST_cod] = DUMP_cod[tDUMP_cod--];
			break;
		case -10:
			DUMP_cod[++tDUMP_cod] = "vuel\t" + VAL + "\r\n";
			break;
		case -13:
			ASIG_cod[++tASIG_cod] = E_cod[tE_cod--] + "\t\tmue\t\t" + E_res[tE_res--] + ", " + LVAR + "\r\n";
			break;
		case -14:
			tEXPAR_cod = tEXPAR_cod + 1;
			EXPAR_cod[tEXPAR_cod] = E_cod[tE_cod - 1] + E_cod[tE_cod];
			EXPAR_cod[tEXPAR_cod] = EXPAR_cod[tEXPAR_cod] + "\t\tmue\t\t" + E_res[tE_res - 1] + ", RA\r\n";
			EXPAR_cod[tEXPAR_cod] = EXPAR_cod[tEXPAR_cod] + "\t\tmue\t\t" + E_res[tE_res] + ", RB\r\n";
			EXPAR_cod[tEXPAR_cod] = EXPAR_cod[tEXPAR_cod] + "\t\tcmpe\t\tRA, RB\r\n";

			System.out.println("EXPAR_cod: \n" + EXPAR_cod[tEXPAR_cod]);
			break;
		case -21:
			T0 = GenVar();
			Temp0 = E_cod[tE_cod--] + T_cod[tT_cod--];
			Temp0 += "\t\tmue\t\t" + E_res[tE_res--] + ", RA\n";
			Temp0 += "\t\tsume\t\t" + T_res[tT_res--] + "\n";
			Temp0 += "\t\tmue\t\t" + "RA, " + T0 + "\n";
			E_cod[++tE_cod] = Temp0;
			E_res[++tE_res] = T0;
			break;
		case -22:
			T0 = GenVar();
			Temp0 = E_cod[tE_cod--] + T_cod[tT_cod--];
			Temp0 += "\t\tmue\t\t" + E_res[tE_res--] + ", RA\n";
			Temp0 += "\t\tsube\t\t" + T_res[tT_res--] + "\n";
			Temp0 += "\t\tmue\t\t" + "RA, " + T0 + "\n";
			E_cod[++tE_cod] = Temp0;
			E_res[++tE_res] = T0;
			break;
		case -23:
			E_cod[++tE_cod] = T_cod[tT_cod--];
			E_res[++tE_res] = T_res[tT_res--];
			break;
		case -24:
			T0 = GenVar();
			Temp0 = T_cod[tT_cod--] + F_cod[tF_cod--];
			Temp0 += "\t\tmue\t\t" + T_res[tT_res--] + ", RA\n";
			Temp0 += "\t\tmule\t\t" + F_res[tF_res--] + "\n";
			Temp0 += "\t\tmue\t\t" + "RA, " + T0 + "\n";
			T_cod[++tT_cod] = Temp0;
			T_res[++tT_res] = T0;
			break;
		case -25:
			T0 = GenVar();
			Temp0 = T_cod[tT_cod--] + F_cod[tF_cod--];
			Temp0 += "\t\tmue\t\t" + T_res[tT_res--] + ", RA\n";
			Temp0 += "\t\tdive\t\t" + F_res[tF_res--] + "\n";
			Temp0 += "\t\tmue\t\t" + "RA, " + T0 + "\n";
			T_cod[++tT_cod] = Temp0;
			T_res[++tT_res] = T0;
			break;
		case -26:
			T_cod[++tT_cod] = F_cod[tF_cod--];
			T_res[++tT_res] = F_res[tF_res--];
			break;
		case -27:
			T0 = GenVar();
			F_cod[++tF_cod] = "\t\tmue\t\t" + VAL + "e, " + T0 + "\n";
			F_res[++tF_res] = T0;
			break;
		case -28:
			F_cod[++tF_cod] = "";
			F_res[++tF_res] = VAL;
			break;
		case -29:
			F_cod[++tF_cod] = E_cod[tE_cod--];
			F_res[++tF_res] = E_res[tE_res--];
			break;
		}
	}

	public static String GenVar() {
		return "VT" + (ContVarTemp++);
	}

	public static void creaRenglonDeLaTabla(String Variable, String Tipo) {
		iSYM++;
		SymVAR[iSYM] = Variable;
		SymTIPO[iSYM] = Tipo;
	}

	public static boolean existe(String Variable) {
		for (int i = 0; i <= iSYM; i++) {
			if (SymVAR[i].equals(Variable)) {
				return true;
			}
		}
		return false;
	}

	public static void printTabla() {
		System.out.println("i\tVAR\tTIPO");
		System.out.println("==================================");
		for (int i = 0; i <= iSYM; i++) {
			System.out.println(i + "\t" + SymVAR[i] + "\t" + SymTIPO[i]);
		}
	}
}
