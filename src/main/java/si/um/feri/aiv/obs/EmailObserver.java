package si.um.feri.aiv.obs;
import si.um.feri.aiv.vao.Community;
import si.um.feri.aiv.vao.MSE;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@ApplicationScoped
public class EmailObserver implements Observer{
    @Resource(lookup = "java:jboss/mail/StudMail")
    private Session mailSession;

    @Override
    public void update(Object arg) {
        if (arg instanceof Community) {
            Community community = (Community) arg;
            for (MSE mse : community.getIncludedMSEs()) {
                try {
                    MimeMessage message = new MimeMessage(mailSession);
                    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mse.getEmail()));
                    message.setSubject("New MSE has been added in our great community");
                    message.setText("We inform you that a new mse has been added to the community '" + community.getCommunityName());
                    Transport.send(message);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
