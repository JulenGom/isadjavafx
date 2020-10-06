package ehu.isad;

import com.google.gson.Gson;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


public class ComboBoxExperiments extends Application  {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("ComboBox Experiment 1");

        ComboBox comboBox = new ComboBox();
        Label textua=new Label("");
        comboBox.getItems().add("BTC");
        comboBox.getItems().add("eth");
        comboBox.getItems().add("ltc");

        comboBox.setEditable(true);

        comboBox.setOnAction(e -> {
            try {
                Txanpona moneda;
                moneda =readFromUrl((String) comboBox.getValue());
                textua.setText("1" + comboBox.getValue() + "=" + moneda.price);
                System.out.println(moneda.price);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        HBox hbox = new HBox(comboBox, textua);

        Scene scene = new Scene(hbox, 200, 120);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {

        Application.launch(args);
    }
    private static Txanpona readFromUrl(String txanpon) throws IOException {

        String inputLine;

        URL coinmarket = new URL("https://api.gdax.com/products/"
                + txanpon + "-eur/ticker");
        URLConnection yc = coinmarket.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(
                yc.getInputStream()));
        inputLine = in.readLine();
        in.close();

        Gson gson = new Gson();
        return gson.fromJson(inputLine, Txanpona.class);

    }
    public class Txanpona{
        @Override
        public String toString() {
            return "Txanpona{" +
                    "trade_id=" + trade_id +
                    ", price=" + price +
                    ", size=" + size +
                    ", time='" + time + '\'' +
                    ", bid=" + bid +
                    ", ask=" + ask +
                    ", volume=" + volume +
                    '}';
        }

        int trade_id;
        float price;
        float size;
        String time;
        float bid;
        float ask;
        float volume;
    }

}
