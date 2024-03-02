package gui;

import entities.Commande;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.ServiceCommande;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import java.io.File;
import java.io.FileOutputStream;


public class itemCommandeController implements Initializable {

    @FXML
    private Button btnModifierCmd;

    @FXML
    private Button btnSupprimerCmd;

    @FXML
    private AnchorPane itemCommandePane;

    @FXML
    private Label labelDateCmd;

    @FXML
    private Label labelDescriptionCmd;

    @FXML
    private Label labelProduitCmd;

    @FXML
    private Label labelStatutCmd;

    @FXML
    private Label labelUserCmd;


    Commande cmd;
    public void setData (Commande commande){
        this.cmd = commande ;

        labelStatutCmd.setText(commande.getStatut());
        labelDateCmd.setText(String.valueOf(commande.getDate()));
        labelDescriptionCmd.setText(commande.getDescription());
        labelProduitCmd.setText(String.valueOf(commande.getIdProduit()));
        labelUserCmd.setText(String.valueOf(commande.getIdUser()));
    }


    public Commande getData (Commande commande){
        this.cmd = commande ;
        return this.cmd;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void open_UpdateCmd(ActionEvent event) throws IOException {
        Parent fxml= FXMLLoader.load(getClass().getResource("updateCommande.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Update Commande");
        stage.setScene(new Scene(fxml));
        stage.showAndWait();
    }

    @FXML
    void supprimerCmd(ActionEvent event) throws SQLException {
        ServiceCommande sc = new ServiceCommande();

        // Afficher une boîte de dialogue de confirmation
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Voulez-vous vraiment supprimer cette commande ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Récupérer l'ID de la commande sélectionnée
            int id = this.cmd.getId();

            // Supprimer la commande de la base de données
            sc.supprimer(id);
        }
    }

    @FXML
    void genererPDF(MouseEvent event) {
        // Afficher la boîte de dialogue de sélection de fichier
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Enregistrer le fichier PDF");
        fileChooser.getExtensionFilters().add(new ExtensionFilter("Fichiers PDF", "*.pdf"));
        File selectedFile = fileChooser.showSaveDialog(((Node) event.getSource()).getScene().getWindow());

        if (selectedFile != null) {
            // Générer le fichier PDF avec l'emplacement de sauvegarde sélectionné
            // Récupérer la liste des produits
            ServiceCommande as = new ServiceCommande();
            int id = this.cmd.getId();
            Commande cmd = null;

            cmd = as.getById(id);

            try {
                // Créer le document PDF
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(selectedFile));
                document.open();
                // Créer une instance de l'image
                Image image = Image.getInstance(System.getProperty("user.dir") + "/src/main/resources/gui/img/logo.jpg");
                // Positionner l'image en haut à gauche
                image.setAbsolutePosition(5, document.getPageSize().getHeight() - 120);
                // Modifier la taille de l'image
                image.scaleAbsolute(152, 100);
                // Ajouter l'image au document
                document.add(image);

                // Créer une police personnalisée pour la date
                Font fontDate = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
                BaseColor color = new BaseColor(114, 0, 0); // Rouge: 114, Vert: 0, Bleu: 0
                fontDate.setColor(color); // Définir la couleur de la police

                // Créer un paragraphe avec le lieu
                Paragraph tunis = new Paragraph("Ariana", fontDate);
                tunis.setIndentationLeft(455); // Définir la position horizontale
                tunis.setSpacingBefore(-30); // Définir la position verticale
                // Ajouter le paragraphe au document
                document.add(tunis);

                // Obtenir la date d'aujourd'hui
                LocalDate today = LocalDate.now();

                // Créer un paragraphe avec la date
                Paragraph date = new Paragraph(today.toString(), fontDate);

                date.setIndentationLeft(437); // Définir la position horizontale
                date.setSpacingBefore(1); // Définir la position verticale
                // Ajouter le paragraphe au document
                document.add(date);

                // Créer une police personnalisée
                Font font = new Font(Font.FontFamily.TIMES_ROMAN, 32, Font.BOLD);
                BaseColor titleColor = new BaseColor(114, 0, 0); //
                font.setColor(titleColor);

                // Ajouter le contenu au document
                Paragraph title = new Paragraph("Facture", font);
                title.setAlignment(Element.ALIGN_CENTER);
                title.setSpacingBefore(50);
                title.setSpacingAfter(20);
                document.add(title);

                PdfPTable table = new PdfPTable(3);
                table.setWidthPercentage(100);
                table.setSpacingBefore(30f);
                table.setSpacingAfter(30f);

                // Ajouter les en-têtes de colonnes
                Font hrFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
                BaseColor hrColor = new BaseColor(255, 255, 255); //
                hrFont.setColor(hrColor);

                PdfPCell cell1 = new PdfPCell(new Paragraph("Statut", hrFont));
                BaseColor bgColor = new BaseColor(114, 0, 0);
                cell1.setBackgroundColor(bgColor);
                cell1.setBorderColor(titleColor);
                cell1.setPaddingTop(20);
                cell1.setPaddingBottom(20);
                cell1.setHorizontalAlignment(Element.ALIGN_CENTER);

                PdfPCell cell2 = new PdfPCell(new Paragraph("Date", hrFont));
                cell2.setBackgroundColor(bgColor);
                cell2.setBorderColor(titleColor);
                cell2.setPaddingTop(20);
                cell2.setPaddingBottom(20);
                cell2.setHorizontalAlignment(Element.ALIGN_CENTER);

                PdfPCell cell3 = new PdfPCell(new Paragraph("Description", hrFont));
                cell3.setBackgroundColor(bgColor);
                cell3.setBorderColor(titleColor);
                cell3.setPaddingTop(20);
                cell3.setPaddingBottom(20);
                cell3.setHorizontalAlignment(Element.ALIGN_CENTER);

                table.addCell(cell1);
                table.addCell(cell2);
                table.addCell(cell3);

                Font hdFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.NORMAL);
                BaseColor hdColor = new BaseColor(255, 255, 255); //
                hrFont.setColor(hdColor);

                PdfPCell cellR1 = new PdfPCell(new Paragraph(String.valueOf(cmd.getStatut()), hdFont));
                cellR1.setBorderColor(titleColor);
                cellR1.setPaddingTop(10);
                cellR1.setPaddingBottom(10);
                cellR1.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cellR1);

                PdfPCell cellR2 = new PdfPCell(new Paragraph(String.valueOf(cmd.getDate()), hdFont));
                cellR2.setBorderColor(titleColor);
                cellR2.setPaddingTop(10);
                cellR2.setPaddingBottom(10);
                cellR2.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cellR2);

                PdfPCell cellR3 = new PdfPCell(new Paragraph(String.valueOf(cmd.getDescription()), hdFont));
                cellR3.setBorderColor(titleColor);
                cellR3.setPaddingTop(10);
                cellR3.setPaddingBottom(10);
                cellR3.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cellR3);

                table.setSpacingBefore(20);
                document.add(table);
                document.close();

                System.out.println("Le fichier PDF a été généré avec succès.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }


}
