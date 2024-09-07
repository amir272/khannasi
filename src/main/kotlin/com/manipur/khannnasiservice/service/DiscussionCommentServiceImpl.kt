package com.manipur.khannnasiservice.service

import com.manipur.khannnasiservice.entity.DiscussionComment
import com.manipur.khannnasiservice.repository.DiscussionCommentRepository
import com.manipur.khannnasiservice.repository.UserBasicsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DiscussionCommentServiceImpl(
    @Autowired private val discussionCommentRepository: DiscussionCommentRepository,
    @Autowired private val userBasicsRepository: UserBasicsRepository
    ) : DiscussionCommentService {

    override fun getAllComments(): List<DiscussionComment> = discussionCommentRepository.findAll()

    override fun getCommentById(commentId: Long): DiscussionComment? = discussionCommentRepository.findById(commentId).orElse(null)

    override fun createComment(comment: DiscussionComment): DiscussionComment {
        if (!checkIfUserExists(comment.userBasics.userId)) {
            throw IllegalArgumentException("User with id ${comment.userBasics.userId} does not exist")
        }
        return discussionCommentRepository.save(comment)
    }
    override fun updateComment(commentId: Long, updatedComment: DiscussionComment): DiscussionComment? {
        return if (discussionCommentRepository.existsById(commentId)) {
            discussionCommentRepository.save(updatedComment)
        } else {
            null
        }
    }

    override fun deleteComment(commentId: Long) {
        if (discussionCommentRepository.existsById(commentId)) {
            discussionCommentRepository.deleteById(commentId)
        }
    }

    private fun checkIfUserExists(userId: Long): Boolean {
        return userBasicsRepository.existsById(userId)
    }
}