package com.dez.hackerspace;

import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity implements ActionBar.TabListener {

	Tab algTab;
	Tab memberTab;
	Tab docTab;
	Tab projectTab;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Set up the action bar.
		final ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		
		algTab = actionBar.newTab()
					.setText("Algorithms")
					.setTabListener(this);
		
		memberTab = actionBar.newTab()
				.setText("Members")
				.setTabListener(this);
		
		docTab = actionBar.newTab()
				.setText("Docs")
				.setTabListener(this);
		
		projectTab = actionBar.newTab()
				.setText("Projects")
				.setTabListener(this);
		
		actionBar.addTab(algTab);
		actionBar.addTab(memberTab);
		actionBar.addTab(docTab);
		actionBar.addTab(projectTab);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.menuitem1) {
			JoinFragment joinFragment = new JoinFragment();
			FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
			ft.replace(R.id.layout_container, joinFragment);
			ft.addToBackStack("fragment join");
			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			ft.commit();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		
		if (tab.equals(algTab)){
			showAlgorithmFragment();
		}
		else if (tab.equals(docTab)){
			showDocumentationFragment();
		}
		else if (tab.equals(memberTab)){
			showMemberFragment();
		}
		else {
			showProjectFragment();
		}
		
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	public void showAlgorithmFragment()
	{
		AlgorithmFragment algorithmFragment = new AlgorithmFragment();
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		
		ft.replace(R.id.layout_container, algorithmFragment);
		ft.addToBackStack("fragment algorithm");
		ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		ft.commit();
	}
	
	public void showMemberFragment()
	{
		MemberFragment memberFragment = new MemberFragment();
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		
		ft.replace(R.id.layout_container, memberFragment);
		ft.addToBackStack("fragment member");
		ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		ft.commit();
	}
	
	public void showDocumentationFragment()
	{
		DocumentationFragment documentationFragment = new DocumentationFragment();
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		
		ft.replace(R.id.layout_container, documentationFragment);
		ft.addToBackStack("fragment documentation");
		ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		ft.commit();
	}
	
	public void showProjectFragment()
	{
		ProjectFragment projectFragment = new ProjectFragment();
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		
		ft.replace(R.id.layout_container, projectFragment);
		ft.addToBackStack("fragment project");
		ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		ft.commit();
	}
	
	public static class AlgorithmFragment extends Fragment{
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
		{
			View v = inflater.inflate(R.layout.fragment_main, container, false);
			WebView webBrowser = (WebView) v.findViewById(R.id.webView1);
			webBrowser.loadUrl("http://dry-sea-7022.herokuapp.com/algorithms");
			return v;
		}
		
	}
	
	public static class MemberFragment extends Fragment{
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
		{
			View v = inflater.inflate(R.layout.fragment_member, container, false);
			return v;
		}
		
	}
	
	public static class ProjectFragment extends Fragment{
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
		{
			View v = inflater.inflate(R.layout.fragment_project, container, false);
			return v;
		}
		
	}

	public static class DocumentationFragment extends Fragment implements OnClickListener{
		
		TextView cpp = null;
		TextView java = null;
		TextView python = null;
		WebView webBrowser = null;
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
		{
			View v = inflater.inflate(R.layout.fragment_documentation, container, false);
			webBrowser = (WebView) v.findViewById(R.id.webView2);
			cpp = (TextView)v.findViewById(R.id.cpp);
			java = (TextView)v.findViewById(R.id.java);
			python = (TextView)v.findViewById(R.id.python);
			webBrowser.loadUrl("http://docs.oracle.com/javase/8/docs/api/index.html");
			cpp.setOnClickListener(this);
			java.setOnClickListener(this);
			python.setOnClickListener(this);
			return v;
		}

		@Override
		public void onClick(View v) {
			if (v == cpp)
				webBrowser.loadUrl("http://www.cplusplus.com/reference/");
			else if (v == python)
				webBrowser.loadUrl("https://docs.python.org/3/reference/index.html");
			else
				webBrowser.loadUrl("http://docs.oracle.com/javase/8/docs/api/index.html");
			
		}
		
	}
	
	public static class JoinFragment extends Fragment{
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
		{
			View v = inflater.inflate(R.layout.fragment_join, container, false);
			return v;
		}
	}
	
}



