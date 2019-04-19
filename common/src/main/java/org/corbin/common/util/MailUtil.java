package org.corbin.common.util;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * 邮件工具类
 */
public class MailUtil {

    /**
     * 发送邮件
     *
     * @param toMail  给谁发
     * @param content 发送内容
     */
    public static void send_mail(String toMail, String content, String topic) throws MessagingException, UnsupportedEncodingException {
        //创建连接对象 连接到邮件服务器
        Properties properties = new Properties();
        //设置发送邮件的基本参数
        //发送邮件服务器
        properties.put("mail.smtp.host", "smtp.163.com");
        //发送端口
        properties.put("mail.smtp.port", "25");
        properties.put("mail.smtp.auth", "true");
        //设置发送邮件的账号和密码
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                //两个参数分别是发送邮件的账户和密码
                return new PasswordAuthentication("corbin_yin@163.com", "qwert12345");
            }
        });

        //创建邮件对象
        Message message = new MimeMessage(session);
        //设置发件人
        message.setFrom(new InternetAddress("corbin_yin@163.com", "听鉴 余声"));
        //设置收件人
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(toMail));
        //设置主题
        message.setSubject(topic);
        //设置邮件正文  第二个参数是邮件发送的类型
        message.setContent(content, "text/html;charset=UTF-8");
        //发送一封邮件
        Transport.send(message);
    }

    /**
     * send VerificationCode
     * @param mail
     * @param verificationCode
     * @param topic
     * @throws UnsupportedEncodingException
     * @throws MessagingException
     */
    public static void sendVerificationCode(String mail, String verificationCode,String topic) throws UnsupportedEncodingException, MessagingException {
        String html=VERIFICATION_CODE_BEFORE+verificationCode+VERIFICATION_CODE_AFTER;
        MailUtil.send_mail(mail, html, topic);
    }




    /**
     * 定义邮件格式
     */

    private static final String VERIFICATION_CODE_BEFORE = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
            "    <title></title>\n" +
            "    <meta charset=\"utf-8\" />\n" +
            "\n" +
            "</head>\n" +
            "<body>\n" +
            "    <div class=\"qmbox qm_con_body_content qqmail_webmail_only\" id=\"mailContentContainer\" style=\"\">\n" +
            "        <style type=\"text/css\">\n" +
            "            .qmbox body {\n" +
            "                margin: 0;\n" +
            "                padding: 0;\n" +
            "                background: #fff;\n" +
            "                font-family: \"Verdana, Arial, Helvetica, sans-serif\";\n" +
            "                font-size: 14px;\n" +
            "                line-height: 24px;\n" +
            "            }\n" +
            "\n" +
            "            .qmbox div, .qmbox p, .qmbox span, .qmbox img {\n" +
            "                margin: 0;\n" +
            "                padding: 0;\n" +
            "            }\n" +
            "\n" +
            "            .qmbox img {\n" +
            "                border: none;\n" +
            "            }\n" +
            "\n" +
            "            .qmbox .contaner {\n" +
            "                margin: 0 auto;\n" +
            "            }\n" +
            "\n" +
            "            .qmbox .title {\n" +
            "                margin: 0 auto;\n" +
            "                background: url() #CCC repeat-x;\n" +
            "                height: 30px;\n" +
            "                text-align: center;\n" +
            "                font-weight: bold;\n" +
            "                padding-top: 12px;\n" +
            "                font-size: 16px;\n" +
            "            }\n" +
            "\n" +
            "            .qmbox .content {\n" +
            "                margin: 4px;\n" +
            "            }\n" +
            "\n" +
            "            .qmbox .biaoti {\n" +
            "                padding: 6px;\n" +
            "                color: #000;\n" +
            "            }\n" +
            "\n" +
            "            .qmbox .xtop, .qmbox .xbottom {\n" +
            "                display: block;\n" +
            "                font-size: 1px;\n" +
            "            }\n" +
            "\n" +
            "            .qmbox .xb1, .qmbox .xb2, .qmbox .xb3, .qmbox .xb4 {\n" +
            "                display: block;\n" +
            "                overflow: hidden;\n" +
            "            }\n" +
            "\n" +
            "            .qmbox .xb1, .qmbox .xb2, .qmbox .xb3 {\n" +
            "                height: 1px;\n" +
            "            }\n" +
            "\n" +
            "            .qmbox .xb2, .qmbox .xb3, .qmbox .xb4 {\n" +
            "                border-left: 1px solid #BCBCBC;\n" +
            "                border-right: 1px solid #BCBCBC;\n" +
            "            }\n" +
            "\n" +
            "            .qmbox .xb1 {\n" +
            "                margin: 0 5px;\n" +
            "                background: #BCBCBC;\n" +
            "            }\n" +
            "\n" +
            "            .qmbox .xb2 {\n" +
            "                margin: 0 3px;\n" +
            "                border-width: 0 2px;\n" +
            "            }\n" +
            "\n" +
            "            .qmbox .xb3 {\n" +
            "                margin: 0 2px;\n" +
            "            }\n" +
            "\n" +
            "            .qmbox .xb4 {\n" +
            "                height: 2px;\n" +
            "                margin: 0 1px;\n" +
            "            }\n" +
            "\n" +
            "            .qmbox .xboxcontent {\n" +
            "                display: block;\n" +
            "                border: 0 solid #BCBCBC;\n" +
            "                border-width: 0 1px;\n" +
            "            }\n" +
            "\n" +
            "            .qmbox .line {\n" +
            "                margin-top: 6px;\n" +
            "                border-top: 1px dashed #B9B9B9;\n" +
            "                padding: 4px;\n" +
            "            }\n" +
            "\n" +
            "            .qmbox .neirong {\n" +
            "                padding: 6px;\n" +
            "                color: #666666;\n" +
            "            }\n" +
            "\n" +
            "            .qmbox .foot {\n" +
            "                padding: 6px;\n" +
            "                color: #777;\n" +
            "            }\n" +
            "\n" +
            "            .qmbox .font_darkblue {\n" +
            "                color: #006699;\n" +
            "                font-weight: bold;\n" +
            "            }\n" +
            "\n" +
            "            .qmbox .font_lightblue {\n" +
            "                color: #008BD1;\n" +
            "                font-weight: bold;\n" +
            "            }\n" +
            "\n" +
            "            .qmbox .font_gray {\n" +
            "                color: #888;\n" +
            "                font-size: 12px;\n" +
            "            }\n" +
            "        </style>\n" +
            "        <div class=\"contaner\">\n" +
            "            <div class=\"content\">\n" +
            "                <p class=\"biaoti\"><b>亲爱的用户，你好！</b></p>\n" +
            "                <p><span id=\"userName\" class=\"font_darkblue\">感谢您使用 听鉴 余声 音乐服务</span></p>" +
            "                <b class=\"xtop\"><b class=\"xb1\"></b><b class=\"xb2\"></b><b class=\"xb3\"></b><b class=\"xb4\"></b></b>\n" +
            "                <div class=\"xboxcontent\">\n" +
            "                    <div class=\"neirong\">\n" +
            "                        <p><b>您的验证码是：</b><span class=\"font_lightblue\"><span id=\"yzm\"  onclick=\"return false;\" t=\"7\" style=\"border-bottom: 1px dashed rgb(204, 204, 204); z-index: 1; position: static;\">";


    private static final String VERIFICATION_CODE_AFTER = "</span></span><br><span class=\"font_gray\">(验证码5分钟内有效)</span></p>\n" +
            "                        <div class=\"line\">如果你未申请我们的服务，请忽略该邮件，谢谢。</div>\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "                <b class=\"xbottom\"><b class=\"xb4\"></b><b class=\"xb3\"></b><b class=\"xb2\"></b><b class=\"xb1\"></b></b>\n" +
            "                <p class=\"foot\">如果仍有问题，请拨打我们的会员服务专线: <span  onclick=\"return false;\" t=\"7\" style=\"border-bottom: 1px dashed rgb(204, 204, 204); z-index: 1; position: static;\">400-300-5400\n" +
            "</span></p>\n" +
            "            </div>\n" +
            "        </div>\n" +
            "        <style type=\"text/css\">\n" +
            "            .qmbox style, .qmbox script, .qmbox head, .qmbox link, .qmbox meta {\n" +
            "                display: none !important;\n" +
            "            }\n" +
            "        </style>\n" +
            "    </div>\n" +
            "</body>\n" +
            "</html>\n";

}
