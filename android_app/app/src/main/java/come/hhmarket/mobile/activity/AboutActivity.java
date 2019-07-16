package come.hhmarket.mobile.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.hhmarket.mobile.R;

import come.hhmarket.mobile.fragment.AboutFragment;
import come.hhmarket.mobile.fragment.TermsFragment;

public class AboutActivity extends AppCompatActivity {

    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        if (savedInstanceState == null) {
            AboutFragment fragment = new AboutFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, fragment, AboutFragment.TAG).commit();
        }

        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(getResources().getString(R.string.menu_about));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    /** Shows the product detail fragment */
    public void showAboutFragment() {

        AboutFragment productFragment = new AboutFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack("about")
                .replace(R.id.fragment_container,
                        productFragment, null).commit();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {

            if (actionBar != null)
                actionBar.setTitle(getResources().getString(R.string.menu_about));
        }

            super.onBackPressed();

    }

    /** Shows the product detail fragment */
    public void showTermsFragment() {

        TermsFragment termsFragment = new TermsFragment();
        if (actionBar != null)
            actionBar.setTitle(getResources().getString(R.string.about_terms));
        getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack("about")
                    .replace(R.id.fragment_container,
                            termsFragment, null).commit();
        }



}
