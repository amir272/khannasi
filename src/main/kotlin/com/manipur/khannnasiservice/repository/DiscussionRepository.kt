package com.manipur.khannnasiservice.repository

import com.manipur.khannnasiservice.entity.Discussion
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DiscussionRepository : JpaRepository<Discussion, Long>