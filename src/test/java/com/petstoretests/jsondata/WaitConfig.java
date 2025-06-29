package com.petstoretests.jsondata;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WaitConfig {

    private Integer waitTimeSeconds;
    private Integer pollTimeMillis;
}
