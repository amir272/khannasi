package com.manipur.khannnasiservice.service

import com.manipur.khannnasiservice.entity.Discussion
import com.manipur.khannnasiservice.repository.DiscussionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DiscussionServiceImpl(
    @Autowired private val discussionRepository: DiscussionRepository,
    @Autowired private val userRepository: DiscussionRepository) : DiscussionService {

    override fun getAllDiscussions(): List<Discussion> = discussionRepository.findAll()

    override fun getDiscussionById(discussionId: Long): Discussion? = discussionRepository.findById(discussionId).orElse(null)

    override fun createDiscussion(discussion: Discussion): Discussion {
        if (!checkIfUserExists(discussion.initiator.userId)) {
            throw IllegalArgumentException("User with id ${discussion.initiator.userId} does not exist")
        }
        return discussionRepository.save(discussion)
    }

    override fun updateDiscussion(discussionId: Long, updatedDiscussion: Discussion): Discussion? {
        return if (discussionRepository.existsById(discussionId)) {
            discussionRepository.save(updatedDiscussion)
        } else {
            null
        }
    }

    override fun deleteDiscussion(discussionId: Long) {
        if (discussionRepository.existsById(discussionId)) {
            discussionRepository.deleteById(discussionId)
        }
    }


    private fun checkIfUserExists(userId: Long): Boolean {
        return userRepository.existsById(userId)
    }
}