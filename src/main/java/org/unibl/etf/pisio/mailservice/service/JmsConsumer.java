package org.unibl.etf.pisio.mailservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import org.unibl.etf.pisio.mailservice.models.User;

import javax.jms.JMSException;
import javax.jms.TextMessage;

@Service
public class JmsConsumer {

    private final Log log = LogFactory.getLog(JmsConsumer.class);
    private final ObjectMapper objectMapper;
    private final EmailService emailService;

    public JmsConsumer(EmailService emailService) {
        this.objectMapper = new ObjectMapper();
        this.emailService = emailService;
    }

    @JmsListener(destination = "${mq.queue}")
    public void receiveMessage(TextMessage message) throws JMSException, JsonProcessingException {
        log.info(message);
        try {
            emailService.sendWelcomeEmail(objectMapper.readValue(message.getText(), User.class));
        } catch (JMSException | JsonProcessingException e) {
            log.error("Error", e);
        }
    }
}
