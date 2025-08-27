package org.os.carcareservice.service;

import org.os.carcareservice.entity.Enquiry;
import org.os.carcareservice.entity.EnquiryReply;
import org.os.carcareservice.repository.EnquiryReplyRepository;
import org.os.carcareservice.repository.EnquiryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EnquiryService {

    private final EnquiryRepository enquiryRepository;
    private final EnquiryReplyRepository replyRepository;

    public EnquiryService(EnquiryRepository enquiryRepository,
                          EnquiryReplyRepository replyRepository) {
        this.enquiryRepository = enquiryRepository;
        this.replyRepository = replyRepository;
    }

    public Enquiry submit(Enquiry enquiry) {
        enquiry.setCreatedAt(LocalDateTime.now());
        if (enquiry.getStatus() == null) {
            enquiry.setStatus("OPEN");
        }
        return enquiryRepository.save(enquiry);
    }

    public List<Enquiry> listAll() {
        return enquiryRepository.findAll();
    }

    public Enquiry getById(int id) {
        return enquiryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Enquiry not found"));
    }

    public void deleteById(int id) {
        enquiryRepository.deleteById(id);
    }

    public Enquiry updateStatus(int id, String status) {
        Enquiry e = getById(id);
        e.setStatus(status);
        return enquiryRepository.save(e);
    }

    public EnquiryReply reply(int enquiryId, String message) {
        Enquiry e = getById(enquiryId);
        EnquiryReply r = new EnquiryReply();
        r.setEnquiry(e);
        r.setMessage(message);
        r.setCreatedAt(LocalDateTime.now());
        return replyRepository.save(r);
    }

    public List<EnquiryReply> getReplies(int enquiryId) {
        return replyRepository.findByEnquiry_EnquiryIdOrderByCreatedAtAsc(enquiryId);
    }
}


