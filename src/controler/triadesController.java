package controler;

import java.util.Scanner;
import visual.visu;

public class triadesController {

    public static void criador() {

        Scanner ler = new Scanner(System.in);

        visu visual = new visu();

        while (true) {

            String linha = ler.nextLine();

            if (linha.equals("0")) {
                System.exit(0);
            }

            String notas[] = linha.split(" ");

            System.out.println(novaTriades(linha));

        }
    }

    public static String novaTriades(String tds_notas) {

        String notas[] = tds_notas.split(" ");
        String resultado = "";
        resultado += ("--------------------------------");
        resultado += ("\nNotas: " + tds_notas);
        resultado += ("\n");

        String escala[] = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#"};

        for (String nota : notas) {
            for (int j = 0; j < escala.length; j++) {
                String[] aux = nota.split("");
                int aux_tonica = 0;
                int aux_terça = 0;
                int aux_quinta = 0;

                if (escala[j].equalsIgnoreCase(aux[0])) {

                    if (aux.length == 1) {
                        aux_terça = 0;
                    } else {
                        if (!aux[1].equalsIgnoreCase("#") && !aux[1].equalsIgnoreCase("m")) {
                            resultado += "Nota inválida";
                            break;
                        }
                        if (aux[1].equalsIgnoreCase("#")) {
                            aux_tonica = 1;
                            aux_terça = 0;
                            aux_quinta = 1;
                        } else if (aux[1].equalsIgnoreCase("m")) {
                            aux_terça = -1;
                        }
                    }
                    resultado += "   " + (escala[j + aux_tonica]) + "           " + (escala[j + 4 + aux_terça]) + "           " + (escala[j + 7 + aux_quinta]);
                    break;

                }

//                if (aux.length == 1) {
//                    if (escala[j].equalsIgnoreCase(nota)) {
//                        resultado += "   " + (escala[j]) + "           " + (escala[j + 4]) + "           " + (escala[j + 7]);
//                        break;
//                    }
//                } else if (aux[1].equalsIgnoreCase("m")) {
//                    if (escala[j].equalsIgnoreCase(aux[0])) {
//                        resultado += "   " + (escala[j]) + "           " + (escala[j + 3]) + "           " + (escala[j + 7]);
//                        break;
//                    }
//                } else if (aux[1].equalsIgnoreCase("#")) {
//                    if (escala[j].equalsIgnoreCase(aux[0])) {
//                        resultado += "   " + (escala[j + 1]) + "           " + (escala[j + 4 + 1]) + "           " + (escala[j + 7 + 1]);
//                        break;
//                    }
//                } else {
//                    resultado += "Nota inválida";
//                    break;
//                }
            }
            resultado += "\n";

        }

        return resultado;
    }

    public static String triades(String tds_notas) {

        String notas[] = tds_notas.split(" ");

        String tri_c[] = {"C", "E", "G"};
        String tri_d[] = {"D", "F#", "A"};
        String tri_e[] = {"E", "G#", "B"};
        String tri_f[] = {"F", "A", "C"};
        String tri_g[] = {"G", "B", "D"};
        String tri_a[] = {"A", "C#", "E"};
        String tri_b[] = {"B", "D#", "F#"};

        String tri_cm[] = {"C", "D#", "G"};
        String tri_dm[] = {"D", "F", "A"};
        String tri_em[] = {"E", "G", "B"};
        String tri_fm[] = {"F", "G#", "C"};
        String tri_gm[] = {"G", "A#", "D"};
        String tri_am[] = {"A", "C", "E"};
        String tri_bm[] = {"B", "D", "F#"};

        String resultado = "";
        resultado += ("--------------------------------");
        resultado += ("\nNotas: " + tds_notas);
        resultado += ("\n");

        for (int j = 0; j < notas.length; j++) {
            if (notas[j].equals("c")) {
                for (int i = 0; i < 3; i++) {
                    resultado += (tri_c[i] + "                 ");

                }
                // System.out.print("\n---|----|----|\n");
            } else if (notas[j].equals("d")) {
                for (int i = 0; i < 3; i++) {
                    resultado += (tri_d[i] + "                ");

                }
                //            System.out.print("\n------|\n");
            } else if (notas[j].equals("e")) {
                for (int i = 0; i < 3; i++) {
                    resultado += (tri_e[i] + "                ");

                }
                //          System.out.print("\n------|\n");
            } else if (notas[j].equals("f")) {
                for (int i = 0; i < 3; i++) {
                    resultado += (tri_f[i] + "                ");

                }
                //      System.out.print("\n------|\n");
            } else if (notas[j].equals("g")) {
                for (int i = 0; i < 3; i++) {
                    resultado += (tri_g[i] + "                ");

                }
                //        System.out.print("\n------|\n");
            } else if (notas[j].equals("a")) {
                for (int i = 0; i < 3; i++) {
                    resultado += (tri_a[i] + "                ");

                }
                //     System.out.print("\n------|\n");
            } else if (notas[j].equals("b")) {
                for (int i = 0; i < 3; i++) {
                    resultado += (tri_b[i] + "                ");

                }
                //    System.out.print("\n------|\n");
            } else if (notas[j].equals("cm")) {
                for (int i = 0; i < 3; i++) {
                    resultado += (tri_cm[i] + "                ");

                }
                //         System.out.print("\n------|\n");
            } else if (notas[j].equals("dm")) {
                for (int i = 0; i < 3; i++) {
                    resultado += (tri_dm[i] + "                ");

                }
                //       System.out.print("\n------|\n");
            } else if (notas[j].equals("em")) {
                for (int i = 0; i < 3; i++) {
                    resultado += (tri_em[i] + "                ");

                }
                //         System.out.print("\n------|\n");
            } else if (notas[j].equals("fm")) {
                for (int i = 0; i < 3; i++) {
                    resultado += (tri_fm[i] + "                ");

                }
                //     System.out.print("\n------|\n");
            } else if (notas[j].equals("gm")) {
                for (int i = 0; i < 3; i++) {
                    resultado += (tri_gm[i] + "                ");

                }
                //       System.out.print("\n------|\n");
            } else if (notas[j].equals("am")) {
                for (int i = 0; i < 3; i++) {
                    resultado += (tri_am[i] + "                ");

                }
                //  System.out.print("\n------|\n");
            } else if (notas[j].equals("bm")) {
                for (int i = 0; i < 3; i++) {
                    resultado += (tri_bm[i] + "                ");

                }
                //   System.out.print("\n------|\n");
            }        //----------
            resultado += ("\n");

        }

        return resultado;
    }

}
