package com.manipur.khannnasiservice.service

import com.manipur.khannnasiservice.entity.ArticleVote

interface ArticleVoteService {
    fun getAllVotes(): List<ArticleVote>
    fun getVoteById(voteId: Long): ArticleVote?
    fun createVote(vote: ArticleVote): ArticleVote
    fun updateVote(voteId: Long, updatedVote: ArticleVote): ArticleVote?
    fun deleteVote(voteId: Long)
}