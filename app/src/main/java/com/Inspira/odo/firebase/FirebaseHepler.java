package com.Inspira.odo.firebase;


import android.content.Context;

 import com.Inspira.odo.data.Model.CompanyOnMap;
import com.Inspira.odo.data.Model.DataCar;
import com.Inspira.odo.data.Model.FavouriteDataAdd;
import com.Inspira.odo.database.SharedPreferencesManager;
import com.Inspira.odo.model.TyresABatteries.OrderList;
import com.Inspira.odo.model.TyresABatteries.Responds;
import com.Inspira.odo.model.accessories.AccessoriesRequest;
import com.Inspira.odo.model.CarDetails;
import com.Inspira.odo.model.accessories.RespondsAC;
import com.Inspira.odo.model.spareParts.PartsDescription;
import com.Inspira.odo.model.spareParts.RespondsSpar;
import com.Inspira.odo.model.spareParts.SpareParts;
import com.Inspira.odo.model.TyresABatteries.TyresABatteries;
import com.Inspira.odo.model.Users.UserBuyer;
import com.Inspira.odo.model.Users.UserSeler;
import com.Inspira.odo.model.accessories.accessoriesDescription;
 import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Map;

public class FirebaseHepler {

    private DatabaseReference mDatabase;
    private Context context ;

    public FirebaseHepler(Context context) {
        mDatabase= FirebaseDatabase.getInstance().getReference();
        this.context = context;
    }
    public  void  addNewByerUser (String phoneNumber, String fullName, String email, String password ,
                                String id ){
        UserBuyer buyerRegistration = new UserBuyer("","","",email,"","",fullName,id,"",password,"buyer");
         mDatabase.child("Users").child(phoneNumber).setValue(buyerRegistration);

    }
    public void addNewSeller (String phoneNumber, String fullName, String email, String password,
                            String companyName, String companyAddress,
                              String companyArea, CompanyOnMap companyOnMap, String companyType,
                               Map<String, Map<String,Map<String,String > >>  workingOn,String ID){
        SharedPreferencesManager sharedPreferencesManager = new SharedPreferencesManager(context);
        sharedPreferencesManager.setPartType(companyType);
        UserSeler userSeler = new UserSeler(companyArea,companyAddress,companyName,companyOnMap,companyType
        ,email,fullName,ID,password,"seller",false,workingOn);
        mDatabase.child("Users").child(phoneNumber).setValue(userSeler);



    }

    public  void addArequestSpareParts(String phone , String carType , String carModel , String   carYear ,
                                       ArrayList<PartsDescription>  orderList , String date ,String engCapasty ,String color ,String part){

        CarDetails  carDetails = new CarDetails(carModel,carType,carYear,engCapasty,color);
        SpareParts  spareParts = new SpareParts(phone ,carDetails,1123421113,"spareParts",orderList);
        mDatabase.child("Users").child(phone).child("Requests").child("spareParts").push().setValue(spareParts);
        mDatabase.child("Requests").child("spareParts").push().setValue(spareParts);
      }
    public  void addArequestBatteries(String phoneNumber, String carType, String carModel, String carYear,
                                      String date,  String engCapasty ,String color , String amper, String size,
                                      String poles, boolean reversed) {
        CarDetails carDetails = new CarDetails(carModel, carType, carYear, engCapasty, color);
        OrderList orderList = new OrderList(size, reversed, poles);
        TyresABatteries tyresABatteries = new TyresABatteries(phoneNumber, carDetails, 1123421113, orderList, "battery");
        mDatabase.child("Users").child(phoneNumber).child("Requests").child("TyresABatteries").push().setValue(tyresABatteries);
        mDatabase.child("Requests").child("TyresABatteries").push().setValue(tyresABatteries);


    }
     public  void addArequestTyres(String phoneNumber, String carType, String carModel,
                                   String carYear, String date  ,
                                 String engCapasty ,String color ,  String size, String tyerNumber ,Boolean runFlatTyres) {

         CarDetails  carDetails = new CarDetails(carModel,carType,carYear,engCapasty,color);
         OrderList orderList = new OrderList(runFlatTyres,size,tyerNumber);
         TyresABatteries  tyresABatteries = new TyresABatteries(phoneNumber,carDetails,1123421113,orderList,"tyres");
         mDatabase.child("Users").child(phoneNumber).child("Requests").child("TyresABatteries").push().setValue(tyresABatteries);
         mDatabase.child("Requests").child("TyresABatteries").push().setValue(tyresABatteries);

     }
    public  void addArequestAccessories(String phone , String carType , String carModel , String   carYear ,
                                        ArrayList<accessoriesDescription>  orderList , ArrayList<String> orderImages , String date , String engCapasty , String color , String part){

         CarDetails  carDetails = new CarDetails(carModel,carType,carYear,engCapasty,color);
        AccessoriesRequest accessoriesRequest = new AccessoriesRequest(phone,1123421113,"accessories",carDetails,orderList );
        mDatabase.child("Users").child(phone).child("Requests").child("accessories").push().setValue(accessoriesRequest);
        mDatabase.child("Requests").child("accessories").push().setValue(accessoriesRequest);

    }

