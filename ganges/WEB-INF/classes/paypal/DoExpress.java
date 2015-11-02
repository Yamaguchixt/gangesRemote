package paypal;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/DoExpress")
public class DoExpress extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DoExpress() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String token = (String)session.getAttribute("token");
		String payerid = (String)session.getAttribute("PAYERID");
		String paymentAmount = (String)session.getAttribute("paymentAmount");
		String serverName ="www.hew2015.com";//これなんで必要なの？

		paypalfunctions ppf = new paypalfunctions();
		HashMap nvp = ppf.confirmPayment(token, payerid, paymentAmount, serverName);
		System.out.println("in DoExpress confirmPayment戻りnvp");
		//paypalfunctions.showNvp(nvp);

		String strAck = nvp.get("ACK").toString();
		System.out.println("confirmPaymentの戻り値:"+strAck);
		if(strAck != null && (strAck.equalsIgnoreCase("Success") || strAck.equalsIgnoreCase("SuccessWithWarning"))){
			session.setAttribute("nvp",nvp);
		}else{
			String ErrorCode = nvp.get("L_ERRORCODE0").toString();
			String ErrorShortMsg = nvp.get("L_SHORTMESSAGE0").toString();
			String ErrorLongMsg = nvp.get("L_LONGMESSAGE0").toString();
			String ErrorSeverityCode = nvp.get("L_SEVERITYCODE0").toString();
		}

		request.getRequestDispatcher("jsp/paypal/PurchaseComplete.jsp").forward(request,response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
