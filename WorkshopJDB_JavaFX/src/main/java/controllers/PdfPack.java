package controllers;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.*;
import entities.Pack;

import java.io.FileOutputStream;
import java.util.List;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;


public class PdfPack {

    public void generatePdf(List<Pack> packs) {
        Document document = new Document();
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("packs_promotion.pdf"));

            // Définir la couleur de la bordure de la page en orange
            writer.setPageEvent(new PdfPageEvent() {
                public void onStartPage(PdfWriter writer, Document document) {
                    PdfContentByte cb = writer.getDirectContent();
                    cb.setColorStroke(new BaseColor(0, 0, 255)); // Couleur orange (255,165,0)
                    cb.setLineWidth(2); // Épaisseur de la bordure
                    cb.rectangle(document.left(), document.bottom(), document.right() - document.left(), document.top() - document.bottom());
                    cb.stroke();
                }

                public void onEndPage(PdfWriter writer, Document document) {
                }

                public void onOpenDocument(PdfWriter writer, Document document) {
                }

                public void onCloseDocument(PdfWriter writer, Document document) {
                }

                public void onParagraph(PdfWriter writer, Document document, float paragraphPosition) {
                }

                public void onParagraphEnd(PdfWriter writer, Document document, float paragraphPosition) {
                }

                public void onChapter(PdfWriter writer, Document document, float paragraphPosition, Paragraph title) {
                }

                public void onChapterEnd(PdfWriter writer, Document document, float paragraphPosition) {
                }

                public void onSection(PdfWriter writer, Document document, float paragraphPosition, int depth, Paragraph title) {
                }

                public void onSectionEnd(PdfWriter writer, Document document, float paragraphPosition) {
                }

                public void onGenericTag(PdfWriter writer, Document document, Rectangle rect, String text) {
                }
            });

            document.open();

            // Définir le style pour le titre
            Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, BaseColor.BLUE);
            Paragraph title = new Paragraph("Promotion à 50% sur tous les packs", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            // Ajouter le nom de l'organisation en dessous à gauche
            Font orgFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLUE);
            Paragraph org = new Paragraph("SMART HOME", orgFont);
            org.setAlignment(Element.ALIGN_LEFT);
            org.setSpacingBefore(10); // Espacement avant le paragraphe
            document.add(org);

            // Ajouter de l'espace avant le tableau
            document.add(new Paragraph("\n"));

            // Créer un tableau pour les détails des packs
            PdfPTable table = new PdfPTable(2); // 2 colonnes pour la description et le prix
            table.setWidthPercentage(100); // Tableau prend 100% de la largeur de la page

            // Définir la police de caractères pour le contenu du tableau
            Font contentFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.BLACK);

            // Ajouter les en-têtes du tableau
            PdfPCell cellDescriptionHeader = new PdfPCell(new Phrase("Description", contentFont));
            PdfPCell cellPriceHeader = new PdfPCell(new Phrase("Prix après promotion (TND)", contentFont));
            table.addCell(cellDescriptionHeader);
            table.addCell(cellPriceHeader);

            // Ajouter les détails des packs dans le tableau
            for (Pack pack : packs) {
                PdfPCell cellDescription = new PdfPCell(new Phrase(pack.getDescription(), contentFont));
                PdfPCell cellPrice = new PdfPCell(new Phrase(String.format("%.2f TND", pack.getPrix() * 0.5f), contentFont)); // Appliquer la réduction de 50% et afficher en TND
                cellPrice.setBackgroundColor(BaseColor.RED); // Couleur rouge pour le prix
                table.addCell(cellDescription);
                table.addCell(cellPrice);
            }

            // Ajouter le tableau au document
            document.add(table);

            document.close();
            System.out.println("PDF généré avec succès !");
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}