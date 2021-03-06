package sk.hppa.frontoffice;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Random;

public class MainActivity extends Activity {

    @Override
    protected void onDestroy() {
        //mDbHelper.close();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final FrontOfficeDbHelper mDbHelper = new FrontOfficeDbHelper(MainActivity.this);
        final String loginTag = "DD";
        final int MY_PERMISSIONS_REQUEST_WRITE_STORAGE = 1;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_WRITE_STORAGE);
        }

        final Button myBtnBuySell = (Button) findViewById(R.id.btnBuySell);
        myBtnBuySell.setOnClickListener(new View.OnClickListener() {
            Intent intentBuySell = new Intent(MainActivity.this, BuySell.class);

            public void onClick(View w) {
                try {
                    startActivity(intentBuySell);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(MainActivity.this, (CharSequence) ex, Toast.LENGTH_SHORT).show();
                }
            }
        });


        final Button btnSettings = (Button) findViewById(R.id.btnConfigure);
        btnSettings.setOnClickListener(new View.OnClickListener() {

            public void onClick(View w) {
                mDbHelper.cleanDatabase();
            }
        });

        final Button btnTransaction = (Button) findViewById(R.id.btnTransactions);
        btnTransaction.setOnClickListener(new View.OnClickListener() {

            public void onClick(View w) {

                Intent intentTransactions = new Intent(MainActivity.this, DisplayView.class);
                intentTransactions.putExtra("type", "transactions");
                startActivity(intentTransactions);

            }
        });

        final Button btnInit = (Button) findViewById(R.id.btnInitDb);
        btnInit.setOnClickListener(new View.OnClickListener() {

            public void onClick(View w) {
                Intent i = new Intent(MainActivity.this, Configuration.class);
                startActivity(i);
                /**
            }
                try {

                    /**
                    mDbHelper.cleanDatabase();

                    final String[] CUSTOMERS = new String[] {
                            "GOOGLE", "YAHOO", "ZOZNAM", "SEZNAM", "MSN", "UBS", "REIFEISEN",
                            "JOHNNY", "JOHN", "CARL", "STEFFAN", "PAUL"
                    };
                    Arrays.sort(CUSTOMERS);
                    for (String CUST : CUSTOMERS) {
                        long t = System.currentTimeMillis();
                        mDbHelper.insertCustomer(CUST, loginTag + Long.toString(t), null);
                    }

                    final String[] GOODS = new String[] {
                            "Iron", "Copper", "Petrol", "Gas", "Coal", "Waste", "Oak", "Troat", "Cupprum",
                            "Aurum", "Gold", "Silver"
                    };
                    Arrays.sort(GOODS);
                    for (String good : GOODS) {
                        long t = System.currentTimeMillis();
                        Random r = new Random();
                        mDbHelper.insertGoods(good, loginTag + Long.toString(t), (double) r.nextInt(101), "Tons", null, null);
                    }

                } catch (Exception ex) {
                    Toast.makeText(MainActivity.this, (CharSequence) ex, Toast.LENGTH_SHORT).show();
                }
                     **/
            }
        });

        final Button btnAddGoods = (Button) findViewById(R.id.btnAddGoods);
        btnAddGoods.setOnClickListener(new View.OnClickListener() {

            public void onClick(View w) {
                Intent intentGoods = new Intent(MainActivity.this, AddGoods.class);
                startActivity(intentGoods);
            }
        });

        final Button btnAddCustomer = (Button) findViewById(R.id.btnAddCustomer);
        btnAddCustomer.setOnClickListener(new View.OnClickListener() {

            public void onClick(View w) {
                Intent i = new Intent(MainActivity.this, AddCustomer.class);
                startActivity(i);
            }
        });
    }
}
