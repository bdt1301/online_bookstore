package com.htphu.coreservlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public abstract class CatalogPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CatalogItem[] items;
	private String[] itemIDs;
	private String title;

	protected void setItems(String[] itemIDs) {
		this.itemIDs = itemIDs;
		items = new CatalogItem[itemIDs.length];
		for (int i = 0; i < items.length; i++) {
			items[i] = Catalog.getItem(itemIDs[i]);
		}
	}

	protected void setTitle(String title) {
		this.title = title;
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (items == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "Missing Items.");
			return;
		}
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String docType = "<!DOCTYPE HTML>\n";
		out.println(docType + "<HTML>\n" + "<HEAD>" + "<meta charset=\"UTF-8\">" + "<TITLE>" + title + "</TITLE>"
				+ "<link rel=\"icon\" type=\"image/x-icon\" href=\"./assets/img/favicon.ico\">"
				+ "<link rel=\"stylesheet\" href=\"./assets/css/reset.css\" />"
				+ "<link rel=\"stylesheet\" href=\"./assets/css/grid.css\" />"
				+ "<link rel=\"stylesheet\" href=\"./assets/css/header.css\" />"
				+ "<link rel=\"stylesheet\" href=\"./assets/fonts/fontawesome-free-6.5.2-web/css/all.min.css\">"
				+ "<link rel=\"stylesheet\" href=\"./assets/css/catalog.css\" />" + "</HEAD>\n"
				+ "<BODY BGCOLOR=\"#FDF5E6\">\n" + "<div class=\"header\">\r\n<h1 class=\"heading-section white-font\">"
				+ title + "</h1>"
				+ "<a href=\"index.html\" class=\"header-icon-link home-icon\"><i class=\"header-icon black-font fa-solid fa-house\"></i></a> "
				+ "<a href=\"/OnlineBookstore/OrderPage\" class=\"header-icon-link cart-icon\"><i class=\"header-icon black-font fa-solid fa-cart-shopping\"></i></a>\n</div>"
				+ "<div class=\"grid wide zindex-1\">\r\n" + "<div class=\"row\">");
		CatalogItem item;
		for (int i = 0; i < items.length; i++) {
			out.println("<HR>");
			item = items[i];
			if (item == null) {
				out.println("<FONT COLOR=\"RED\">" + "Unknown item ID " + itemIDs[i] + "</FONT>");
			} else {
				out.println();
				String formURL = "OrderPage";
				formURL = response.encodeURL(formURL);
				out.println("<FORM ACTION=\"" + formURL + "\">\n" + "<INPUT TYPE=\"HIDDEN\" NAME=\"itemID\" VALUE=\""
						+ item.getItemID() + "\">\n" + "<div class=\"book-details\">\n" + "<img src=\""
						+ item.getImage() + "\" class=\"book-image\" alt=\"Illustration\">\n"
						+ "<div class=\"book-description\">\n" + "<H2>" + item.getShortDescription() + " ($"
						+ item.getCost() + ")</H2>\n<p>" + item.getLongDescription() + "</p>"
						+ "<center><input class=\"submit-btn\" type=\"submit\" value=\"Add to Shopping Cart\"></center>"
						+ "</div>\n" + "</div>\n</FORM>");

			}
		}
		String currentServletUrl = request.getRequestURL().toString();
		HttpSession session = request.getSession();
		session.setAttribute("previousServletUrl", currentServletUrl);
		out.println("<HR>\n</div>\r\n" + "</div></BODY></HTML>");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}