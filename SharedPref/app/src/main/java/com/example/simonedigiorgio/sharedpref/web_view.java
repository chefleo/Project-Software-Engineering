package com.example.simonedigiorgio.sharedpref;

import android.content.Intent;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.HttpAuthHandler;

public class web_view extends AppCompatActivity
	implements NavigationView.OnNavigationItemSelectedListener {

	private WebView browser;
	private boolean checked = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_web_view);
	Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
	setSupportActionBar(toolbar);

	Intent ottenuto = getIntent();

	String url = ottenuto.getStringExtra("url");
	String username = ottenuto.getStringExtra("username");
	String password = ottenuto.getStringExtra("password");

	DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
	ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
		this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
	drawer.addDrawerListener(toggle);
	toggle.syncState();

	NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
	navigationView.setNavigationItemSelectedListener(this);

	browser = (WebView)findViewById(R.id.browser);
	WebSettings webSettings = browser.getSettings();
	webSettings.setJavaScriptEnabled(true);

	browser.setHttpAuthUsernamePassword(url,"", username, password);
	browser.setWebViewClient(new MyWebViewClient());
	browser.loadUrl(url);

}

	@Override
	public void onBackPressed() {
		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		if (drawer.isDrawerOpen(GravityCompat.START)) {
			drawer.closeDrawer(GravityCompat.START);
		} else {
			super.onBackPressed();
		}
	}

	@SuppressWarnings("StatementWithEmptyBody")
	@Override
	public boolean onNavigationItemSelected(MenuItem item) {
		// Handle navigation view item clicks here.
		int id = item.getItemId();

		if (id == R.id.Go_home) {
			final Intent ritorna = new Intent(web_view.this, MainActivity.class);
			// check in MainActivity become false
			ritorna.putExtra("checked", checked);
			startActivity(ritorna);
		}

		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawer.closeDrawer(GravityCompat.START);
		return true;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if((keyCode == KeyEvent.KEYCODE_BACK) && browser.canGoBack()){
			browser.canGoBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	public class MyWebViewClient extends WebViewClient {

		Intent gettedValue = getIntent();

		String url = gettedValue.getStringExtra("url");
		String username = gettedValue.getStringExtra("username");
		String password = gettedValue.getStringExtra("password");

	@Override
	public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {
		handler.proceed(username, password);
	}

		public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
			handler.proceed() ;
		}

}
}
