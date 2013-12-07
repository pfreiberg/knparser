package cz.pfreiberg.knparser.domain.rizeni;

import java.util.Date;

import cz.pfreiberg.knparser.util.VfkUtil;

/**
 * 
 * Třída reprezentující "Vazba Listiny – Další údaje listin".
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class ListinyDalsiUdaje {

	private Long listinId;
	private String dulKod;
	private Date createDate;

	public Long getListinId() {
		return listinId;
	}

	public void setListinId(Long listinId) {
		this.listinId = listinId;
	}

	public String getDulKod() {
		return dulKod;
	}

	public void setDulKod(String dulKod) {
		this.dulKod = dulKod;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "" + VfkUtil.formatValue(listinId) + ","
				+ VfkUtil.formatValue(dulKod) + ","
				+ VfkUtil.formatValue(createDate) 
				+ VfkUtil.getTerminator();
	}

}
