package org.corbin.client.service;

import lombok.extern.slf4j.Slf4j;
import org.corbin.client.repository.*;
import org.corbin.common.base.Response.ResponseCode;
import org.corbin.common.base.exception.ServiceException;
import org.corbin.common.entity.VerificationLog;
import org.corbin.common.util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Random;

@Service
@Slf4j
public class VerificationLogService {
    @Autowired
    private CollectInfoRepository collectInfoRepository;
    @Autowired
    private CommentInfoRepository commentInfoRepository;
    @Autowired
    private SingerInfoRepository singerInfoRepository;
    @Autowired
    private SongInfoRepository songInfoRepository;
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private VerificationLogRepository verificationLogRepository;

    /**
     * create 6  VerificationCode
     *
     * @return
     */
    public String createVerificationCode() {

        Random ra = new Random();

        String code = String.valueOf((char) (ra.nextInt(26) + 65));
        code += ra.nextInt(10);
        code += (char) (ra.nextInt(26) + 97);
        code += (char) (ra.nextInt(26) + 65);
        code += (char) (ra.nextInt(26) + 65);
        code += (char) (ra.nextInt(26) + 65);

        log.info(code);
        return code;
    }

    /**
     * send VerificationCode to mail address
     * @param mail
     */
    public void sendVerificationCode(String mail) {

        String code=createVerificationCode();
        try {
            MailUtil. sendVerificationCode(mail,code,"听鉴 余声 注册验证码");
        } catch (MessagingException |UnsupportedEncodingException  e) {
            log.error(null,e);
            throw new ServiceException(ResponseCode.ERR_12001);
        }

        //save to database
        VerificationLog verificationLog=new VerificationLog();
        verificationLog.setFailureTime(new Date(System.currentTimeMillis()+5*60*1000));
        verificationLog.setMail(mail);
        verificationLog.setVerificationCode(code);
        verificationLogRepository.save(verificationLog);

    }
}
