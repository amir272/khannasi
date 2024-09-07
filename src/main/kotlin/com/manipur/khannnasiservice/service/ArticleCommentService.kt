package com.manipur.khannnasiservice.service

import com.manipur.khannnasiservice.entity.ArticleComment

interface ArticleCommentService {
    fun getAllComments(): List<ArticleComment>
    fun getCommentById(commentId: Long): ArticleComment?
    fun createComment(comment: ArticleComment): ArticleComment
    fun updateComment(commentId: Long, updatedComment: ArticleComment): ArticleComment?
    fun deleteComment(commentId: Long)
}