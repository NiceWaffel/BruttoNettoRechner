package de.waffel.bruttonettorechner;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.math.BigInteger;
import java.security.Guard;

public class ReduceFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View newView = inflater.inflate(R.layout.reduce_fragment, container, false);

        Button reduceButton = ((Button) newView.findViewById(R.id.reduce_button));
        reduceButton.setOnClickListener(view -> {
            final EditText numInput = ((EditText) newView.findViewById(R.id.numerator));
            final EditText denomInput = ((EditText) newView.findViewById(R.id.denominator));
            final String numInputString = numInput.getText().toString();
            final String denomInputString = denomInput.getText().toString();

            if(numInputString.length() < 1 || denomInputString.length() < 1) {
                Toast.makeText(view.getContext(), "Bitte gibt einen Zähler und einen Nenner an!", Toast.LENGTH_SHORT).show();
                return;
            }

            BigInteger numerator = new BigInteger(numInput.getText().toString());
            BigInteger denominator = new BigInteger(denomInput.getText().toString());
            if(denominator.compareTo(new BigInteger("0")) == 0) {
                Toast.makeText(view.getContext(), "Teilen durch 0 ist nicht möglich!.", Toast.LENGTH_SHORT).show();
                return;
            }

            BigInteger gcd = numerator.gcd(denominator);
            if(gcd.compareTo(new BigInteger("1")) == 0) {
                Toast.makeText(view.getContext(), "Der Bruch ist bereits gekürzt.", Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(view.getContext(), String.format("Kürze den Bruch mit %s.", gcd), Toast.LENGTH_SHORT).show();
            numerator = numerator.divide(gcd);
            denominator = denominator.divide(gcd);

            numInput.setText(numerator.toString());
            denomInput.setText(denominator.toString());
        });

        return newView;
    }
}
