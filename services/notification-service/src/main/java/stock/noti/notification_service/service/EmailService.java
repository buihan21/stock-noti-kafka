package stock.noti.notification_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import stock.noti.notification_service.dto.StockAlertEvent;

@Service
public class EmailService {
    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendAlertEmail(StockAlertEvent alert) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(alert.getEmail());
        message.setSubject("Stock Alert: " + alert.getSymbol());
        message.setText("Giá cổ phiếu " + alert.getSymbol() + " đã đạt mức " + alert.getTargetPrice());
        mailSender.send(message);
    }

}
