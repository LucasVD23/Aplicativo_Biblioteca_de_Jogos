package com.ufscar.dc.appbibliotecadejogos;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.ufscar.dc.appbibliotecadejogos.databinding.ActivityMainBinding;
import com.ufscar.dc.appbibliotecadejogos.fragments.CollectionFragment;
import com.ufscar.dc.appbibliotecadejogos.fragments.ExploreFragment;
import com.ufscar.dc.appbibliotecadejogos.fragments.HomeFragment;
import com.ufscar.dc.appbibliotecadejogos.utils.MyContextWrapper;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Tela inicial de quando o aplicativo é aberto
        replaceFragment(new HomeFragment(), getString(R.string.home_title));

        // Navegação do app pela barra inferior
        binding.bottomNavigationMenu.setOnItemSelectedListener(item -> {
            String title;
            int id = item.getItemId();

            if (id == R.id.home) {
                title = getString(R.string.home_title);
                replaceFragment(new HomeFragment(), title);
            }
            else if (id == R.id.explore) {
                title = getString(R.string.explore_title);
                replaceFragment(new ExploreFragment(), title);
            }
            else if (id == R.id.collection) {
                title = getString(R.string.collection_title);
                replaceFragment(new CollectionFragment(), title);
            }
            return true;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.action_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.selectLanguage) {
            final String[] languages = {getString(R.string.english), getString(R.string.portuguese)};
            AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
            mBuilder.setTitle(R.string.select_language);
            mBuilder.setSingleChoiceItems(languages, -1, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    switch (i) {
                        case 0:
                            editor.putString("Language", "en");
                            //Toast.makeText(MainActivity.this, "clicou em ingles", Toast.LENGTH_SHORT).show();
                            break;
                        case 1:
                            editor.putString("Language", "pt");
                            //Toast.makeText(MainActivity.this, "clicou em portugues", Toast.LENGTH_SHORT).show();
                            break;
                    }
                    editor.apply();
                    recreate();
                    dialogInterface.dismiss();
                }
            });

            AlertDialog mDialog = mBuilder.create();
            mDialog.show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(newBase);
        String langValue = sharedPreferences.getString("Language", "");

        super.attachBaseContext(MyContextWrapper.wrap(newBase,langValue));
    }


    private void replaceFragment(Fragment fragment, String title) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager
                .getActiveNetworkInfo();
        return activeNetworkInfo != null
                && activeNetworkInfo.isConnectedOrConnecting();
    }
}