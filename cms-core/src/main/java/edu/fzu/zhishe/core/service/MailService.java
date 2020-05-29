package edu.fzu.zhishe.core.service;

/**
 * @author xjliang(gnulxj @ gmail.com)
 * @date 5/29/2020
 */
public interface MailService {

    void sendSimpleMail(String to, String subject, String content);

    void sendHtmlMail(String to, String subject, String content);
}
