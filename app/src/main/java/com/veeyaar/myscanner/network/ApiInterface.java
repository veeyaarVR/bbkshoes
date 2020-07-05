package com.veeyaar.myscanner.network;

import com.veeyaar.myscanner.model.FirstReturnResponse;
import com.veeyaar.myscanner.model.HeaderData;
import com.veeyaar.myscanner.model.ItemModel;
import com.veeyaar.myscanner.model.SecondReturn;
import com.veeyaar.myscanner.model.SessionLogin;
import com.veeyaar.myscanner.model.SessionResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {

    @POST("Login")
    Call<SessionResponse> sessionLogin(@Body SessionLogin requestBody);

    @GET("sml.svc/AIS_PACKLIST_LOADDCDETAILParameters(DocEntry='{code}')/AIS_PACKLIST_LOADDCDETAIL")
    Call<ItemModel> getItems(@Header("Cookie") String sessionIdAndToken, @Path("code") String code);

    @GET("sml.svc/AIS_PACKLIST_LOADDCHEADERParameters(DocEntry='{code}')/AIS_PACKLIST_LOADDCHEADER")
    Call<HeaderData> getHeaderContent(@Header("Cookie") String sessionIdAndToken, @Path("code") String code);

    @POST("InventoryGenExits")
    Call<SessionResponse> firstApi(@Body FirstReturnResponse requestBody);

    @POST("InventoryGenExits")
    Call<SessionResponse> secondApi(@Body SecondReturn requestBody);
}
                                                                                                