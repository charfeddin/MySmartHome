package com.example.rafafx;

import com.example.rafafx.entities.Post;
import com.example.rafafx.services.PostService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import javafx.scene.image.Image;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import java.io.File;

public class PostDialog {
    public JFXButton qrCodeButton;
    Post li=new Post();
    public JFXTextField description;
    public Label selectedImageLabel;
    public JFXButton add;
    public JFXButton chooseImageButton;
    public JFXButton Annuler;
    public JFXButton retour;
    public JFXTextField titre;
    private PostService postService=new PostService();

    public void AddPost(ActionEvent actionEvent) throws IOException, WriterException {
        Post P =new Post();
        Boolean valid=true;
        titre.resetValidation();
        description.resetValidation();



        if(titre.getText().isEmpty()||description.getText().isEmpty())

        {
            titre.validate();description.validate();
            valid=false;
        }

        if(add.getText()!="Modifier"&&valid) {

            Post p = new Post();
            p.setTitre(titre.getText());
            p.setDescription(description.getText());
            p.setImage(selectedImageLabel.getText());
            String QrDta=p.getTitre()+p.getDescription();

            postService.ajouter(p);
            vider();
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);alert1.setHeaderText(null);
            alert1.setContentText("Post Ajouté avec réussite");
            alert1.showAndWait();


            Image qrImage = generateQRCodeImage(QrDta, 200, 200);

            // Display QR Code in a new window
            Stage qrStage = new Stage();
            ImageView qrView = new ImageView(qrImage);
            StackPane root = new StackPane();
            root.getChildren().add(qrView);

            Scene scene = new Scene(root, 250, 250);

            qrStage.setTitle("QR Code JavaFX");
            qrStage.setScene(scene);
            qrStage.show();



            Stage stage = (Stage) add.getScene().getWindow();
            stage.close();

        } else if (add.getText()=="Modifier"&&valid) {

           Post updated=new Post(titre.getText(),description.getText(),selectedImageLabel.getText());
            postService.modifier(updated);
            vider();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);alert.setHeaderText(null);
            alert.setContentText("Post Modifiée Avec réussite  ");alert.showAndWait();
            Stage stage = (Stage) add.getScene().getWindow();
            stage.close();

        }
        
    }



    public void Retour(ActionEvent actionEvent) {
        retour.getScene().getWindow().hide();
    }

    public void chooseImage(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Image");
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            selectedImageLabel.setText(file.getName());
            // You can do further processing with the selected image file here
        } else {
            selectedImageLabel.setText("No image selected");
        }
    }
    public void vider() {
        titre.clear();description.clear();
        titre.resetValidation();

        description.resetValidation();

    }
    public void setUpdate(String modifier) {
        add.setText(modifier.toString());
    }
    public void setcommd(Post c) {
        this.li=c;

        titre.setText(li.getTitre());
        description.setText(li.getDescription());
       selectedImageLabel.setText(li.getImage());

    }



    public void SetVisibilite(boolean b) {
        Annuler.setVisible(b);
    }

    public Image generateQRCodeImage(String text, int width, int height) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height, hints);

        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
        byte[] pngData = pngOutputStream.toByteArray();

        return new Image(new ByteArrayInputStream(pngData));
    }

    public void generateQRCodeAction(ActionEvent actionEvent) {
        try {
            // Generate QR Code Image
            Image qrImage = generateQRCodeImage("Hello World", 200, 200);

            // Display QR Code in a new window
            Stage qrStage = new Stage();
            ImageView qrView = new ImageView(qrImage);
            StackPane root = new StackPane();
            root.getChildren().add(qrView);

            Scene scene = new Scene(root, 250, 250);

            qrStage.setTitle("QR Code JavaFX");
            qrStage.setScene(scene);
            qrStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
