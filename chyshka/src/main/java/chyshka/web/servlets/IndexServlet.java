package chyshka.web.servlets;

import chyshka.models.view.AllProductsViewModel;
import chyshka.service.ProductService;
import chyshka.util.HtmlReader;
import chyshka.util.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class IndexServlet extends HttpServlet {

    private final static String INDEX_HTML_FILE_PATH = "D:\\Repository\\Java-Web-Development-Basics\\chyshka\\src\\main\\resources\\views\\index.html";

    private final ProductService productService;
    private final HtmlReader htmlReader;
    private final ModelMapper modelMapper;

    @Inject
    public IndexServlet(ProductService productService, HtmlReader htmlReader, ModelMapper modelMapper) {
        this.productService = productService;
        this.htmlReader = htmlReader;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String htmlFileContent = this.htmlReader
                .readHtmlFile(INDEX_HTML_FILE_PATH)
                .replace("{{listItems}}", this.formatListItems());

        resp.getWriter().println(htmlFileContent);
    }

    private String formatListItems() {
        StringBuilder listItems = new StringBuilder();

        this.productService
                .findAllProducts()
                .stream()
                .map((productServiceModel -> this.modelMapper.map(productServiceModel, AllProductsViewModel.class)))
                .forEach(product -> listItems.append(String.format("<li><a href=\"/products/details?name=%s\">%s</a></li>"
                        , product.getName(), product.getName()))
                        .append(System.lineSeparator()));

        return listItems.toString();
    }
}
