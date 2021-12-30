package br.com.marcelo.bluefood.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class FormatUtils {
	
	private static final Locale LOCALE_BRAZIL = new Locale("pt","BR");

	public static NumberFormat newCurrecyFormat() {
		NumberFormat nf = NumberFormat.getNumberInstance(LOCALE_BRAZIL);
		
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
		nf.setGroupingUsed(false);
		
		return nf;
		
	}

	public static String formatCurrency(BigDecimal number) {
		if(number == null) {
			return null;
		}
		
		return newCurrecyFormat().format(number);
		
	}
	
}
