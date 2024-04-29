package com.htphu.coreservlets;

import javax.servlet.annotation.WebServlet;

@WebServlet("/NovelBooksPage")
public class NovelBooksPage extends CatalogPage {
	private static final long serialVersionUID = 1L;

	public void init() {
		String[] ids = { "lewis001", "alexander001", "rowling001" };
		setItems(ids);
		setTitle("The Best Novels and Short Stories of All Time");
	}
} 	