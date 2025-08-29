package org.os.carcareservice.repository;

import org.os.carcareservice.entity.EnquiryReply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnquiryReplyRepository extends JpaRepository<EnquiryReply, Long> {
    List<EnquiryReply> findByEnquiry_EnquiryIdOrderByCreatedAtAsc(int enquiryId);
}


