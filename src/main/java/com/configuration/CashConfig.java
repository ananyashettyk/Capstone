package com.configuration;

import static springfox.documentation.builders.PathSelectors.regex;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.model.Cash;
import com.model.TransactionData;
import com.repository.CashRepository;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class CashConfig {
	@Autowired
	private CashRepository cashRepository;

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(regex("/cashService.*")).build().apiInfo(metaInfo());
	}

	private ApiInfo metaInfo() {
		ApiInfo apiInfo = new ApiInfo("CashMicroservice CRUD Operations", "CashService", "1.0", "Terms of Service",
				new Contact("Ananya", "-----", "ananya.shetty@capco.com"), null, null);
		return apiInfo;
	}

	@PostConstruct
	public void loadCashData() {
		List<Cash> cashes = new ArrayList<>();
		TransactionData transactionalData1 = TransactionData.builder().count(1).earliestDate("2018-04-02").lastDate("2011-03-04").build();
		Cash cash1 = Cash.builder().cashId(1).type("cash:Current").providerID("DEMO1").accountReference("7729")
				.accountHolderName("Robert Cantu").balanceDate("2020-07-12").amount(new BigDecimal(2500)).aer("1")
				.overdraftLimit(5064).accountHolderName("accountHoldername1").providerName("Starling")
				.accountName("accountName1").sortCodeAccountNumber("56002926207728").iban("HS2960161393846819").transactionData(transactionalData1).build();

		cashes.add(cash1);

		TransactionData transactionalData2 = TransactionData.builder().count(2).earliestDate("2015-07-01").lastDate("2019-07-14").build();
		Cash cash2 = Cash.builder().cashId(2).type("cash:Previous").providerID("DEMO2").accountReference("8829")
				.accountHolderName("Albert Cantu").balanceDate("2019-07-11").amount(new BigDecimal(2000)).aer("2")
				.overdraftLimit(2540).accountHolderName("accountHoldername2").providerName("Starling")
				.accountName("accountName2").sortCodeAccountNumber("26036926205829").iban("GB8175361331927293").transactionData(transactionalData2).build();

		cashes.add(cash2);
		
		TransactionData transactionalData3 = TransactionData.builder().count(3).earliestDate("2014-07-01").lastDate("2017-07-10").build();
		Cash cash3 = Cash.builder().cashId(3).type("Current").providerID("DEMO3").accountReference("6822")
				.accountHolderName("Rahul").balanceDate("2014-07-14").amount(new BigDecimal(3700)).aer("3")
				.overdraftLimit(6382).accountHolderName("accountHoldername3").providerName("HDFC")
				.accountName("accountName3").sortCodeAccountNumber("26036926205829").iban("SR8175392031927346").transactionData(transactionalData3).build();

		cashes.add(cash3);
		
		TransactionData transactionalData4 = TransactionData.builder().count(4).earliestDate("2015-09-11").lastDate("2019-07-14").build();
		Cash cash4 = Cash.builder().cashId(4).type("cash:Previous").providerID("DEMO4").accountReference("8829")
				.accountHolderName("Albert Cantu").balanceDate("2018-03-14").amount(new BigDecimal(4500)).aer("4")
				.overdraftLimit(2540).accountHolderName("accountHoldername4").providerName("Starling")
				.accountName("accountName4").sortCodeAccountNumber("26036926205829").iban("HY8175361331927293").transactionData(transactionalData4).build();

		cashes.add(cash4);

		cashRepository.saveAll(cashes);

	}
}
