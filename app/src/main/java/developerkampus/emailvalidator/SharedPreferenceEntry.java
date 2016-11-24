package developerkampus.emailvalidator;

/**
 * Created by Dodi Rivaldi on 17/11/2016.
 */

public class SharedPreferenceEntry {
    private final String mName;
    private final String mEmail;

    public SharedPreferenceEntry(String name, String email) {
        mName = name;
        mEmail = email;
    }

    public String getName() {
        return mName;
    }
    public String getEmail() {
        return mEmail;
    }
}
