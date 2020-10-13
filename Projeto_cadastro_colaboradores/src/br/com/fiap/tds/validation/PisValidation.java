package br.com.fiap.tds.validation;

import java.util.InputMismatchException;

public class PisValidation {

	// Valida o PIS
	public boolean isPis(String pis) {

		if (pis.length() != 11)
			return false;

		String n = pis;

		int i;
		int digito;
		int coeficiente;
		int soma;
		int encontraDv;

		try {

			int dv = Integer.parseInt(String.valueOf(n.charAt(n.length() - 1)));

			soma = 0;
			coeficiente = 2;

			for (i = n.length() - 2; i >= 0; i--) {

				digito = Integer.parseInt(String.valueOf(n.charAt(i)));
				soma += digito * coeficiente;
				coeficiente++;
				if (coeficiente > 9) {
					coeficiente = 2;
				}
			}

			encontraDv = 11 - soma % 11;
			if (encontraDv >= 10) {
				encontraDv = 0;
			}
			return dv == encontraDv;

		} catch (InputMismatchException e) {
			return false;
		}
	}

}
