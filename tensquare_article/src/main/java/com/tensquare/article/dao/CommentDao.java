package com.tensquare.article.dao;

import com.tensquare.article.pojo.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by Hello-XSH on 2018-12-3.
 */
public interface CommentDao extends MongoRepository<Comment, String> {

    /*** 根据文章ID查询评论列表*/
    public List<Comment> findByArticleid(String articleid);

    public void deleteByArticleid(String articleid);
}
