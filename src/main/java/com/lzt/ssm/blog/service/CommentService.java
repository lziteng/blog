package com.lzt.ssm.blog.service;

import com.github.pagehelper.PageInfo;
import com.lzt.ssm.blog.entity.Comment;

import java.util.List;

/**
 * @author lzt
 * @date 2020/1/13 10:57
 */
public interface CommentService extends BaseCrudService<Comment> {

    /**
     * 获取所有评论记录
     *
     * @return 所有评论记录
     */
    List<Comment> listComment();

    /**
     * 获取所有评论记录
     *
     * @param pageIndex 第几页
     * @param pageSize  每页条数
     * @return 所有评论记录
     */
    PageInfo<Comment> listCommentByPage(Integer pageIndex, Integer pageSize) throws Exception;

    /**
     * 根据文章id获取所有评论记录
     *
     * @param articleId 文章id
     * @return 所有评论记录
     */
    List<Comment> listCommentByArticleId(Integer articleId);

    /**
     * 根据文章id获取评论记录
     *
     * @param articleId     文章id
     * @param queryChildTag 是否查询子评论(commentPid不为0的评价)
     * @return
     */
    List<Comment> listCommentByArticleIdAndPidTag(Integer articleId, Boolean queryChildTag);

    /**
     * 删除所有子评论
     *
     * @param commentPid 父评论id
     */
    void deleteChildComment(Integer commentPid);

    /**
     * 获取最新的访客评价记录列表
     *
     * @param limit 查询数量
     * @return 评价记录列表
     */
    List<Comment> listRecentComment(Integer limit) throws Exception;

}