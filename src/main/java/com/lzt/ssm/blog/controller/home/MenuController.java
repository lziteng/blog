package com.lzt.ssm.blog.controller.home;

import com.lzt.ssm.blog.entity.Link;
import com.lzt.ssm.blog.entity.User;
import com.lzt.ssm.blog.enums.LinkStatus;
import com.lzt.ssm.blog.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * 定制后台菜单功能中，每个菜单对应的功能
 *
 * @Author: lzt
 * @Date: 2020/1/12 21:14
 */
@Controller
public class MenuController {

    @Autowired
    private LinkService linkService;

    /**
     * 申请友链页面
     *
     * @return
     */
    @RequestMapping("/applyLink")
    public String applyLink() {
        return "Home/Page/applyLink";
    }

    @RequestMapping(value = "/applyLinkSubmit", method = RequestMethod.POST)
    @ResponseBody
    public void applyLinkSubmit(Link link, HttpSession session) throws Exception {
        link.setLinkStatus(LinkStatus.HIDDEN.getValue());
        link.setLinkCreateTime(new Date());
        link.setLinkUpdateTime(new Date());
        Integer maxOrder = linkService.getMaxOrder();
        link.setLinkOrder(++maxOrder);

        User user = (User) session.getAttribute("user");
        if(user != null){
            link.setLinkOwnerNickname(user.getUserNickname());
        }
        linkService.insertEntity(link);
    }

}
