package edu.fzu.zhishe.security.component;

import cn.hutool.json.JSONUtil;
import edu.fzu.zhishe.common.api.ErrorResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.stereotype.Component;

/**
 * 自定义返回结果：没有权限访问时
 *
 * @author liang
 * @date 2020/4/13
 */
@Component
public class RestfulAccessDeniedHandler implements AccessDeniedHandler{
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException e) throws IOException, ServletException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control","no-cache");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(JSONUtil.parse(ErrorResponseBody.forbidden(e.getMessage())));
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.getWriter().flush();
    }
}
