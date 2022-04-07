package spring.boot.porlet;

import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.View;
import org.springframework.web.util.NestedServletException;

public class ViewRendererServlet extends HttpServlet {
    public static final String WEB_APPLICATION_CONTEXT_ATTRIBUTE;
    public static final String VIEW_ATTRIBUTE;
    public static final String MODEL_ATTRIBUTE;

    public ViewRendererServlet() {
    }

    protected final void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.processRequest(request, response);
    }

    protected final void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.processRequest(request, response);
    }

    protected final void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            this.renderView(request, response);
        } catch (ServletException var4) {
            throw var4;
        } catch (IOException var5) {
            throw var5;
        } catch (Exception var6) {
            throw new NestedServletException("View rendering failed", var6);
        }
    }

    protected void renderView(HttpServletRequest request, HttpServletResponse response) throws Exception {
        View view = (View)request.getAttribute(VIEW_ATTRIBUTE);
        if (view == null) {
            throw new ServletException("Could not complete render request: View is null");
        } else {
            Map<String, Object> model = (Map)request.getAttribute(MODEL_ATTRIBUTE);
            view.render(model, request, response);
        }
    }

    static {
        WEB_APPLICATION_CONTEXT_ATTRIBUTE = DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE;
        VIEW_ATTRIBUTE = ViewRendererServlet.class.getName() + ".VIEW";
        MODEL_ATTRIBUTE = ViewRendererServlet.class.getName() + ".MODEL";
    }
}
