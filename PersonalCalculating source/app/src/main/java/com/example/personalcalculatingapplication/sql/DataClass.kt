package com.example.personalcalculatingapplication.sql

class G{
    companion object{
        const val  DBNAME = "PERSONAL_CALC_DB"
        const val  PREFERENCE_NAME = "PERSONAL_CALC_PREFERENCE"
        const val  USERNAME= "username"
    }
}


class Income() {
    var id: Int = 0;
    var subject: String = "";
    var price: String = "";
    var date: String = "";
    var desc: String = "";
    var hasNotify: String = "";

    constructor( subject: String,  price: String,  date: String,  desc: String , hasNotify:String) : this() {
        this.subject = subject
        this.price = price
        this.date = date
        this.desc = desc
        this.hasNotify =hasNotify
    }
}

class Entire() {
    var id: Int = 0;
    var subject: String = "";
    var price: String = "";
    var date: String = "";
    var desc: String = "";
    var hasNotify: String = "";

    constructor( subject: String,  price: String,  date: String,  desc: String , hasNotify:String) : this() {
        this.subject = subject
        this.price = price
        this.date = date
        this.desc = desc
        this.hasNotify =hasNotify
    }
}

class Loan() {
    var id: Int = 0;
    var subject: String = "";
    var price: String = "";
    var date: String = "";
    var desc: String = "";
    var hasNotify: String = "";

    constructor( subject: String,  price: String,  date: String,  desc: String , hasNotify:String) : this() {
        this.subject = subject
        this.price = price
        this.date = date
        this.desc = desc
        this.hasNotify =hasNotify
    }
}

class Chek() {
    var id: Int = 0;
    var subject: String = "";
    var price: String = "";
    var desc: String = "";
    var hasNotify: String = "";
    var serial: String = "";
    var status: String = "";
    var createdDate: String = "";
    var endDate: String = "";

    constructor( subject: String,  price: String,   desc: String , hasNotify:String , serial:String , status: String , createdDate: String , endDate: String) : this() {
        this.subject = subject
        this.price = price
        this.desc = desc
        this.hasNotify =hasNotify
        this.serial =serial
        this.status =status
        this.createdDate =createdDate
        this.endDate =endDate
    }
}

class Payment() {
    var id: Int = 0;
    var subject: String = "";
    var price: String = "";
    var date: String = "";
    var desc: String = "";
    var hasNotify: String = "";
    var count: String = "";
    var eachCost: String = "";
    var paymentLoan: String = "";

    constructor( subject: String,  price: String,  date: String,  desc: String , hasNotify:String , count: String , eachCost: String, paymentLoan: String) : this() {
        this.subject = subject
        this.price = price
        this.date = date
        this.desc = desc
        this.hasNotify =hasNotify
        this.count = count
        this.eachCost = eachCost
        this.paymentLoan = paymentLoan
    }
}