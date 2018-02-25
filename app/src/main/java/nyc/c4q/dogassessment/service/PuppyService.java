package nyc.c4q.dogassessment.service;

import nyc.c4q.dogassessment.model.RandoPuppies;
import nyc.c4q.dogassessment.model.RandoPuppy;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by joannesong on 2/25/18.
 */

public interface PuppyService {
    @GET("api/breed/terrier/images/random")
    Call<RandoPuppy> getTerrier();
    @GET("api/breed/spaniel/images/random")
    Call<RandoPuppy> getSpaniel();
    @GET("api/breed/retriever/images/random")
    Call<RandoPuppy> getRetriever();
    @GET("api/breed/poodle/images/random")
    Call<RandoPuppy> getPoodle();

    @GET("api/breed/terrier/images")
    Call<RandoPuppies> getPuppy();
    @GET("api/breed/spaniel/images")
    Call<RandoPuppies> getSpaniels();
    @GET("api/breed/retriever/images")
    Call<RandoPuppies> getRetreivers();
    @GET("api/breed/poodle/images")
    Call<RandoPuppies> getPoodles();
}
