package com.example.araragi.mycashflow.fragments;

import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.araragi.mycashflow.R;
import com.example.araragi.mycashflow.activities.MainActivity;
import com.example.araragi.mycashflow.dao.DaoSQLite;
import com.example.araragi.mycashflow.transactions.MoneyTransaction;

/**
 * Created by Araragi on 2017-02-27.
 */

public class ExpenseMainFragment extends Fragment{

    public static final String TAG = "ExpenseMainFragment";

    TextView dateExpense;
    EditText amount;
    Button saveBtn;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        setHasOptionsMenu(true);

        return inflater.inflate(R.layout.expence_fragment_main, null);
    }

    @Override
    public void onStart() {
        super.onStart();

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);


        dateExpense = (TextView) getActivity().findViewById(R.id.date_expence);
        dateExpense.setText((day + "-" + month + "-" + year));

        saveBtn = (Button)getActivity().findViewById(R.id.save_btn_expense_fr);
        saveBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                MoneyTransaction moneyTransaction = viewToMoneyTransaction(view);
                ((MainActivity)getActivity()).moneyTransactionToDao(moneyTransaction);
                amount.setText("");
                Toast.makeText(getActivity(), "Expense saved", Toast.LENGTH_SHORT).show();
            }

        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    public MoneyTransaction viewToMoneyTransaction(View v){

        amount = (EditText)getActivity().findViewById(R.id.incom_amount);
        double amountDouble = Double.parseDouble(amount.getText().toString());

        Log.i(TAG, "----" + amountDouble + "----");

        dateExpense = (TextView) getActivity().findViewById(R.id.date_expence);
        String date = dateExpense.getText().toString();

        Log.i(TAG, "----" + date + "----");

        return (new MoneyTransaction(amountDouble, MoneyTransaction.TYPE_EXPENSE, date));

    }




}

