package filter;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        // ���ʵ�¼ҳ��֮ǰ�����ʵ�ҳ�棬��ͨ�����ֵ��ת��֮ǰ��ҳ��
        String returnUri = request.getParameter("return_uri");
        RequestDispatcher rd = null;
        if (userName == null || password == null) {
            request.setAttribute("msg", "�û���������Ϊ��");
        } else {
            if (userName.equals("stydt") && password.equals("123456")) {
                /* ��¼�ɹ� */
                // ����¼״̬���浽session������
                request.getSession().setAttribute("flag", "login_success");
                /* �жϵ�¼֮ǰ����һ��ҳ���Ƿ���� */
                if (returnUri != null) {
                    // ��������ת����¼֮ǰ�Ľ���
                    rd = request.getRequestDispatcher(returnUri);
                    rd.forward(request, response);
                } else {
                    // ����������ת����ҳ
                    rd = request.getRequestDispatcher("/index.jsp");
                    rd.forward(request, response);
                }
            } else {
                /* ��¼ʧ�� */
                // ����¼״̬�޸�Ϊʧ��
                request.getSession().setAttribute("flag", "login_error");
                request.setAttribute("msg", "�û������������");
                // ʧ�ܺ���ת����¼����
                rd = request.getRequestDispatcher("/login.jsp");
                rd.forward(request, response);
            }
        }
    }

}