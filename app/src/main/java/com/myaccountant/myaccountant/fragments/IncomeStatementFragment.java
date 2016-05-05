package com.myaccountant.myaccountant.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.myaccountant.myaccountant.R;
import com.myaccountant.myaccountant.data.BusinessInfo;
import com.myaccountant.myaccountant.data.CurrentAssets;
import com.myaccountant.myaccountant.data.Expenses;
import com.myaccountant.myaccountant.data.NonCurrentAssets;
import com.myaccountant.myaccountant.data.NonCurrentLiabilities;
import com.myaccountant.myaccountant.data.OpeningInventory;
import com.myaccountant.myaccountant.data.OtherIncome;
import com.myaccountant.myaccountant.data.Purchases;
import com.myaccountant.myaccountant.data.Sales;

import net.redwarp.library.database.DatabaseHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by user on 11/24/2015.
 */
public class IncomeStatementFragment extends Fragment {
    TextView totalSales, openingStock, purchases, closingStock, costOfSales, grossProfit, otherIncome, depreciation, expenses,
            profitBeforeInterestAndTax, interest, profitBeforeTax, tax, profitAfterTax;
    TextView nameOfBusiness, typeOfBusiness, time, downloadPdf;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.income_statement_layout, container, false);
        totalSales = (TextView) rootView.findViewById(R.id.income_statement_total_sales);
        openingStock = (TextView) rootView.findViewById(R.id.income_statement_open_stock);
        closingStock = (TextView) rootView.findViewById(R.id.income_statement_closing_stock);
        purchases = (TextView) rootView.findViewById(R.id.income_statement_purchases);
        costOfSales = (TextView) rootView.findViewById(R.id.income_statement_cost_of_sales);
        grossProfit = (TextView) rootView.findViewById(R.id.income_statement_gross_profit);
        otherIncome = (TextView) rootView.findViewById(R.id.income_statement_other_income);
        depreciation = (TextView) rootView.findViewById(R.id.income_statement_depreciation);
        expenses = (TextView) rootView.findViewById(R.id.income_statement_expenses);
        profitBeforeInterestAndTax = (TextView) rootView.findViewById(R.id.income_statement_profit_before_interest_and_tax);
        interest = (TextView) rootView.findViewById(R.id.income_statement_interest);
        profitBeforeTax = (TextView) rootView.findViewById(R.id.income_statement_profit_before_tax);
        profitAfterTax = (TextView) rootView.findViewById(R.id.income_statement_profit_after_tax);
        tax = (TextView) rootView.findViewById(R.id.income_statement_tax);
        typeOfBusiness = (TextView) rootView.findViewById(R.id.is_type_of_business);
        nameOfBusiness = (TextView) rootView.findViewById(R.id.is_name_of_business);
        time = (TextView) rootView.findViewById(R.id.is_time);
        downloadPdf = (TextView) rootView.findViewById(R.id.income_statement_download_pdf);

        time.setText("AS AT "+getDate()+" "+getTime());
        setTotalSales();
        setOpeningStock();
        setPurchases();
        setClosingStock();
        setCostOfSales();
        setGrossProfit();
        setOtherIncome();
        setDepreciation();
        setExpenses();
        setProfitBeforeInterestAndTax();
        setInterest();
        setProfitBeforeTax();
        setTax();
        setProfitAfterTax();

        return rootView;
    }

    public void setTotalSales() {
        double total = 0;
        DatabaseHelper helper = new DatabaseHelper(getActivity());
        List<Sales> items = helper.getAll(Sales.class);
        ArrayList<Sales> allSales = (ArrayList) items;
        for (Sales sales : allSales) {
            total += sales.getAmount();
        }
        totalSales.setText("" + total);
    }

    public void setOpeningStock() {
        double total = 0;
        DatabaseHelper helper = new DatabaseHelper(getActivity());
        List<OpeningInventory> items = helper.getAll(OpeningInventory.class);
        ArrayList<OpeningInventory> allCurrentAssets = (ArrayList) items;
        for (OpeningInventory currentAssets : allCurrentAssets) {
            if (currentAssets.getCategory().equalsIgnoreCase("Inventory")) {
                total = currentAssets.getWorth();
                break;
            } else {
                continue;
            }
        }
        openingStock.setText("" + total);
    }

    public void setPurchases() {
        double total = 0;
        DatabaseHelper helper = new DatabaseHelper(getActivity());
        List<Purchases> items = helper.getAll(Purchases.class);
        ArrayList<Purchases> allPurchases = (ArrayList) items;
        for (Purchases purchases : allPurchases) {
            total += purchases.getAmount();
        }
        purchases.setText("" + total);
    }

    public void setClosingStock() {
        double total = 0;
        DatabaseHelper helper = new DatabaseHelper(getActivity());
        List<CurrentAssets> items = helper.getAll(CurrentAssets.class);
        ArrayList<CurrentAssets> allCurrentAssets = (ArrayList) items;
        for (CurrentAssets currentAssets : allCurrentAssets) {
            if (currentAssets.getCategory().equalsIgnoreCase("Inventory")) {
                total += currentAssets.getWorth();
            }
        }
        closingStock.setText("" + total);
    }

    public void setCostOfSales() {
        double openingStockValue = Double.parseDouble(openingStock.getText().toString());
        double purchasesValue = Double.parseDouble(purchases.getText().toString());
        double closingStockValue = Double.parseDouble(closingStock.getText().toString());

        double costOfSalesValue = (openingStockValue + purchasesValue) - closingStockValue;

        costOfSales.setText("" + costOfSalesValue);
    }

    public void setGrossProfit() {
        double totalSalesValue = Double.parseDouble(totalSales.getText().toString());
        double costOfSalesValue = Double.parseDouble(costOfSales.getText().toString());

        double grossProfitValue = totalSalesValue - costOfSalesValue;

        grossProfit.setText("" + grossProfitValue);
    }

    public void setOtherIncome() {
        double total = 0;
        DatabaseHelper helper = new DatabaseHelper(getActivity());
        List<OtherIncome> items = helper.getAll(OtherIncome.class);
        ArrayList<OtherIncome> allOtherIncome = (ArrayList) items;
        for (OtherIncome otherIncome : allOtherIncome) {
            total += otherIncome.getAmount();
        }
        otherIncome.setText("" + total);
    }

    public void setExpenses() {
        double total = 0;
        DatabaseHelper helper = new DatabaseHelper(getActivity());
        List<Expenses> items = helper.getAll(Expenses.class);
        ArrayList<Expenses> allExpenses = (ArrayList) items;
        for (Expenses expenses : allExpenses) {
            total += expenses.getAmount();
        }
        expenses.setText("" + total);
    }

    public void setDepreciation() {
        double totalReducingBalance = 0;
        double totalStraightLine = 0;
        DatabaseHelper helper = new DatabaseHelper(getActivity());
        List<NonCurrentAssets> items = helper.getAll(NonCurrentAssets.class);
        ArrayList<NonCurrentAssets> allCurrentAssets = (ArrayList) items;
        for (NonCurrentAssets nonCurrentAssets : allCurrentAssets) {
            if (nonCurrentAssets.getDepreciationRateMethod().equalsIgnoreCase("Reducing Balance")) {
                totalReducingBalance += (nonCurrentAssets.getNetBookValue() * (nonCurrentAssets.getDepreciationRate() / 100))/12;
            }
            if (nonCurrentAssets.getDepreciationRateMethod().equalsIgnoreCase("Straight Line")) {
                totalStraightLine += (nonCurrentAssets.getCost() * (nonCurrentAssets.getDepreciationRate() / 100))/12;
            }
        }
        //depreciation.setText("" + (totalReducingBalance + totalStraightLine));
        depreciation.setText(""+0);
    }

    public void setProfitBeforeInterestAndTax() {
        double grossProfitValue = Double.parseDouble(grossProfit.getText().toString());
        double otherIncomeValue = Double.parseDouble(otherIncome.getText().toString());
        double expensesValue = Double.parseDouble(expenses.getText().toString());
        double depreciationValue = Double.parseDouble(depreciation.getText().toString());

        profitBeforeInterestAndTax.setText("" + ((grossProfitValue + otherIncomeValue) - (expensesValue + depreciationValue)));
    }

    public void setInterest() {
        double total = 0;
        DatabaseHelper helper = new DatabaseHelper(getActivity());
        List<NonCurrentLiabilities> items = helper.getAll(NonCurrentLiabilities.class);
        ArrayList<NonCurrentLiabilities> allNonCurrentLiabilities = (ArrayList) items;
        for (NonCurrentLiabilities nonCurrentLiabilities : allNonCurrentLiabilities) {
            total += nonCurrentLiabilities.getAmount() * (nonCurrentLiabilities.getInterestRate() / 100);
        }
        interest.setText("" + total);
    }

    public void setProfitBeforeTax() {
        double profitBeforeInterestAndTaxValue = Double.parseDouble(profitBeforeInterestAndTax.getText().toString());
        double interestValue = Double.parseDouble(interest.getText().toString());

        profitBeforeTax.setText("" + (profitBeforeInterestAndTaxValue - interestValue));
    }

    public void setTax() {
        double taxValue = 0;
        double profitBeforeTaxValue = Double.parseDouble(profitBeforeTax.getText().toString());
        DatabaseHelper helper = new DatabaseHelper(getActivity());
        List<BusinessInfo> items = helper.getAll(BusinessInfo.class);
        ArrayList<BusinessInfo> allItems = (ArrayList) items;
        for (BusinessInfo businessInfo : allItems) {
            nameOfBusiness.setText(businessInfo.getName());
            typeOfBusiness.setText(businessInfo.getStructure());
            if (businessInfo.getStructure().equalsIgnoreCase("company")) {
                taxValue += profitBeforeTaxValue * (30 / 100);
            } else if (businessInfo.getStructure().equalsIgnoreCase("sole proprietorship")) {
                if (profitBeforeTaxValue <= Double.parseDouble(""+121968)) {
                    taxValue += profitBeforeTaxValue * (10 / 100);
                }
                if(profitBeforeTaxValue>Double.parseDouble(""+121968) && profitBeforeTaxValue<Double.parseDouble(""+236880)){
                    taxValue += profitBeforeTaxValue*(15/100);
                }
                if(profitBeforeTaxValue>Double.parseDouble(""+236880) && profitBeforeTaxValue<Double.parseDouble(""+351792)){
                    taxValue += profitBeforeTaxValue*(20/100);
                }
                if(profitBeforeTaxValue>Double.parseDouble(""+351792) && profitBeforeTaxValue<Double.parseDouble(""+466704)){
                    taxValue += profitBeforeTaxValue*(25/100);
                }
                if(profitBeforeTaxValue>Double.parseDouble(""+466704)){
                    taxValue += profitBeforeTaxValue*(30/100);
                }
            }else if(businessInfo.getStructure().equalsIgnoreCase("partnership")){
                taxValue += 0;
            }
        }
        tax.setText(""+taxValue);
    }
    public void setProfitAfterTax(){
        double profitBeforeTaxValue=Double.parseDouble(profitBeforeTax.getText().toString());
        double taxValue=Double.parseDouble(tax.getText().toString());

        profitAfterTax.setText(""+(profitBeforeTaxValue-taxValue));
    }
    public String getDate(){
        String date="";
        Calendar calendar= Calendar.getInstance();
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        date=dateFormat.format(calendar.getTime());

        return date;
    }
    public String getTime(){
        String time="";
        Calendar calendar= Calendar.getInstance();
        DateFormat dateFormat=new SimpleDateFormat("h:mm:a");
        time=dateFormat.format(calendar.getTime());

        return time;
    }
}
