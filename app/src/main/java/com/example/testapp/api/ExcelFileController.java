package com.example.testapp.api;

import android.content.Context;

import com.example.testapp.entiteti.Evidencija;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class ExcelFileController {

    private static FirebaseStorage storage = FirebaseStorage.getInstance();

    public static void writeExcelFileAndSendByEmail(Context context, String toEmail, List<Evidencija> evidencijaList) {
        new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    // Download the Excel file from Firebase Storage
                    StorageReference storageRef = storage.getReference().child("evidencija.xlsx");
                    File localFile = File.createTempFile("evidencija", "xlsx");
                    storageRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            try {
                                System.out.println("file downloaded");
                                // Edit the Excel file
                                FileInputStream inputStream = new FileInputStream(localFile);
                                Workbook workbook = new XSSFWorkbook(inputStream);
                                int numberOfRecords = evidencijaList.size();
                                CellStyle style = workbook.createCellStyle();
                                style.setBorderTop(BorderStyle.THIN);
                                style.setBorderBottom(BorderStyle.THIN);
                                style.setBorderLeft(BorderStyle.THIN);
                                style.setBorderRight(BorderStyle.THIN);
                                Sheet sheet = workbook.getSheetAt(0);
                                Row firstRow = sheet.getRow(0);
                                Cell cell = firstRow.getCell(0);
                                String sentence = cell.getStringCellValue();
                                String updatedSentence = sentence.replace("xx", "2023");
                                cell.setCellValue(updatedSentence);

                                Row emptyRow = sheet.getRow(5);

                                for(int i=0;i<numberOfRecords;i++)
                                {

                                    Row newRow = sheet.createRow(5+i);

                                    newRow.setRowStyle(emptyRow.getRowStyle());
                                    for(int j=0;j< emptyRow.getLastCellNum();j++)
                                    {
                                        Cell cell1 = emptyRow.getCell(j);
                                        Cell newCell = newRow.createCell(j);
                                        newCell.setCellStyle(cell1.getCellStyle());
                                        newCell.setCellValue(cell1.getStringCellValue());
                                    }

                                }

                                int br=0;
                                for(int i=5;i<5+numberOfRecords;i++)
                                {

                                    Row row = sheet.getRow(i);

                                    Cell brCell = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                                    brCell.setCellValue(br+1);

                                    brCell.setCellStyle(style);
                                    Cell kulturaCell = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                                    kulturaCell.setCellValue(evidencijaList.get(br).getImeBiljke());
                                    kulturaCell.setCellStyle(style);
                                    Cell nazivCell = row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                                    nazivCell.setCellValue(evidencijaList.get(br).getPoljeIme());
                                    Cell arkodCell = row.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                                    arkodCell.setCellValue(evidencijaList.get(br).getArkodId());
                                    Cell povrsinaCell = row.getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                                    povrsinaCell.setCellValue(evidencijaList.get(br).getTretiranaPovrsina());
                                    Cell pesticidCell = row.getCell(5, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                                    pesticidCell.setCellValue(evidencijaList.get(br).getImePesticida());
                                    Cell datumCell = row.getCell(6, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                                    String datum = evidencijaList.get(br).getVrijemeStart().format(dateTimeFormatter);
                                    datumCell.setCellValue(datum);
                                    dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
                                    String pocetakString = evidencijaList.get(br).getVrijemeStart().format(dateTimeFormatter);
                                    Cell pocetakCell = row.getCell(7, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                                    pocetakCell.setCellValue(pocetakString);
                                    Cell krajCell = row.getCell(8, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

                                    String krajString = evidencijaList.get(br).getVrijemeKraj().format(dateTimeFormatter);
                                    krajCell.setCellValue(krajString);
                                    Cell dozaCell = row.getCell(9, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                                    dozaCell.setCellValue(evidencijaList.get(br).getKoristenaDoza());
                                    dozaCell.setCellStyle(style);
                                    krajCell.setCellStyle(style);
                                    pocetakCell.setCellStyle(style);
                                    datumCell.setCellStyle(style);
                                    pesticidCell.setCellStyle(style);
                                    povrsinaCell.setCellStyle(style);
                                    arkodCell.setCellStyle(style);
                                    nazivCell.setCellStyle(style);
                                    br++;
                                }







                                inputStream.close();

                                // Save the edited Excel file
                                FileOutputStream outputStream = new FileOutputStream(localFile);
                                workbook.write(outputStream);
                                outputStream.close();

                                // Send the edited Excel file via email
                                sendExcelFileByEmail(context, localFile, toEmail);

                                // Delete the local Excel file
                                workbook.close();
                               // localFile.delete();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }

    private static void sendExcelFileByEmail(Context context, File file, String toEmail) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                // Set the email properties
                String fromEmail = "testingaccfr@gmail.com";
                String fromPassword = "rtvvetfvmpvpxrly";

                String subject = "EasyFarm-Evidencija";
                String body = "Vaša evidencija se nalazi u privitku.";

                Properties props = new Properties();
                props.put("mail.smtp.host", "smtp.gmail.com");
                props.put("mail.smtp.port", "587");
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.starttls.enable", "true");

                // Create a new session with the email server
                Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(fromEmail, fromPassword);
                    }
                });

                // Create a new message
                Message message = new MimeMessage(session);
                try {
                    message.setFrom(new InternetAddress(fromEmail));
                    message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
                    message.setSubject(subject);

                    // Create a multipart message
                    Multipart multipart = new MimeMultipart();

                    // Add the Excel file as an attachment
                    MimeBodyPart attachmentPart = new MimeBodyPart();
                    attachmentPart.setDataHandler(new DataHandler(new FileDataSource(file)));
                    attachmentPart.setFileName("evidencija.xlsx");
                    multipart.addBodyPart(attachmentPart);

                    // Add the body of the message
                    MimeBodyPart messagePart = new MimeBodyPart();
                    messagePart.setText(body);
                    multipart.addBodyPart(messagePart);

                    // Set the multipart message as the content of the email
                    message.setContent(multipart);

                    // Send the email
                    Transport.send(message);
                    file.delete();


                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        }).start();



    }
}

