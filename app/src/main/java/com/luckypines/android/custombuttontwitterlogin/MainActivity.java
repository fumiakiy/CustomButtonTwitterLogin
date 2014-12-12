package com.luckypines.android.custombuttontwitterlogin;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;


public class MainActivity extends ActionBarActivity {

    private Button twitterLoginButton;

    private TwitterAuthClient authClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (twitterLoginButton == null) {
            twitterLoginButton = (Button) findViewById(R.id.twitterLoginButton);
            twitterLoginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    twitterLogin();
                }
            });
        }
    }

    private void twitterLogin() {

        if (authClient != null)
            return;

        authClient = new TwitterAuthClient();
        authClient.authorize(this, new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                final TwitterSession session = result.data;
                if (session != null) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            doSomethingAwesomeWithTwitterToken(session);
                        }
                    });
                }
            }

            @Override
            public void failure(TwitterException exception) {
                authClient = null;
                // Handle exception
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (authClient == null)
            return;
        authClient.onActivityResult(requestCode, resultCode, data);
    }

    private void doSomethingAwesomeWithTwitterToken(TwitterSession session) {
        // Go crazy with the session
        Toast.makeText(this, session.getUserName(), Toast.LENGTH_LONG).show();
    }

}
