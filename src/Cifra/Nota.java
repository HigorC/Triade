/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cifra;

/**
 *
 * @author Higor - PC
 */
public class Nota {

    private final String escala[] = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#"};
    private String nome;
    private String nota;

    public boolean isNulo() {
        return getNota().equals("");
    }

    public Nota(String nota) {
        try {
            if (new Escala().foiImplementado(nota)) {
                this.setNota(nota);
            } else {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException ex) {
            System.err.println("A nota (" + nota + ") não existe!\n" + ex);
        }
    }

    public Nota[] getTriade() {
        int auxTonica = 0;
        int auxTerca = 0;
        int auxQuinta = 0;
        String tipo[] = getNota().split("");

        switch (tipo.length) {
            case 1:
                auxTerca = 4;
                auxQuinta = 7;
                break;
            case 2:
                if (tipo[1].equalsIgnoreCase("m")) {
                    auxTerca = 3;
                    auxQuinta = 7;
                } else if (tipo[1].equalsIgnoreCase("#")) {
                    auxTonica = 1;
                    auxTerca = 5;
                    auxQuinta = 8;
                }
                break;
            case 3:
                if (tipo[1].equalsIgnoreCase("m")) {
                    auxTerca = 3;
                    auxQuinta = 7;
                } else if (tipo[1].equalsIgnoreCase("#")) {
                    auxTonica = 1;
                    auxTerca = 5;
                    auxQuinta = 8;

                    if (tipo[2].equalsIgnoreCase("m")) {
                        auxTerca = auxTerca - 1;
                    }
                }
                break;
            default:
                break;
        }

        for (int i = 0; i < escala.length; i++) {
            if (escala[i].equalsIgnoreCase(tipo[0])) {
                Nota triade[] = new Nota[3];
                triade[0] = new Nota(escala[i + auxTonica]);
                triade[1] = new Nota(escala[i + auxTerca]);
                triade[2] = new Nota(escala[i + auxQuinta]);
                return triade;
            }
        }
        return null;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the nota
     */
    public String getNota() {
        return nota;
    }

    /**
     * @param nota the nota to set
     */
    public void setNota(String nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return this.getNota() + " ";

    }

}
