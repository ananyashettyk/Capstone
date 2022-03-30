package com.serviceImpl;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.annotation.TrackExecutionTime;
import com.model.Cash;
import com.exception.CashApiRequestException;
import com.repository.CashRepository;
import com.service.CashService;
import com.constants.Constants;

import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

@EqualsAndHashCode
@Slf4j
@Service
public class CashServiceImpl implements CashService {

	boolean isPost = false;
	private CashRepository cashRepository;

	CashServiceImpl(CashRepository cashRepository) {
		this.cashRepository = cashRepository;
	}

	@TrackExecutionTime
	@Override
	public Cash postCashDetails(Cash cash) {
		Integer newCashId = cash.getCashId();
		if (!cashRepository.findById(newCashId).isEmpty()) {
			throw new CashApiRequestException(Constants.CASH_ID_ALREDAY_EXISTS);
		}
		cashRepository.save(cash);
		return cash;
	}

	@Override
	public Cash getCashDetailsById(Integer id) {
		log.info("From the database...");
		Optional<Cash> cash = cashRepository.findById(id);

		if (cash.isPresent())
			return cash.get();
		else
			throw new CashApiRequestException("Invalid Cash id>>>>>>");
	}

	@Override
	public List<Cash> getCashDetails() {
		log.info("From the database...");
		return cashRepository.findAll();
	}

	@Override
	public Cash updateCashDetailsWithId(Cash cash) {
		Cash resultCash = new Cash();
		resultCash.setCashId(cash.getCashId());
		Optional<Cash> existingCash = cashRepository.findById(cash.getCashId());
		if (existingCash.get() == null) {
			throw new CashApiRequestException("Invalid cash id for update");
		}

		if ((!cash.getAccountName().isBlank())
				&& !cash.getAccountName().trim().equals(existingCash.get().getAccountName())) {
			resultCash.setAccountName(cash.getAccountName());
		} else {
			resultCash.setAccountName(existingCash.get().getAccountName());
		}

		if ((!cash.getType().isBlank()) && !cash.getType().trim().equals(existingCash.get().getType())) {
			resultCash.setType(cash.getType());
		} else {
			resultCash.setType(existingCash.get().getType());
		}

		if ((!cash.getProviderName().isBlank())
				&& !cash.getProviderName().equals(existingCash.get().getProviderName())) {
			resultCash.setProviderName(cash.getProviderName());
		} else {
			resultCash.setProviderName(existingCash.get().getProviderName());
		}

		if ((!cash.getProviderID().isBlank()) && !cash.getProviderID().equals(existingCash.get().getProviderID())) {
			resultCash.setProviderID(cash.getProviderID());
		} else {
			resultCash.setProviderID(existingCash.get().getProviderID());
		}

		if ((!cash.getAccountReference().isBlank())
				&& !cash.getAccountReference().equals(existingCash.get().getAccountReference())) {
			resultCash.setAccountReference(cash.getAccountReference());
		} else {
			resultCash.setAccountReference(existingCash.get().getAccountReference());
		}

		if ((!cash.getAccountHolderName().isBlank())
				&& !cash.getAccountHolderName().equals(existingCash.get().getAccountHolderName())) {
			resultCash.setAccountHolderName(cash.getAccountHolderName());
		} else {
			resultCash.setAccountHolderName(existingCash.get().getAccountHolderName());
		}

		if (!cash.getBalanceDate().isBlank() && !cash.getBalanceDate().equals(existingCash.get().getBalanceDate())) {
			resultCash.setBalanceDate(cash.getBalanceDate());
		} else {
			resultCash.setBalanceDate(existingCash.get().getBalanceDate());
		}

		if ((cash.getAmount() == null || cash.getAmount().intValue() == 0)) {
			resultCash.setAmount(existingCash.get().getAmount());
		} else {
			resultCash.setAmount(cash.getAmount());
		}

		if ((!cash.getAer().isBlank()) && !cash.getAer().equals(existingCash.get().getAer())) {
			resultCash.setAer(cash.getAer());
		} else {
			resultCash.setAer(existingCash.get().getAer());
		}

		if ((cash.getOverdraftLimit() != null && cash.getOverdraftLimit().intValue() == 0)
				&& !cash.getOverdraftLimit().equals(existingCash.get().getOverdraftLimit())) {
			resultCash.setOverdraftLimit(cash.getOverdraftLimit());
		} else {
			resultCash.setOverdraftLimit(existingCash.get().getOverdraftLimit());
		}

		if ((!cash.getSortCodeAccountNumber().isBlank())
				&& !cash.getSortCodeAccountNumber().equals(existingCash.get().getSortCodeAccountNumber())) {
			resultCash.setSortCodeAccountNumber(cash.getSortCodeAccountNumber());
		} else {
			resultCash.setSortCodeAccountNumber(existingCash.get().getSortCodeAccountNumber());
		}

		if ((!cash.getIban().isBlank()) && !cash.getIban().equals(existingCash.get().getIban())) {
			resultCash.setIban(cash.getIban());
		} else {
			resultCash.setIban(existingCash.get().getIban());
		}

		cashRepository.save(resultCash);
		return resultCash;
	}

	@Override
	public String deleteCashDetails(Integer id) {
		cashRepository.deleteById(id);
		return "Cash details deleted and saved";
	}
}
