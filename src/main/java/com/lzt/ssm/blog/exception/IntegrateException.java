package com.lzt.ssm.blog.exception;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.*;

import javax.servlet.http.*;
import java.util.*;

/**
 * 全局异常处理类
 *
 * @author lzt
 * @date 2020/1/18 10:21
 */
public class IntegrateException implements HandlerExceptionResolver {

    public static Logger logger = Logger.getLogger(IntegrateException.class);


    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex) {

        logger.error(ex.getMessage(), ex);

        //判断是否为ajax请求，默认不是
        boolean isAjaxRequest = false;

        //这段代码能够得知是否为ajax请求，也就是我们说的json请求
        if (!StringUtils.isBlank(request.getHeader("x-requested-with")) &&
                request.getHeader("x-requested-with").equals("XMLHttpRequest")) {
            isAjaxRequest = true;
        }

        //如果是ajax请求
        if (isAjaxRequest) {
            CustomException customException = null;
            if (ex instanceof CustomException) {
                customException = (CustomException) ex;
            } else {
                //如果抛出的不是系统自定义的异常则重新构造一个未知错误异常
                //也可用CustomException，实际中应该要再定义一个新的异常
                customException = new CustomException("系统未知错误,请联系管理员");
            }
            String message = customException.getMessage();
            //向前台返回错误信息
            ModelAndView model = new ModelAndView();
            FastJsonJsonView view = new FastJsonJsonView();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("code", 500);
            map.put("message", message);
            view.setAttributesMap(map);
            model.setView(view);
            return model;
        } else {
            //如果不是ajax请求 那么异常后需要返回页面
            ReturnViewException returnViewException = null;
            //如果抛出的异常类型是我们预想的，会想前台返回我们指定在参数里的页面
            if (ex instanceof ReturnViewException) {
                returnViewException = (ReturnViewException) ex;
            } else {
                //这里的500是我资源下的500.jsp，使用了mvc的映射省去了前缀后缀
                //系统默认跳转到500页面
                returnViewException = new ReturnViewException(ex.getMessage());
            }
            ModelAndView model = new ModelAndView();
            model.addObject("message", returnViewException.getMessage());
            model.setViewName("Admin/Error/403");
            return model;
        }
    }
}
