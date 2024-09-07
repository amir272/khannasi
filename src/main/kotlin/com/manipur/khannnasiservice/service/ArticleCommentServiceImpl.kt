package com.manipur.khannnasiservice.service

import com.manipur.khannnasiservice.entity.ArticleComment
import com.manipur.khannnasiservice.repository.ArticleCommentRepository
import com.manipur.khannnasiservice.repository.UserBasicsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ArticleCommentServiceImpl(
    @Autowired private val articleCommentRepository: ArticleCommentRepository,
    @Autowired private val userBasicsRepository: UserBasicsRepository
) : ArticleCommentService {

    override fun getAllComments(): List<ArticleComment> = articleCommentRepository.findAll()

    override fun getCommentById(commentId: Long): ArticleComment? = articleCommentRepository.findById(commentId).orElse(null)

    override fun createComment(comment: ArticleComment): ArticleComment {
        if(!checkIfUserExists(comment.userBasics.userId)) {
            throw IllegalArgumentException("User with id ${comment.userBasics.userId} does not exist")
        }
        return articleCommentRepository.save(comment)
    }

    override fun updateComment(commentId: Long, updatedComment: ArticleComment): ArticleComment? {
        return if (articleCommentRepository.existsById(commentId)) {
            articleCommentRepository.save(updatedComment)
        } else {
            null
        }
    }

    override fun deleteComment(commentId: Long) {
        if (articleCommentRepository.existsById(commentId)) {
            articleCommentRepository.deleteById(commentId)
        }
    }

    private fun checkIfUserExists(userId: Long): Boolean {
        return userBasicsRepository.existsById(userId)
    }
}