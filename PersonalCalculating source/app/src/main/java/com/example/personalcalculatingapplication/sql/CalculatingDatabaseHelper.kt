package com.example.personalcalculatingapplication.sql


import android.content.ClipData.Item
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log


class CalculatingDatabaseHelper(val context: Context, val dbName: String, val version: Int) :
    SQLiteOpenHelper(context, dbName, null, version) {

    // all cheks
    private val TABLE_CHEK= "tbl_chek"
    private val KEY_CHEK_ID = "chek_id"
    private val KEY_CHEK_SUBJECT = "chek_subject"
    private val KEY_CHEK_HAS_NOTIFY = "chek_has_notify"
    private val KEY_CHEK_PRICE = "chek_price"
    private val KEY_CHEK_DESC= "chek_desc"
    private val KEY_CHEK_SERIAL= "chek_serial"
    private val KEY_CHEK_STATUS= "chek_status"
    private val KEY_CHEK_CREATED_DATE= "chek_created_date"
    private val KEY_CHEK_END_DATE= "chek_end_date"

    // all income
    private val TABLE_INCOME= "tbl_income"
    private val KEY_INCOME_ID = "income_id"
    private val KEY_INCOME_SUBJECT = "income_subject"
    private val KEY_INCOME_HAS_NOTIFY = "income_has_notify"
    private val KEY_INCOME_PRICE = "income_price"
    private val KEY_INCOME_DATE = "income_date"
    private val KEY_INCOME_DESC= "income_desc"

    // all entire
    private val TABLE_ENTIRE= "tbl_entire"
    private val KEY_ENTIRE_ID = "entire_id"
    private val KEY_ENTIRE_SUBJECT = "entire_subject"
    private val KEY_ENTIRE_HAS_NOTIFY = "entire_has_notify"
    private val KEY_ENTIRE_PRICE = "entire_price"
    private val KEY_ENTIRE_DATE = "entire_date"
    private val KEY_ENTIRE_DESC= "entire_desc"

    // all loan
    private val TABLE_LOAN= "tbl_loan"
    private val KEY_LOAN_ID = "loan_id"
    private val KEY_LOAN_SUBJECT = "loan_subject"
    private val KEY_LOAN_HAS_NOTIFY = "loan_has_notify"
    private val KEY_LOAN_PRICE = "loan_price"
    private val KEY_LOAN_DATE = "loan_date"
    private val KEY_LOAN_DESC= "loan_desc"

    // all payment
    private val TABLE_PAYMENT= "tbl_payment"
    private val KEY_PAYMENT_ID = "payment_id"
    private val KEY_PAYMENT_SUBJECT = "payment_subject"
    private val KEY_PAYMENT_HAS_NOTIFY = "payment_has_notify"
    private val KEY_PAYMENT_PRICE = "payment_price"
    private val KEY_PAYMENT_DATE = "payment_date"
    private val KEY_PAYMENT_DESC= "payment_desc"
    private val KEY_PAYMENT_COUNT= "payment_count"
    private val KEY_PAYMENT_EACH_COST= "payment_each_cost"
    private val KEY_PAYMENT_LOAN= "payment_loan"

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHEK);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_INCOME);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_ENTIRE);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOAN);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_PAYMENT);
            onCreate(db);
        }
    }

    override fun onCreate(db: SQLiteDatabase) {

        val CREATE_CHECK_TABLE = "CREATE TABLE " + TABLE_CHEK +
                "(" +
                KEY_CHEK_ID + " INTEGER PRIMARY KEY," +
                KEY_CHEK_SUBJECT + " TEXT," +
                KEY_CHEK_PRICE + " TEXT," +
                KEY_CHEK_HAS_NOTIFY + " TEXT," +
                KEY_CHEK_SERIAL + " TEXT," +
                KEY_CHEK_STATUS + " TEXT," +
                KEY_CHEK_CREATED_DATE + " TEXT," +
                KEY_CHEK_END_DATE + " TEXT," +
                KEY_CHEK_DESC + " TEXT" +
                ")"

        val CREATE_LOAN_TABLE = "CREATE TABLE " + TABLE_LOAN +
                "(" +
                KEY_LOAN_ID + " INTEGER PRIMARY KEY," +
                KEY_LOAN_SUBJECT + " TEXT," +
                KEY_LOAN_PRICE + " TEXT," +
                KEY_LOAN_DATE + " TEXT," +
                KEY_LOAN_HAS_NOTIFY + " TEXT," +
                KEY_LOAN_DESC + " TEXT" +
                ")"

        val CREATE_INCOME_TABLE = "CREATE TABLE " + TABLE_INCOME +
                "(" +
                KEY_INCOME_ID + " INTEGER PRIMARY KEY," +
                KEY_INCOME_SUBJECT + " TEXT," +
                KEY_INCOME_PRICE + " TEXT," +
                KEY_INCOME_DATE + " TEXT," +
                KEY_INCOME_HAS_NOTIFY + " TEXT," +
                KEY_INCOME_DESC + " TEXT" +
                ")"

        val CREATE_ENTIRE_TABLE = "CREATE TABLE " + TABLE_ENTIRE +
                "(" +
                KEY_ENTIRE_ID + " INTEGER PRIMARY KEY," +
                KEY_ENTIRE_SUBJECT + " TEXT," +
                KEY_ENTIRE_PRICE + " TEXT," +
                KEY_ENTIRE_DATE + " TEXT," +
                KEY_ENTIRE_HAS_NOTIFY + " TEXT," +
                KEY_ENTIRE_DESC + " TEXT" +
                ")"

        val CREATE_PAYMENT_TABLE = "CREATE TABLE " + TABLE_PAYMENT +
                "(" +
                KEY_PAYMENT_ID + " INTEGER PRIMARY KEY," +
                KEY_PAYMENT_SUBJECT + " TEXT," +
                KEY_PAYMENT_PRICE + " TEXT," +
                KEY_PAYMENT_DATE + " TEXT," +
                KEY_PAYMENT_HAS_NOTIFY + " TEXT," +
                KEY_PAYMENT_LOAN + " TEXT," +
                KEY_PAYMENT_EACH_COST + " TEXT," +
                KEY_PAYMENT_COUNT + " TEXT," +
                KEY_PAYMENT_DESC + " TEXT" +
                ")"

        db.execSQL(CREATE_CHECK_TABLE)
        db.execSQL(CREATE_LOAN_TABLE)
        db.execSQL(CREATE_INCOME_TABLE)
        db.execSQL(CREATE_ENTIRE_TABLE)
        db.execSQL(CREATE_PAYMENT_TABLE)
    }

    public fun deleteTable(){
        val db = writableDatabase
        db.execSQL("delete from "+ TABLE_CHEK);
        db.execSQL("delete from "+ TABLE_INCOME);
        db.execSQL("delete from "+ TABLE_ENTIRE);
        db.execSQL("delete from "+ TABLE_LOAN);
        db.execSQL("delete from "+ TABLE_PAYMENT);
    }


    fun addChek(item: Chek) {
        val db = writableDatabase

        db.beginTransaction()
        try {
            val values = ContentValues()
            values.put(KEY_CHEK_SUBJECT, item.subject)
            values.put(KEY_CHEK_PRICE, item.price)
            values.put(KEY_CHEK_HAS_NOTIFY, item.hasNotify)
            values.put(KEY_CHEK_DESC, item.desc)
            values.put(KEY_CHEK_SERIAL, item.serial)
            values.put(KEY_CHEK_STATUS, item.status)
            values.put(KEY_CHEK_CREATED_DATE, item.createdDate)
            values.put(KEY_CHEK_END_DATE, item.endDate)

            db.insertOrThrow(TABLE_CHEK, null, values)

            db.setTransactionSuccessful()
        } catch (e: Exception) {
            Log.d("BookApp", "Error while trying to add post to database")
        } finally {
            db.endTransaction()
        }
    }
    fun addIncome(item: Income) {
        val db = writableDatabase

        db.beginTransaction()
        try {
            val values = ContentValues()
            values.put(KEY_INCOME_SUBJECT, item.subject)
            values.put(KEY_INCOME_PRICE, item.price)
            values.put(KEY_INCOME_HAS_NOTIFY, item.hasNotify)
            values.put(KEY_INCOME_DATE, item.date)
            values.put(KEY_INCOME_DESC, item.desc)

            db.insertOrThrow(TABLE_INCOME, null, values)

            db.setTransactionSuccessful()
        } catch (e: Exception) {
            Log.d("BookApp", "Error while trying to add post to database")
        } finally {
            db.endTransaction()
        }
    }
    fun addEntire(item: Entire) {
        val db = writableDatabase

        db.beginTransaction()
        try {
            val values = ContentValues()
            values.put(KEY_ENTIRE_SUBJECT, item.subject)
            values.put(KEY_ENTIRE_PRICE, item.price)
            values.put(KEY_ENTIRE_HAS_NOTIFY, item.hasNotify)
            values.put(KEY_ENTIRE_DATE, item.date)
            values.put(KEY_ENTIRE_DESC, item.desc)

            db.insertOrThrow(TABLE_ENTIRE, null, values)

            db.setTransactionSuccessful()
        } catch (e: Exception) {
            Log.d("BookApp", "Error while trying to add post to database")
        } finally {
            db.endTransaction()
        }
    }
    fun addLoan(item: Loan) {
        val db = writableDatabase

        db.beginTransaction()
        try {
            val values = ContentValues()
            values.put(KEY_LOAN_SUBJECT, item.subject)
            values.put(KEY_LOAN_PRICE, item.price)
            values.put(KEY_LOAN_HAS_NOTIFY, item.hasNotify)
            values.put(KEY_LOAN_DATE, item.date)
            values.put(KEY_LOAN_DESC, item.desc)

            db.insertOrThrow(TABLE_LOAN, null, values)

            db.setTransactionSuccessful()
        } catch (e: Exception) {
            Log.d("BookApp", "Error while trying to add post to database")
        } finally {
            db.endTransaction()
        }
    }
    fun addPayment(item: Payment) {
        val db = writableDatabase

        db.beginTransaction()
        try {
            val values = ContentValues()
            values.put(KEY_PAYMENT_SUBJECT, item.subject)
            values.put(KEY_PAYMENT_PRICE, item.price)
            values.put(KEY_PAYMENT_HAS_NOTIFY, item.hasNotify)
            values.put(KEY_PAYMENT_DATE, item.date)
            values.put(KEY_PAYMENT_DESC, item.desc)
            values.put(KEY_PAYMENT_COUNT, item.count)
            values.put(KEY_PAYMENT_EACH_COST, item.eachCost)
            values.put(KEY_PAYMENT_LOAN, item.paymentLoan)

            db.insertOrThrow(TABLE_PAYMENT, null, values)

            db.setTransactionSuccessful()
        } catch (e: Exception) {
            Log.d("BookApp", "Error while trying to add post to database")
        } finally {
            db.endTransaction()
        }
    }


    public fun getAllCheks(): List<Chek>{
        val list = ArrayList<Chek>()

        val itemQuery = String.format(
            "SELECT * FROM %s ",
            TABLE_CHEK
        )

        val db = readableDatabase
        val cursor = db.rawQuery(itemQuery, null)

        try {
            if (cursor.moveToFirst()) {
                do {
                    val newPost = Chek()
                    newPost.id= cursor.getInt(cursor.getColumnIndex(KEY_CHEK_ID))
                    newPost.subject= cursor.getString(cursor.getColumnIndex(KEY_CHEK_SUBJECT))
                    newPost.hasNotify= cursor.getString(cursor.getColumnIndex(KEY_CHEK_HAS_NOTIFY))
                    newPost.price= cursor.getString(cursor.getColumnIndex(KEY_CHEK_PRICE))
                    newPost.desc= cursor.getString(cursor.getColumnIndex(KEY_CHEK_DESC))
                    newPost.serial= cursor.getString(cursor.getColumnIndex(KEY_CHEK_SERIAL))
                    newPost.status= cursor.getString(cursor.getColumnIndex(KEY_CHEK_STATUS))
                    newPost.createdDate= cursor.getString(cursor.getColumnIndex(KEY_CHEK_CREATED_DATE))
                    newPost.endDate= cursor.getString(cursor.getColumnIndex(KEY_CHEK_END_DATE))

                    list.add(newPost)
                } while (cursor.moveToNext())
            }
        } catch (e: Exception) {
            Log.d("BookApp", "Error while trying to get posts from database")
        } finally {
            if (cursor != null && !cursor.isClosed) {
                cursor.close()
            }
        }


        return list
    }
    public fun getAllLoans(): List<Loan>{
        val list = ArrayList<Loan>()

        val itemQuery = String.format(
            "SELECT * FROM %s ",
            TABLE_LOAN
        )

        val db = readableDatabase
        val cursor = db.rawQuery(itemQuery, null)

        try {
            if (cursor.moveToFirst()) {
                do {
                    val newPost = Loan()
                    newPost.id= cursor.getInt(cursor.getColumnIndex(KEY_LOAN_ID))
                    newPost.subject= cursor.getString(cursor.getColumnIndex(KEY_LOAN_SUBJECT))
                    newPost.hasNotify= cursor.getString(cursor.getColumnIndex(KEY_LOAN_HAS_NOTIFY))
                    newPost.price= cursor.getString(cursor.getColumnIndex(KEY_LOAN_PRICE))
                    newPost.date= cursor.getString(cursor.getColumnIndex(KEY_LOAN_DATE))
                    newPost.desc= cursor.getString(cursor.getColumnIndex(KEY_LOAN_DESC))

                    list.add(newPost)
                } while (cursor.moveToNext())
            }
        } catch (e: Exception) {
            Log.d("BookApp", "Error while trying to get posts from database")
        } finally {
            if (cursor != null && !cursor.isClosed) {
                cursor.close()
            }
        }


        return list
    }
    public fun getAllIncome(): List<Income>{
        val list = ArrayList<Income>()

        val itemQuery = String.format(
            "SELECT * FROM %s ",
            TABLE_INCOME
        )

        val db = readableDatabase
        val cursor = db.rawQuery(itemQuery, null)

        try {
            if (cursor.moveToFirst()) {
                do {
                    val newPost = Income()
                    newPost.id= cursor.getInt(cursor.getColumnIndex(KEY_INCOME_ID))
                    newPost.subject= cursor.getString(cursor.getColumnIndex(KEY_INCOME_SUBJECT))
                    newPost.hasNotify= cursor.getString(cursor.getColumnIndex(KEY_INCOME_HAS_NOTIFY))
                    newPost.price= cursor.getString(cursor.getColumnIndex(KEY_INCOME_PRICE))
                    newPost.date= cursor.getString(cursor.getColumnIndex(KEY_INCOME_DATE))
                    newPost.desc= cursor.getString(cursor.getColumnIndex(KEY_INCOME_DESC))

                    list.add(newPost)
                } while (cursor.moveToNext())
            }
        } catch (e: Exception) {
            Log.d("BookApp", "Error while trying to get posts from database")
        } finally {
            if (cursor != null && !cursor.isClosed) {
                cursor.close()
            }
        }


        return list
    }
    public fun getAllEntire(): List<Entire>{
        val list = ArrayList<Entire>()

        val itemQuery = String.format(
            "SELECT * FROM %s ",
            TABLE_ENTIRE
        )

        val db = readableDatabase
        val cursor = db.rawQuery(itemQuery, null)

        try {
            if (cursor.moveToFirst()) {
                do {
                    val newPost = Entire()
                    newPost.id= cursor.getInt(cursor.getColumnIndex(KEY_ENTIRE_ID))
                    newPost.subject= cursor.getString(cursor.getColumnIndex(KEY_ENTIRE_SUBJECT))
                    newPost.hasNotify= cursor.getString(cursor.getColumnIndex(KEY_ENTIRE_HAS_NOTIFY))
                    newPost.price= cursor.getString(cursor.getColumnIndex(KEY_ENTIRE_PRICE))
                    newPost.date= cursor.getString(cursor.getColumnIndex(KEY_ENTIRE_DATE))
                    newPost.desc= cursor.getString(cursor.getColumnIndex(KEY_ENTIRE_DESC))

                    list.add(newPost)
                } while (cursor.moveToNext())
            }
        } catch (e: Exception) {
            Log.d("BookApp", "Error while trying to get posts from database")
        } finally {
            if (cursor != null && !cursor.isClosed) {
                cursor.close()
            }
        }
        return list
    }
    public fun getAllPayment(): List<Payment>{
        val list = ArrayList<Payment>()

        val itemQuery = String.format(
            "SELECT * FROM %s ",
            TABLE_PAYMENT
        )

        val db = readableDatabase
        val cursor = db.rawQuery(itemQuery, null)

        try {
            if (cursor.moveToFirst()) {
                do {
                    val newPost = Payment()
                    newPost.id= cursor.getInt(cursor.getColumnIndex(KEY_PAYMENT_ID))
                    newPost.subject= cursor.getString(cursor.getColumnIndex(KEY_PAYMENT_SUBJECT))
                    newPost.hasNotify= cursor.getString(cursor.getColumnIndex(KEY_PAYMENT_HAS_NOTIFY))
                    newPost.price= cursor.getString(cursor.getColumnIndex(KEY_PAYMENT_PRICE))
                    newPost.date= cursor.getString(cursor.getColumnIndex(KEY_PAYMENT_DATE))
                    newPost.desc= cursor.getString(cursor.getColumnIndex(KEY_PAYMENT_DESC))
                    newPost.count= cursor.getString(cursor.getColumnIndex(KEY_PAYMENT_COUNT))
                    newPost.eachCost= cursor.getString(cursor.getColumnIndex(KEY_PAYMENT_EACH_COST))
                    newPost.paymentLoan= cursor.getString(cursor.getColumnIndex(KEY_PAYMENT_LOAN))

                    list.add(newPost)
                } while (cursor.moveToNext())
            }
        } catch (e: Exception) {
            Log.d("BookApp", "Error while trying to get posts from database")
        } finally {
            if (cursor != null && !cursor.isClosed) {
                cursor.close()
            }
        }
        return list
    }


    public fun deleteChek(item: Chek): Int{
        val db = this.writableDatabase
        return db.delete(
            TABLE_CHEK,  KEY_CHEK_ID + " = ?", arrayOf(item.id.toString())
        )
    }
    public fun deleteIncome(item: Income): Int{
        val db = this.writableDatabase
        return db.delete(
            TABLE_INCOME,  KEY_INCOME_ID + " = ?", arrayOf(item.id.toString())
        )
    }
    public fun deleteEntire(item: Entire): Int{
        val db = this.writableDatabase
        return db.delete(
            TABLE_ENTIRE,  KEY_ENTIRE_ID + " = ?", arrayOf(item.id.toString())
        )
    }
    public fun deleteLoan(item: Loan): Int{
        val db = this.writableDatabase
        return db.delete(
            TABLE_LOAN,  KEY_LOAN_ID + " = ?", arrayOf(item.id.toString())
        )
    }
    public fun deletePayment(item: Payment): Int{
        val db = this.writableDatabase
        return db.delete(
            TABLE_PAYMENT,  KEY_PAYMENT_ID + " = ?", arrayOf(item.id.toString())
        )
    }

}