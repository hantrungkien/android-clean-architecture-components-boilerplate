package com.kienht.presentation.data;

/**
 * Note:
 * Created by kienht on 5/3/18.
 */
public class Resource<T> {

    private ResourceState status;
    private T data;
    private String message;

    public Resource() {
    }

    public Resource(ResourceState status, T data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public Resource<T> success(T data) {
        return new Resource<>(ResourceState.SUCCESS, data, null);
    }

    public Resource<T> error(String message) {
        return new Resource<>(ResourceState.ERROR, null, message);
    }

    public Resource<T> loading() {
        return new Resource<>(ResourceState.LOADING, null, null);
    }

    public ResourceState getStatus() {
        return status;
    }

    public void setStatus(ResourceState status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
