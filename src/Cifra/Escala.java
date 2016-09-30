/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cifra;

import Controller.BuscaSequencialR;

/**
 *
 * @author Higor - PC
 */
public class Escala {

    private final String escala[] = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B", "C"};
    private final String notasImplementadas[] = {"C", "D", "E", "F", "G", "A", "B",
        "Cm", "Dm", "Em", "Fm", "Gm", "Am", "Bm",
        "C#", "D#", "F#", "G#", "A#",
        "C#m", "D#m", "F#m", "G#m", "A#m", "B#m"};

    public boolean foiImplementado(String nota) {
        for (String n : getNotasImplementadas()) {
            if (n.equalsIgnoreCase(nota)) {
                return true;
            }
        }
        return false;
    }

    public String getNotaMaisProxima(Nota notaAtual, Nota proximaTriade[]) {
        //Busca que será usada para calcular a distância de cada nota
        BuscaSequencialR buscaS = new BuscaSequencialR();
        //É calculada a posição da nota atual no vetor, e de cada nota da triade.
        //Para cada nota, são feitas duas busca, uma da esquerda pra direita e outra da 
        //direita pra esquerda.
        //Isso porque a Escala sempre repete, como um círculo.
        //Se por exemplo, fosse pesquisado apenas de um lado,
        //a distância entre o C e o B, seria de 11,
        //quando na verdade é apenas de 1.
        int posicaoDaNotaAtualNoVetor_p = buscaS.BuscaSeqRecursivaEsqDir(escala, notaAtual.getNota());
        int posicaoDaNota1DaTriade_p = buscaS.BuscaSeqRecursivaEsqDir(escala, proximaTriade[0].getNota());
        int posicaoDaNota2DaTriade_p = buscaS.BuscaSeqRecursivaEsqDir(escala, proximaTriade[1].getNota());
        int posicaoDaNota3DaTriade_p = buscaS.BuscaSeqRecursivaEsqDir(escala, proximaTriade[2].getNota());
        //Aqui é calculado a distância de cada nota da triade com relação a nota atual.
        int distancia_1_p = Math.abs(posicaoDaNota1DaTriade_p - posicaoDaNotaAtualNoVetor_p);
        int distancia_2_p = Math.abs(posicaoDaNota2DaTriade_p - posicaoDaNotaAtualNoVetor_p);
        int distancia_3_p = Math.abs(posicaoDaNota3DaTriade_p - posicaoDaNotaAtualNoVetor_p);

        int posicaoDaNotaAtualNoVetor_s = buscaS.BuscaSeqRecursivaDirEsq(escala, notaAtual.getNota());
        int posicaoDaNota1DaTriade_s = buscaS.BuscaSeqRecursivaDirEsq(escala, proximaTriade[0].getNota());
        int posicaoDaNota2DaTriade_s = buscaS.BuscaSeqRecursivaDirEsq(escala, proximaTriade[1].getNota());
        int posicaoDaNota3DaTriade_s = buscaS.BuscaSeqRecursivaDirEsq(escala, proximaTriade[2].getNota());
        int distancia_1_s = Math.abs(posicaoDaNota1DaTriade_s - posicaoDaNotaAtualNoVetor_s);
        int distancia_2_s = Math.abs(posicaoDaNota2DaTriade_s - posicaoDaNotaAtualNoVetor_s);
        int distancia_3_s = Math.abs(posicaoDaNota3DaTriade_s - posicaoDaNotaAtualNoVetor_s);

        int distanciaCerta_1;
        int distanciaCerta_2;
        int distanciaCerta_3;
        //Como fiz duas buscas, aqui vejo qual valor devo utilizar, o menor de cada, no caso.
        if (distancia_1_p < distancia_1_s) {
            distanciaCerta_1 = distancia_1_p;
        } else {
            distanciaCerta_1 = distancia_1_s;
        }

        if (distancia_2_p < distancia_2_s) {
            distanciaCerta_2 = distancia_2_p;
        } else {
            distanciaCerta_2 = distancia_2_s;
        }

        if (distancia_3_p < distancia_3_s) {
            distanciaCerta_3 = distancia_3_p;
        } else {
            distanciaCerta_3 = distancia_3_s;
        }
        System.out.println("NOTA ATUAL = " + posicaoDaNotaAtualNoVetor_p);
        System.out.println("1 nota = " + posicaoDaNota1DaTriade_p);
        System.out.println("2 nota = " + posicaoDaNota2DaTriade_p);
        System.out.println("3 nota = " + posicaoDaNota3DaTriade_p);
        System.out.println("distancia 1 = " + distancia_1_p);
        System.out.println("distancia 2 = " + distancia_2_p);
        System.out.println("distancia 3 = " + distancia_3_p);

        if (distanciaCerta_1 < distanciaCerta_2) {
            if (distanciaCerta_1 < distanciaCerta_3) {
                return escala[posicaoDaNota1DaTriade_p];
            } else {
                return escala[posicaoDaNota3DaTriade_p];
            }
        } else if (distanciaCerta_2 < distanciaCerta_3) {
            return escala[posicaoDaNota2DaTriade_p];
        } else {
            return escala[posicaoDaNota3DaTriade_p];
        }
    }

    /**
     * Recebe uma triade e a primeira nota cantada na cifra como parâmetro;
     * Remove esta nota da triade, e retorna-a.
     *
     * @param triade
     * @param primeiraNota
     * @return
     */
    public Nota[] getPossibilidades(Nota triade[], Nota primeiraNota) {
        Nota restante[] = new Nota[triade.length - 1];
        for (int i = 0, cont = 0; i < triade.length; i++) {
            if (!triade[i].getNota().equalsIgnoreCase(primeiraNota.getNota())) {
                restante[cont] = new Nota(triade[i].getNota());

                cont++;
                System.out.println(cont);
            }
        }
        return restante;
    }

    /**
     * @return the notasImplementadas
     */
    public String[] getNotasImplementadas() {
        return notasImplementadas;
    }
}
