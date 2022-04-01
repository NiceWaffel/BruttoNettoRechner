package de.waffel.bruttonettorechner;

import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class CalculateClickListener implements View.OnClickListener {

    @Override
    public void onClick(View view) {
        View root = view.getRootView();
        String valueAsString = ((EditText) root.findViewById(R.id.editTextNumberDecimal)).getText().toString();
        float value = Float.parseFloat(valueAsString);

        ConversionDirection conversionDirection = ConversionDirection.BRUTTO_TO_NETTO;
        RadioButton nToBRadioButton = ((RadioButton) root.findViewById(R.id.n_to_b));
        if(nToBRadioButton.isChecked()) {
            conversionDirection = ConversionDirection.NETTO_TO_BRUTTO;
        }

        float taxRate = Float.parseFloat(BruttoNettoFragment.selectedTaxRate.substring(0, BruttoNettoFragment.selectedTaxRate.length() - 1)) / 100.0f;

        float result = value;
        if(conversionDirection == ConversionDirection.BRUTTO_TO_NETTO) {
            // Brutto zu Netto
            result /= (1 + taxRate);
        } else {
            // Netto zu Brutto
            result *= (1 + taxRate);
        }

        ((TextView) root.findViewById(R.id.label_tax)).setText(String.format("%s: %.2f", root.getResources().getString(R.string.tax), Math.abs(value - result)));
        ((TextView) root.findViewById(R.id.label_result)).setText(String.format("%s: %.2f", root.getResources().getString(R.string.value), result));
    }
}
