package com.example.demo.users;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StateType {

    Y("y"), N("n");

    public final String text;

    public boolean isState(){
        return this == Y;
    }
}
