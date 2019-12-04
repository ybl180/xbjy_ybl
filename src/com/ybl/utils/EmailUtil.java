package com.ybl.utils;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

/**
 * @author admin
 * @version 1.0.1
 * @company 东方标准
 * @date 2019/12/3 11:54
 * @description
 */
public class EmailUtil {

    /***
     *@desciption
     *@author ybl
     *@date 2019/12/3 15:38
     *@param [emailName 收件人, code 验证码]
     *@return void
     */
    public static void sendEmail(String emailName, String code) {
        try {
            //设置发件人
            String from = "1609632141@qq.com";
            //设置收件人
            String to = emailName;
            //设置邮件发送的服务器，这里为QQ邮件服务器
            String host = "smtp.qq.com";
            //获取系统属性
            Properties properties = System.getProperties();
            //SSL加密
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.ssl.socketFactory", sf);

            //设置系统属性
            properties.setProperty("mail.smtp.host", host);
            properties.put("mail.smtp.auth", "true");

            //获取发送邮件会话、获取第三方登录授权码
            Session session = Session.getDefaultInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    //第三方登录授权码
                    return new PasswordAuthentication(from, "hmaduljrpfkjfjgh");
                }
            });
            Message message = new MimeMessage(session);
            //防止邮件被当然垃圾邮件处理，披上Outlook的马甲
            message.addHeader("X-Mailer", "Microsoft Outlook Express 6.00.2900.2869");
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            //邮件标题
            message.setSubject("验证码");
            BodyPart bodyPart = new MimeBodyPart();
            //消息体
            bodyPart.setText("修改密码验证码：" + String.valueOf(code));
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(bodyPart);
            //附件
//            bodyPart = new MimeBodyPart();
//            String fileName = "C:\\Users\\wang\\Desktop\\xbjy.sql";
//            DataSource dataSource = new FileDataSource(fileName);
//            bodyPart.setDataHandler(new DataHandler(dataSource));
//            bodyPart.setFileName("文件显示的名称");
//            multipart.addBodyPart(bodyPart);
            message.setContent(multipart);
            //发送邮件
            Transport.send(message);
//            System.out.println("发送邮件成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


//    public static void main(String[] args) {
//        sendEmail("3503076318@qq.com","0605");
//    }

}
