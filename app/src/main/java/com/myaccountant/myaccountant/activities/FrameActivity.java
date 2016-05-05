package com.myaccountant.myaccountant.activities;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.myaccountant.myaccountant.R;
import com.myaccountant.myaccountant.adapters.BottomListViewAdapter;
import com.myaccountant.myaccountant.adapters.TopListViewAdapter;
import com.myaccountant.myaccountant.fragments.BioDataFragment;
import com.myaccountant.myaccountant.fragments.BusinessInfoFragment;
import com.myaccountant.myaccountant.fragments.CurrentAssetsFragment;
import com.myaccountant.myaccountant.fragments.CurrentLiabilityFragment;
import com.myaccountant.myaccountant.fragments.ExpensesFragment;
import com.myaccountant.myaccountant.fragments.ExpensesListFragment;
import com.myaccountant.myaccountant.fragments.FaqsFragment;
import com.myaccountant.myaccountant.fragments.FeedbackFragment;
import com.myaccountant.myaccountant.fragments.IncomeStatementFragment;
import com.myaccountant.myaccountant.fragments.NonCurrentAssetsFragment;
import com.myaccountant.myaccountant.fragments.NonCurrentLiabilityFragment;
import com.myaccountant.myaccountant.fragments.OtherIncomeFragment;
import com.myaccountant.myaccountant.fragments.OtherIncomeListFragment;
import com.myaccountant.myaccountant.fragments.ProgressReportFragment;
import com.myaccountant.myaccountant.fragments.PurchaseListFragment;
import com.myaccountant.myaccountant.fragments.PurchasesFragment;
import com.myaccountant.myaccountant.fragments.SCFFragment;
import com.myaccountant.myaccountant.fragments.SFPFragment;
import com.myaccountant.myaccountant.fragments.SalesFragment;
import com.myaccountant.myaccountant.fragments.SalesListFragment;

/**
 * Created by user on 11/8/2015.
 */
public class FrameActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ListView topListView,bottomListView;

    String category="";
    Fragment fragment=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame);
        //init views
        drawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        topListView=(ListView)findViewById(R.id.top_list_view);
        bottomListView=(ListView)findViewById(R.id.bottom_list_view);

        topListView.setAdapter(new TopListViewAdapter(this));
        bottomListView.setAdapter(new BottomListViewAdapter(this));

        category=getIntent().getStringExtra("category");

        switch(category){
            case "businessInfo":
                fragment=new BusinessInfoFragment();
                getSupportActionBar().setTitle("Business Info");
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.blue)));
                break;
            case "sales":
                fragment=new SalesListFragment();
                getSupportActionBar().setTitle("Sales");
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.fbutton_color_emerald)));
                break;
            case "purchases":
                fragment=new PurchaseListFragment();
                getSupportActionBar().setTitle("Purchases");
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.blue)));
                break;
            case "expenses":
                fragment=new ExpensesListFragment();
                getSupportActionBar().setTitle("Expenses");
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.fbutton_color_alizarin)));
                break;
            case "otherIncome":
                fragment=new OtherIncomeListFragment();
                getSupportActionBar().setTitle("Other Income");
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.fbutton_color_emerald)));
                break;
            case "progressReport":
                fragment=new ProgressReportFragment();
                getSupportActionBar().setTitle("Progress Report");
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.secondary_color)));
                break;
//            case "faqs":
//                fragment=new FaqsFragment();
//                getSupportActionBar().setTitle("FAQs");
//                 getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.secondary_color)));
//                break;
//            case "feedback":
//                fragment=new FeedbackFragment();
//                getSupportActionBar().setTitle("Feedback");
//                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.secondary_color)));
//                break;
            case "bioData":
                fragment=new BioDataFragment();
                getSupportActionBar().setTitle("Bio Data");
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.blue)));
                break;
            case "currentAssets":
                fragment=new CurrentAssetsFragment();
                getSupportActionBar().setTitle("Current Assets");
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.blue)));
                break;
            case "nonCurrentAssets":
                fragment=new NonCurrentAssetsFragment();
                getSupportActionBar().setTitle("Non Current Assets");
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.blue)));
                break;
            case "currentLiabilities":
                fragment=new CurrentLiabilityFragment();
                getSupportActionBar().setTitle("Current Liabilities");
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.blue)));
                break;
            case "nonCurrentLiabilities":
                fragment=new NonCurrentLiabilityFragment();
                getSupportActionBar().setTitle("Non Current Liabilities");
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.blue)));
                break;
            case "isReport":
                fragment=new IncomeStatementFragment();
                getSupportActionBar().setTitle("Progress Report");
                getSupportActionBar().setSubtitle("Income Statement");
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.secondary_color)));
                break;
            case "sfpReport":
                fragment=new SFPFragment();
                getSupportActionBar().setTitle("Progress Report");
                getSupportActionBar().setSubtitle("Statement of Financial Position");
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.secondary_color)));
                break;
            case "scfReport":
                fragment=new SCFFragment();
                getSupportActionBar().setTitle("Progress Report");
                getSupportActionBar().setSubtitle("Statement of Cash Flow");
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.secondary_color)));
                break;
            default:
                break;
        }

            FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content_frame,fragment);
            fragmentTransaction.commit();

        //drawer clicks listener
        bottomListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        //fragment=new FaqsFragment();

                        break;
                    case 1:
                        //fragment=new FeedbackFragment();
                        break;
                    case 2:
                        //do something
                        break;
                    default:
                        break;
                }
                setFragment(fragment);
                drawerLayout.closeDrawers();
            }
        });
        topListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        fragment=new BusinessInfoFragment();
                        getSupportActionBar().setTitle("Business Info");
                        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.blue)));
                        category="businessInfo";
                        break;
                    case 1:
                        fragment=new SalesListFragment();
                        getSupportActionBar().setTitle("Sales");
                        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.fbutton_color_emerald)));
                        category="sales";
                        break;
                    case 2:
                        fragment=new PurchaseListFragment();
                        getSupportActionBar().setTitle("Purchase");
                        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.blue)));
                        category="purchases";
                        break;
                    case 3:
                        fragment=new ExpensesListFragment();
                        getSupportActionBar().setTitle("Expenses");
                        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.fbutton_color_alizarin)));
                        category="expenses";
                        break;
                    case 4:
                        fragment=new OtherIncomeListFragment();
                        getSupportActionBar().setTitle("Other Income");
                        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.fbutton_color_emerald)));
                        category="otherIncome";
                        break;
                    case 5:
                        //fragment=new ProgressReportFragment();
                        break;
                    default:
                        break;
                }
                setFragment(fragment);
                invalidateOptionsMenu();
                drawerLayout.closeDrawers();
            }
        });
    }
    public void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame,fragment);
        fragmentTransaction.commit();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if(category.equalsIgnoreCase("sales") || category.equalsIgnoreCase("purchases") ||
                category.equalsIgnoreCase("expenses") || category.equalsIgnoreCase("otherIncome")){
            getMenuInflater().inflate(R.menu.menu_frame, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            if(category.equalsIgnoreCase("sales")){
                setFragment(new SalesFragment());
            }else if(category.equalsIgnoreCase("purchases")){
                setFragment(new PurchasesFragment());
            }else if(category.equalsIgnoreCase("expenses")){
                setFragment(new ExpensesFragment());
            }else if(category.equalsIgnoreCase("otherIncome")){
                setFragment(new OtherIncomeFragment());
            }else if(category.equalsIgnoreCase("businessInfo")){
                //do nothing
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
