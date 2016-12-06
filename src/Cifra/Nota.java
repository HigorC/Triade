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

    private final String escala[] = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#","G","G#","A","A#","B","C"};
    private String nome;
    private String nota;

    public boolean isNulo() {
        return getNota().equals("");
    }

    public Nota(String nota) {
        try {
//            if (new Escala().foiImplementado(nota)) {
                this.setNota(nota);
//            } else {
//                throw new IllegalArgumentException();
//            }
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
            // NOTAS NATURAIS
            case 1:
                auxTerca = 4;
                auxQuinta = 7;
                break;
            case 2:
                //NOTAS MENORES
                if (tipo[1].equalsIgnoreCase("m")) {
                    auxTerca = 3;
                    auxQuinta = 7;
                    //NOTAS COM SUSTENIDOS
                } else if (tipo[1].equalsIgnoreCase("#")) {
                    auxTonica = 1;
                    auxTerca = 5;
                    auxQuinta = 8;
                    //NOTAS COM SÉTIMA
                } else if (tipo[1].equalsIgnoreCase("7")) {
                    auxTerca = 4;
                    auxQuinta = 10;
                } else {
                    return null;
                }
                break;
            case 3:
                //NOTAS MENORES
                if (tipo[1].equalsIgnoreCase("m")) {
                    auxTerca = 3;
                    auxQuinta = 7;
                    //NOTAS MENORES COM SÉTIMA
                    if (tipo[2].equalsIgnoreCase("7")) {
                        auxQuinta = 10;
                    } else{
                        return null;
                    }
                    //NOTAS COM SUSTENIDO    
                } else if (tipo[1].equalsIgnoreCase("#")) {
                    auxTonica = 1;
                    auxTerca = 5;
                    auxQuinta = 8;
                    //SUSTENIDO MENOR
                    if (tipo[2].equalsIgnoreCase("m")) {
                        auxTerca = auxTerca - 1;
                        //SUTENIDO COM SÉTIMA
                    } else if (tipo[2].equalsIgnoreCase("7")) {
                        auxQuinta = 10;
                    } else {
                        return null;  
                    }
                } else {
                    return null;
                }
                break;
            case 4:
                if (tipo[1].equalsIgnoreCase("#")) {
                    auxTonica = 1;
                    auxTerca = 5;
                    auxQuinta = 8;
                    if (tipo[2].equalsIgnoreCase("m")) {
                        auxTerca = auxTerca - 1;
                        if (tipo[3].equalsIgnoreCase("7")) {
                            auxQuinta = 10;
                        } else{
                            return null;
                        }
                    } else {
                        return null;
                    }
                    break;
                } else{
                    return null;
                }
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
