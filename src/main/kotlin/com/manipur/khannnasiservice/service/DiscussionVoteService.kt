package com.manipur.khannnasiservice.service

import com.manipur.khannnasiservice.entity.DiscussionVote

interface DiscussionVoteService {
    fun getAllVotes(): List<DiscussionVote>
    fun getVoteById(voteId: Long): DiscussionVote?
    fun createVote(vote: DiscussionVote): DiscussionVote
    fun updateVote(voteId: Long, updatedVote: DiscussionVote): DiscussionVote?
    fun deleteVote(voteId: Long)
}