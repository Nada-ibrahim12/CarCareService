package org.os.carcareservice.controller;

import org.os.carcareservice.dto.EnquiryReplyDto;
import org.os.carcareservice.entity.Enquiry;
import org.os.carcareservice.entity.EnquiryReply;
import org.os.carcareservice.service.EnquiryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/enquiries")
public class EnquiryController {

    private final EnquiryService enquiryService;

    public EnquiryController(EnquiryService enquiryService) {
        this.enquiryService = enquiryService;
    }

    // POST /api/enquiries → Customer/Provider submits enquiry.
    @PostMapping
    public ResponseEntity<Enquiry> submit(@RequestBody Enquiry enquiry) {
        return ResponseEntity.ok(enquiryService.submit(enquiry));
    }

    // GET /api/enquiries → (Admin) List all enquiries.
    @GetMapping
    public ResponseEntity<List<Enquiry>> list() {
        return ResponseEntity.ok(enquiryService.listAll());
    }

    // GET /api/enquiries/{id} → (Admin) Get enquiry details.
    @GetMapping("/{id}")
    public ResponseEntity<Enquiry> get(@PathVariable int id) {
        return ResponseEntity.ok(enquiryService.getById(id));
    }

    // DELETE /api/enquiries/{id} → (Admin) Delete/hide enquiry.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        enquiryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // PUT /api/enquiries/{id}/status → Mark enquiry as resolved/unresolved.
    @PutMapping("/{id}/status")
    public ResponseEntity<Enquiry> updateStatus(@PathVariable int id, @RequestBody Map<String, String> body) {
        String status = body.getOrDefault("status", "OPEN");
        return ResponseEntity.ok(enquiryService.updateStatus(id, status));
    }

    // POST /api/enquiries/{id}/reply → Admin reply to the enquiries
    @PostMapping("/{id}/reply")
    public ResponseEntity<EnquiryReply> reply(@PathVariable int id, @RequestBody Map<String, String> body) {
        return ResponseEntity.ok(enquiryService.reply(id, body.get("message")));
    }

    // GET /api/enquiries/{id}/replies  → Customer or provider can see replies
    @GetMapping("/{id}/replies")
    public ResponseEntity<List<EnquiryReplyDto>> replies(@PathVariable int id) {
        return ResponseEntity.ok(enquiryService.getReplies(id));
    }
}


