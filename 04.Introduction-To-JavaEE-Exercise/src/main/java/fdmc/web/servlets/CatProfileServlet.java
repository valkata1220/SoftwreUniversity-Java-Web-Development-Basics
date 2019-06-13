package fdmc.web.servlets;

import fdmc.domain.entities.Cat;
import fdmc.web.util.HtmlReader;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/cats/profile")
public class CatProfileServlet extends HttpServlet {

    private final static String CAT_PROFILE_HTML_FILE_PATH = "D:\\Repository\\Java-Web-Development-Basics\\04.Introduction-To-JavaEE-Exercise\\src\\main\\resources\\views\\cat-profile.html";
    private final static String CAT_PROFILE_NOT_EXISTING_HTML_FILE_PATH = "D:\\Repository\\Java-Web-Development-Basics\\04.Introduction-To-JavaEE-Exercise\\src\\main\\resources\\views\\non-existent-cat.html";

    private final HtmlReader htmlReader;

    @Inject
    public CatProfileServlet(HtmlReader htmlReader) {
        this.htmlReader = htmlReader;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String catName = req.getQueryString().split("=")[1];
        String htmlFileContent;

        if (req.getSession().getAttribute("cats") == null) {
            htmlFileContent = this.htmlReader
                    .readHtmlFile(CAT_PROFILE_NOT_EXISTING_HTML_FILE_PATH)
                    .replace("{{catName}}", catName);
        } else {
            Cat cat = ((Map<String, Cat>) req.getSession().getAttribute("cats")).get(catName);

            htmlFileContent = this.htmlReader
                    .readHtmlFile(CAT_PROFILE_HTML_FILE_PATH)
                    .replace("{{catName}}", cat.getName())
                    .replace("{{catBreed}}", cat.getBreed())
                    .replace("{{catColor}}", cat.getColor())
                    .replace("{{catAge}}", cat.getAge().toString());
        }
        resp.getWriter().println(htmlFileContent);
    }
}
