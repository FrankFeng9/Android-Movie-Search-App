package ca.mohawkcollege.feng.network;

/**
 * data response call back interface
 * @param <T>
 */
public interface OnDataResponseCallback<T> {

    void onReceiveData(T data);

}
