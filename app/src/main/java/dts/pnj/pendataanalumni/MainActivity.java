package dts.pnj.pendataanalumni;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            checkLoginStatus();
        }

        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            int id = item.getItemId();

            if (id == R.id.navigation_home) {
                selectedFragment = new HomeFragment();
            } else if (id == R.id.navigation_news) {
                selectedFragment = new BeritaFragment();
            } else if (id == R.id.navigation_profile) {
                selectedFragment = new ProfileFragment();
            }

            if (selectedFragment != null) {
                loadFragment(selectedFragment);
                return true;
            }

            return false;
        });
    }

    public void setToolbarVisibility(boolean visible) {
        if (toolbar != null) {
            toolbar.setVisibility(visible ? View.VISIBLE : View.GONE);
        }
    }

    public void setToolbarTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    public void setBottomNavigationViewVisibility(boolean visible) {
        if (bottomNavigationView != null) {
            bottomNavigationView.setVisibility(visible ? View.VISIBLE : View.GONE);
        }
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        invalidateMenu();
    }

    private void checkLoginStatus() {
        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean("is_logged_in", false);

        if (isLoggedIn) {
            navigateToHomeFragment();
            setBottomNavigationViewVisibility(true);
            setToolbarVisibility(true);
        } else {
            loadFragment(new LoginFragment());
            setBottomNavigationViewVisibility(false);
            setToolbarVisibility(false);
        }
    }

    public void navigateToHomeFragment() {
        loadFragment(new HomeFragment());
        setBottomNavigationViewVisibility(true);
        setToolbarVisibility(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_app, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Fragment selectedFragment;
        if (item.getItemId() == R.id.menu_data_alumni) {
            selectedFragment = new DataAlumniFragment();
        } else if (item.getItemId() == R.id.menu_tambah_data) {
            selectedFragment = new TambahDataFragment();
        } else {
            return super.onOptionsItemSelected(item);
        }

        loadFragment(selectedFragment);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        Log.d("MainActivity", "Current Fragment: " + currentFragment);
        menu.setGroupVisible(R.id.menu_toolbar_group, !(currentFragment instanceof TambahDataFragment));
        return super.onPrepareOptionsMenu(menu);
    }
}