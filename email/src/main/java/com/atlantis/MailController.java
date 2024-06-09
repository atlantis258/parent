package com.atlantis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.io.UnsupportedEncodingException;

@Controller
public class MailController {

    @Autowired
    private MailUtil mailUtil;

    /**
     * 发送邮件
     */
    @RequestMapping("/email")
    public void sendMail() {
        String emailMsg = "测试发送邮件";
        try {
            // 发送普通文本邮件
            mailUtil.sendMail("1964832804@qq.com", "邮件发送测试", "<a href='https://www.baidu.com' >百度一下</a>");
            // 发送带附件的邮件
//            mailUtil.sendAttachmentMail("xxxxxxx@163.com", "邮件发送测试", "<a href='https://www.baidu.com' >百度一下</a>", "D:\\my_resources\\company_relate\\MobileFile\\IMG_2052.JPG");
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}