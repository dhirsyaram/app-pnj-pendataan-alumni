package dts.pnj.pendataanalumni;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import java.util.Objects;

public class ToolBarActivity extends AppCompatActivity {

    protected Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Common setup for Toolbar
    }

    protected void setupToolbar(int toolbarId, String title, boolean showBackButton) {
        toolbar = findViewById(toolbarId);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            Objects.requireNonNull(getSupportActionBar()).setTitle(title);
            getSupportActionBar().setDisplayHomeAsUpEnabled(showBackButton);
            toolbar.setNavigationOnClickListener(v -> onBackPressed());
        }
    }

    protected void setToolbarVisibility(boolean visible) {
        if (toolbar != null) {
            toolbar.setVisibility(visible ? View.VISIBLE : View.GONE);
        }
    }
}
