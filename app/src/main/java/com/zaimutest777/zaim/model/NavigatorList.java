package com.zaimutest777.zaim.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Ilham Shihnazarow  on 9/7/2021
 */

public class NavigatorList {

    @SerializedName("creditcards")
    private ArrayList<MapNavigatorList> creditcards;
    @SerializedName("debitcards")
    private ArrayList<MapNavigatorList> debitcards;
    @SerializedName("rasrochka")
    private ArrayList<MapNavigatorList> rasrochka;
    @SerializedName("credits")
    private ArrayList<MapNavigatorList> credits;
    @SerializedName("loans")
    private ArrayList<MapNavigatorList> loans;

    @SerializedName("mastercard")
    private ArrayList<MapNavigatorList> mastercard;
    @SerializedName("mir")
    private ArrayList<MapNavigatorList> mir;
    @SerializedName("visa")
    private ArrayList<MapNavigatorList> visa;
    @SerializedName("qiwi")
    private ArrayList<MapNavigatorList> qiwi;
    @SerializedName("yandex")
    private ArrayList<MapNavigatorList> yandex;
    @SerializedName("cash")
    private ArrayList<MapNavigatorList> cash;


/** Get*/

    public ArrayList<MapNavigatorList> getCreditcards(){ return creditcards; }
    public ArrayList<MapNavigatorList> geDebitcards(){ return debitcards; }
    public ArrayList<MapNavigatorList> getRasrochka(){ return rasrochka; }
    public ArrayList<MapNavigatorList> getCredits(){ return credits; }
    public ArrayList<MapNavigatorList> getLoans(){ return loans; }

    public ArrayList<MapNavigatorList> getMastercard(){ return mastercard; }
    public ArrayList<MapNavigatorList> geMir(){ return mir; }
    public ArrayList<MapNavigatorList> getVisa(){ return visa; }
    public ArrayList<MapNavigatorList> getQiwi(){ return qiwi; }
    public ArrayList<MapNavigatorList> getYandex(){ return yandex; }
    public ArrayList<MapNavigatorList> getCash(){ return cash; }

/** Set*/

    public void setCreditcards(ArrayList<MapNavigatorList> creditcards){ this.creditcards = creditcards; }
    public void setDebitcards(ArrayList<MapNavigatorList> debitcards){ this.debitcards = debitcards; }
    public void setRasrochka(ArrayList<MapNavigatorList> rasrochka){ this.rasrochka = rasrochka; }
    public void setCredits(ArrayList<MapNavigatorList> credits){ this.credits = credits; }
    public void setLoans(ArrayList<MapNavigatorList> loans){ this.loans = loans; }

    public void setMastercard(ArrayList<MapNavigatorList> mastercard){ this.mastercard = mastercard; }
    public void setMir(ArrayList<MapNavigatorList> mir){ this.mir = mir; }
    public void setVisa(ArrayList<MapNavigatorList> visa){ this.visa = visa; }
    public void setQiwi(ArrayList<MapNavigatorList> qiwi){ this.qiwi = qiwi; }
    public void setYandex(ArrayList<MapNavigatorList> yandex){ this.yandex = yandex; }
    public void setCash(ArrayList<MapNavigatorList> cash){ this.cash = cash; }
}
