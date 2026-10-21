package uy.utec.pa.servicio;



import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class ServicioCorreo {

    private final String correoEmisor = "secu0576@gmail.com";
    private final String password = "pkvcsoqiitcxuzll";


    public void enviarCorreo(String destinatario, String asunto, String contenido) {

        Properties props = new Properties();

        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(correoEmisor, password);
            }
        });

        try {
            Message mensaje = new MimeMessage(session);

            mensaje.setFrom(new InternetAddress(correoEmisor));
            mensaje.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(destinatario)
            );

            mensaje.setSubject(asunto);
            mensaje.setText(contenido);

            Transport.send(mensaje);

            System.out.println("Correo enviado correctamente.");

        } catch (MessagingException e) {
            System.out.println("Error al enviar correo: " + e.getMessage());
        }
    }
}