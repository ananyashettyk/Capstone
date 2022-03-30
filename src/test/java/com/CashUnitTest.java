package com;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.model.Cash;
import com.repository.CashRepository;
import com.service.CashService;

@SpringBootTest
public class CashUnitTest {
	@Mock
	private CashRepository cashRepository;

	@MockBean
	CashService cashServiceMock;

	@Test
	public void shouldPostCashDetails() {
		Cash cashes = new Cash();
		cashes.setCashId(1);
		cashes.setAccountHolderName("accountHolderName1");
		cashes.setAccountName("acountName1");
		
		when(cashServiceMock.postCashDetails(cashes)).thenReturn(cashes);
		assertTrue(cashServiceMock.postCashDetails(cashes).getAccountHolderName().equals("accountHolderName1"));
	}
	
	@Test
	public void shouldGetCashDetails() {
		List<Cash> cashes = buildCashes();
		
		when(cashServiceMock.getCashDetails()).thenReturn(cashes);
		assertTrue(cashServiceMock.getCashDetails().get(0).getAccountHolderName().equals("accountHolderName1"));
	}
	
	@Test
	public void shouldGetCashDetailsById() {
		List<Cash> cashes = buildCashes();
		
		when(cashServiceMock.getCashDetailsById(2)).thenReturn(cashes.get(1));
		assertTrue(cashServiceMock.getCashDetailsById(2).getAccountHolderName().equals("accountHolderName2"));
	}

	@Test
	public void shouldUpdateCashDetails() {
		List<Cash> cashes = buildCashes();
		cashes.get(0).setAccountName("newAccount1");
		
		when(cashServiceMock.updateCashDetailsWithId(cashes.get(0))).thenReturn(cashes.get(0));
		assertTrue(cashServiceMock.updateCashDetailsWithId(cashes.get(0)).getAccountName().equals("newAccount1"));
	}
		
	
	private List<Cash> buildCashes() {
		Cash cash1 = new Cash();
		cash1.setCashId(1);
		cash1.setAccountHolderName("accountHolderName1");
		cash1.setAccountName("acountName1");
		
		Cash cash2 = new Cash();
		cash2.setCashId(2);
		cash2.setAccountHolderName("accountHolderName2");
		cash2.setAccountName("acountName2");
		
		List<Cash> cashes = new ArrayList<>();
		cashes.add(cash1);
		cashes.add(cash2);
		return cashes;
	}

}