    public  void addToFaverits(String phone,String RequestsType,String Kye ,String carType){
        FavouriteDataAdd favouriteDataAdd = new FavouriteDataAdd(Kye,RequestsType,carType);
        if(RequestsType.equals("Accessories")){

            mDatabase.child("Users").child(phone).child("Requests").child(RequestsType).child(Kye).child("makOrderAccesoriesArrayList").child("0").child("favorite").setValue("true");


        }else {
            mDatabase.child("Users").child(phone).child("Requests").child(RequestsType).child(Kye).child("favorite").setValue("true");

        }
         mDatabase.child("Users").child(phone).child("Favorites").push().setValue(favouriteDataAdd);
    }
      public  void  putdataCar(){
          DataCar dataCar =  new DataCar();
          dataCar.getAllData();
          mDatabase.child("DataCarType").setValue(dataCar.getAllData());
      }

    public  void  sendAceeerois (String  buyerPhoneNumber , String TypeRequest ,String id_Post ,String selerPhone,String index,RespondsAC respondToOrder){
        mDatabase.child("Users").child(buyerPhoneNumber).child("Requests").child(TypeRequest).child(id_Post).child("Responds").child(selerPhone).child(index).setValue(respondToOrder);
        mDatabase.child("Users").child(selerPhone).child("Responds").child(id_Post).setValue(true);
    }
    public  void ResponSparParts(String  buyerPhoneNumber , String TypeRequest ,String id_Post ,String selerPhone,String index,RespondsSpar respondToOrder){
        mDatabase.child("Users").child(buyerPhoneNumber).child("Requests").child(TypeRequest).child(id_Post).child("Responds").child(selerPhone).child(index).setValue(respondToOrder);
        mDatabase.child("Users").child(selerPhone).child("Responds").child(id_Post).setValue(true);
    }
    public  void ResponToOrderTyerBattres(String  buyerPhoneNumber , String TypeRequest ,String id_Post ,String selerPhone,Responds respondToOrder){
        mDatabase.child("Users").child(buyerPhoneNumber).child("Requests").child(TypeRequest).child(id_Post).child("Responds").child(selerPhone).setValue(respondToOrder);
        mDatabase.child("Users").child(selerPhone).child("Responds").child(id_Post).setValue(true);
    }
     public  void setVisited (String  buyerPhoneNumber ,String keyoFpost ,boolean check ){
         mDatabase.child("Users").child(buyerPhoneNumber).child("Visited").child(keyoFpost).setValue(check);

     }
     public void setFaverits(String  buyerPhoneNumber ,String keyoFpost ,boolean check ){
         mDatabase.child("Users").child(buyerPhoneNumber).child("favourites").child(keyoFpost).setValue(check);


     }


}
