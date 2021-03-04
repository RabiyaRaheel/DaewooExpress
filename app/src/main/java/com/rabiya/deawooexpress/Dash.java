package com.rabiya.deawooexpress;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Dash extends AppCompatActivity implements View.OnClickListener {
    private CardView book,buy,pbuy,mytic,mybook,refund,complain,cargo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash);
        book=(CardView) findViewById(R.id.book);
        buy=(CardView) findViewById(R.id.buy);
        pbuy=(CardView) findViewById(R.id.pbuy);
        mytic=(CardView) findViewById(R.id.mytic);
        mybook=(CardView) findViewById(R.id.mybook);
        refund=(CardView) findViewById(R.id.refund);
        complain=(CardView) findViewById(R.id.complain);
        cargo=(CardView) findViewById(R.id.cargo);

        //add click listener to card
        book.setOnClickListener(this);
        buy.setOnClickListener(this);
        pbuy.setOnClickListener(this);
        mytic.setOnClickListener(this);
        mybook.setOnClickListener(this);
        refund.setOnClickListener(this);
        complain.setOnClickListener(this);
        cargo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.book:
                i = new Intent(this, BookSeats.class);
                startActivity(i);
                break;
            case R.id.buy:
                i = new Intent(this, BuyTickets.class);
                startActivity(i);
                break;
            case R.id.pbuy:
                i = new Intent(this, PreBooking.class);
                startActivity(i);
                break;
            case R.id.mytic:
                i = new Intent(Dash.this, MyTickets.class);
                startActivity(i);
                break;
            case R.id.mybook:
                i = new Intent(Dash.this, MyBookings.class);
                startActivity(i);
                break;
            case R.id.refund:
                i = new Intent(Dash.this, Refund.class);
                startActivity(i);
                break;
            case R.id.complain:
                i = new Intent(Dash.this, Complain.class);
                startActivity(i);
                break;
            case R.id.cargo:
                i = new Intent(Dash.this, Cargo.class);
                startActivity(i);
                break;


            default:
                break;


        }


    }
}