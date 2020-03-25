package com.example.act.wechat;

import com.example.act.util.WeChatAuthorizedUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;


@Controller
@RequestMapping("/wx")
public class WXLoginController {
    private static final Logger logger = Logger.getLogger(WXLoginController.class);

    @Autowired
    private HttpServletRequest request;



    /**
     * 公众号微信登录授权
     *
     * @return
     * @throws ParseException
     * @parameter
     */
    @RequestMapping(value = "/wxLogin", method = RequestMethod.GET)
    public String wxLogin() throws ParseException {

        return "redirect:" + WeChatAuthorizedUtil.wxLogin();// 必须重定向，否则不能成功
    }

    /**
     * 公众号微信登录授权回调函数
     *
     * @return
     * @throws ServletException
     * @throws IOException
     * @parameter
     */
    @RequestMapping(value = "/callBack", method = RequestMethod.GET)
    @ResponseBody
    public String callBack() throws ServletException, IOException {
        return WeChatAuthorizedUtil.callBack(request).toString();
    }

}

