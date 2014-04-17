package edu.gatech.nutrack.model;

public class Nutrition {
	private String foodName, summary, upc;
	private int calories, totalFat, satFat, transFat, 
	protein, totalCarb, dietFiber, sugars, sodium, cholesterol;
	
	public Nutrition() {
		this.foodName = "";
		this.calories = 0;
		this.totalFat = 0;
		this.satFat = 0;
		this.transFat = 0;
		this.protein = 0;
		this.totalCarb = 0;
		this.dietFiber = 0;
		this.sugars = 0;
		this.sodium = 0;
		this.cholesterol = 0;
		this.summary = "";
		this.upc = "";
	}

	public Nutrition(String foodName, int calories, int totalFat, int satFat,
			int transFat, int protein, int totalCarb, int dietFiber,
			int sugars, int sodium, int cholesterol, String summary, String upc) {
		this.foodName = foodName;
		this.calories = calories;
		this.totalFat = totalFat;
		this.satFat = satFat;
		this.transFat = transFat;
		this.protein = protein;
		this.totalCarb = totalCarb;
		this.dietFiber = dietFiber;
		this.sugars = sugars;
		this.sodium = sodium;
		this.cholesterol = cholesterol;
		this.summary = summary;
		this.upc = upc;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public int getCalories() {
		return calories;
	}

	public void setCalories(int calories) {
		this.calories = calories;
	}

	public int getTotalFat() {
		return totalFat;
	}

	public void setTotalFat(int totalFat) {
		this.totalFat = totalFat;
	}

	public int getSatFat() {
		return satFat;
	}

	public void setSatFat(int satFat) {
		this.satFat = satFat;
	}

	public int getTransFat() {
		return transFat;
	}

	public void setTransFat(int transFat) {
		this.transFat = transFat;
	}

	public int getProtein() {
		return protein;
	}

	public void setProtein(int protein) {
		this.protein = protein;
	}

	public int getTotalCarb() {
		return totalCarb;
	}

	public void setTotalCarb(int totalCarb) {
		this.totalCarb = totalCarb;
	}

	public int getDietFiber() {
		return dietFiber;
	}

	public void setDietFiber(int dietFiber) {
		this.dietFiber = dietFiber;
	}

	public int getSugars() {
		return sugars;
	}

	public void setSugars(int sugars) {
		this.sugars = sugars;
	}

	public int getSodium() {
		return sodium;
	}

	public void setSodium(int sodium) {
		this.sodium = sodium;
	}

	public int getCholesterol() {
		return cholesterol;
	}

	public void setCholesterol(int cholesterol) {
		this.cholesterol = cholesterol;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getUpc() {
		return upc;
	}

	public void setUpc(String upc) {
		this.upc = upc;
	}
}
