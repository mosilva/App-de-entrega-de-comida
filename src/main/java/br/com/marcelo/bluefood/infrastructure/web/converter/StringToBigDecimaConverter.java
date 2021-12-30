package br.com.marcelo.bluefood.infrastructure.web.converter;

import java.math.BigDecimal;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.marcelo.bluefood.util.StringUtils;

@Component
public class StringToBigDecimaConverter implements Converter<String, BigDecimal> {

	@Override
	public BigDecimal convert(String source) {
		if(StringUtils.isEmpty(source)) {
			return null;
		}
		
		source = source.replace(",", ".").trim();
		return BigDecimal.valueOf(Double.valueOf(source));
	}



	
	
	

	
}
