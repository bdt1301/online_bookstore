package com.htphu.coreservlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;

@WebServlet("/OrderPage")
public class OrderPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String itemID = request.getParameter("itemID");
		ShoppingCart cart;
		synchronized (session) {
			cart = (ShoppingCart) session.getAttribute("shoppingCart");
			if (cart == null) {
				cart = new ShoppingCart();
				session.setAttribute("shoppingCart", cart);
			}
			if (itemID != null) {
				String numItemsString = request.getParameter("numItems");
				if (numItemsString == null) {
					cart.addItem(itemID);
				} else {
					int numItems;
					try {
						numItems = Integer.parseInt(numItemsString);
					} catch (NumberFormatException nfe) {
						numItems = 1;
					}
					cart.setNumOrdered(itemID, numItems);
				}
			}
		}
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String title = "Status of Your Order";
		String docType = "<!DOCTYPE HTML>\n";
		out.println(docType + "<HTML>\n" + "<HEAD>" + "<meta charset=\"UTF-8\">" + "<TITLE>" + title + "</TITLE>"
				+ "<link rel=\"icon\" type=\"image/x-icon\" href=\"./assets/img/favicon.ico\">"
				+ "<link rel=\"stylesheet\" href=\"./assets/css/header.css\" />" + "<link rel=\"stylesheet\"\r\n"
				+ "	href=\"./assets/fonts/fontawesome-free-6.5.2-web/css/all.min.css\">"
				+ "<link rel=\"stylesheet\" href=\"./assets/css/order.css\" />"
				+ "<script src=\"./assets/script/order.js\"></script>" + "</HEAD>\n"
				+ "<BODY>\n<div class=\"header\">\r\n<h1 class=\"heading-section\" style=\"margin:0;padding:0;\">"
				+ title + "</h1>"
				+ "<a href=\"index.html\" class=\"header-icon-link home-icon\"><i class=\"black-font header-icon fa-solid fa-house\"></i></a> "
				+ "<a href=\"/OnlineBookstore/OrderPage\" class=\"header-icon-link cart-icon\"><i class=\"black-font header-icon fa-solid fa-cart-shopping\"></i></a>\n</div>");
		synchronized (session) {
			List<?> itemsOrdered = cart.getItemsOrdered();
			if (itemsOrdered.size() == 0) {
				out.println("<H2 style=\"text-align:center;\"><I>No items in your cart...</I></H2>");
			} else {
				out.println("<TABLE align=\"center\">\n" + "<TR BGCOLOR=\"#FFAD00\">\n" + " <TH>Image<TH>Description\n"
						+ " <TH>Unit Cost<TH>Number<TH>Total Cost");
				ItemOrder order;
				NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
				double totalOrderCost = 0.0;
				for (int i = 0; i < itemsOrdered.size(); i++) {
					order = (ItemOrder) itemsOrdered.get(i);
					out.println("<TR>\n" + " <TD><img src=\"" + order.getImage() + "\" alt=\"\">\n" + " <TD>"
							+ order.getShortDescription() + "\n" + " <TD>" + formatter.format(order.getUnitCost())
							+ "\n" + " <TD>" + "<FORM>\n" + "<INPUT TYPE=\"HIDDEN\" NAME=\"itemID\" VALUE=\""
							+ order.getItemID() + "\">\n"
							+ "<INPUT class=\"input-btn\" TYPE=\"BUTTON\" VALUE=\"-\" onclick=\"decrementQuantity(" + i
							+ ")\">\n" + "<INPUT TYPE=\"TEXT\" id=\"quantity" + i
							+ "\" NAME=\"numItems\" SIZE=3 VALUE=\"" + order.getNumItems() + "\">\n"
							+ "<INPUT class=\"input-btn\" TYPE=\"BUTTON\" VALUE=\"+\" onclick=\"incrementQuantity(" + i
							+ ")\">\n" + "<SMALL>\n" + "<INPUT class=\"input-btn\" TYPE=\"SUBMIT\"\n "
							+ " VALUE=\"Update\">\n" + "</SMALL>\n" + "</FORM>\n" + " <TD>"
							+ formatter.format(order.getTotalCost()));
					totalOrderCost += order.getTotalCost();
				}
				out.println("<p class=\"total-values\"><strong>Total Order Values:</strong> "
						+ formatter.format(totalOrderCost) + "</p>");
				out.println("</TABLE>\n");
			}
			String previousServletUrl = (String) session.getAttribute("previousServletUrl");
			if (previousServletUrl != null) {
				out.println("<form align=\"center\" action=\"" + previousServletUrl + "\">"
						+ "<button class=\"input-btn back-btn\" type=\"submit\">Back</button>" + "</form>");
			}
			out.println("</BODY></HTML>");
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}