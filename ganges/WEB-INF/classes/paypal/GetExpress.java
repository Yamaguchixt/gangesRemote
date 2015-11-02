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
 * Servlet implementation class GetExpress
 */
@WebServlet("/GetExpress")
public class GetExpress extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public GetExpress() {
        super();
        // TODO Auto-generated constructor stub
    }


	@SuppressWarnings({ "rawtypes", "unused" })
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String token = (String) request.getParameter("token");
		System.out.println("in GetExpress token:"+token);
		if ( token != null){
			//String token = (String) session.getAttribute("token");
			//tokenはpaypalから返してもらえるのか、自分でsession保管なのかどっち。
			paypalfunctions ppf = new paypalfunctions();
			HashMap nvp = ppf.getExpressCheckoutDetails(token);
			System.out.println("in GetExpress Detailを取得したnvpの中身");
			System.out.println(nvp.keySet());
			//paypalfunctions.showNvp(nvp);

			if (nvp == null){System.out.println("GetExpressCheckoutDetailsの戻り値nvpがnull");	return;}

			session.setAttribute("nvp",nvp);

			String strAck = nvp.get("ACK").toString();
			if(strAck != null && (strAck.equalsIgnoreCase("Success") || strAck.equalsIgnoreCase("SuccessWithWarning"))){
				String email 			= nvp.get("EMAIL").toString(); // ' Email address of payer.
				String payerId 			= nvp.get("PAYERID").toString(); // ' Unique PayPal customer account identification number.

				session.setAttribute("PAYERID", payerId);

				if(nvp.containsKey("PAYERSTATUS")){String payerStatus = nvp.get("PAYERSTATUS").toString();} // ' Status of payer. Character length and limitations: 10 single-byte alphabetic characters.
				if(nvp.containsKey("SALUTATION")){String salutation = nvp.get("SALUTATION").toString();} // ' Payer's salutation.
				if(nvp.containsKey("FIRSTNAME")){String firstName = nvp.get("FIRSTNAME").toString();} // ' Payer's first name.
				if(nvp.containsKey("MIDDLENAME")){String middleName	= nvp.get("MIDDLENAME").toString();} // ' Payer's middle name.
				if(nvp.containsKey("LASTNAME")){String lastName	= nvp.get("LASTNAME").toString();} // ' Payer's last name.
				if(nvp.containsKey("SUFFIX")){String suffix	= nvp.get("SUFFIX").toString();}
				/*
				String cntryCode		= nvp.get("COUNTRYCODE").toString(); // ' Payer's country of residence in the form of ISO standard 3166 two-character country codes.
				String business			= nvp.get("BUSINESS").toString(); // ' Payer's business name.
				String shipToName		= nvp.get("PAYMENTREQUEST_0_SHIPTONAME").toString(); // ' Person's name associated with this address.
				String shipToStreet		= nvp.get("PAYMENTREQUEST_0_SHIPTOSTREET").toString(); // ' First street address.
				String shipToStreet2	= nvp.get("PAYMENTREQUEST_0_SHIPTOSTREET2").toString(); // ' Second street address.
				String shipToCity		= nvp.get("PAYMENTREQUEST_0_SHIPTOCITY").toString(); // ' Name of city.
				String shipToState		= nvp.get("PAYMENTREQUEST_0_SHIPTOSTATE").toString(); // ' State or province
				String shipToCntryCode	= nvp.get("PAYMENTREQUEST_0_SHIPTOCOUNTRYCODE").toString(); // ' Country code.
				String shipToZip		= nvp.get("PAYMENTREQUEST_0_SHIPTOZIP").toString(); // ' U.S. Zip code or other country-specific postal code.
				String addressStatus 	= nvp.get("ADDRESSSTATUS").toString(); // ' Status of street address on file with PayPal
				String invoiceNumber	= nvp.get("INVNUM").toString(); // ' Your own invoice or tracking number, as set by you in the element of the same name in SetExpressCheckout request .
				String phonNumber		= nvp.get("PHONENUM").toString(); // ' Payer's contact telephone number. Note:  PayPal returns a contact telephone number only if your Merchant account profile settings require that the buyer enter one.
				*/
				/*
				' The information that is returned by the GetExpressCheckoutDetails call should be integrated by the partner into his Order Review
				' page
				*/
			} else{
				// Display a user friendly Error on the page using any of the following error information returned by PayPal

				String ErrorCode = nvp.get("L_ERRORCODE0").toString();
				String ErrorShortMsg = nvp.get("L_SHORTMESSAGE0").toString();
				String ErrorLongMsg = nvp.get("L_LONGMESSAGE0").toString();
				String ErrorSeverityCode = nvp.get("L_SEVERITYCODE0").toString();
			}
		}

		System.out.println("in GetExpress dispathcerの直前まできてる");
		request.getRequestDispatcher("jsp/paypal/PurchaseConfirm.jsp").forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
