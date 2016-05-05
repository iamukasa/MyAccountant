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
import com.myaccountant.myaccountant.data.CurrentLiability;
import com.myaccountant.myaccountant.data.NonCurrentAssets;
import com.myaccountant.myaccountant.data.NonCurrentLiabilities;

import net.redwarp.library.database.DatabaseHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by user on 11/24/2015.
 */
public class SFPFragment extends Fragment {
    TextView landAndBuilding,vehicle,furnitureAndFittings,machinery,intangibleAssets,nonCurrentAssetsOther,cashAndBank,debtors,inventory,
             prepaidExpenses,currentAssetsOther,totalAssets,nonCurrentLiabilityLoan,nonCurrentLiabilityOther,currentLiabilityLoan,
             currentLiabilityPayable,currentLiabilityBankOverDraft,currentLiabilityShortBankLoan,currentLiabilityUnearnedRevenue,
             currentLiabilityAccruedExpenses,currentLiabilitiesOther,totalLiabilities;
    TextView nameOfBusiness,typeOfBusiness,time;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.sfp_layout,container,false);

        landAndBuilding=(TextView)rootView.findViewById(R.id.sfp_nca_land_building);
        vehicle=(TextView)rootView.findViewById(R.id.sfp_nca_vehicle);
        furnitureAndFittings=(TextView)rootView.findViewById(R.id.sfp_nca_furniture_fitting);
        machinery=(TextView)rootView.findViewById(R.id.sfp_nca_machinery);
        intangibleAssets=(TextView)rootView.findViewById(R.id.sfp_nca_intangible_assets);
        nonCurrentAssetsOther=(TextView)rootView.findViewById(R.id.sfp_nca_other);
        cashAndBank=(TextView)rootView.findViewById(R.id.sfp_ca_cash_bank);
        debtors=(TextView)rootView.findViewById(R.id.sfp_ca_debtors);
        inventory=(TextView)rootView.findViewById(R.id.sfp_ca_inventory);
        prepaidExpenses=(TextView)rootView.findViewById(R.id.sfp_ca_prepaid_expenses);
        nonCurrentAssetsOther=(TextView)rootView.findViewById(R.id.sfp_nca_other);
        currentAssetsOther=(TextView)rootView.findViewById(R.id.sfp_ca_other);
        totalAssets=(TextView)rootView.findViewById(R.id.sfp_total_assets);
        nonCurrentLiabilityLoan=(TextView)rootView.findViewById(R.id.sfp_ncl_loan);
        nonCurrentLiabilityOther=(TextView)rootView.findViewById(R.id.sfp_ncl_other);
        currentLiabilityLoan=(TextView)rootView.findViewById(R.id.sfp_cl_loan);
        currentLiabilityPayable=(TextView)rootView.findViewById(R.id.sfp_cl_payable);
        currentLiabilityBankOverDraft=(TextView)rootView.findViewById(R.id.sfp_cl_bank_overdraft);
        currentLiabilityShortBankLoan=(TextView)rootView.findViewById(R.id.sfp_cl_short_bank_loan);
        currentLiabilityUnearnedRevenue=(TextView)rootView.findViewById(R.id.sfp_cl_unearned_revenue);
        currentLiabilityAccruedExpenses=(TextView)rootView.findViewById(R.id.sfp_cl_accrued_expenses);
        currentLiabilitiesOther=(TextView)rootView.findViewById(R.id.sfp_cl_other);
        totalLiabilities=(TextView)rootView.findViewById(R.id.sfp_total_liabilities);
        nameOfBusiness=(TextView)rootView.findViewById(R.id.sfp_name_of_business);
        typeOfBusiness=(TextView)rootView.findViewById(R.id.sfp_type_of_business);
        time=(TextView)rootView.findViewById(R.id.sfp_time);

        time.setText("AS AT "+getDate()+" "+getTime());
        setNonCurrentAssets();
        setCurrentAssets();
        setNonCurrentLiabilities();
        setCurrentLiabilities();
        setTotalAssets();
        setTotalLiabilities();
        setNameOfBusiness();
        setTypeOfBusiness();

        return rootView;
    }

    public void setNameOfBusiness()
    {
        String name="";

    }
    public void setTypeOfBusiness()
    {
        String type="";
    }
    public void setNonCurrentAssets(){
        double landAndBuildingValue=0;
        double vehicleValue=0;
        double furnitureAndFittingsValue=0;
        double machineryValue=0;
        double intangibleAssetsValue=0;
        double otherValue=0;
        DatabaseHelper helper=new DatabaseHelper(getActivity());
        List<NonCurrentAssets> items=helper.getAll(NonCurrentAssets.class);
        ArrayList<NonCurrentAssets> allItems=(ArrayList)items;
        for(NonCurrentAssets nonCurrentAssets:allItems){
            if(nonCurrentAssets.getCategory().equalsIgnoreCase("Land and Building")){
                landAndBuildingValue+=nonCurrentAssets.getCost();
            }
            if(nonCurrentAssets.getCategory().equalsIgnoreCase("Motor vehicles")){
                vehicleValue+=nonCurrentAssets.getCost();
            }
            if(nonCurrentAssets.getCategory().equalsIgnoreCase("Furniture and Fittings")){
                furnitureAndFittingsValue+=nonCurrentAssets.getCost();
            }
            if(nonCurrentAssets.getCategory().equalsIgnoreCase("Machinery")){
                machineryValue+=nonCurrentAssets.getCost();
            }
            if(nonCurrentAssets.getCategory().equalsIgnoreCase("Intangible Assets")){
                intangibleAssetsValue+=nonCurrentAssets.getCost();
            }
            if(nonCurrentAssets.getCategory().equalsIgnoreCase("Other")){
                otherValue+=nonCurrentAssets.getCost();
            }
        }
        landAndBuilding.setText("" + landAndBuildingValue);
        vehicle.setText("" + vehicleValue);
        furnitureAndFittings.setText("" + furnitureAndFittingsValue);
        machinery.setText("" + machineryValue);
        intangibleAssets.setText("" + intangibleAssetsValue);
        nonCurrentAssetsOther.setText("" + otherValue);
    }

    public void setCurrentAssets()
    {

        double cashAndBankValue=0;
        double DebtorsValue=0;
        double InventoryValue=0;
        double prepaidExpensesValue=0;
        double othercurrentValue=0;
        DatabaseHelper helper=new DatabaseHelper(getActivity());
        List<CurrentAssets> items=helper.getAll(CurrentAssets.class);
        ArrayList<CurrentAssets> allItems=(ArrayList)items;
        for(CurrentAssets CurrentAssets:allItems){
            if(CurrentAssets.getCategory().equalsIgnoreCase("Cash And Bank")){
                cashAndBankValue+=CurrentAssets.getWorth();
            }
            if(CurrentAssets.getCategory().equalsIgnoreCase("Debtors")){
                DebtorsValue+=CurrentAssets.getWorth();
            }
            if(CurrentAssets.getCategory().equalsIgnoreCase("Inventory")){
                InventoryValue+=CurrentAssets.getWorth();
            }
            if(CurrentAssets.getCategory().equalsIgnoreCase("Prepaid Expenses")){
                prepaidExpensesValue+=CurrentAssets.getWorth();
            }
            if(CurrentAssets.getCategory().equalsIgnoreCase("Other")){
                othercurrentValue+=CurrentAssets.getWorth();
            }
        }

        cashAndBank.setText("" + cashAndBankValue);
        debtors.setText("" + DebtorsValue);
        inventory.setText("" + InventoryValue);
        prepaidExpenses.setText("" + prepaidExpensesValue);
        currentAssetsOther.setText("" + othercurrentValue);

    }


    public void setNonCurrentLiabilities()
    {

        double LoanValue=0;
        double OtherNonCurrentLiabilityValue=0;
        DatabaseHelper helper=new DatabaseHelper(getActivity());
        List<NonCurrentLiabilities> items=helper.getAll(NonCurrentLiabilities.class);
        ArrayList<NonCurrentLiabilities> allItems=(ArrayList)items;
        for(NonCurrentLiabilities NonCurrentLiabilities:allItems){
            if(NonCurrentLiabilities.getCategory().equalsIgnoreCase("Loan")){
                LoanValue+=NonCurrentLiabilities.getOutstandingAmount();
            }
            if(NonCurrentLiabilities.getCategory().equalsIgnoreCase("Other")){
                OtherNonCurrentLiabilityValue+=NonCurrentLiabilities.getOutstandingAmount();
            }

        }

        nonCurrentLiabilityLoan.setText("" + LoanValue);
        nonCurrentLiabilityOther.setText("" + OtherNonCurrentLiabilityValue);
    }


    public void setCurrentLiabilities()
    {

        double LoanClValue=0;
        double PayableValue=0;
        double BankOverdraftValue=0;
        double UnernedRevenueValue=0;
        double AccruedExpensesValue=0;
        double ShortBankLoanValue=0;
        double otherCLValue=0;

        DatabaseHelper helper=new DatabaseHelper(getActivity());
        List<CurrentLiability> items=helper.getAll(CurrentLiability.class);
        ArrayList<CurrentLiability> allItems=(ArrayList)items;
        for(CurrentLiability CurrentLiability:allItems){
            if(CurrentLiability.getCategory().equalsIgnoreCase("Loan")){
                LoanClValue+=CurrentLiability.getAmount();
            }
            if(CurrentLiability.getCategory().equalsIgnoreCase("Payables")){
                PayableValue+=CurrentLiability.getAmount();
            }
            if(CurrentLiability.getCategory().equalsIgnoreCase("Bank Overdraft")){
                BankOverdraftValue+=CurrentLiability.getAmount();
            }
            if(CurrentLiability.getCategory().equalsIgnoreCase("Unearned Revenue")){
                UnernedRevenueValue+=CurrentLiability.getAmount();
            }
            if (CurrentLiability.getCategory().equalsIgnoreCase("Short Bank Loan"))
            {
                ShortBankLoanValue+=CurrentLiability.getAmount();
            }
            if(CurrentLiability.getCategory().equalsIgnoreCase("Accrued Expenses"))
            {
                AccruedExpensesValue+=CurrentLiability.getAmount();
            }
            if(CurrentLiability.getCategory().equalsIgnoreCase("Other")){
                otherCLValue+=CurrentLiability.getAmount();
            }
        }

        currentLiabilitiesOther.setText("" + otherCLValue);
        currentLiabilityAccruedExpenses.setText(("" + AccruedExpensesValue));
        currentLiabilityUnearnedRevenue.setText("" + UnernedRevenueValue);
        currentLiabilityShortBankLoan.setText("" + ShortBankLoanValue);
        currentLiabilityBankOverDraft.setText("" + BankOverdraftValue);
        currentLiabilityPayable.setText("" + PayableValue);
        currentLiabilityLoan.setText("" + LoanClValue);


    }

    public void setTotalLiabilities()
    {
        double othrerValue = Double.parseDouble(currentAssetsOther.getText().toString());
        double AccruedExpenseValue =Double.parseDouble(currentLiabilityAccruedExpenses.getText().toString());
        double UnearnedRevenueValue = Double.parseDouble(currentLiabilityUnearnedRevenue.getText().toString());
        double ShortBankLoanValue = Double.parseDouble(currentLiabilityShortBankLoan.getText().toString());
        double BankOverdraftValue = Double.parseDouble(currentLiabilityBankOverDraft.getText().toString());
        double payableValue = Double.parseDouble(currentLiabilityPayable.getText().toString());
        double ClLoanValue = Double.parseDouble(currentLiabilityLoan.getText().toString());
        double NCLLoanValue = Double.parseDouble(nonCurrentLiabilityLoan.getText().toString());
        double NCLother = Double.parseDouble(nonCurrentLiabilityOther.getText().toString());

        double totalLiabilitiesValue = othrerValue+AccruedExpenseValue+UnearnedRevenueValue+ShortBankLoanValue+BankOverdraftValue+
                payableValue+ClLoanValue+NCLLoanValue+NCLother;
        totalLiabilities.setText(""+totalLiabilitiesValue);
    }
    public void setTotalAssets()
    {

        double landAndBuildingValue=Double.parseDouble(landAndBuilding.getText().toString());
        double vehicleValue = Double.parseDouble(vehicle.getText().toString());
        double furnitureAndFittingsValue = Double.parseDouble(furnitureAndFittings.getText().toString());
        double machineryValue = Double.parseDouble(machinery.getText().toString());
        double intangiableAssetsValue = Double.parseDouble(intangibleAssets.getText().toString());
        double otherAssets=Double.parseDouble(nonCurrentAssetsOther.getText().toString());
        double currentAssetOtherValue = Double.parseDouble(currentAssetsOther.getText().toString());
        double prepaidValue= Double.parseDouble(prepaidExpenses.getText().toString());
        double InventoryValue = Double.parseDouble(inventory.getText().toString());
        double cashAndBankValue = Double.parseDouble(cashAndBank.getText().toString());
        double debtorsValue = Double.parseDouble(debtors.getText().toString());

        double totalA=landAndBuildingValue+vehicleValue+furnitureAndFittingsValue+machineryValue+intangiableAssetsValue+otherAssets+currentAssetOtherValue
                +prepaidValue+InventoryValue+cashAndBankValue+debtorsValue;
        totalAssets.setText("" + totalA);

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
