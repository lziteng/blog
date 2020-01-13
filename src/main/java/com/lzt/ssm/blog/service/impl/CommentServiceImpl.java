package com.lzt.ssm.blog.service.impl;

import com.github.pagehelper.*;
import com.lzt.ssm.blog.entity.*;
import com.lzt.ssm.blog.enums.ArticleStatus;
import com.lzt.ssm.blog.mapper.CommentMapper;
import com.lzt.ssm.blog.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lzt
 * @date 2020/1/13 11:24
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private ArticleService articleService;

    @Override
    public List<Comment> listComment() {
        CommentExample commentExample = new CommentExample();
        return commentMapper.selectByExample(commentExample);
    }

    @Override
    public PageInfo<Comment> listCommentByPage(Integer pageIndex, Integer pageSize) {
        PageHelper.startPage(pageIndex, pageSize);

        List<Comment> commentList = listComment();
        for (Comment comment : commentList) {
            Article article =
                    articleService.getEntityByStatusAndId(ArticleStatus.PUBLISH.getValue(), comment.getCommentArticleId());
            comment.setArticle(article);

        }
        return new PageInfo<>(commentList);
    }

    @Override
    public List<Comment> listCommentByArticleId(Integer articleId) {
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria().andCommentArticleIdEqualTo(articleId);
        commentExample.setOrderByClause("comment_create_time asc");
        return commentMapper.selectByExample(commentExample);
    }

    @Override
    public List<Comment> listCommentByArticleIdAndPidTag(Integer articleId, Boolean queryChildTag) {
        CommentExample commentExample = new CommentExample();
        CommentExample.Criteria criteria = commentExample.createCriteria();
        criteria.andCommentArticleIdEqualTo(articleId);
        if (queryChildTag) {
            criteria.andCommentPidNotEqualTo(0);
        } else {
            criteria.andCommentPidEqualTo(0);
        }
        commentExample.setOrderByClause("comment_create_time asc");
        return commentMapper.selectByExample(commentExample);
    }

    @Override
    public void deleteChildComment(Integer commentPid) {
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria().andCommentPidEqualTo(commentPid);
        commentMapper.deleteByExample(commentExample);
    }

    @Override
    public List<Comment> listRecentComment(Integer limit) {
        PageHelper.startPage(1, limit);
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria().andCommentRoleEqualTo(0);
        commentExample.setOrderByClause(" comment_create_time desc ");
        List<Comment> commentList = commentMapper.selectByExample(commentExample);
        for (Comment comment : commentList) {
            Article article =
                    articleService.getEntityByStatusAndId(ArticleStatus.PUBLISH.getValue(), comment.getCommentArticleId());
            comment.setArticle(article);
        }

        return commentList;
    }

    @Override
    public void insertEntity(Comment comment) {
        commentMapper.insertSelective(comment);
    }

    @Override
    public void deleteEntityById(Integer id) {
        commentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateEntity(Comment comment) {
        commentMapper.updateByPrimaryKeySelective(comment);
    }

    @Override
    public Comment getEntityById(Integer id) {
        return commentMapper.selectByPrimaryKey(id);
    }
}
