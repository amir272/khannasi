package com.manipur.khannnasiservice.service

import com.manipur.khannnasiservice.entity.ArticleVote
import com.manipur.khannnasiservice.repository.ArticleVoteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ArticleVoteServiceImpl(@Autowired private val articleVoteRepository: ArticleVoteRepository) : ArticleVoteService {

    override fun getAllVotes(): List<ArticleVote> = articleVoteRepository.findAll()

    override fun getVoteById(voteId: Long): ArticleVote? = articleVoteRepository.findById(voteId).orElse(null)

    override fun createVote(vote: ArticleVote): ArticleVote = articleVoteRepository.save(vote)

    override fun updateVote(voteId: Long, updatedVote: ArticleVote): ArticleVote? {
        return if (articleVoteRepository.existsById(voteId)) {
            articleVoteRepository.save(updatedVote)
        } else {
            null
        }
    }

    override fun deleteVote(voteId: Long) {
        if (articleVoteRepository.existsById(voteId)) {
            articleVoteRepository.deleteById(voteId)
        }
    }
}