package cz.pfreiberg.knparser.domain;

import java.util.Date;

/**
 * Doménové třídy s datumem vzniku a zániku.
 *
 * @author Petr Freiberg (freibergp@gmail.com)
 *
 */
public interface DomainWithDate {

	public Date getDatumVzniku();

	public Date getDatumZaniku();

}
