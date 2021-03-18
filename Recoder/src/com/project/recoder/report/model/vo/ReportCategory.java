/**
 * 
 */
package com.project.recoder.report.model.vo;

/**
 * @author YJ
 *
 */
public class ReportCategory {
	private int categoryCd;
	private String categoryNm;
 	
	public ReportCategory() {
		// TODO Auto-generated constructor stub
	}

	public ReportCategory(int categoryCd, String categoryNm) {
		super();
		this.categoryCd = categoryCd;
		this.categoryNm = categoryNm;
	}

	public int getCategoryCd() {
		return categoryCd;
	}

	public void setCategoryCd(int categoryCd) {
		this.categoryCd = categoryCd;
	}

	public String getCategoryNm() {
		return categoryNm;
	}

	public void setCategoryNm(String categoryNm) {
		this.categoryNm = categoryNm;
	}

	@Override
	public String toString() {
		return "ReportCategory [categoryCd=" + categoryCd + ", categoryNm=" + categoryNm + "]";
	}
	
	
}
