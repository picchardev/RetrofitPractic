package com.example.retrofitpractic;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;

    LinearLayoutManager manager;

    MyAdapter myAdapter;

    List<PostModel>postModelList;

    ApiClient mApiClient;

    List<Todo>todoList;

    MyTodoAdapter myTodoAdapter;

    ConstraintLayout mConstraintLayout;

    JsonPlaceHolderApi jsonPlaceHolderApi;

    Button BtShowAll,BtShowRouteTodo,BtShowUsingQuery,BtPostTodo;

    ImageButton BtBack;

    int lastId = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        postModelList = new ArrayList<>();

        todoList = new ArrayList<>();

        initView();

        manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        todoList = new ArrayList<>();

        Retrofit mRetrofit = ApiClient.getClient();

        jsonPlaceHolderApi = mRetrofit.create(JsonPlaceHolderApi.class);

        /*

        Call<List<PostModel>> call = jsonPlaceHolderApi.getPost();

        call.enqueue(new Callback<List<PostModel>>() {
            @Override
            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {

                if(!response.isSuccessful()){
                    Log.d("ResponseError",Integer.toString(response.code()));
                    return;
                }else {

                    postModelList = response.body();

                    Log.d("Data",postModelList.toString());

                    myAdapter = new MyAdapter(MainActivity.this,postModelList);
                    mRecyclerView.setLayoutManager(manager);

                    mRecyclerView.setAdapter(myAdapter);

                }
            }

            @Override
            public void onFailure(Call<List<PostModel>> call, Throwable t) {

                Log.d("RetrofitError",t.getMessage());

            }
        });
        */


        
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.Rv);
        mConstraintLayout = findViewById(R.id.ClMain);
        BtShowAll = findViewById(R.id.BtShowAllTodo);
        BtShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                BtBack.setVisibility(View.VISIBLE);

                mRecyclerView.setVisibility(View.VISIBLE);

                mConstraintLayout.setVisibility(View.GONE);

                Call<List<Todo>> call = jsonPlaceHolderApi.getTodo(5,"id","desc");

                call.enqueue(new Callback<List<Todo>>() {
                    @Override
                    public void onResponse(Call<List<Todo>> call, Response<List<Todo>> response) {

                        if(response.isSuccessful()){
                            todoList = response.body();

                            lastId = todoList.get(4).getId();

                            Log.d("ListId",Integer.toString(todoList.get(4).getId()));
                            myTodoAdapter = new MyTodoAdapter(MainActivity.this,todoList);

                            mRecyclerView.setLayoutManager(manager);

                            mRecyclerView.setAdapter(myTodoAdapter);

                        }
                    }

                    @Override
                    public void onFailure(Call<List<Todo>> call, Throwable t) {

                        Log.d("RetrofitError",t.getMessage());
                    }
                });
            }
        });

        BtShowRouteTodo = findViewById(R.id.BtShowUsingRouteParam);

        BtShowRouteTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);

                View alertView = View.inflate(MainActivity.this,R.layout.add_id_view_layout,null);

                alertDialog.setView(alertView);
                final AlertDialog myAlertdialog=alertDialog.create();
                //myAlertdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                final EditText EdId = alertView.findViewById(R.id.EdId);
                Button BtOk = alertView.findViewById(R.id.BtOk);
                BtOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        myAlertdialog.cancel();

                        todoList.clear();

                        BtBack.setVisibility(View.VISIBLE);

                        mRecyclerView.setVisibility(View.VISIBLE);

                        mConstraintLayout.setVisibility(View.GONE);


                        Call<Todo> todoCall = jsonPlaceHolderApi.getTodoWithPath(Integer.parseInt(EdId.getText().toString()));

                        todoCall.enqueue(new Callback<Todo>() {
                            @Override
                            public void onResponse(Call<Todo> call, Response<Todo> response) {

                                if(response.isSuccessful()) {
                                    Todo todo = response.body();

                                    todoList.add(todo);

                                    myTodoAdapter = new MyTodoAdapter(MainActivity.this, todoList);

                                    mRecyclerView.setLayoutManager(manager);

                                    mRecyclerView.setAdapter(myTodoAdapter);

                                }
                            }

                            @Override
                            public void onFailure(Call<Todo> call, Throwable t) {

                            }
                        });
                    }
                });

                myAlertdialog.show();


            }
        });
        BtShowUsingQuery = findViewById(R.id.BtShowAllUsingQuery);
        BtShowUsingQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);

                View alertView = View.inflate(MainActivity.this,R.layout.add_id_view_layout,null);

                alertDialog.setView(alertView);
                final AlertDialog myAlertdialog=alertDialog.create();
                myAlertdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                final EditText EdId = alertView.findViewById(R.id.EdId);
                Button BtOk = alertView.findViewById(R.id.BtOk);
                BtOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        myAlertdialog.cancel();

                        todoList.clear();

                        BtBack.setVisibility(View.VISIBLE);

                        mRecyclerView.setVisibility(View.VISIBLE);

                        mConstraintLayout.setVisibility(View.GONE);


                        Call<List<Todo>> todoCall = jsonPlaceHolderApi.getTodoUsingQuery(Integer.parseInt(EdId.getText().toString()),true,"id","desc",3);

                        todoCall.enqueue(new Callback<List<Todo>>() {
                            @Override
                            public void onResponse(Call<List<Todo>> call, Response<List<Todo>> response) {

                                if(response.isSuccessful()) {

                                    todoList = response.body();

                                    myTodoAdapter = new MyTodoAdapter(MainActivity.this, todoList);

                                    mRecyclerView.setLayoutManager(manager);

                                    mRecyclerView.setAdapter(myTodoAdapter);

                                }

                            }

                            @Override
                            public void onFailure(Call<List<Todo>> call, Throwable t) {

                            }
                        });
                    }
                });

                myAlertdialog.show();

            }
        });

        BtPostTodo = findViewById(R.id.BtPostTodo);
        BtPostTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Todo todo = new Todo(1,"this is the title",false);

                Call<Todo>todoPostCall = jsonPlaceHolderApi.postTodo(todo);
                todoPostCall.enqueue(new Callback<Todo>() {
                    @Override
                    public void onResponse(Call<Todo> call, Response<Todo> response) {

                        Log.d("PostReply",response.body().toString());
                    }

                    @Override
                    public void onFailure(Call<Todo> call, Throwable t) {

                    }
                });
            }
        });
        BtBack = findViewById(R.id.BtBack);
        BtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mRecyclerView.setVisibility(View.GONE);
                BtBack.setVisibility(View.GONE);
                mConstraintLayout.setVisibility(View.VISIBLE);

            }
        });

    }

    public void loadMoreData(){

        Call<List<Todo>> call = jsonPlaceHolderApi.getMoreTodo(lastId,"desc","id",5);

        call.enqueue(new Callback<List<Todo>>() {
            @Override
            public void onResponse(Call<List<Todo>> call, Response<List<Todo>> response) {
                Log.d("EndAt",response.body().toString());
            }

            @Override
            public void onFailure(Call<List<Todo>> call, Throwable t) {

            }
        });
    }
}
