package com.example.prestigeportfoliocreators.service;

import com.example.prestigeportfoliocreators.dto.PaginationForm;
import com.example.prestigeportfoliocreators.errors.CustomErrors;
import com.example.prestigeportfoliocreators.models.Message;
import com.example.prestigeportfoliocreators.repository.ContactRepository;
import com.example.prestigeportfoliocreators.util.DateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ContactService {
    @Autowired
    public ContactRepository contactRepo;
    @Autowired
    public EmailService emailService;


    public void sendMessage(Message message){
        String name = message.getName();
        String contactInfo = message.getContactInfo();
        String body = message.getBody();
        message.setDate(DateFormat.yyyyMMddHHmmss());
        contactRepo.save(message);
        String emailSubject = name + "sent you a message";
        String emailBody = "Name: " + name + "\n" +
                            "Contact info: " + contactInfo + "\n\n"+
                            body + "\n";
        emailService.sendEmail(EmailService.toEmail(), emailSubject, emailBody);
    }

    public Page<Message> getMessages(PaginationForm paginationForm){
        Pageable pageable = PageRequest.of(paginationForm.getPageNum(), paginationForm.getPageSize());
        Page<Message> page = contactRepo.findAllWithPaginationOrderedByDate(pageable);
        return page;
    }

    public String deleteMessage(Long id){
        Optional<Message> optional = contactRepo.findById(id);
        if (!optional.isPresent()){
            return CustomErrors.MESSAGE_ID_ERROR;
        }
        contactRepo.delete(optional.get());
        return null;
    }
}
