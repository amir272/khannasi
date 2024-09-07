package com.manipur.khannnasiservice.service

import com.manipur.khannnasiservice.entity.DiscussionVote
import com.manipur.khannnasiservice.repository.DiscussionVoteRepository
import com.manipur.khannnasiservice.repository.UserBasicsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DiscussionVoteServiceImpl(
    @Autowired private val discussionVoteRepository: DiscussionVoteRepository,
    @Autowired private val userBasicsRepository: UserBasicsRepository) : DiscussionVoteService {

    override fun getAllVotes(): List<DiscussionVote> = discussionVoteRepository.findAll()

    override fun getVoteById(voteId: Long): DiscussionVote? = discussionVoteRepository.findById(voteId).orElse(null)

    override fun createVote(vote: DiscussionVote): DiscussionVote {
        if (!checkIfUserExists(vote.userBasics.userId)) {
            throw IllegalArgumentException("User with id ${vote.userBasics.userId} does not exist")
        }
        return discussionVoteRepository.save(vote)
    }

    override fun updateVote(voteId: Long, updatedVote: DiscussionVote): DiscussionVote? {
        return if (discussionVoteRepository.existsById(voteId)) {
            discussionVoteRepository.save(updatedVote)
        } else {
            null
        }
    }

    override fun deleteVote(voteId: Long) {
        if (discussionVoteRepository.existsById(voteId)) {
            discussionVoteRepository.deleteById(voteId)
        }
    }

    private fun checkIfUserExists(userId: Long): Boolean {
        return userBasicsRepository.existsById(userId)
    }
}