package cz.pfreiberg.knparser.domain.rizeni;

import java.util.Date;

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

}
