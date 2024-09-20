package com.markian.moviereview.Comment.Dto;

import com.markian.moviereview.Comment.Comment;
import org.springframework.stereotype.Service;

@Service
public class CommentMapper {

    public Comment fromDtoToComment(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setCommenttext(commentDto.commenttext());
        return comment;
    }

    public CommentResponseDto fromCommentToDto(Comment comment) {
        return new CommentResponseDto(
                comment.getId(),
                comment.getCommenttext()
        );
    }

    public CommentListResponseDto fromCommentListToDto(Comment comment) {
        return new CommentListResponseDto(
                comment.getId(),
                comment.getCommenttext()
        );
    }
}
