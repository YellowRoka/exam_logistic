package hu.webuni.logistic.service;

import org.springframework.data.domain.Page;

public class ExtendedPage<T> {
	private Page<T> pages;
	private long    X_Total_Count = 0;
	
	public Page<T> getPages() {
		return pages;
	}
	public void setPages(Page<T> pages) {
		this.pages = pages;
	}
	public long getX_Total_Count() {
		return X_Total_Count;
	}
	public void setX_Total_Count(long x_Total_Count) {
		X_Total_Count = x_Total_Count;
	}
	

}

