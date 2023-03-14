package com.matheus.bookservice.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Cambio implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String from;
	private String to;
	private Double conversionFactor;
	private Double convertedValue;
	private String environment;


}
