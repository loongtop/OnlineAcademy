package com.gkhy.servicebase.error;

import java.io.Serializable;

/***
 * Error code and error message definition interface
 * @author leo
 */

public interface IAcademyError extends Serializable {
    int getCode();
    String getMessage();
}
