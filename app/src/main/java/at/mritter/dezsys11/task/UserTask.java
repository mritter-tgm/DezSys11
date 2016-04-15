package at.mritter.dezsys11.task;

import android.os.AsyncTask;
import android.widget.Toast;

import at.mritter.dezsys11.activity.UserActivity;
import at.mritter.dezsys11.model.Response;
import at.mritter.dezsys11.model.User;


/**
 * Represents an asynchronous login/registration task used to authenticate
 * the user.
 */
public class UserTask extends AsyncTask<Void, Void, Boolean> {

    private UserActivity userActivity;
    private User user;
    private Response response;

    public UserTask(UserActivity userActivity) {
        this.user = new User(userActivity.getEmail(), userActivity.getPassword());
        this.userActivity = userActivity;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        response = userActivity.callRestAPI(user);
        return response.getStatus() == 200 || response.getStatus() == 201;
    }

    @Override
    protected void onPostExecute(final Boolean success) {

        if (success) {
            userActivity.success();
        } else {
            userActivity.showProgress(false);
            Toast.makeText(userActivity.getApplicationContext(), response.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onCancelled() {
        userActivity.showProgress(false);
    }
}
