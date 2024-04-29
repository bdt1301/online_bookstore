package com.htphu.coreservlets;

import javax.servlet.annotation.WebServlet;

@WebServlet("/PsychoBooksPage")
public class PsychoBooksPage extends CatalogPage {
	private static final long serialVersionUID = 1L;

	public void init() {
		String[] ids = { "duhigg001", "dweck001", "manson001" };
		setItems(ids);
		setTitle("The Best Human Psychology Books of All Time");
	}
}
