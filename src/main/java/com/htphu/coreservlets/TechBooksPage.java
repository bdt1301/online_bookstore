package com.htphu.coreservlets;

import javax.servlet.annotation.WebServlet;

@WebServlet("/TechBooksPage")
public class TechBooksPage extends CatalogPage {
	private static final long serialVersionUID = 1L;

	public void init() {
		String[] ids = { "hall001", "hall002", "singh001" };
		setItems(ids);
		setTitle("The Best Science and Technology Books of All Time");
	}
}