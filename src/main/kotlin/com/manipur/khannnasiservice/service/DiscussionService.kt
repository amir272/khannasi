package com.manipur.khannnasiservice.service

import com.manipur.khannnasiservice.entity.Discussion

interface DiscussionService {
    fun getAllDiscussions(): List<Discussion>
    fun getDiscussionById(discussionId: Long): Discussion?
    fun createDiscussion(discussion: Discussion): Discussion
    fun updateDiscussion(discussionId: Long, updatedDiscussion: Discussion): Discussion?
    fun deleteDiscussion(discussionId: Long)
}