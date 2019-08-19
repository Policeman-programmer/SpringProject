package ua.epam.springProject.domain;

import static java.util.stream.Collectors.*;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.Test;

public class TestAuditorium {
	
	@Test
	public void testCountVips() {
		Auditorium a = new Auditorium();
		a.setVipSeats(Stream.of(1L,2L,3L).collect(toSet()));
		assertEquals(0, a.countVipSeats(Arrays.asList(10L, 20L, 30L)));
		assertEquals(1, a.countVipSeats(Arrays.asList(10L, 2L, 30L)));
		assertEquals(2, a.countVipSeats(Arrays.asList(10L, 2L, 3L, 4L, 5L, 6L)));
	}

	@Test
	public void testGetAllSeats() {
	    Auditorium a = new Auditorium();
	    a.setNumberOfSeats(10);
	    assertEquals(10, a.getAllSeats().size());
	}

}
