package cz.pfreiberg.knparser.domain.rizeni;

import java.util.Date;

/**
 * 
 * Třída reprezentující "Objekty řízení (parcely, budovy,..)".
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class ObjektyRizeni {

	private Long id;
	private Long rizeniId;
	private Long parId;
	private Long budId;
	private Long jedId;
	private Date datumPlomby;
	private Date datumOdstraneniPlomby;
	private Date datumHistOd;
	private Date datumHistDo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRizeniId() {
		return rizeniId;
	}

	public void setRizeniId(Long rizeniId) {
		this.rizeniId = rizeniId;
	}

	public Long getParId() {
		return parId;
	}

	public void setParId(Long parId) {
		this.parId = parId;
	}

	public Long getBudId() {
		return budId;
	}

	public void setBudId(Long budId) {
		this.budId = budId;
	}

	public Long getJedId() {
		return jedId;
	}

	public void setJedId(Long jedId) {
		this.jedId = jedId;
	}

	public Date getDatumPlomby() {
		return datumPlomby;
	}

	public void setDatumPlomby(Date datumPlomby) {
		this.datumPlomby = datumPlomby;
	}

	public Date getDatumOdstraneniPlomby() {
		return datumOdstraneniPlomby;
	}

	public void setDatumOdstraneniPlomby(Date datumOdstraneniPlomby) {
		this.datumOdstraneniPlomby = datumOdstraneniPlomby;
	}

	public Date getDatumHistOd() {
		return datumHistOd;
	}

	public void setDatumHistOd(Date datumHistOd) {
		this.datumHistOd = datumHistOd;
	}

	public Date getDatumHistDo() {
		return datumHistDo;
	}

	public void setDatumHistDo(Date datumHistDo) {
		this.datumHistDo = datumHistDo;
	}

}
