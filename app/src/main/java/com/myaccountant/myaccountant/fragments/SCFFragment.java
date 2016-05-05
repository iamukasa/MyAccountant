package com.myaccountant.myaccountant.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.myaccountant.myaccountant.R;

/**
 * Created by user on 11/27/2015.
 */
public class SCFFragment extends Fragment {
    TextView profitBeforeTax,adjustmentFinanceCost,changesFinanceCost,depreciation,disposalLossGain,increaseDecreasePayables,increaseDecreaseReceivables,
             increaseDecreaseInventory,tax,cashFlowInvesting,loanFromBank,increaseDebentures,decreaseDebentures,
             cashFlowFinancing,totalCashFlow,cashBeginning,cashEnd,cashFlowOperating,purchaseNonCurrent,disposalNonCurrent;
    TextView nameOfBusiness,typeOfBusiness,time;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.scf_layout,container,false);

        profitBeforeTax=(TextView)rootView.findViewById(R.id.scf_profit_before_tax);
        adjustmentFinanceCost=(TextView)rootView.findViewById(R.id.scf_adjustment_financial_cost);
        changesFinanceCost=(TextView)rootView.findViewById(R.id.scf_changes_finance_cost);
        depreciation=(TextView)rootView.findViewById(R.id.scf_adjustment_depreciation);
        disposalLossGain=(TextView)rootView.findViewById(R.id.scf_adjustment_disposal_loss_gain);
        increaseDecreasePayables=(TextView)rootView.findViewById(R.id.scf_changes_increase_decrease_payables);
        increaseDecreaseReceivables=(TextView)rootView.findViewById(R.id.scf_changes_increase_decrease_receivables);
        increaseDecreaseInventory=(TextView)rootView.findViewById(R.id.scf_changes_increase_decrease_inventory);
        tax=(TextView)rootView.findViewById(R.id.scf_changes_tax);
        cashFlowInvesting=(TextView)rootView.findViewById(R.id.scf_investing_cash_flow_activities);
        loanFromBank=(TextView)rootView.findViewById(R.id.scf_financial_activities_loan_bank);
        increaseDebentures=(TextView)rootView.findViewById(R.id.scf_financial_activities_increase_debentures);
        decreaseDebentures=(TextView)rootView.findViewById(R.id.scf_financial_activities_decrease_debentures);
        cashFlowFinancing=(TextView)rootView.findViewById(R.id.scf_financial_activities_cash_flow);
        totalCashFlow=(TextView)rootView.findViewById(R.id.scf_total_cash_flow);
        cashBeginning=(TextView)rootView.findViewById(R.id.scf_financial_activities_cash_at_beginning);
        cashEnd=(TextView)rootView.findViewById(R.id.scf_financial_activities_cash_at_end);
        cashFlowOperating=(TextView)rootView.findViewById(R.id.scf_changes_cash_flow_operating_activities);
        purchaseNonCurrent=(TextView)rootView.findViewById(R.id.scf_investing_purchase_nca);
        disposalNonCurrent=(TextView)rootView.findViewById(R.id.scf_investing_disposal_nca);
        nameOfBusiness=(TextView)rootView.findViewById(R.id.scf_name_of_business);
        typeOfBusiness=(TextView)rootView.findViewById(R.id.scf_type_of_business);
        time=(TextView)rootView.findViewById(R.id.scf_time);

        return rootView;
    }
}
