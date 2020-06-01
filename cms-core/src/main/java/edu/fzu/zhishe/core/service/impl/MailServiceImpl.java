package edu.fzu.zhishe.core.service.impl;

import edu.fzu.zhishe.common.exception.Asserts;
import edu.fzu.zhishe.core.service.MailService;
import java.util.Collection;
import java.util.Map;
import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import javax.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 * @author liang
 */
@Service
public class MailServiceImpl implements MailService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public void sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);

        try {
            mailSender.send(message);
            logger.info("send mail from {} to {} successfully.", from, to);
        } catch (Exception e) {
            logger.error("Exception occurred while sending email", e);
            Asserts.fail("send mail failed!");
        }
    }

    @Override
    public void sendHtmlMail(String to, String subject, String content) {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            // true 表示需要创建一个 multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            mailSender.send(message);
            logger.info("send html email successfully");
        } catch (MailSendException e) {
            Exception[] messageExceptions = e.getMessageExceptions();
            if (messageExceptions.length != 0) {
                logger.error(messageExceptions[0].getMessage() + " " + to);
                Asserts.fail(messageExceptions[0].getMessage());
            } else {
                logger.error("Exception occurred while sending email", e);
                Asserts.fail("Exception occurred while sending email");
            }
        } catch (MessagingException e) {
            logger.error("Exception occurred while sending email", e);
        }
    }
}
