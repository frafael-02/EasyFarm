package com.example.testapp.api;



import javax.activation.DataHandler;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.util.ByteArrayDataSource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWriterExample {
    public static void writeExcelFileAndSendByEmail() {
        new Thread(new Runnable(){
            @Override
            public void run() {
                try {Workbook workbook = new XSSFWorkbook();

                    // Create a new sheet
                    Sheet sheet = workbook.createSheet("Evidencija");

                    // Create the header row
                    Row headerRow = sheet.createRow(0);
                    headerRow.createCell(0).setCellValue("Name");
                    headerRow.createCell(1).setCellValue("Age");
                    headerRow.createCell(2).setCellValue("Email");

                    // Add some data
                    Row dataRow = sheet.createRow(1);
                    dataRow.createCell(0).setCellValue("John Doe");
                    dataRow.createCell(1).setCellValue(30);
                    dataRow.createCell(2).setCellValue("john.doe@example.com");

                    // Create a byte array output stream to store the workbook as bytes
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    try {
                        workbook.write(outputStream);
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    // Convert the byte array to a byte array input stream
                    ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());

                    // Set the email properties
                    String fromEmail = "testingaccfr@gmail.com";
                    String fromPassword = "rtvvetfvmpvpxrly";
                    String toEmail = "filiprafael781@gmail.com";
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
                        attachmentPart.setDataHandler(new DataHandler(new ByteArrayDataSource(inputStream, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")));

                        //attachmentPart.setContent(inputStream, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                        attachmentPart.setFileName("example.xlsx");
                        multipart.addBodyPart(attachmentPart);

                        // Add the body of the message
                        MimeBodyPart messagePart = new MimeBodyPart();
                        messagePart.setText(body);
                        multipart.addBodyPart(messagePart);

                        // Set the multipart message as the content of the email
                        message.setContent(multipart);

                        // Send the email
                        Transport.send(message);
                        System.out.println("email sent");
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }

                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }).start();


    }
}
