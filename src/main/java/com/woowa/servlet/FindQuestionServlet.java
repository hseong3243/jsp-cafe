package com.woowa.servlet;

import com.woowa.exception.AuthenticationException;
import com.woowa.framework.web.ResponseEntity;
import com.woowa.handler.QuestionHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;
import java.util.regex.Pattern;

public class FindQuestionServlet extends HttpServlet {

    private static final String PREFIX = "/questions/";
    private static final String UPDATE_SUFFIX = "/update";

    private final QuestionHandler questionHandler;

    public FindQuestionServlet(QuestionHandler questionHandler) {
        this.questionHandler = questionHandler;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String questionId = req.getRequestURI().replace(PREFIX, "");
        ResponseEntity response;
        if (questionId.endsWith(UPDATE_SUFFIX)) {
            questionId = questionId.replace(UPDATE_SUFFIX, "");
            HttpSession session = req.getSession(false);
            if(session == null) {
                throw new AuthenticationException("세션이 없습니다.");
            }
            Object userId = session.getAttribute("userId");
            if(userId == null) {
                throw new AuthenticationException("세션이 없습니다.");
            }
            response = questionHandler.updateQuestionForm((String) userId, questionId);
        } else {
            response = questionHandler.findQuestion(questionId);
        }
        req.setAttribute("question", response.getModel().get("question"));
        req.getRequestDispatcher("/WEB-INF/classes/static" + response.getViewName() + ".jsp").forward(req, resp);
    }
}
