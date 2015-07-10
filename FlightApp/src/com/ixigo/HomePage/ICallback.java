package com.ixigo.HomePage;

public interface ICallback<S, F> {
    public void success(S success);

    public void failure(F failure);
}
