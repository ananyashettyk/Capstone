package com.model;

import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class TransactionData {

	private Integer count;

	private String earliestDate;

	private String lastDate;

}
