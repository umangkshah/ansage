import java.util.Date;
import java.util.Properties;
 
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
/**
 * A utility class for sending e-mail messages
 * @author www.codejava.net
 *
 */
public class EmailUtility {
    public static void sendEmail(String host, String port,
            final String userName, final String password, String reqs[], String owner,
            String subject, String message1, String message2) throws AddressException,
            MessagingException {
 
        // sets SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
 
        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };
        Session session = Session.getInstance(properties, auth);
        
        //Email Bidders
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(userName));
        for(int i=0;i < reqs.length; i++){
        	InternetAddress[] toAddresses = { new InternetAddress(reqs[i]) };
            msg.setRecipients(Message.RecipientType.BCC, toAddresses);
        }
        msg.setSubject(subject);
        msg.setSentDate(new Date());
        msg.setText(message1);
        Transport.send(msg);
        
        //Email Question Owner
        Message msg2 = new MimeMessage(session);
        msg2.setFrom(new InternetAddress(userName));
        
        	InternetAddress[] toAddresses = { new InternetAddress(owner) };
            msg2.setRecipients(Message.RecipientType.TO, toAddresses);
        
        msg2.setSubject(subject);
        msg2.setSentDate(new Date());
        msg2.setText(message2);
        Transport.send(msg2);
 
    }
}