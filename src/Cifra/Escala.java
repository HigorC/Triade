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

    public int getDistanciaNotas(Nota notaAtual, Nota proximaNota) {
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
        //BUSCANDO ESQ PRA DIREITA
        int posicaoDaProxNota_p = buscaS.BuscaSeqRecursivaEsqDir(escala, proximaNota.getNota());
        int distancia_p = Math.abs(posicaoDaProxNota_p - posicaoDaNotaAtualNoVetor_p);

        int posicaoDaNotaAtualNoVetor_s = buscaS.BuscaSeqRecursivaDirEsq(escala, notaAtual.getNota());
        int posicaoDaProxNota_s = buscaS.BuscaSeqRecursivaDirEsq(escala, proximaNota.getNota());
        int distancia_s = Math.abs(posicaoDaProxNota_s - posicaoDaNotaAtualNoVetor_s);
        //Como fiz duas buscas, aqui vejo qual valor devo utilizar, o menor de cada, no caso.
        if (distancia_p < distancia_s) {
            return distancia_p;
        } else {
            return distancia_s;
        }
    }

    public String getNotaMaisProxima(Nota notaAtual, Nota proximaTriade[]) {
        BuscaSequencialR buscaS = new BuscaSequencialR();

        int distanciaCerta_1 = getDistanciaNotas(notaAtual, proximaTriade[0]);
        int distanciaCerta_2 = getDistanciaNotas(notaAtual, proximaTriade[1]);
        int distanciaCerta_3 = getDistanciaNotas(notaAtual, proximaTriade[2]);

        if (distanciaCerta_1 < distanciaCerta_2) {
            if (distanciaCerta_1 < distanciaCerta_3) {
                return escala[buscaS.BuscaSeqRecursivaEsqDir(escala, proximaTriade[0].getNota())];
            } else {
                return escala[buscaS.BuscaSeqRecursivaEsqDir(escala, proximaTriade[2].getNota())];
            }
        } else if (distanciaCerta_2 < distanciaCerta_3) {
            return escala[buscaS.BuscaSeqRecursivaEsqDir(escala, proximaTriade[1].getNota())];
        } else {
            return escala[buscaS.BuscaSeqRecursivaEsqDir(escala, proximaTriade[2].getNota())];
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
