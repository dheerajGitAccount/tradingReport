package model.instruction;

import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

/*
 * Following Test File will test Rank POJO fields
 */
public class RankTest {
private Rank rank;
	
	@Before
	public void setup() {
		rank = new Rank();
		rank.setEntity("foo");
		rank.setTotalAmount(BigDecimal.valueOf(100.00).setScale(2, BigDecimal.ROUND_HALF_EVEN));
	}
	
	@Test
	public void testRankModel() {
		//compareTo
		Rank compareRankObj = new Rank();
		compareRankObj.setEntity("foo");
		compareRankObj.setTotalAmount(BigDecimal.valueOf(100.00).setScale(2, BigDecimal.ROUND_HALF_EVEN));
		assertNotNull(rank.compareTo(compareRankObj));
	}
}
