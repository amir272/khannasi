package com.manipur.khannnasiservice.controller

import com.manipur.khannnasiservice.entity.ArticleVote
import com.manipur.khannnasiservice.service.ArticleVoteService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/votes")
class ArticleVoteController(@Autowired private val articleVoteService: ArticleVoteService) {

    @GetMapping
    fun getAllVotes(): List<ArticleVote> = articleVoteService.getAllVotes()

    @GetMapping("/{id}")
    fun getVoteById(@PathVariable id: Long): ResponseEntity<ArticleVote> {
        val vote = articleVoteService.getVoteById(id)
        return if (vote != null) {
            ResponseEntity.ok(vote)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    fun createVote(@RequestBody vote: ArticleVote): ArticleVote = articleVoteService.createVote(vote)

    @PutMapping("/{id}")
    fun updateVote(@PathVariable id: Long, @RequestBody vote: ArticleVote): ResponseEntity<ArticleVote> {
        val updatedVote = articleVoteService.updateVote(id, vote)
        return if (updatedVote != null) {
            ResponseEntity.ok(updatedVote)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteVote(@PathVariable id: Long): ResponseEntity<Void> {
        articleVoteService.deleteVote(id)
        return ResponseEntity.noContent().build()
    }
}