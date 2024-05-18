package br.com.boilerplate.java21.presentation.dto.record;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;

@Builder
public record MyIpRecord(@SerializedName("origin") String myIp) {
}
