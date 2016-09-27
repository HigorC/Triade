/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author Higor - PC
 */
public class BuscaSequencialR {

    public int BuscaSeqRecursivaEsqDir(String vetor[], String procurado) {
        for (int i = 0; i < vetor.length; i++) {
            if (vetor[i].equalsIgnoreCase(procurado)) {
                return i;
            }
        }
        return -1;
    }

    public int BuscaSeqRecursivaDirEsq(String vetor[], String procurado) {
        for (int i = vetor.length - 1; i >= 0; i--) {
            if (vetor[i].equalsIgnoreCase(procurado)) {
                return i;
            }
        }
        return -1;
    }
}
