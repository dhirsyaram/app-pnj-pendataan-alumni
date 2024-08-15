package dts.pnj.pendataanalumni.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import dts.pnj.pendataanalumni.R;
import dts.pnj.pendataanalumni.fragment.BeritaFragment;
import dts.pnj.pendataanalumni.fragment.HomeFragment;
import dts.pnj.pendataanalumni.fragment.LoginFragment;
import dts.pnj.pendataanalumni.fragment.ProfileFragment;

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

    public void setBottomNavigationViewVisibility(boolean isVisible) {
        if (bottomNavigationView != null) {
            bottomNavigationView.setVisibility(isVisible ? View.VISIBLE : View.GONE);
        }
    }

    public void setToolbarBackButton(boolean show) {
        if (toolbar != null) {
            if (show) {
                toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_24);
                toolbar.setNavigationOnClickListener(v -> onBackPressed());
            } else {
                toolbar.setNavigationIcon(null);
            }
        }
    }

    private void checkLoginStatus() {
        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean("is_logged_in", false);

        if (isLoggedIn) {
            loadFragment(new HomeFragment());
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
        Intent intent;
        if (item.getItemId() == R.id.menu_data_alumni) {
            intent = new Intent(this, DataAlumniActivity.class);
        } else if (item.getItemId() == R.id.menu_tambah_data) {
            intent = new Intent(this, TambahDataActivity.class);
        } else {
            return super.onOptionsItemSelected(item);
        }

        startActivity(intent);
        return true;
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }
}