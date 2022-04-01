package de.waffel.bruttonettorechner;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class BruttoNettoFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private final String[] taxRates = {"19%", "7%"};

    public static String selectedTaxRate;

    public BruttoNettoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View newView = inflater.inflate(R.layout.brutto_netto_fragment, container, false);
        Spinner spinner = newView.findViewById(R.id.spinner);
        ArrayAdapter adapter = new ArrayAdapter(this.getContext(), android.R.layout.simple_spinner_item, taxRates);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        selectedTaxRate = taxRates[0];
        spinner.setSelection(0);

        Button calcButton = newView.findViewById(R.id.calc_button);
        calcButton.setOnClickListener(new CalculateClickListener());

        return newView;
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int index, long id) {
        selectedTaxRate = taxRates[index];
        Toast.makeText(this.getContext(), "Selected Tax Rate: " + selectedTaxRate , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
