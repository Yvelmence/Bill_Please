package sg.edu.rp.c346.id22021958.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    TextView TotalBill;
    TextView SplitBill;
    EditText amountInput;
    EditText paxInput;
    EditText discountInput;
    ToggleButton toggleSVS;
    ToggleButton toggleGST;
    RadioGroup groupPayment;
    RadioButton radioCash;
    RadioButton radioPayNow;
    Button buttonSplit;
    Button buttonReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TotalBill = findViewById(R.id.textViewTotalBill);
        SplitBill = findViewById(R.id.textViewSplitBill);
        amountInput = findViewById(R.id.editNumber);
        paxInput = findViewById(R.id.editPax);
        discountInput = findViewById(R.id.editDiscount);
        toggleSVS = findViewById(R.id.toggleButtonSVS);
        toggleGST = findViewById(R.id.toggleButtonGST);
        groupPayment = findViewById(R.id.radioGroupPayment);
        radioCash = findViewById(R.id.radioButtonCash);
        radioPayNow = findViewById(R.id.radioButtonPayNow);
        buttonSplit = findViewById(R.id.buttonSplit);
        buttonReset = findViewById(R.id.buttonReset);


        buttonSplit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double amount=0.0;
                if(amountInput.getText().toString().trim().length()!=0 && paxInput.getText().toString().trim().length()!=0){
                    if(toggleSVS.isChecked() && toggleGST.isChecked()){
                        amount=Double.parseDouble(amountInput.getText().toString())*1.18;
                    }
                    else if(toggleSVS.isChecked() && !toggleGST.isChecked()){
                        amount=Double.parseDouble(amountInput.getText().toString())*1.1;
                    }
                    else if(!toggleSVS.isChecked() && toggleGST.isChecked()){
                        amount=Double.parseDouble(amountInput.getText().toString())*1.08;
                    }
                    else{
                        amount=Double.parseDouble(amountInput.getText().toString());
                    }
                }
                if(discountInput.getText().toString().trim().length()!=0){
                    amount=amount*(1-(Double.parseDouble(discountInput.getText().toString())/100));
                }

                int checkedRadioId=groupPayment.getCheckedRadioButtonId();
                String paymentMethod;
                if(checkedRadioId==R.id.radioButtonCash){
                    paymentMethod="via Cash";
                }
                else{
                    paymentMethod="via PayNow to 912345678";
                }
                TotalBill.setText("Total Bill: $" + String.format("%.2f", amount));
                int noPax=Integer.parseInt(paxInput.getText().toString());
                if(noPax!=1){
                    SplitBill.setText("Each Pays: $" + String.format("%.2f %s", amount/noPax,paymentMethod));
                }
                else{
                    SplitBill.setText("Each Pays: $" + String.format("%.2f %s", amount, paymentMethod));
                }
            }
        });
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amountInput.setText("");
                paxInput.setText("");
                discountInput.setText("");
                toggleSVS.setChecked(false);
                toggleGST.setChecked(false);
                TotalBill.setText("");
                SplitBill.setText("");
                radioCash.setChecked(false);
                radioPayNow.setChecked(false);
            }
        });
    }
}