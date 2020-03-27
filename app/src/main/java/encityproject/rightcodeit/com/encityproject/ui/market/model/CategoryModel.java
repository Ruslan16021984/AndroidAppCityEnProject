package encityproject.rightcodeit.com.encityproject.ui.market.model;

import android.graphics.drawable.Icon;

public class CategoryModel {
    String categoryId;
    String categoryName;
    int picDraw;

    public CategoryModel() {
    }

    public CategoryModel(String categoryId, String categoryName, int picDraw) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.picDraw=picDraw;
    }

    public int getPicDraw() {
        return picDraw;
    }

    public void setPicDraw(int picDraw) {
        this.picDraw = picDraw;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "CategoryModel{" +
                "categoryId='" + categoryId + '\'' +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
