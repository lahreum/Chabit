package backend.controller;

import lombok.Data;

@Data
public class CommentRequest {
    private String userEmail;
    private String commentContent;
}
