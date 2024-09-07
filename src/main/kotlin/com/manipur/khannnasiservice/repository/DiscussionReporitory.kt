package com.manipur.khannnasiservice.repository

import com.manipur.khannnasiservice.entity.DiscussionComment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DiscussionCommentRepository : JpaRepository<DiscussionComment, Long>