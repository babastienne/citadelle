package test.game;

import static org.junit.Assert.*;

import org.junit.Test;

import game.Bank;

public class BankTest {

	@Test
	public void testAddGoldInBank() {
		Bank bank = new Bank();
		assertEquals(false, bank.addGoldInBank(50));
	}
	
	@Test
	public void testGetGoldInBank() {
		Bank bank = new Bank();
		assertEquals(12, bank.getGoldInBank(12));
		assertEquals(0, bank.getGoldInBank(20));
	}

}
