package paypal;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SetExpress
 */
@WebServlet("/SetExpress")
public class SetExpress extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SetExpress() {
        super();
        // TODO Auto-generated constructor stub
    }


	@SuppressWarnings("rawtypes")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String paymentAmount = request.getParameter("paymentAmount");
        HttpSession session = request.getSession(true);
        session.setAttribute("paymentAmount",paymentAmount);//paypalからの戻りでも使うのでsessionでもつ


        //String returnURL = request.getRequestURL().append("Doexpress").toString();
        String returnURL = "http://localhost:8080/ganges/GetExpress"; //ハードコードはまずい、request.getからなんとか加工する。
        System.out.println("returnURL:"+returnURL);
        String cancelURL = "http://localhost:8080/ganges/jsp/paypal/PurchaseCancel.jsp";

        paypalfunctions ppf = new paypalfunctions();
        //nvp はpaypalのAPIからのレスポンスを保持している。
        HashMap nvp = ppf.CallShortcutExpressCheckout (paymentAmount, returnURL, cancelURL);

        String strAck = nvp.get("ACK").toString();
        if(strAck !=null && strAck.equalsIgnoreCase("Success")){
            session.setAttribute("token", nvp.get("TOKEN").toString());
            System.out.println("in setExpress nvp.get(TOKEN): "+ nvp.get("TOKEN").toString());
            String redirectURL = ppf.PAYPAL_URL+nvp.get("TOKEN");
            System.out.println("redirectURL:"+redirectURL);
            response.sendRedirect(response.encodeRedirectURL(redirectURL));
        }
        else
        {
            // Display a user friendly Error on the page using any of the following error information returned by PayPal

            String ErrorCode = nvp.get("L_ERRORCODE0").toString();
            String ErrorShortMsg = nvp.get("L_SHORTMESSAGE0").toString();
            String ErrorLongMsg = nvp.get("L_LONGMESSAGE0").toString();
            String ErrorSeverityCode = nvp.get("L_SEVERITYCODE0").toString();
        }
	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
