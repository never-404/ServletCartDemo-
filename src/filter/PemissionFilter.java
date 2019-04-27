package filter;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PemissionFilter implements Filter {

    public PemissionFilter() {
    }

    public void init(FilterConfig fConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // ����������Ӧ����ת��
        HttpServletResponse res = (HttpServletResponse) response;
        HttpServletRequest req = (HttpServletRequest) request;
        // ��÷��ʽ����url�ļ���ַ
        String servletPath = req.getServletPath();
        HttpSession session = req.getSession();
        // ��ȡ��¼״̬
        String flag = (String) session.getAttribute("flag");
        /* �ж��Ƿ��ǵ�¼ҳ����ҳ����¼servlet */
        if (servletPath != null && (servletPath.equals("/login.jsp") || servletPath.equals("/index.jsp") || servletPath.equals("/LoginServlet"))) {
            // ����ֱ��ת������һ���
            chain.doFilter(request, response);
        } else {
            // ������֤��¼״̬
            if (flag != null) {
                if (flag.equals("login_success")) {
                    // ��¼�ɹ���ֱ��ת������һ���
                    chain.doFilter(request, response);
                } else {
                    // ��¼ʧ�ܣ���ת����¼ҳ������֤��ǰ��ҳ��url�ļ�·��
                    req.setAttribute("msg", "��¼ʧ��");
                    req.setAttribute("return_uri", servletPath);
                    RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
                    rd.forward(req, res);
                }
            } else {
                // δ��¼����ת����¼ҳ������֤��ǰ��ҳ��url�ļ�·��
                req.setAttribute("msg", "����δ��¼�����¼");
                req.setAttribute("return_uri", servletPath);
                RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
                rd.forward(req, res);
            }
        }
    }

    public void destroy() {
    }

}