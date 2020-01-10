package com.example.retrofitpractic;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi {

    @GET("posts")
    Call<List<PostModel>> getPost();

    @GET("/todos")
    Call<List<Todo>> getTodo( @Query("_limit") int limit,
                              @Query("_sort") String sort,
                              @Query("_order") String order
                            );

    @GET("/todos/{id}")
    Call<Todo> getTodoWithPath(@Path("id") int id);

    @GET("/todos")
    Call<List<Todo>> getTodoUsingQuery(@Query("userId") int id,
                                       @Query("completed") boolean iscompleted,
                                       @Query("_sort") String sort,
                                       @Query("_order") String order,
                                       @Query("_limit") int limit
                                      );

    @POST("/todos")
    Call<Todo> postTodo(@Body Todo todo);

    @GET("/todos")
    Call<List<Todo>> getMoreTodo(@Query("end") int end,
                                 @Query("_order") String order,
                                 @Query("_sort") String sort,
                                 @Query("_limit") int limit
                                );

}
