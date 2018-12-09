package com.tensquare.article.service;

import com.tensquare.article.dao.CommentDao;
import com.tensquare.article.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import util.IdWorker;

import java.util.Date;
import java.util.List;

/**
 * Created by Hello-XSH on 2018-12-3.
 */
@Service
@Transactional
public class CommentService {

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private IdWorker idWorker;

    public void addcomment(Comment comment) {
        comment.set_id(String.valueOf(idWorker.nextId()));
        comment.setPublishdat(new Date());
        commentDao.save(comment);
    }

    public void deleteByArticleid(String articlei) {
        commentDao.deleteByArticleid(articlei);
    }

    public List<Comment> findByArticleid(String articleid) {
        return commentDao.findByArticleid(articleid);
    }


}