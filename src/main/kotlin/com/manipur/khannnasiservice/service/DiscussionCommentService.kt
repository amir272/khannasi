package com.manipur.khannnasiservice.service

import com.manipur.khannnasiservice.entity.DiscussionComment

interface DiscussionCommentService {
    fun getAllComments(): List<DiscussionComment>
    fun getCommentById(commentId: Long): DiscussionComment?
    fun createComment(comment: DiscussionComment): DiscussionComment
    fun updateComment(commentId: Long, updatedComment: DiscussionComment): DiscussionComment?
    fun deleteComment(commentId: Long)
}