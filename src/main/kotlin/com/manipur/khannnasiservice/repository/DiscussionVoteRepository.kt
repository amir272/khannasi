package com.manipur.khannnasiservice.repository

import com.manipur.khannnasiservice.entity.DiscussionVote
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DiscussionVoteRepository : JpaRepository<DiscussionVote, Long>