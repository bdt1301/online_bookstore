package com.htphu.coreservlets;

public class CatalogItem {
	private String itemID;
	private String shortDescription;
	private String longDescription;
	private String image;
	private double cost;

	public CatalogItem(String itemID, String shortDescription, String longDescription, String image, double cost) {
		setItemID(itemID);
		setShortDescription(shortDescription);
		setLongDescription(longDescription);
		setImage(image);
		setCost(cost);
	}

	public String getItemID() {
		return itemID;
	}

	protected void setItemID(String itemID) {
		this.itemID = itemID;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	protected void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getLongDescription() {
		return longDescription;
	}

	protected void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	public String getImage() {
		return image;
	}

	protected void setImage(String image) {
		this.image = image;
	}

	public double getCost() {
		return cost;
	}

	protected void setCost(double cost) {
		this.cost = cost;
	}
}
