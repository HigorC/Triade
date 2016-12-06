/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Cifra.Escala;
import Cifra.Nota;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author Higor - PC
 */
public class PrincipalController implements Initializable {

    @FXML
    private TextField tfEntrada;
    @FXML
    private Button fxEnviar;

    @FXML
    private HBox hbVozUm;
    @FXML
    private HBox hbVozDois;
    @FXML
    private Label lbVozUm;
    @FXML
    private Label lbVozDois;
    @FXML
    private Label lbMsg;
    @FXML
    private TextArea tfSaida;
    @FXML
    private Label lbMsg2;

    Escala escala = new Escala();
    @FXML
    private ComboBox<String> cbPrimeiraNota;

//    ArrayList<String> noImp = escala.getNotasImplementadas();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Setando no comboBox todas as notas que foram implementadas .
        ObservableList<String> obsList = FXCollections.observableArrayList();

//        obsList.addAll(noImp);
//        cbNotasNaturais.setItems(obsList);
//        cbNotasNaturais.setValue(noImp.get(0));
        lbMsg.setText("Digite uma cifra, e a primeira nota que\n  a voz principal da música reproduz");
    }

    private void setHboxPadrao() {
        hbVozUm.getChildren().removeAll(hbVozUm.getChildren());
        hbVozDois.getChildren().removeAll(hbVozDois.getChildren());
        setText(lbVozUm, "Voz 1", 20);
        setText(lbVozDois, "Voz 2", 20);
        hbVozUm.getChildren().add(lbVozUm);
        hbVozDois.getChildren().add(lbVozDois);
        lbMsg2.setVisible(false);
    }

    @FXML
    private void enviar(ActionEvent event) throws InterruptedException {

        hbVozUm.getChildren().removeAll(hbVozUm.getChildren());
        hbVozDois.getChildren().removeAll(hbVozDois.getChildren());
        if (tfEntrada.getText().equals("") | cbPrimeiraNota.getValue() == null) {
            lbMsg.setText("Nenhum dos campo pode ser vazio!");
            setHboxPadrao();
        } else {
            String cifra[] = tfEntrada.getText().split(" ");
            boolean notaErrada = false;
//            for (int i = 0; i < cifra.length; i++) {
//                if (!(escala.foiImplementado(cifra[i]))) {
//                    notaErrada = true;
//                }
//            }
//            if (notaErrada == true) {
//                lbMsg.setText("Alguma nota digitada está incorreta\nou ainda não foi implementada");
//                setHboxPadrao();
//            } else {
            lbMsg.setText("Vozes possíveis para essa cifra");
            Nota primeiraNotaCantada = new Nota(cbPrimeiraNota.getValue());
            String cf[] = tfEntrada.getText().split(" ");
            Nota primeiraTriade[] = new Nota(cf[0]).getTriade();

            if (primeiraTriade != null) {
                int cont = 0;
                for (int i = 0; i < primeiraTriade.length; i++) {
                    if (primeiraNotaCantada.getNota().equalsIgnoreCase(primeiraTriade[i].getNota())) {
                        cont++;
                    }
                }
                if (cont > 0) {
                    setLabels();
                    imprimirTriades();
                    criarVoz();
                    lbMsg2.setVisible(true);
                } else {
                    lbMsg.setText("A primeira nota cantada deve ser\numa das notas que compõem a triade\nda primeira nota na cifra!");
                    lbMsg.setAlignment(Pos.CENTER);
                    setText(lbVozUm, "Voz 1", 20);
                    setText(lbVozDois, "Voz 2", 20);
                    setHboxPadrao();
                }
            } else {
                lbMsg.setText("Alguma nota digitada está incorreta\nou ainda não foi implementada");
                setHboxPadrao();
            }
        }
//        }
    }

    private void setText(Label label, String texto, int tamanho) {
        label.setText(texto);
        label.setStyle("-fx-font-size: " + tamanho);
    }

    private void setLabels() {
        String primNota = tfEntrada.getText().split(" ")[0];
        Nota primTriade[] = new Nota(primNota).getTriade();

        int aux = 0;
        for (int i = 0; i < 3; i++) {
            if (primTriade[i].getNota().equalsIgnoreCase(cbPrimeiraNota.getValue())) {
                aux = i;
            }
        }
        Label l1 = null;
        Label l2 = null;
        switch (aux) {
            case 0:
                l1 = new Label("Terça");
                l2 = new Label("Quinta");

                break;
            case 1:
                l1 = new Label("Tônica");
                l2 = new Label("Quinta");

                break;
            case 2:
                l1 = new Label("Tônica");
                l2 = new Label("Terça");
                break;
        }

        l1.setStyle("-fx-font-size: 20");
        l2.setStyle("-fx-font-size: 20");
        l1.setTextFill(Color.WHITE);
        l2.setTextFill(Color.WHITE);

        hbVozUm.getChildren().add(l1);
        hbVozDois.getChildren().add(l2);

    }

    public void criarChoiceBox(Nota notaPai, Nota notaCriada, HBox local) {
        ObservableList<String> obsList = FXCollections.observableArrayList();
        Nota triade[] = notaPai.getTriade();
        for (Nota n : triade) {
            obsList.add(n.getNota());
        }
        ComboBox cbTriade = new ComboBox();
        cbTriade.setItems(obsList);
        cbTriade.setValue(notaCriada.getNota());
        local.getChildren().add(cbTriade);
    }

    public void criarVoz() {
        Nota primeiraNotaCantada = new Nota(cbPrimeiraNota.getValue());
        String cifra[] = tfEntrada.getText().split(" ");
        Nota primeiraTriade[] = new Nota(cifra[0]).getTriade();

        Nota duoRestante[] = escala.getPossibilidades(new Nota(cifra[0]).getTriade(), primeiraNotaCantada);
        for (String nota : cifra) {
            Nota[] proxTriade = new Nota(nota).getTriade();
            if (proxTriade != null) {
                criarChoiceBox(new Nota(nota), new Nota(escala.getNotaMaisProxima(duoRestante[0], proxTriade)), hbVozUm);
                criarChoiceBox(new Nota(nota), new Nota(escala.getNotaMaisProxima(duoRestante[1], proxTriade)), hbVozDois);
            } else {
                lbMsg.setText("Alguma nota digitada está incorreta\nou ainda não foi implementada");
                setHboxPadrao();
            }
        }
    }

    public void imprimirTriades() {
        String cifra[] = tfEntrada.getText().split(" ");
        String saida = "";
        for (String c : cifra) {
            Nota n = new Nota(c);
            if (n != null) {
                if (!(n.isNulo())) {
                    saida += c + " - " + Arrays.toString(n.getTriade()) + "\n";
                }
            }
        }
        tfSaida.setText(saida);
    }

//    @FXML
//    private void adicionarNota(ActionEvent event) {
//        if (tfEntrada.getText().equals("")) {
//            tfEntrada.setText(cbNotasNaturais.getValue());
//        } else {
//            tfEntrada.setText(tfEntrada.getText() + " " + cbNotasNaturais.getValue());
//        }
//    }
    @FXML
    private void alteracaoEntrada(KeyEvent event) {
    }

    //Adiciona uma nova nota ao txt que contém todas as notas implementadas
    @FXML
    private void adicionarNovaNota() {

    }
}
