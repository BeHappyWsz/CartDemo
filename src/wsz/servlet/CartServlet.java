package wsz.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wsz.dao.ItemsDAO;
import wsz.entity.Cart;
import wsz.entity.Items;

public class CartServlet extends HttpServlet {
	
	private String action;
	
	private ItemsDAO idao = new ItemsDAO();
	
	private static final long serialVersionUID = 1L;

    public CartServlet() {
    	
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("text/html;charset=utf-8");
		action = request.getParameter("action");
		if(action != null) {
			if("add".equals(action)) {
				if(addToCart(request,response)) {
					request.getRequestDispatcher("/success.jsp").forward(request, response);
				}else {
					request.getRequestDispatcher("/failure.jsp").forward(request, response);
				}
			}else if("show".equals(action)) {
				request.getRequestDispatcher("/cart.jsp").forward(request, response);
			}else if("delete".equals(action)){
				if(deleteFromCart(request,response)){
					request.getRequestDispatcher("/cart.jsp").forward(request, response);
				}else{
					request.getRequestDispatcher("/cart.jsp").forward(request, response);
				}
			}
		}
	}

	/**
	 * 从购物车中删除商品
	 * @param request
	 * @param response
	 * @return
	 */
	private boolean deleteFromCart(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		Cart cart = (Cart)request.getSession().getAttribute("cart");
	    Items item = idao.getItemsById(Integer.parseInt(id));
	    if(cart.removeGoods(item)){
	    	return true;
	    }
	    return false;
	}

	private boolean addToCart(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String number = request.getParameter("num");
		
		Items item = idao.getItemsById(Integer.parseInt(id));
		Cart session = (Cart) request.getSession().getAttribute("cart");
		if(session == null) {
			Cart cart = new Cart();
			request.getSession().setAttribute("cart", cart);
		}
		
		if(session.addGoods(item, Integer.parseInt(number)))
			return true;
		return false;
	}

	
}
