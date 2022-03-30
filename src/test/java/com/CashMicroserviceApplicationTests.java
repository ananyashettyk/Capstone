package com;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.model.Cash;
import com.model.TransactionData;
import com.exception.CashApiRequestException;
import com.repository.CashRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
class CashMicroserviceApplicationTests {

	private RestTemplate restTemplate;
	String cashResourceUrl;

	@Autowired
	CashRepository cashRepository;

	@BeforeEach
	public void SetUp() {
		cashResourceUrl = "http://localhost:8080/cashService";
		restTemplate = new RestTemplate();
	}

	@Test
	public void shouldPostCashDetails() {
		Cash cash = buildCash();

		postCashDetails(cash);
		log.info("cashResourceUrl " + cashResourceUrl);
		assertThat(cash.getAccountName()).isEqualTo("Starling Current Account");

	}

	@Test
	public void shouldGetCashDetails() {
//		shouldPostCashDetails();

		ResponseEntity<String> getCashDetails = restTemplate.getForEntity(cashResourceUrl + "/getCash", String.class);
		log.info(getCashDetails.getBody());
	}

	@Test
	public void shouldGetCashDetailsById() {
//		shouldPostCashDetails();

		ResponseEntity<String> getCashDetails = restTemplate.getForEntity(cashResourceUrl + "/getCash" + "?id=",
				String.class);
		log.info(getCashDetails.getBody());
	}

//	@Test  tbd need to fix it
	public void shouldPutCashDetails() {
//		shouldPostCashDetails();

		Cash cash = buildCash();

		restTemplate.put(cashResourceUrl + "/updateCash/" + 10, cash, String.class);
		Optional<Cash> updatedCash = cashRepository.findById(4);

		assertTrue(updatedCash.get().getAccountName().equals("Starling Previous Account"));
		assertTrue(updatedCash.get().getAccountHolderName().equals("Albert Cantu"));
		assertTrue(updatedCash.get().getAccountReference().equals("8829"));

		log.info(">>>>>>>>>>>>>>>>>>>>>>>>" + updatedCash.get().getAccountName());
	}

	@Test
	public void shouldDeleteCashDetails() {
//		shouldPostCashDetails();

		int id = 10;
		Boolean flag = false;
		List<Cash> list = cashRepository.findAll();
		for (Cash cash : list) {
			if (cash.getCashId() == id) {
				restTemplate.delete(cashResourceUrl + "/deleteCash/" + id);
				flag = true;
			}
		}
		assertTrue(flag.equals(true));
	}

//	@Test  tbd need to fix it
	public void shouldThrowExceptionWhenTypeNullPostCashDetails2() {
		log.info("cashResourceUrl " + cashResourceUrl);
		Exception exception = assertThrows(CashApiRequestException.class, () -> {
			Cash cash = buildCash();
			postCashDetails(cash);
		});

		String expectedMessage = "TYPE is mandatory";
		String actualMessage = exception.getMessage();
		System.out.println(">>>>>>>>>>>>>>>>>>" + exception.getMessage());
		assertTrue(actualMessage.contains(expectedMessage));
	}

	private Cash buildCash() {
		TransactionData transactionalData = TransactionData.builder().count(3).earliestDate("2018-04-02")
				.lastDate("2011-03-04").build();
		Cash cash = Cash.builder().cashId(10).accountName("Starling Current Account").type("cash:Current")
				.providerName("Starling").providerID("DEMO").accountReference("7729").accountHolderName("Robert Cantu")
				.balanceDate("2020-07-12").amount(new BigDecimal(124885)).aer("0").overdraftLimit(2)
				.sortCodeAccountNumber("56002926207729").iban("GB2960161331926819").transactionData(transactionalData)
				.build();

		return cash;
	}

	private void postCashDetails(Cash cash) {
		ResponseEntity<String> postCashDetails = restTemplate.postForEntity(cashResourceUrl + "/postCash", cash,
				String.class);
		log.info(">>>>>>>>>>>>>>>" + postCashDetails.getBody());
		assertTrue(postCashDetails.getBody().contains("Starling Current Account"));

	}
}