package com.manipur.khannnasiservice.service

import com.manipur.khannnasiservice.entity.Article
import com.manipur.khannnasiservice.repository.ArticleRepository
import com.manipur.khannnasiservice.repository.UserBasicsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ArticleServiceImpl(
    @Autowired private val articleRepository: ArticleRepository,
                         @Autowired private val userBasicsRepository: UserBasicsRepository
) : ArticleService {

    override fun getAllArticles(): List<Article> = articleRepository.findAll()

    override fun getArticleById(articleId: Long): Article? = articleRepository.findById(articleId).orElse(null)

    override fun createArticle(article: Article): Article {
        if (!checkIfUserExists(article.author.userId)) {
            throw IllegalArgumentException("User with id ${article.author.userId} does not exist")
        }
        return articleRepository.save(article)
    }

    override fun updateArticle(articleId: Long, updatedArticle: Article): Article? {
        return if (articleRepository.existsById(articleId)) {
            articleRepository.save(updatedArticle)
        } else {
            null
        }
    }

    override fun deleteArticle(articleId: Long) {
        if (articleRepository.existsById(articleId)) {
            articleRepository.deleteById(articleId)
        }
    }


    private fun checkIfUserExists(userId: Long): Boolean {
        return userBasicsRepository.existsById(userId)
    }
}