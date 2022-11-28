package com.example.shopappfront.ui.edit;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.shopappfront.ui.activities.ShopAppActivityWithModel;
import com.example.shopappfront.data.models.ApplicationModelWithId;
import com.example.shopappfront.data.requests.response.RestSingleResponse;
import com.example.shopappfront.data.requests.response.TextHttpResponseHandlerWithDefault;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public abstract class EditModelActivity<T extends ApplicationModelWithId>
        extends ShopAppActivityWithModel<T> {

    public Button cancelButton;
    public Button confirmButton;
    public Button deleteButton;
    public EditModelViewModel<T> viewModel;
    public boolean isCreateActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = getModel();
        viewModel = getViewModel();
        bindModel();
        bindButtons();
        isCreateActivity = !getIntent().getBooleanExtra("cancelVisible",true);
        cancelButton.setVisibility(!isCreateActivity ? View.VISIBLE : View.GONE);
        viewModel.getFormState().observe(this, editModelFormState -> {
            setFormErrors(editModelFormState);
            confirmButton.setEnabled(viewModel.getFormState().getValue().isDataValid());
        });
        cancelButton.setOnClickListener(this::onCancelClick);
        confirmButton.setOnClickListener(this::onConfirmClick);
        deleteButton.setOnClickListener(this::onDeleteClick);

    }


    protected void onCancelClick(View v) {
        finish();
    }

    protected void onDeleteClick(View v) {
        repository.delete(model, new TextHttpResponseHandlerWithDefault<T>() {
            @Override
            public void defaultResponse(T obj) {
                repository.removeModel(model);
                finish();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                showError(EditModelActivity.this, responseString);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                showSuccess(EditModelActivity.this, "Deleted!");
                repository.removeModel(model);
                finish();
            }
        }, this);
    }

    protected void onConfirmClick(View v) {
        model = getUpdatedModel();
        if (isCreateActivity){
            repository.removeModel(model);
            repository.create(model, new TextHttpResponseHandler() {
                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    Toast.makeText(EditModelActivity.this, "Oops!" , Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {
                    Toast.makeText(EditModelActivity.this, "Created!" , Toast.LENGTH_SHORT).show();
                    repository.addNewModel(new RestSingleResponse<T>(getTypeToken(), responseString).getData());
                    finish();
                }
            }, this);
        } else {
            repository.update(model, new TextHttpResponseHandler() {
                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    Toast.makeText(EditModelActivity.this, "Oops!" , Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {
                    Toast.makeText(EditModelActivity.this, "updated!" , Toast.LENGTH_SHORT).show();
                    repository.updateModel(new RestSingleResponse<T>(getTypeToken(), responseString).getData());
                    finish();
                }
            }, this);
        }
    }

    @Override
    protected T getModel(){
        int modelId = getIntent().getIntExtra("modelId", -1);
        return repository.getById(modelId);
    }

    protected abstract TypeToken<T> getTypeToken();

//    protected abstract void setBinding();

//    protected abstract RepositoryWithId<T> getRepository();

    protected abstract EditModelViewModel<T> getViewModel();

//    protected abstract void bindModel();

    protected abstract void bindButtons();

    protected abstract T getUpdatedModel();

    protected abstract void setFormErrors(EditModelFormState<T> tEditModelFormState);




}
