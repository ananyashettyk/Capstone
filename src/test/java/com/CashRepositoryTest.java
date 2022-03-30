package com;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import com.model.Cash;
import com.model.TransactionData;
import com.repository.CashRepository;

@DataJpaTest
public class CashRepositoryTest {

	@Autowired
	private CashRepository cashRepository;

	@Test
	void shouldSaveAndFlush() {
		// given
		TransactionData transactionalData = TransactionData.builder().count(3).earliestDate("2018-04-02")
				.lastDate("2011-03-04").build();

		Cash cash = Cash.builder()
				.cashId(1)
				  .accountName("Starling Current Account")
				  .type("cash:Current")
				  .providerName("Starling")
				  .providerID("DEMO")
				  .accountReference("7729")
				  .accountHolderName("Robert Cantu")
				  .balanceDate("2020-07-12")
				  .amount(new BigDecimal(124885))
				  .aer("0")
				  .overdraftLimit(2)
				  .sortCodeAccountNumber("56002926207729")
				  .iban("GB2960161331926819")
				  .transactionData(transactionalData)
				  .build();
		

		cashRepository.save(cash);

		// when
		boolean exists = cashRepository.saveAndFlush(cash) != null;

		// then
		assertThat(exists).isTrue();
	}

}
