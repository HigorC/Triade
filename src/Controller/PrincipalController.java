/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Cifra.Escala;
import Cifra.Nota;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author Higor - PC
 */
public class PrincipalController implements Initializable {

    @FXML
    private TextField tfPrimeiraNota;
    @FXML
    private TextField tfEntrada;
    @FXML
    private Button fxEnviar;
    @FXML
    private TextField tfVozUm;
    @FXML
    private TextField tfVozDois;
    @FXML
    private TextArea tfSaida;
    Escala escala = new Escala();
    @FXML
    private ComboBox<String> cbNotasNaturais;
    @FXML
    private Button btnAdicionar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Setando no comboBox todas as notas que foram implementadas .
        ObservableList<String> obsList = FXCollections.observableArrayList();
        String noImp[] = escala.getNotasImplementadas();
        obsList.addAll(noImp);
        cbNotasNaturais.setItems(obsList);
        cbNotasNaturais.setValue(noImp[0]);
    }

    @FXML
    private void enviar(ActionEvent event) throws InterruptedException {
        if (tfEntrada.getText().equals("") | tfPrimeiraNota.getText().equals("")) {
            tfSaida.setText("Nenhum dos campo pode ser vazio!");
        } else {
            imprimirTriades();
            criarVoz();
        }

//    Nota n = new Nota(tfPrimeiraNota.getText());
//        System.out.println(Arrays.toString(n.getTriade()));
    }

    public void criarVoz() {
        Nota primeiraNotaCantada = new Nota(tfPrimeiraNota.getText());
        String cifra[] = tfEntrada.getText().split(" ");
        Nota primeiraTriade[] = new Nota(cifra[0]).getTriade();
        String vozUm = "";
        String vozDois = "";

        int cont = 0;
        for (int i = 0; i < primeiraTriade.length; i++) {
            if (primeiraNotaCantada.getNota().equalsIgnoreCase(primeiraTriade[i].getNota())) {
                cont++;
            }
        }

        if (cont > 0) {

            Nota duoRestante[] = escala.getPossibilidades(new Nota(cifra[0]).getTriade(), primeiraNotaCantada);
            for (int i = 0; i < cifra.length; i++) {
                Nota proxTriade[] = new Nota(cifra[i]).getTriade();

                vozUm += escala.getNotaMaisProxima(duoRestante[0], proxTriade) + " ";
                vozDois += escala.getNotaMaisProxima(duoRestante[1], proxTriade) + " ";
            }
            tfVozUm.setText(vozUm);
            tfVozDois.setText(vozDois);
        } else {
            tfSaida.setText("A primeira nota cantada, deve ser uma das notas que compõem\n"
                    + " a triade da primeira nota na cifra!");
        }
    }

    // f c g f c g f c g f g a
    public void imprimirTriades() {
        String cifra[] = tfEntrada.getText().split(" ");
        String saida = "";
        for (String c : cifra) {
            Nota n = new Nota(c);
            if (!(n.isNulo())) {
                saida += Arrays.toString(n.getTriade()) + "\n";
            }
        }
        tfSaida.setText(saida);
    }

    @FXML
    private void adicionarNota(ActionEvent event) {
        if (tfEntrada.getText().equals("")) {
            tfEntrada.setText(cbNotasNaturais.getValue());
        } else {
            tfEntrada.setText(tfEntrada.getText() + " " + cbNotasNaturais.getValue());
        }
    }

    @FXML
    private void alteracaoEntrada(KeyEvent event) {

    }
}
