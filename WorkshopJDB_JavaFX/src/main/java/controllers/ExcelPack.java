package controllers;

import entities.Pack;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelPack {

    public void generateExcel(List<Pack> packs) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Packs");

            // Créer le style pour le titre en rouge
            CellStyle titleStyle = workbook.createCellStyle();
            Font titleFont = workbook.createFont();
            titleFont.setColor(IndexedColors.RED.getIndex());
            titleStyle.setFont(titleFont);

            // Créer le style pour les en-têtes de colonne en bleu
            CellStyle headerStyle = workbook.createCellStyle();
            Font headerFont = workbook.createFont();
            headerFont.setColor(IndexedColors.BLUE.getIndex());
            headerStyle.setFont(headerFont);

            // Créer l'en-tête du fichier Excel avec le titre rouge
            Row headerRow = sheet.createRow(0);
            Cell titleCell = headerRow.createCell(0);
            titleCell.setCellValue("Liste des Packs");
            titleCell.setCellStyle(titleStyle);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3)); // Fusionner les cellules pour le titre

            // Créer les en-têtes des colonnes en bleu
            String[] columns = { "Description", "Prix", "Categorie", "Disponibilite"};
            Row columnRow = sheet.createRow(1);
            for (int i = 0; i < columns.length; i++) {
                Cell cell = columnRow.createCell(i);
                cell.setCellValue(columns[i]);
                cell.setCellStyle(headerStyle); // Appliquer le style d'en-tête
            }

            // Remplir les données des packs dans le fichier Excel
            int rowNum = 2; // Commencer à partir de la troisième ligne après le titre et les en-têtes
            for (Pack pack : packs) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(pack.getDescription());
                row.createCell(1).setCellValue(pack.getPrix());
                row.createCell(2).setCellValue(pack.getNom_categorie().toString());
                row.createCell(3).setCellValue(pack.getDispo().toString());
            }

            // Ajuster la largeur des colonnes
            for (int i = 0; i < columns.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // Écrire le contenu dans le fichier Excel
            try (FileOutputStream fileOut = new FileOutputStream("packs.xlsx")) {
                workbook.write(fileOut);
                System.out.println("Fichier Excel généré avec succès !");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
