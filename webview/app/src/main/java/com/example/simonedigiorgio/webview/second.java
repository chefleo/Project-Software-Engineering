package com.example.simonedigiorgio.webview;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.CredentialRequest;
import com.google.android.gms.auth.api.credentials.CredentialRequestResponse;
import com.google.android.gms.auth.api.credentials.Credentials;
import com.google.android.gms.auth.api.credentials.CredentialsClient;
import com.google.android.gms.auth.api.credentials.IdentityProviders;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.net.URL;

public class second extends AppCompatActivity {

	private WebView browser;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		Intent toy = getIntent();
		// Get the url string from MainActivity
		String web = toy.getStringExtra("Url");

		// Get host         if I use browser.getUrl() the app crash
		//String host = browser.getUrl();

		//Creation of webview
		browser = (WebView) findViewById(R.id.browser);
		browser.setWebViewClient(new WebViewClient());
		browser.getSettings().setJavaScriptEnabled(true);
		CredentialsClient mCredentialsClient;
		mCredentialsClient = Credentials.getClient(this);

		CredentialRequest mCredentialRequest = new CredentialRequest.Builder()
			.setPasswordLoginSupported(true)
			.setAccountTypes(IdentityProviders.GOOGLE, IdentityProviders.TWITTER)
			.build();

		mCredentialsClient.request(mCredentialRequest).addOnCompleteListener(
			new OnCompleteListener<CredentialRequestResponse>() {
				@Override
				public void onComplete(@NonNull Task<CredentialRequestResponse> task) {
					if (task.isSuccessful()) {
						// See "Handle successful credential requests"
						onCredentialRetrieved(task.getResult().getCredential());
						return;
					}
					// See "Handle unsuccessful and incomplete credential requests"
					// ...
				}
			});


		// Use the string "web" for load the page
		browser.loadUrl("" + web);
	}

	}

	private void onCredentialRetrieved(Credential credential) {
		String accountType = credential.getAccountType();
		if (accountType == null) {
			// Sign the user in with information from the Credential.
			signInWithPassword(credential.getId(), credential.getPassword());
		} else if (accountType.equals(IdentityProviders.GOOGLE)) {
			// The user has previously signed in with Google Sign-In. Silently
			// sign in the user with the same ID.
			// See https://developers.google.com/identity/sign-in/android/
			GoogleSignInOptions gso =
				new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
					.requestEmail()
					.build();

			GoogleSignInClient signInClient = GoogleSignIn.getClient(this, gso);
			Task<GoogleSignInAccount> task = signInClient.silentSignIn();
			// ...
		}
	}

	@Override
	public void onBackPressed() {
		if (browser.canGoBack()) {
			browser.goBack();
		} else {
			super.onBackPressed();
		}
	}
}
