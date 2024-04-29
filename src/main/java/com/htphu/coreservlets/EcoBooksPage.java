package com.htphu.coreservlets;

import javax.servlet.annotation.WebServlet;

@WebServlet("/EcoBooksPage")
public class EcoBooksPage extends CatalogPage {
	private static final long serialVersionUID = 1L;

	public void init() {
		String[] ids = { "knowles001", "forsythe001", "thompson001" };
		setItems(ids);
		setTitle("The Best Economic Books of All Time");
	}
}