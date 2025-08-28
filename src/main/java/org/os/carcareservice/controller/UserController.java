package org.os.carcareservice.controller;


import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.os.carcareservice.dto.AuthResponse;
import org.os.carcareservice.dto.BaseUserDTO;
import org.os.carcareservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.Map;


@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<BaseUserDTO> getProfile(Authentication authentication) {
        String email = authentication.getName();
        BaseUserDTO dto = userService.getProfile(email);
        return ResponseEntity.ok(dto);
    }


// ! =========================================================================================


// ?   Send What You Want To Update in the Body and the wrong fields will be ignored
// ?
// ?   {
// ?     "location" : "Giza, Egypt"
// ?   }
    @PatchMapping("/profile")
    public ResponseEntity<BaseUserDTO> updateProfile(
            Authentication authentication,
            @RequestBody JsonNode updatedProfile) {

        String email = authentication.getName();
        BaseUserDTO dto = userService.updateUserProfile(email, updatedProfile);
        return ResponseEntity.ok(dto);
    }


// ! =========================================================================================


// ?   {
// ?     "email" : "ephraim@gmail.com"
// ?   }
    @PatchMapping("/profile/reset-email")
    public ResponseEntity<AuthResponse> updateEmail(
            Authentication authentication,
            @RequestBody Map<String, String> request) {

        AuthResponse response = userService.updateEmail(
                authentication.getName(),
                request.get("email")
        );

        return ResponseEntity.ok(response);
    }


// ! =========================================================================================


// ?    {
// ?         "oldPassword" : "123456",
// ?         "newPassword" : "12345678"
// ?    }
    @PatchMapping("/profile/reset-password")
    public ResponseEntity<AuthResponse> updatePassword(
            Authentication authentication,
            @RequestBody Map<String, String> request) {

        AuthResponse response = userService.updatePassword(
                authentication.getName(),
                request.get("oldPassword"),
                request.get("newPassword")
        );

        return ResponseEntity.ok(response);
    }


// ! =========================================================================================


    @DeleteMapping("/profile")
    public ResponseEntity<String> deleteProfile(Authentication authentication) {
        String email = authentication.getName();
        userService.deleteProfile(email);
        return ResponseEntity.ok("The account has been deleted successfully");
    }


// ! =========================================================================================

}

