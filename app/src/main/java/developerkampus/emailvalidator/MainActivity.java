package developerkampus.emailvalidator;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText edtName, edtEmail;
    private Button btnSave, btnDelete;
    private static final String TAG = "MainActivity";
    private SharedPreferencesHelper mSharedPreferencesHelper;
    private EmailValidator mEmailValidator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtName = (EditText) findViewById(R.id.edt_name);
        edtEmail = (EditText) findViewById(R.id.edt_email);
        btnSave = (Button) findViewById(R.id.btn_save);
        btnDelete = (Button) findViewById(R.id.btn_delete);

        // Setup field validators.
        mEmailValidator = new EmailValidator();
        edtEmail.addTextChangedListener(mEmailValidator);

        // Instantiate a SharedPreferencesHelper.
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mSharedPreferencesHelper = new SharedPreferencesHelper(sharedPreferences);

        populateUi();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                // Get the text from the input fields.
                String name = edtName.getText().toString();
                String email = edtEmail.getText().toString();

                // Create a Setting model class to persist.
                SharedPreferenceEntry sharedPreferenceEntry =
                        new SharedPreferenceEntry(name, email);

                if (!mEmailValidator.isValid()) {
                    edtEmail.setError("Invalid email");
                    Log.w(TAG, "Not saving personal information: Invalid email");
                    return;
                }

                // Persist the personal information.
                boolean isSuccess = mSharedPreferencesHelper.savePersonalInfo(sharedPreferenceEntry);
                if (isSuccess) {
                    Toast.makeText(MainActivity.this, "Data disimpan", Toast.LENGTH_LONG).show();
                    Log.i(TAG, "Data disimpan");
                } else {
                    Log.e(TAG, "Gagal menyimpan data");
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                populateUi();
                Toast.makeText(MainActivity.this, "Data dihapus", Toast.LENGTH_LONG).show();
                Log.i(TAG, "Data dihapus");
            }
        });
    }

    private void populateUi() {
        SharedPreferenceEntry sharedPreferenceEntry;
        sharedPreferenceEntry = mSharedPreferencesHelper.getPersonalInfo();
        edtName.setText(sharedPreferenceEntry.getName());
        edtEmail.setText(sharedPreferenceEntry.getEmail());
    }
}
