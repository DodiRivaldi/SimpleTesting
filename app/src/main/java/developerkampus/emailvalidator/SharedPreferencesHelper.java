package developerkampus.emailvalidator;

import android.content.SharedPreferences;

/**
 * Created by Dodi Rivaldi on 17/11/2016.
 */

public class SharedPreferencesHelper {

    // Keys for saving values in SharedPreferences.
    static final String KEY_NAME = "key_name";
    static final String KEY_EMAIL = "key_email";

    // The injected SharedPreferences implementation to use for persistence.
    private final SharedPreferences mSharedPreferences;

    public SharedPreferencesHelper(SharedPreferences sharedPreferences) {
        mSharedPreferences = sharedPreferences;
    }

    public boolean savePersonalInfo(SharedPreferenceEntry sharedPreferenceEntry){
        // Start a SharedPreferences transaction.
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(KEY_NAME, sharedPreferenceEntry.getName());
        editor.putString(KEY_EMAIL, sharedPreferenceEntry.getEmail());

        // Commit changes to SharedPreferences.
        return editor.commit();
    }

    public SharedPreferenceEntry getPersonalInfo() {
        // Get data from the SharedPreferences.
        String name = mSharedPreferences.getString(KEY_NAME, "");
        String email = mSharedPreferences.getString(KEY_EMAIL, "");

        // Create and fill a SharedPreferenceEntry model object.
        return new SharedPreferenceEntry(name, email);
    }
}
