package org.mirza.tinyurl.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BaseResponse<T> {
    private int code;
    private String message;
    private T data;
}
