package com.mmm.server.common.util;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public class MailUtil {
    private static final String HOST = "smtp.163.com";
    private static final Integer PORT = 25;
    private static final String USERNAME = "nihaozhouyi@163.com";
    private static final String PASSWORD = "zhouyi123";
    private static final String EMAILFORM = "nihaozhouyi@163.com";
    private static JavaMailSenderImpl mailSender = createMailSender();

    /**
     * 邮件发送器
     *
     * @return 配置好的工具
     */
    private static JavaMailSenderImpl createMailSender() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(HOST);
        sender.setPort(PORT);
        sender.setUsername(USERNAME);
        sender.setPassword(PASSWORD);
        sender.setDefaultEncoding("Utf-8");
        Properties p = new Properties();
        p.setProperty("mail.smtp.timeout", "25000");
        p.setProperty("mail.smtp.auth", "false");
        sender.setJavaMailProperties(p);
        return sender;
    }

    /**
     * 发送邮件
     *
     * @param to
     *            接受人
     * @param subject
     *            主题
     * @param html
     *            发送内容
     * @throws MessagingException
     *             异常
     * @throws UnsupportedEncodingException
     *             异常
     */
    public static void sendHtmlMail(String to, String subject, String html)
    {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        // 设置utf-8或GBK编码，否则邮件会有乱码
        MimeMessageHelper messageHelper;
        try {
            messageHelper = new MimeMessageHelper(mimeMessage,
                    true, "UTF-8");
            messageHelper.setFrom(EMAILFORM, "系统名称");
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            messageHelper.setText(html, true);
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        mailSender.send(mimeMessage);
    }

    public static void main(String args[]){
        // 测试
        sendHtmlMail("1585195399@qq.com","澳门首家线上赌场上线了","给你不一样的全新体验");
        System.out.println("发送成功");
    }
}
