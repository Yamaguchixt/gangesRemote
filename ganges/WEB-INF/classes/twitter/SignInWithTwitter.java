package twitter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import util.Util;

/**
 * Servlet implementation class SignInWithTwitter
 */
@WebServlet("/SignInWithTwitter")
public class SignInWithTwitter extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SignInWithTwitter() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Twitter twitter = new TwitterFactory().getInstance();
		twitter.setOAuthConsumer("nna3NOQROozZzcSLdvjGElI5O","VHkzk4I2vAkTWPP7jqzb74Lfbztb8NNXNyNkWaRYEzffBQjZv9");
		String action = request.getParameter("action");
		HttpSession session = request.getSession();

		if("authorize".equals(action)){
			try{
					System.out.println("in authorize action");
				RequestToken requestToken = twitter.getOAuthRequestToken(request.getRequestURL() + "?action=callback");

					System.out.println("==== requestToken ====");
					Util.printFields(requestToken);
					System.out.println("==== requetToken ====");

				session.setAttribute("requestToken",requestToken);
				response.sendRedirect(requestToken.getAuthenticationURL());
					System.out.println("requestToke#getAuenticationURL :" + requestToken.getAuthenticationURL());

			}catch(TwitterException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}

		if("callback".equals(action)){
			try {
				AccessToken accessToken = twitter.getOAuthAccessToken(
					(RequestToken)session.getAttribute("requestToken"),
					request.getParameter("oauth_verifier"));
				session.setAttribute("accessToken",accessToken);
				session.removeAttribute("requestToken");
			} catch (TwitterException e) {
				e.printStackTrace();
			}
		}

		if(null != session.getAttribute("accessToken")){
			session.setAttribute("isAuthorized",true);
			request.getRequestDispatcher("jsp/main.jsp").forward(request,response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
