package com.snoozingturtles.auctioz.services.impl;

import com.snoozingturtles.auctioz.dto.BiddingDto;
import com.snoozingturtles.auctioz.dto.UserDto;
import com.snoozingturtles.auctioz.services.BiddingService;
import com.snoozingturtles.auctioz.services.EmailService;
import com.snoozingturtles.auctioz.services.ProductService;
import com.snoozingturtles.auctioz.services.UserService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

@Service
@EnableScheduling
@EnableAsync
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;
    private final BiddingService biddingService;
    private final UserService userService;
    private final ProductService productService;

    @Value("${email.from}")
    private String from;

    @Value("${email.BASE_URL}")
    private String BASE_URL;


    @Override
    @Scheduled(fixedDelayString = "${email.timeIntervalToSendEmail}")
    @Async
    public void sendSimpleEmail() {
        List<BiddingDto> biddingDtoList = biddingService.getAllBiddings();
        List<BiddingDto> winningBids = biddingDtoList.stream()
                .filter(biddingDto -> LocalDateTime.parse(biddingDto.getDeadline()).isBefore(LocalDateTime.now())
                            && !productService.getProductById(biddingDto.getProductId()).isSoldOut())
                .toList();
        List<String> bidWinnerEmailList = winningBids.stream()
                .map(BiddingDto::getCurrentBidderId)
                .map(userService::getUserById)
                .map(UserDto::getEmail)
                .toList();
        String subject = "Congratulations on winning the bid!";
        int l = bidWinnerEmailList.size();
        for(int i = 0; i < l; i++) {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

            String email = bidWinnerEmailList.get(i);
            simpleMailMessage.setFrom(from);
            simpleMailMessage.setTo(email);
            simpleMailMessage.setSubject(subject);

            /*
            TODO:
              A new web-page (made using React) will open on clicking the link where the users have to pay the required amount,
              and it will send a request to the /orders endpoint on the backend to save details about the order
            */
            BiddingDto bid = winningBids.get(i);
            String body = String.format("Congratulations on winning the bid! Here's " +
                    "your link to purchase the product: %s", BASE_URL + "/users/" +
                    bid.getCurrentBidderId() +
                    "/products/" +
                    bid.getProductId() +
                    "/orders/pay");
            simpleMailMessage.setText(body);

            javaMailSender.send(simpleMailMessage);
            System.out.println("\n\nMail sent to " + email + "\n\n");
        }

    }


    @Override
    public void sendEmailWithAttachment(String sendTo, String body, String subject, String attachment) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

        mimeMessageHelper.setFrom(from);
        mimeMessageHelper.setTo(sendTo);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(body);

        FileSystemResource fileSystemResource = new FileSystemResource(new File(attachment));
        if(fileSystemResource.getFilename() != null) {
            mimeMessageHelper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);
        }
        javaMailSender.send(mimeMessage);
        System.out.println("Mail with attachment sent..");
    }
}
