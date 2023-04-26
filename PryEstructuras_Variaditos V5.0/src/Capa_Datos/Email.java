/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa_Datos;

import Capa_Cliente.Enviar_Gmail;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;

/**
 *
 * @author Gianx
 */
public class Email {
    private static String emailFrom = "correo";
    private static String passwordFrom = "****************";
    private static String emailTo;
    private static String subject;
    private static String content;
    private static String ubiArchivo,nombArchivo;

    
    public String getNombArchivo() {
        return nombArchivo;
    }

    public void setNombArchivo(String nombArchivo) {
        Email.nombArchivo = nombArchivo;
    }

    public  String getUbiArchivo() {
        return ubiArchivo;
    }

    public  void setUbiArchivo(String ubiArchivo) {
        Email.ubiArchivo = ubiArchivo;
    }
    
    public String getEmailFrom() {
        return emailFrom;
    }

    public void setEmailFrom(String emailFrom) {
        Email.emailFrom = emailFrom;
    }

    public String getPasswordFrom() {
        return passwordFrom;
    }

    public void setPasswordFrom(String passwordFrom) {
        Email.passwordFrom = passwordFrom;
    }

    public String getEmailTo() {
        return emailTo;
    }

    public void setEmailTo(String emailTo) {
        this.emailTo = emailTo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    
    
    public static void createEmail() throws Exception {
        Properties mProperties = new Properties();
         // Simple mail transfer protocol
        mProperties.put("mail.smtp.host", "smtp.gmail.com");
        mProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        mProperties.setProperty("mail.smtp.starttls.enable", "true");
        mProperties.setProperty("mail.smtp.port", "587");
        mProperties.setProperty("mail.smtp.user",emailFrom);
        mProperties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        mProperties.setProperty("mail.smtp.auth", "true");
        
        try {
            Session mSession;
            mSession = Session.getDefaultInstance(mProperties);
            
     
            BodyPart texto = new MimeBodyPart();
            texto.setText(content);
            BodyPart adjunto = new MimeBodyPart();
            adjunto.setDataHandler(new DataHandler(new FileDataSource(ubiArchivo)));
            adjunto.setFileName(nombArchivo);
                    
            MimeMultipart multiParte = new MimeMultipart();
            multiParte.addBodyPart(texto);
            multiParte.addBodyPart(adjunto);
            
            MimeMessage mCorreo;
            mCorreo = new MimeMessage(mSession);
            mCorreo.setFrom(new InternetAddress(emailFrom));
            mCorreo.setRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
            
            mCorreo.setSubject(subject);
            //mCorreo.setText(content, "ISO-8859-1", "html");
            mCorreo.setContent(multiParte);
            
            Transport mTransport = mSession.getTransport("smtp");
            mTransport.connect(emailFrom, passwordFrom);
            mTransport.sendMessage(mCorreo, mCorreo.getRecipients(Message.RecipientType.TO));
            mTransport.close();
            
            JOptionPane.showMessageDialog(null, "Correo enviado");
        } catch (AddressException ex) {
            Logger.getLogger(Enviar_Gmail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(Enviar_Gmail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    
}
